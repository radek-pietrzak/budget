package com.home.budget.connectors.security;

import com.home.budget.connectors.API;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SsoController {
    @GetMapping(API.LOGOUT)
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }
}
