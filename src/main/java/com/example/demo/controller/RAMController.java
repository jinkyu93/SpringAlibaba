package com.example.demo.controller;

import com.aliyun.sdk.service.ram20150501.models.ListUsersResponseBody;
import com.example.demo.service.RAMService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/ram")
public class RAMController {

    private RAMService service;

    @GetMapping("/users")
    public ResponseEntity<ListUsersResponseBody.Users> getData() {
        var response = service.getUsers();
        return ResponseEntity.ok(response.getUsers());
    }
}
