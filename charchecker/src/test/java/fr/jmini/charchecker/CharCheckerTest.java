package fr.jmini.charchecker;

import com.selesse.jxlint.model.rules.LintRulesImpl;

import fr.jmini.charchecker.rules.CharCheckerLintRules;

import org.junit.Before;

public class CharCheckerTest {
    @Before
    public void setup() {
        LintRulesImpl.setInstance(new CharCheckerLintRules());
    }
}
