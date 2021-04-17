package com.ge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController2 {

    @GetMapping("/hello")
    public String hello() {
        return "provider 2";
    }
}
