package fr.jmini.txtlinter.maven;

import org.apache.maven.plugins.annotations.Mojo;

import com.selesse.jxlint.maven.AbstractJxlintMojo;
import com.selesse.jxlint.model.rules.LintRules;
import com.selesse.jxlint.settings.ProgramSettings;

import fr.jmini.txtlinter.rules.TxtLinterRules;
import fr.jmini.txtlinter.settings.TxtLinterProgramSettings;

@Mojo(name = "generate-report")
public class TxtLinterMojo extends AbstractJxlintMojo {

  @Override
  protected ProgramSettings provideProgramSettings() {
    return new TxtLinterProgramSettings();
  }

  @Override
  protected LintRules provideLintRules() {
    return new TxtLinterRules();
  }
}
