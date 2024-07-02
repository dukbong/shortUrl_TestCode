package com.example.demo.serviceImpl.goodTestCode;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.demo.service.ShortUrlService;

import lombok.RequiredArgsConstructor;

/***
 * 테스트 하기 쉬운 코드로 만들기
 */
@Service
@RequiredArgsConstructor
public class ShortUrlServiceImplModify implements ShortUrlService {
	
	private final Map<String, String> URLPACK;
	private final RandomStringGenerator randomStringGenerator;
	
	@Override
	public synchronized String shortUrl(String url) {
		Assert.hasText(url, "url은 빈문자열일 수 없습니다.");
	    while (true) {
	        String shortKey = randomStringGenerator.generate(7);
	        
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
