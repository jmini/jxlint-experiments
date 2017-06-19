package fr.jmini.txtlinter.rules.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.io.Resources;
import com.selesse.jxlint.model.rules.LintError;

import fr.jmini.txtlinter.TxtLinterTest;

public class LinesAreNotLongerThan80CharsRuleTest extends TxtLinterTest {

  private LinesAreNotLongerThan80CharsRule linesAreNotLongerThan80CharsRule;

  @Before
  @Override
  public void setup() {
    super.setup();

    linesAreNotLongerThan80CharsRule = Mockito.spy(new LinesAreNotLongerThan80CharsRule());

    File sampleTestDirectory = new File(Resources.getResource("length/").getPath());
    when(linesAreNotLongerThan80CharsRule.getSourceDirectory()).thenReturn(sampleTestDirectory);
  }

  @Test
  public void testGetFilesToValidate() throws Exception {
    List<File> filesToValidate = linesAreNotLongerThan80CharsRule.getFilesToValidate();

    assertThat(filesToValidate).hasSize(2);
    assertThat(filesToValidate.get(0).getName()).isEqualTo("nok.txt");
    assertThat(filesToValidate.get(1).getName()).isEqualTo("ok.txt");
  }

  @Test
  public void testGetLintErrors() throws Exception {
    linesAreNotLongerThan80CharsRule.validate();

    List<LintError> lintErrors = linesAreNotLongerThan80CharsRule.getLintErrors();

    assertThat(lintErrors).hasSize(1);

    for (LintError lintError : lintErrors) {
      switch (lintError.getLineNumber()) {
        case 2:
          assertThat(lintError.getFile().getName()).isEqualTo("nok.txt");
          assertThat(lintError.getLineNumber()).isEqualTo(2);
          assertThat(lintError.getMessage()).isEqualTo("Line 2 is too long (length: 105)");
          break;
        default:
          fail("Did not expect LintError " + lintError);
      }
    }
  }
}
