package fr.jmini.charchecker.rules;

import com.selesse.jxlint.model.rules.AbstractLintRules;

import fr.jmini.charchecker.rules.impl.LowercaseRule;
import fr.jmini.charchecker.rules.impl.NumberRule;
import fr.jmini.charchecker.rules.impl.UppercaseRule;

public class CharCheckerLintRules extends AbstractLintRules {
  @Override
  public void initializeLintRules() {
    lintRules.add(new LowercaseRule());
    lintRules.add(new UppercaseRule());

    lintRules.add(new NumberRule());
  }
}
