package com.selesse.jxlint.charchecker.rules.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.selesse.jxlint.model.rules.LintError;
import com.selesse.jxlint.model.rules.LintRule;
import com.selesse.jxlint.model.rules.Severity;
import com.selesse.jxlint.utils.FileUtils;

public abstract class AbstractTxtLintRule extends LintRule {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractTxtLintRule.class);

  public AbstractTxtLintRule(String name, String summary, Severity severity, Enum<?> category) {
    super(name, summary, "", severity, category);
  }

  @Override
  public List<File> getFilesToValidate() {
    return FileUtils.allFilesMatching(getSourceDirectory(), ".*\\.txt");
  }

  protected boolean lineContainsCharInRange(String line, char start, char end) {
    for (char c = start; c <= end; c++) {
      if (line.contains(String.valueOf(c))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<LintError> getLintErrors(File file) {
    List<LintError> errors = Lists.newArrayList();
    LOG.info("Validating {}", file.getAbsolutePath());

    try {
      List<String> lines = Files.readLines(file, Charsets.UTF_8);
      for (int i = 0; i < lines.size(); i++) {
        String line = lines.get(i);

        if (lineContainsCharInRange(line, provideStartChar(), provideEndChar())) {
          int lineNumber = i + 1;
          errors.add(
              LintError.with(this, file)
                  .andErrorMessage(createErrorMessage(lineNumber))
                  .andLineNumber(lineNumber)
                  .create());
        }
      }
    }
    catch (IOException e) {
      LOG.error("Error reading file", e);
    }
    return errors;
  }

  protected abstract char provideStartChar();

  protected abstract char provideEndChar();

  protected abstract String createErrorMessage(int lineNumber);
}
