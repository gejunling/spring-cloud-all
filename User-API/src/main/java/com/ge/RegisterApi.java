package com.ge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/User")
public interface RegisterApi {

    @GetMapping("/isAlive")
    public String isAlive();
}
