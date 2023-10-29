package com.freund.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    MessageSource messageSource;
     
    @GetMapping("/error")
    public String index(Locale locale) {
        return messageSource.getMessage("error.notfound", null, locale);
    }
    
    //Headers
    //Accept-Language = es_ES
    //Accept-Language = en_US
}
