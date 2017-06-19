package fr.jmini.txtlinter.rules;

import com.selesse.jxlint.model.rules.AbstractLintRules;

import fr.jmini.txtlinter.rules.impl.LinesAreNotLongerThan80CharsRule;
import fr.jmini.txtlinter.rules.impl.NoMultipleNewLinesAtTheEndRule;

public class TxtLinterRules extends AbstractLintRules {
  @Override
  public void initializeLintRules() {
    lintRules.add(new LinesAreNotLongerThan80CharsRule());
    lintRules.add(new NoMultipleNewLinesAtTheEndRule());
  }
}
