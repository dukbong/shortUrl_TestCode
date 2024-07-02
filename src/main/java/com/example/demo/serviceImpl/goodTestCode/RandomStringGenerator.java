package com.example.demo.serviceImpl.goodTestCode;

import java.security.SecureRandom;

public class RandomStringGenerator {
	
    private final String SHORT = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private final SecureRandom random = new SecureRandom();

    public String generate(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(SHORT.length());
            sb.append(SHORT.charAt(randomIndex));
        }
        return sb.toString();
    }
}
