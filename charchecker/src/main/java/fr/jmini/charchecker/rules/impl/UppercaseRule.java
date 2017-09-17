package fr.jmini.charchecker.rules.impl;

import com.selesse.jxlint.model.rules.Severity;

import fr.jmini.charchecker.CharCheckerCategory;

public class UppercaseRule extends AbstractTxtLintRule {
  private static final String name = UppercaseRule.class.getSimpleName();
  private static final String summary = "The line contains an upper case letter";

  public UppercaseRule() {
    super(name, summary, Severity.WARNING, CharCheckerCategory.LETTER);
    setDetailedDescription(getMarkdownDescription());
  }

  @Override
  protected char provideStartChar() {
    return 'A';
  }

  @Override
  protected char provideEndChar() {
    return 'Z';
  }

  @Override
  protected String createErrorMessage(int lineNumber) {
    return "Line " + lineNumber + " contains an upper case letter";
  }
}
