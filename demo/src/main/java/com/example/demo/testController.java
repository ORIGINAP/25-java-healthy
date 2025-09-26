package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/api/greeting")
    public Map<String, String> greeting() {
        Map<String, String> result = new HashMap<>();
        result.put("message", "Hello from Spring Boot!");
        return result;
    }
}