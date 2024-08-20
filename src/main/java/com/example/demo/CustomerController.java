package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.ArrayList;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CustomerController {

    private List<Custommer> customerList = new ArrayList<Custommer>(List.of(
        new Custommer(0, "testName", "nicolas@gmail.com", "San Martin 123", 18, "name123123"),
        new Custommer(2, "pablo", "pablo@gmail.com", "San pablo 123", 18, "pablo123123")
    ));

    @GetMapping("/customers")
    public List<Custommer> getCustomers() {
        return this.customerList;
    }

    @GetMapping("/token")
    public CsrfToken getMethodName(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/create")
    public String postMethodName(@RequestBody Custommer custommer) {
        this.customerList.add(custommer);
        return custommer.toString();
    }

}
