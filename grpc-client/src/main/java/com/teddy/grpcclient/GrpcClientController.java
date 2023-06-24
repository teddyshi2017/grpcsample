package com.teddy.grpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcClientController {

    @Autowired
    private GrpcClient client;

    @GetMapping("/greeting")
    public ResponseEntity<String> sendGreeting(@RequestParam String firstName, @RequestParam  String lastName) {
        return ResponseEntity.ok(client.sendGreeting(firstName, lastName).getGreeting().toString());
    }
}
