package com.ge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @GetMapping("")
    public String hello() {
        System.out.println("test csrf");
        return "csrf-1";
        //return "test";
    }
}
