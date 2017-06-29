package com.selesse.jxlint.charchecker.rules.impl;

import com.selesse.jxlint.charchecker.CharCheckerCustomCategories;
import com.selesse.jxlint.model.rules.Severity;

public class LowercaseRule extends AbstractTxtLintRule {
  private static final String name = LowercaseRule.class.getSimpleName();
  private static final String summary = "The line contains a lower case letter";

  public LowercaseRule() {
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
    return "Line " + lineNumber + " contains a lower case letter";
  }
}
