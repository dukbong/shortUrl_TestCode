package com.example.demo.serviceImpl.badTestCode;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.service.ShortUrlService;

/***
 * 테스트 하기 어려운 코드
 */
@Service
public class ShortUrlServiceImplOrigin implements ShortUrlService {
	
	private static final Map<String, String> URLPACK = new HashMap<>();
	private static final String SHORT = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	private static final SecureRandom random = new SecureRandom();
	
	@Override
	public synchronized String shortUrl(String url) {
	    while (true) {
	        StringBuilder sb = new StringBuilder();
	        
	        for (int i = 0; i < 7; i++) {
	            int randomIndex = random.nextInt(SHORT.length());
	            sb.append(SHORT.charAt(randomIndex));
	        }
	        
	        String shortKey = sb.toString();
	        
	        if (!URLPACK.containsKey(shortKey)) {
	            URLPACK.put(shortKey, url);
	            return shortKey;
	        }
	    }
	}

	@Override
	public String getOriginalUrl(String shortKey) {
		return URLPACK.get(shortKey);
	}

}
