package com.selesse.jxlintimpl.maven;

import org.apache.maven.plugins.annotations.Mojo;

import com.selesse.jxlint.maven.AbstractJxlintMojo;
import com.selesse.jxlint.model.rules.LintRules;
import com.selesse.jxlint.settings.ProgramSettings;
import com.selesse.jxlintimpl.CustomCategories;
import com.selesse.jxlintimpl.rules.JxlintImplRules;
import com.selesse.jxlintimpl.settings.JxlintImplProgramSettings;

@Mojo(name = "generate-report")
public class JxlintImplMojo extends AbstractJxlintMojo {

  @Override
  protected ProgramSettings provideProgramSettings() {
    return new JxlintImplProgramSettings();
  }

  @Override
  protected LintRules provideLintRules() {
    return new JxlintImplRules();
  }

  @Override
  protected Class<? extends Enum<?>> provideCategories() {
    return CustomCategories.class;
  }

}
