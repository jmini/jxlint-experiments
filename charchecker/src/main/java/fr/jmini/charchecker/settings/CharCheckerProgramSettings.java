package fr.jmini.charchecker.settings;

import java.io.File;

import com.selesse.jxlint.settings.ProgramSettings;

public class CharCheckerProgramSettings implements ProgramSettings {
  @Override
  public String getProgramVersion() {
    return "1.0.0";
  }

  @Override
  public String getProgramName() {
    return "charchecker";
  }

  @Override
  public void initializeForWeb(File projectRoot) {
  }
}
