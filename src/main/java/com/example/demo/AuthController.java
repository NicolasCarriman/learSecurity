package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/start")
public class AuthController {

    @GetMapping()
    public String start(HttpServletRequest request) {
        return "testauth" + request.getSession().getId();
    }


}


//csfr es una vulnerability
//tenemos un cliente que envia una request con session id en sus cookies y el haker lo intercepta
//
//csfr 
