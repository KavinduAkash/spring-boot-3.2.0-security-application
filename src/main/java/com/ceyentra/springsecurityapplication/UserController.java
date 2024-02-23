package com.ceyentra.springsecurityapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @GetMapping("/home")
    public String home() {
        return "Welcome Home";
    }

    @GetMapping("user")
    public String user() {
        return "Welcome User";
    }

    @GetMapping("admin")
    public String admin() {
        return "Welcome User";
    }
}
