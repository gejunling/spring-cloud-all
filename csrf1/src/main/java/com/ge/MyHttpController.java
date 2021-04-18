package com.ge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyHttpController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }
}
