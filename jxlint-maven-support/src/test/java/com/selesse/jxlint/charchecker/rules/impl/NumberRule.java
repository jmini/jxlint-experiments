package com.selesse.jxlint.charchecker.rules.impl;

import com.selesse.jxlint.charchecker.CharCheckerCustomCategories;
import com.selesse.jxlint.model.rules.Severity;

public class NumberRule extends AbstractTxtLintRule {
  private static final String name = NumberRule.class.getSimpleName();
  private static final String summary = "The line contains a number";

  public NumberRule() {
    super(name, summary, Severity.WARNING, CharCheckerCustomCategories.NUMBER);
    setDetailedDescription(getMarkdownDescription());
  }

  @Override
  protected char provideStartChar() {
    return '0';
  }

  @Override
  protected char provideEndChar() {
    return '9';
  }

  @Override
  protected String createErrorMessage(int lineNumber) {
    return "Line " + lineNumber + " contains a number";
  }
}
