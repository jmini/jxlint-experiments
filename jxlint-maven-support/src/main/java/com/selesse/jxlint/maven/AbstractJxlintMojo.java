package com.selesse.jxlint.maven;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import com.selesse.jxlint.Dispatcher;
import com.selesse.jxlint.model.JxlintOption;
import com.selesse.jxlint.model.ProgramOptions;
import com.selesse.jxlint.model.rules.Categories;
import com.selesse.jxlint.model.rules.LintRules;
import com.selesse.jxlint.model.rules.LintRulesImpl;
import com.selesse.jxlint.settings.Profiler;
import com.selesse.jxlint.settings.ProgramSettings;

public abstract class AbstractJxlintMojo extends AbstractMojo {

  private static final String SOURCE_DIRECTORY = "sourceDirectory";
  private static final String WARNINGS_ARE_ERRORS = "warningAsError";
  private static final String OUTPUT_TYPE = "outputType";
  private static final String OUTPUT_FILE = "outputFile";

  @Parameter(property = SOURCE_DIRECTORY, defaultValue = "${project.basedir}")
  protected File paramSourceDirectory;

  /**
   * Treat all warnings as errors.
   */
  @Parameter(property = WARNINGS_ARE_ERRORS, defaultValue = "false")
  protected boolean paramWaringsAreError = false;

  /**
   * Type of report that should be created (xml, html)
   */
  @Parameter(property = OUTPUT_TYPE, defaultValue = "html")
  protected String paramOutputType;

  /**
   * Name of the file, where the report will be created.
   */
  @Parameter(property = OUTPUT_FILE, defaultValue = "${project.build.directory}/report.html")
  protected File paramOutputFile;

  protected abstract ProgramSettings provideProgramSettings();

  protected abstract LintRules provideLintRules();

  protected abstract Class<? extends Enum<?>> provideCategories();

  protected ProgramOptions createProgramOptions() {
    ProgramOptions options = new ProgramOptions();

    getLog().debug("set source directory option to '" + paramSourceDirectory.getAbsolutePath() + "'");
    options.setSourceDirectory(paramSourceDirectory.getAbsolutePath());

    addOption(options, JxlintOption.OUTPUT_TYPE, paramOutputType);
    addOption(options, JxlintOption.OUTPUT_TYPE_PATH, paramOutputFile.getAbsolutePath());

    String werror = paramWaringsAreError ? "true" : "false";
    addOption(options, JxlintOption.WARNINGS_ARE_ERRORS, werror);

    return options;
  }

  private void addOption(ProgramOptions options, JxlintOption option, String value) {
    getLog().debug("set option '" + option + "' to '" + value + "'");
    options.addOption(option, value);
  }

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    ProgramSettings programSettings = provideProgramSettings();
    getLog().info("running '" + programSettings.getProgramName() + "' version '" + programSettings.getProgramVersion() + "'");

    LintRules lintRules = provideLintRules();
    Class<? extends Enum<?>> categories = provideCategories();

    ProgramOptions programOptions = createProgramOptions();

    Profiler.setEnabled(false);
    Categories.setCategories(categories);

    LintRulesImpl.setInstance(lintRules);
    LintRulesImpl.setExitAfterReporting(false);

    Dispatcher dispatcher = new Dispatcher(programOptions, programSettings);
    dispatcher.dispatch();
  }
}
