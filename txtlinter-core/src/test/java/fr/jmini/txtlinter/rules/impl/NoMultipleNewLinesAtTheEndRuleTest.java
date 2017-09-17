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

public class NoMultipleNewLinesAtTheEndRuleTest extends TxtLinterTest {

  private NoMultipleNewLinesAtTheEndRule noMultipleNewLinesAtTheEndRule;

  @Before
  @Override
  public void setup() {
    super.setup();

    noMultipleNewLinesAtTheEndRule = Mockito.spy(new NoMultipleNewLinesAtTheEndRule());

    File sampleTestDirectory = new File(Resources.getResource("file-end/").getPath());
    when(noMultipleNewLinesAtTheEndRule.getSourceDirectory()).thenReturn(sampleTestDirectory);
  }

  @Test
  public void testGetFilesToValidate() throws Exception {
    List<File> filesToValidate = noMultipleNewLinesAtTheEndRule.getFilesToValidate();

    assertThat(filesToValidate).hasSize(6);
    assertThat(filesToValidate.get(0).getName()).isEqualTo("empty-with-new-lines.txt");
    assertThat(filesToValidate.get(1).getName()).isEqualTo("empty.txt");
    assertThat(filesToValidate.get(2).getName()).isEqualTo("no-new-line.txt");
    assertThat(filesToValidate.get(3).getName()).isEqualTo("nok-with-withespaces.txt");
    assertThat(filesToValidate.get(4).getName()).isEqualTo("nok.txt");
    assertThat(filesToValidate.get(5).getName()).isEqualTo("single-new-line.txt");
  }

  @Test
  public void testGetLintErrors() throws Exception {
    noMultipleNewLinesAtTheEndRule.validate();

    List<LintError> lintErrors = noMultipleNewLinesAtTheEndRule.getLintErrors();

    assertThat(lintErrors).hasSize(6);

    for (LintError lintError : lintErrors) {
      switch (lintError.getFile().getName()) {
        case "empty-with-new-lines.txt": {
          switch (lintError.getLineNumber()) {
            case 1:
              assertThat(lintError.getMessage()).isEqualTo("Line 1 is one of the multiple empty new lines at the end of the document. This is not allowed");
              break;
            case 2:
              assertThat(lintError.getMessage()).isEqualTo("Line 2 is one of the multiple empty new lines at the end of the document. This is not allowed");
              break;
            case 3:
              assertThat(lintError.getMessage()).isEqualTo("Line 3 is one of the multiple empty new lines at the end of the document. This is not allowed");
              break;
            default:
              fail("Did not expect LintError " + lintError);
          }
          break;
        }
        case "nok-with-withespaces.txt": {
          switch (lintError.getLineNumber()) {
            case 3:
              assertThat(lintError.getMessage()).isEqualTo("Line 3 is one of the multiple empty new lines at the end of the document. This is not allowed");
              break;
            default:
              fail("Did not expect LintError " + lintError);
          }
          break;
        }
        case "nok.txt": {
          switch (lintError.getLineNumber()) {
            case 3:
              assertThat(lintError.getMessage()).isEqualTo("Line 3 is one of the multiple empty new lines at the end of the document. This is not allowed");
              break;
            case 4:
              assertThat(lintError.getMessage()).isEqualTo("Line 4 is one of the multiple empty new lines at the end of the document. This is not allowed");
              break;
            default:
              fail("Did not expect LintError " + lintError);
          }
          break;
        }
        default:
          fail("Did not expect LintError " + lintError);
      }
    }
  }
}
