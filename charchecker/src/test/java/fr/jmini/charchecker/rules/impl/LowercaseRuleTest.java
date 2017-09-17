package fr.jmini.charchecker.rules.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.selesse.jxlint.model.rules.LintError;

import fr.jmini.charchecker.CharCheckerTest;

public class LowercaseRuleTest extends CharCheckerTest {

  @Test
  public void testOk() throws Exception {
    LowercaseRule rule = new LowercaseRule();
    List<LintError> errors = rule.createErrors(new File("file.txt"), Arrays.asList("..", "----", "", "<<>>"));
    assertThat(errors).hasSize(0);
  }

  @Test
  public void testNok() throws Exception {
    LowercaseRule rule = new LowercaseRule();
    List<LintError> errors = rule.createErrors(new File("file.txt"), Arrays.asList(".a.", "----", "z", "", "<<x>>"));
    assertThat(errors).hasSize(3);
  }
}
