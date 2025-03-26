package com.example.demo.controller;

import com.example.demo.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class TestController {

    private TestService service;

    @GetMapping("/test/data")
    public ResponseEntity<String> getData() {
        var response = service.getData();
        return ResponseEntity.ok(response);
    }
}
