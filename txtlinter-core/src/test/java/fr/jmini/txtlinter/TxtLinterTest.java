package fr.jmini.txtlinter;

import com.selesse.jxlint.model.rules.LintRulesImpl;

import fr.jmini.txtlinter.rules.TxtLinterRules;

import org.junit.Before;

public class TxtLinterTest {
    @Before
    public void setup() {
        LintRulesImpl.setInstance(new TxtLinterRules());
    }
}
