package fr.jmini.charchecker.rules.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.selesse.jxlint.model.rules.LintError;

import fr.jmini.charchecker.CharCheckerTest;

public class UppercaseRuleTest extends CharCheckerTest {

  @Test
  public void testOk() throws Exception {
    UppercaseRule rule = new UppercaseRule();
    List<LintError> errors = rule.createErrors(new File("file.txt"), Arrays.asList("..", "----", "", "<<>>"));
    assertThat(errors).hasSize(0);
  }

  @Test
  public void testNok() throws Exception {
    UppercaseRule rule = new UppercaseRule();
    List<LintError> errors = rule.createErrors(new File("file.txt"), Arrays.asList(".A.", "----", "Z", "", "<<X>>"));
    assertThat(errors).hasSize(3);
  }
}
