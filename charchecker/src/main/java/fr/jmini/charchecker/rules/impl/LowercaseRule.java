package fr.jmini.charchecker.rules.impl;

import com.selesse.jxlint.model.rules.Severity;

import fr.jmini.charchecker.CharCheckerCategory;

public class LowercaseRule extends AbstractTxtLintRule {
  private static final String name = LowercaseRule.class.getSimpleName();
  private static final String summary = "The line contains a lower case letter";

  public LowercaseRule() {
    super(name, summary, Severity.WARNING, CharCheckerCategory.LETTER);
    setDetailedDescription(getMarkdownDescription());
  }

  @Override
  protected char provideStartChar() {
    return 'a';
  }

  @Override
  protected char provideEndChar() {
    return 'z';
  }

  @Override
  protected String createErrorMessage(int lineNumber) {
    return "Line " + lineNumber + " contains a lower case letter";
  }
}
