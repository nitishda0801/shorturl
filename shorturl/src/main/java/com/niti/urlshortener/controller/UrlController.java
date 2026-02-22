package com.niti.urlshortener.controller;

import com.niti.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UrlController {

    @Autowired
    private UrlService urlService;

    // 1. POST: Create Short URL
    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shorten(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        String shortCode = urlService.shortenUrl(originalUrl);

        String shortUrl = "http://localhost:8080/api/v1/" + shortCode;
        return ResponseEntity.ok(Map.of("shortUrl", shortUrl));
    }

    // 2. GET: Redirect to Original URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

}
