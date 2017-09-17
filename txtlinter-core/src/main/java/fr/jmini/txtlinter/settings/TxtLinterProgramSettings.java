package fr.jmini.txtlinter.settings;

import java.io.File;

import com.selesse.jxlint.settings.ProgramSettings;

public class TxtLinterProgramSettings implements ProgramSettings {
  @Override
  public String getProgramVersion() {
    return "1.0.0";
  }

  @Override
  public String getProgramName() {
    return "txtlinter";
  }

  @Override
  public void initializeForWeb(File projectRoot) {
  }
}
