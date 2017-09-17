package fr.jmini.charchecker;

import org.apache.maven.plugins.annotations.Mojo;

import com.selesse.jxlint.maven.AbstractJxlintMojo;
import com.selesse.jxlint.model.rules.LintRules;
import com.selesse.jxlint.settings.ProgramSettings;

import fr.jmini.charchecker.rules.CharCheckerLintRules;
import fr.jmini.charchecker.settings.CharCheckerProgramSettings;

@Mojo(name = "check")
public class CharCheckerMojo extends AbstractJxlintMojo {

  @Override
  protected ProgramSettings provideProgramSettings() {
    return new CharCheckerProgramSettings();
  }

  @Override
  protected LintRules provideLintRules() {
    return new CharCheckerLintRules();
  }

  @Override
  protected Class<? extends Enum<?>> provideCategories() {
    return CharCheckerCategory.class;
  }
}
