package com.ge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProviderController1 {

    @GetMapping("/hello")
    public String hello() {
        return "provider 1";
    }


    @GetMapping("/getMap")
    public Map<String,String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("get map key", "get map value");

        return map;
    }
}
