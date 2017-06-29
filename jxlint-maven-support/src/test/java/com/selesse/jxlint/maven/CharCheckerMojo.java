package com.selesse.jxlint.maven;

import com.selesse.jxlint.charchecker.CharCheckerCustomCategories;
import com.selesse.jxlint.charchecker.CharCheckerProgramSettings;
import com.selesse.jxlint.charchecker.rules.CharCheckerLintRules;
import com.selesse.jxlint.model.rules.LintRules;
import com.selesse.jxlint.settings.ProgramSettings;

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
    return CharCheckerCustomCategories.class;
  }
}
