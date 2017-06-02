package com.selesse.jxlintimpl;

import com.selesse.jxlint.Jxlint;
import com.selesse.jxlint.model.rules.Categories;
import com.selesse.jxlintimpl.rules.JxlintImplRules;
import com.selesse.jxlintimpl.settings.JxlintImplProgramSettings;

public class Main {
    public static void main(String[] args) {
        Categories.setCategories(CustomCategories.class);

        Jxlint jxlint = new Jxlint(new JxlintImplRules(), new JxlintImplProgramSettings()
            /* <customized> do not call System#exit(..) after reporting in order to continue with the maven build */, false /* </customized> */);
        jxlint.parseArgumentsAndDispatch(args);
    }
}