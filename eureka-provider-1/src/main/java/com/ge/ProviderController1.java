package com.ge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController1 {

    @GetMapping("/hello")
    public String hello() {
        return "provider 1";
    }
}
