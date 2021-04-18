package com.ge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements RegisterApi {

    @Override
    public String isAlive() {
        // TODO Auto-generated method stub
        return "ok";
    }

}
