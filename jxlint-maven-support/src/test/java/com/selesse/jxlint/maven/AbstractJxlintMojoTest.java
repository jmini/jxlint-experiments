package com.selesse.jxlint.maven;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Files;
import com.selesse.jxlint.model.JxlintOption;
import com.selesse.jxlint.model.OutputType;
import com.selesse.jxlint.model.ProgramOptions;
import com.selesse.jxlint.model.rules.LintRules;
import com.selesse.jxlint.settings.ProgramSettings;

public class AbstractJxlintMojoTest {

  private TestJxlintMojo mojoUnderTest;
  protected File tempDirectory;

  @Before
  public void setup() {
    tempDirectory = Files.createTempDir();
    tempDirectory.deleteOnExit();

    mojoUnderTest = new TestJxlintMojo();
  }

  private void initValidConfiguration() {
    mojoUnderTest.setSourceDirectory(tempDirectory);
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
    mojoUnderTest.setNoWarnings(true);
    mojoUnderTest.setAllWarnings(true);
    mojoUnderTest.setWaringsAreErrors(true);
    mojoUnderTest.setOutputType("xml");
    mojoUnderTest.setOutputFile(reportFile);

    ProgramOptions options = mojoUnderTest.callCreateProgramOptions();
    assertThat(options.getSourceDirectory()).isEqualTo(srcFolder.getAbsolutePath());
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

  private static class TestJxlintMojo extends AbstractJxlintMojo {

    @Override
    protected ProgramSettings provideProgramSettings() {
      return null;
    }

    @Override
    protected LintRules provideLintRules() {
      return null;
    }

    @Override
    protected Class<? extends Enum<?>> provideCategories() {
      return null;
    }

    public void setSourceDirectory(File sourceDirectory) {
      this.sourceDirectory = sourceDirectory;
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

    public ProgramOptions callCreateProgramOptions() {
      return createProgramOptions();
    }
  }
}
