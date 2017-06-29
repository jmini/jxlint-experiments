package com.selesse.jxlint.charchecker.rules;

import com.selesse.jxlint.charchecker.rules.impl.UppercaseRule;
import com.selesse.jxlint.model.rules.AbstractLintRules;

public class CharCheckerLintRules extends AbstractLintRules {
  @Override
  public void initializeLintRules() {
    lintRules.add(new UppercaseRule());
//    lintRules.add(new NoMultipleNewLinesAtTheEndRule());
  }
}
