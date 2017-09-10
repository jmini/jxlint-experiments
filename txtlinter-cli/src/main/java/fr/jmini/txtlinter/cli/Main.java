package fr.jmini.txtlinter.cli;

import com.selesse.jxlint.Jxlint;
import com.selesse.jxlint.model.rules.Categories;

import fr.jmini.txtlinter.CustomCategories;
import fr.jmini.txtlinter.rules.TxtLinterRules;
import fr.jmini.txtlinter.settings.TxtLinterProgramSettings;

public class Main {
  public static void main(String[] args) {
    Categories.setCategories(CustomCategories.class);

    Jxlint jxlint = new Jxlint(new TxtLinterRules(), new TxtLinterProgramSettings(), false);
    jxlint.parseArgumentsAndDispatch(args);
  }
}
