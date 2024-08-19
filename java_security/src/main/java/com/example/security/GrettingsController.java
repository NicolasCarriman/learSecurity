package com.example.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/greetings")
public class GrettingsController {
    
    @GetMapping()
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from our API");
    }

    @GetMapping("/good-bye")
    public ResponseEntity<String> getMethodName(@RequestParam String param) {
        return ResponseEntity.ok("good bye");
    }
    
}
