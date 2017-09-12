package fr.jmini.txtlinter.cli;

import com.selesse.jxlint.Jxlint;

import fr.jmini.txtlinter.rules.TxtLinterRules;
import fr.jmini.txtlinter.settings.TxtLinterProgramSettings;

public class Main {
  public static void main(String[] args) {
    Jxlint jxlint = new Jxlint(new TxtLinterRules(), new TxtLinterProgramSettings(), false);
    jxlint.parseArgumentsAndDispatch(args);
  }
}
