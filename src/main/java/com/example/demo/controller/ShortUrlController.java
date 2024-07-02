package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ShortUrlService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/short")
@RequiredArgsConstructor
public class ShortUrlController {
	
	private final ShortUrlService shortUrlServiceImpl;
	
	@PostMapping("/url")
	public ResponseEntity<String> shortUrl(@RequestBody String url) {
		String makingUrl = shortUrlServiceImpl.shortUrl(url);
		return ResponseEntity.ok().body(makingUrl);
	}
	
    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortKey, HttpServletResponse response) {
        String originalUrl = shortUrlServiceImpl.getOriginalUrl(shortKey);

        if (originalUrl != null) {
            response.setHeader("Location", originalUrl);
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
}
