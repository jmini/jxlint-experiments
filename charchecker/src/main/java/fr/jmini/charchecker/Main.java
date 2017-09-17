package fr.jmini.charchecker;

import com.selesse.jxlint.Jxlint;
import com.selesse.jxlint.model.rules.Categories;

import fr.jmini.charchecker.rules.CharCheckerLintRules;
import fr.jmini.charchecker.settings.CharCheckerProgramSettings;

public class Main {

  public static void main(String[] args) {
    Categories.setCategories(CharCheckerCategory.class);

    Jxlint jxlint = new Jxlint(new CharCheckerLintRules(), new CharCheckerProgramSettings(), false);
    jxlint.parseArgumentsAndDispatch(args);
  }
}
