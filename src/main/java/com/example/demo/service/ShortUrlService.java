package com.example.demo.service;

public interface ShortUrlService {

	String shortUrl(String url);

	String getOriginalUrl(String shortKey);

}
