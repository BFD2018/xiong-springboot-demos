package org.xjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    @GetMapping("pages/forms/general")
    public String pagesFormsGeneral() {
        return "pages/forms/general";
    }

    @GetMapping("pages/forms/editors")
    public String pagesFormsEditors() {
        return "pages/forms/editors";
    }

    @GetMapping("pages/forms/advanced")
    public String pagesFormsAdvanced() {
        return "pages/forms/advanced";
    }

    @GetMapping("pages/forms/validation")
    public String pagesFormsValidation() {
        return "pages/forms/validation";
    }

}
