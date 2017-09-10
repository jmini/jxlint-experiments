package fr.jmini.txtlinter.rules.impl;

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

import fr.jmini.txtlinter.CustomCategories;

public class LinesAreNotLongerThan80CharsRule extends LintRule {
  private static final Logger LOG = LoggerFactory.getLogger(LinesAreNotLongerThan80CharsRule.class);

  private static final int MAX_LENGTH = 80;
  private static final String name = "Lines should not be longer than 80 characters";
  private static final String summary = "The line length should be less than or equal to 80.";

  public LinesAreNotLongerThan80CharsRule() {
    super(name, summary, "", Severity.WARNING, CustomCategories.FORMAT);
    setDetailedDescription(getMarkdownDescription());
  }

  @Override
  public List<File> getFilesToValidate() {
    return FileUtils.allFilesMatching(getSourceDirectory(), ".*\\.txt");
  }

  @Override
  public List<LintError> getLintErrors(File file) {
    List<LintError> errors = Lists.newArrayList();
    LOG.info("Validating {}", file.getAbsolutePath());

    try {
      List<String> lines = Files.readLines(file, Charsets.UTF_8);
      for (int i = 0; i < lines.size(); i++) {
        String l = lines.get(i);
        if (l.length() > MAX_LENGTH) {
          String errorMessage = "Line " + (i + 1) + " is too long (length: " + l.length() + ")";
          errors.add(
              LintError.with(this, file)
                  .andErrorMessage(errorMessage)
                  .andLineNumber(i + 1)
                  .create());
        }
      }
    }
    catch (IOException e) {
      LOG.error("Error reading file", e);
    }
    return errors;
  }
}
