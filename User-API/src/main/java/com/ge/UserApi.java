package com.ge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/User")
public interface UserApi {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/getById")
    public String getById(Integer id);
}
