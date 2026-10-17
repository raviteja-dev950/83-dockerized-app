package com.ravi.devops.dockerizedapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class DockerController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
            "project", "83-dockerized-app",
            "tier", "9 - DevOps + Cloud",
            "status", "Docker Ready"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "dockerized-app");
    }
}