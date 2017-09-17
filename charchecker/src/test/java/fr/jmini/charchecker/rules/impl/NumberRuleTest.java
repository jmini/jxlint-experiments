package fr.jmini.charchecker.rules.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.selesse.jxlint.model.rules.LintError;

import fr.jmini.charchecker.CharCheckerTest;

public class NumberRuleTest extends CharCheckerTest {

  @Test
  public void testOk() throws Exception {
    NumberRule rule = new NumberRule();
    List<LintError> errors = rule.createErrors(null, Arrays.asList("..", "----", "", "<<>>"));
    assertThat(errors).hasSize(0);
  }

  @Test
  public void testNok() throws Exception {
    NumberRule rule = new NumberRule();
    List<LintError> errors = rule.createErrors(new File("file.txt"), Arrays.asList(".0.", "----", "9", "", "<<5>>"));
    assertThat(errors).hasSize(3);
  }
}
