package com.backend.api_pizzaria;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class AppInitializer {

    private static final String UPLOAD_DIR = "uploads";

    @PostConstruct
    public void init() {
        try {
            // checks if directory exists
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                // create the directory if it does not exist
                Files.createDirectories(path);
                System.out.println("Directory 'uploads' created successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error creating directory 'uploads': " + e.getMessage());
            throw new RuntimeException("Error creating directory 'uploads'", e);
        }
    }
}
