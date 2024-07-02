package com.example.demo.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.serviceImpl.goodTestCode.RandomStringGenerator;
import com.example.demo.serviceImpl.goodTestCode.ShortUrlServiceImplModify;

@ExtendWith(MockitoExtension.class)
public class ShortUrlServiceImplModifyTest {
	
	@Mock
	private Map<String, String> map;
	
	@Mock
    private RandomStringGenerator randomStringGenerator; 

    @InjectMocks
    private ShortUrlServiceImplModify shortUrlServiceImplModify;

    @Test
    void shortUrl() {
        // given
        String url = "www.google.com";
        String shortUrl = "testcod";
        
        when(randomStringGenerator.generate(7)).thenReturn(shortUrl);
        when(map.containsKey(shortUrl)).thenReturn(false);
        when(map.put(shortUrl, url)).thenReturn(null);

        // when
        String result = shortUrlServiceImplModify.shortUrl(url);
        
        // then
        assertThat(result).isEqualTo(shortUrl);
        assertThat(shortUrl.length()).isEqualTo(7);
        verify(map, times(1)).put(result, url);
    }
    
    @Test
    void shortUrlWithUrlIsNull() {
    	// given
    	String url = null;
    	
    	assertThatThrownBy(() -> shortUrlServiceImplModify.shortUrl(url)).isInstanceOf(IllegalArgumentException.class).hasMessage("url은 빈문자열일 수 없습니다.");
    }
    
    @Test
    void shortUrlWithUrlIsEmpty() {
    	// given
    	String url = "";
    	
    	assertThatThrownBy(() -> shortUrlServiceImplModify.shortUrl(url)).isInstanceOf(IllegalArgumentException.class).hasMessage("url은 빈문자열일 수 없습니다.");
    }
}
