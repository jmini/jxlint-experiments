package fr.jmini.txtlinter.rules.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.selesse.jxlint.model.rules.Category;
import com.selesse.jxlint.model.rules.LintError;
import com.selesse.jxlint.model.rules.LintRule;
import com.selesse.jxlint.model.rules.Severity;
import com.selesse.jxlint.utils.FileUtils;

public class NoMultipleNewLinesAtTheEndRule extends LintRule {
  private static final Logger LOG = LoggerFactory.getLogger(NoMultipleNewLinesAtTheEndRule.class);

  private static final String name = "Multiple new lines at the end of the document";
  private static final String summary = "Text document should end with zero or one new line. Additional new lines should be removed.";

  public NoMultipleNewLinesAtTheEndRule() {
    super(name, summary, "", Severity.WARNING, Category.STYLE);
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
      for (int i = lines.size(); i > 0; i--) {
        String l = lines.get(i - 1).trim();
        if (l.length() > 0) {
          return errors;
        }
        else {
          String errorMessage = "Line " + i + " is one of the multiple empty new lines at the end of the document. This is not allowed";
          errors.add(
              LintError.with(this, file)
                  .andErrorMessage(errorMessage)
                  .andLineNumber(i)
                  .create());
        }
      }
    }
    catch (

    IOException e) {
      LOG.error("Error reading file", e);
    }
    return errors;
  }
}
