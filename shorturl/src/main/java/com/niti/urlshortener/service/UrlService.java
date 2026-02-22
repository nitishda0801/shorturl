package com.niti.urlshortener.service;

import com.niti.urlshortener.model.Url;
import com.niti.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UrlService {
    @Autowired
    private UrlRepository repository;

    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String shortenUrl(String originalUrl) {
        Url url = Url.builder()
                .originalUrl(originalUrl)
                .createdAt(LocalDateTime.now())
                .build();

        Url savedUrl = repository.save(url);

        // Convert ID to Base62 string
        String shortCode = encode(savedUrl.getId());
        savedUrl.setShortCode(shortCode);

        repository.save(savedUrl);
        return shortCode;
    }
    public String getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }
    private String encode(long id) {
        StringBuilder shortCode = new StringBuilder();
        while (id > 0) {
            shortCode.append(ALLOWED_CHARACTERS.charAt((int) (id % 62)));
            id /= 62;
        }
        return shortCode.reverse().toString();
    }
}
