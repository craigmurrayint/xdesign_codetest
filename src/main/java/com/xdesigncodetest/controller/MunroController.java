package com.xdesigncodetest.controller;

import com.xdesigncodetest.service.MunroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MunroController {
    
    @Autowired
    private MunroService munroService;
    
    @GetMapping("/munros")
    public String getAllMunros() {
        return "Test";
    }
}
