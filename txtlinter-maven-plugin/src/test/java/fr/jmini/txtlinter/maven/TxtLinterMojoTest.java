package fr.jmini.txtlinter.maven;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.io.Files;

public class TxtLinterMojoTest extends AbstractMojoTestCase {

  protected File tempDirectory;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    tempDirectory = Files.createTempDir();
    tempDirectory.deleteOnExit();
  }

  public void testMinimal() throws Exception {
    File createdFile = executeMojo("target/test-classes/poms/pom-minimal.xml");
    NodeList nodeList = loadIssuesFromXml(createdFile);
    assertEquals(2, nodeList.getLength());
  }

  private File executeMojo(String pomRelativePath) throws Exception, IllegalAccessException, MojoExecutionException, MojoFailureException {
    File testPom = new File(getBasedir(), pomRelativePath);
    TxtLinterMojo mojo = (TxtLinterMojo) lookupMojo("generate-report", testPom);
    assertNotNull(mojo);

    replacePlaceholder(mojo, TxtLinterMojo.SOURCE_DIRECTORY, testPom);
    replacePlaceholder(mojo, TxtLinterMojo.OUTPUT_FILE, testPom);

    File ouputFile = (File) getVariableValueFromObject(mojo, TxtLinterMojo.OUTPUT_FILE);
    assertFalse(ouputFile.exists());

    mojo.execute();

    assertTrue(ouputFile.exists());
    return ouputFile;
  }

  private void replacePlaceholder(TxtLinterMojo mojo, String variable, File testPom) throws IllegalAccessException {
    File value = (File) getVariableValueFromObject(mojo, variable);
    String path = value.getPath();
    path = path.replace("${project.basedir}", testPom.getParent());
    path = path.replace("${project.build.directory}", tempDirectory.getPath());
    setVariableValueToObject(mojo, variable, new File(path));
  }

  private NodeList loadIssuesFromXml(File createdFile) throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(createdFile);

    NodeList nodeList = document.getElementsByTagName("issue");
    return nodeList;
  }

}
