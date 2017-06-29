package com.selesse.jxlint.maven;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Files;
import com.selesse.jxlint.model.JxlintOption;
import com.selesse.jxlint.model.OutputType;
import com.selesse.jxlint.model.ProgramOptions;
import com.selesse.jxlint.model.rules.LintRules;
import com.selesse.jxlint.settings.ProgramSettings;
import com.selesse.jxlintimpl.CustomCategories;
import com.selesse.jxlintimpl.rules.JxlintImplRules;
import com.selesse.jxlintimpl.rules.impl.FunctionsStartingWithTestAreTests;
import com.selesse.jxlintimpl.settings.JxlintImplProgramSettings;

public class AbstractJxlintMojoTest {

  private static final String CATEGORY_NAME = CustomCategories.PROBABLY_ACCIDENT.toString();
  private static final String RULE_NAME = (new FunctionsStartingWithTestAreTests()).getName();
  private TestJxlintImplMojo mojoUnderTest;
  protected File tempDirectory;

  @Before
  public void setup() {
    tempDirectory = Files.createTempDir();
    tempDirectory.deleteOnExit();

    mojoUnderTest = new TestJxlintImplMojo();
    mojoUnderTest.callInitJxlint();
  }

  private void initValidConfiguration() {
    mojoUnderTest.setSourceDirectory(tempDirectory);
    mojoUnderTest.setDisableRules(null);
    mojoUnderTest.setEnableOnlyRules(null);
    mojoUnderTest.setEnableRules(null);
    mojoUnderTest.setEnableCategories(null);
    mojoUnderTest.setNoWarnings(false);
    mojoUnderTest.setAllWarnings(false);
    mojoUnderTest.setWaringsAreErrors(false);
    mojoUnderTest.setOutputType("html");
    mojoUnderTest.setOutputFile(new File(tempDirectory, "my-report.html"));
  }

  @Test
  public void testValidConfiguration() throws Exception {
    initValidConfiguration();

    ProgramOptions options = mojoUnderTest.callCreateProgramOptions();
    assertThat(options.getSourceDirectory()).isEqualTo(tempDirectory.getAbsolutePath());
    assertThat(options.hasOption(JxlintOption.DISABLE)).isEqualTo(false);
    assertThat(options.hasOption(JxlintOption.CHECK)).isEqualTo(false);
    assertThat(options.hasOption(JxlintOption.ENABLE)).isEqualTo(false);
    assertThat(options.hasOption(JxlintOption.NO_WARNINGS)).isEqualTo(false);
    assertThat(options.hasOption(JxlintOption.ALL_WARNINGS)).isEqualTo(false);
    assertThat(options.hasOption(JxlintOption.WARNINGS_ARE_ERRORS)).isEqualTo(false);
    assertThat(options.getOutputType()).isEqualTo(OutputType.HTML);
    assertThat(options.getOption(JxlintOption.OUTPUT_TYPE_PATH)).isEqualTo(new File(tempDirectory, "my-report.html").getAbsolutePath());
  }

  @Test
  public void testAlternativeValidConfiguration() throws Exception {
    File reportFile = new File(tempDirectory, "report.xml");
    File srcFolder = new File(tempDirectory, "src");

    mojoUnderTest.setSourceDirectory(srcFolder);
    mojoUnderTest.setDisableRules(Collections.singletonList(RULE_NAME));
    mojoUnderTest.setEnableOnlyRules(Collections.singletonList(RULE_NAME));
    mojoUnderTest.setEnableRules(Collections.singletonList(RULE_NAME));
    mojoUnderTest.setEnableCategories(Collections.singletonList(CATEGORY_NAME));
    mojoUnderTest.setNoWarnings(true);
    mojoUnderTest.setAllWarnings(true);
    mojoUnderTest.setWaringsAreErrors(true);
    mojoUnderTest.setOutputType("xml");
    mojoUnderTest.setOutputFile(reportFile);

    ProgramOptions options = mojoUnderTest.callCreateProgramOptions();
    assertThat(options.getSourceDirectory()).isEqualTo(srcFolder.getAbsolutePath());
    assertThat(options.getOption(JxlintOption.DISABLE)).isEqualTo(RULE_NAME);
    assertThat(options.getOption(JxlintOption.CHECK)).isEqualTo(RULE_NAME);
    assertThat(options.getOption(JxlintOption.ENABLE)).isEqualTo(RULE_NAME);
    assertThat(options.getOption(JxlintOption.CATEGORY)).isEqualTo(CATEGORY_NAME);
    assertThat(options.hasOption(JxlintOption.NO_WARNINGS)).isEqualTo(true);
    assertThat(options.hasOption(JxlintOption.ALL_WARNINGS)).isEqualTo(true);
    assertThat(options.hasOption(JxlintOption.WARNINGS_ARE_ERRORS)).isEqualTo(true);
    assertThat(options.getOutputType()).isEqualTo(OutputType.XML);
    assertThat(options.getOption(JxlintOption.OUTPUT_TYPE_PATH)).isEqualTo(reportFile.getAbsolutePath());
  }

  @Test
  public void testOutputTypeQuiet() throws Exception {
    initValidConfiguration();
    mojoUnderTest.setOutputType("quiet");

    ProgramOptions options = mojoUnderTest.callCreateProgramOptions();
    assertThat(options.getOutputType()).isEqualTo(OutputType.QUIET);
  }

  @Test
  public void testOutputTypeDefault() throws Exception {
    initValidConfiguration();
    mojoUnderTest.setOutputType("default");

    ProgramOptions options = mojoUnderTest.callCreateProgramOptions();
    assertThat(options.getOutputType()).isEqualTo(OutputType.DEFAULT);
  }

  @Test
  public void testInvalidOutputType() throws Exception {
    initValidConfiguration();
    mojoUnderTest.setOutputType("xxx");

    ProgramOptions options = mojoUnderTest.callCreateProgramOptions();
    assertThat(options.getOutputType()).isEqualTo(OutputType.DEFAULT);
  }

  @Test
  public void testInvalidRuleNameType() throws Exception {
    try {
      initValidConfiguration();
      mojoUnderTest.setEnableOnlyRules(Collections.singletonList("abcd"));

      mojoUnderTest.callCreateProgramOptions();
      fail("MojoExecutionException expected");
    }
    catch (MojoExecutionException e) {
      assertThat(e.getMessage()).isEqualTo("Lint rule 'abcd' does not exist.");
    }
  }

  @Test
  public void testInvalidCategoryNameType() throws Exception {
    try {
      initValidConfiguration();
      mojoUnderTest.setEnableCategories(Collections.singletonList("xyz"));

      mojoUnderTest.callCreateProgramOptions();
      fail("MojoExecutionException expected");
    }
    catch (MojoExecutionException e) {
      assertThat(e.getMessage()).isEqualTo("Category \"xyz\" does not exist. Try one of: Probably An Accident.");
    }
  }

  private static class TestJxlintImplMojo extends AbstractJxlintMojo {

    @Override
    protected ProgramSettings provideProgramSettings() {
      return new JxlintImplProgramSettings();
    }

    @Override
    protected LintRules provideLintRules() {
      return new JxlintImplRules();
    }

    @Override
    protected Class<? extends Enum<?>> provideCategories() {
      return CustomCategories.class;
    }

    public void setSourceDirectory(File sourceDirectory) {
      this.sourceDirectory = sourceDirectory;
    }

    public void setDisableRules(List<String> disableRules) {
      this.disableRules = disableRules;
    }

    public void setEnableOnlyRules(List<String> enableOnlyRules) {
      this.enableOnlyRules = enableOnlyRules;
    }

    public void setEnableRules(List<String> enableRules) {
      this.enableRules = enableRules;
    }

    public void setEnableCategories(List<String> enableCategories) {
      this.enableCategories = enableCategories;
    }

    public void setNoWarnings(boolean noWarnings) {
      this.noWarnings = noWarnings;
    }

    public void setAllWarnings(boolean allWarnings) {
      this.allWarnings = allWarnings;
    }

    public void setWaringsAreErrors(boolean waringsAreErrors) {
      this.waringsAreErrors = waringsAreErrors;
    }

    public void setOutputType(String outputType) {
      this.outputType = outputType;
    }

    public void setOutputFile(File outputFile) {
      this.outputFile = outputFile;
    }

    public void callInitJxlint() {
      initJxlint();
    }

    public ProgramOptions callCreateProgramOptions() throws MojoExecutionException {
      return createProgramOptions();
    }
  }
}
