package com.azizspring.aziz.controlleur;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityControlleur {

    @GetMapping("/errorPage")
    public String erroPage()
    {
        return "errorpage";
    }
}
