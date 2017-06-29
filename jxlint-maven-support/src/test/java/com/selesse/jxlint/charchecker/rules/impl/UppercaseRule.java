package com.selesse.jxlint.charchecker.rules.impl;

import com.selesse.jxlint.charchecker.CharCheckerCustomCategories;
import com.selesse.jxlint.model.rules.Severity;

public class UppercaseRule extends AbstractTxtLintRule {
  private static final String name = UppercaseRule.class.getSimpleName();
  private static final String summary = "The line contains an upper case letter";

  public UppercaseRule() {
    super(name, summary, Severity.WARNING, CharCheckerCustomCategories.LETTER);
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
