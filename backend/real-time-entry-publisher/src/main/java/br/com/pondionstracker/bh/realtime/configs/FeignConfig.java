package br.com.pondionstracker.bh.realtime.configs;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import feign.Util;
import feign.codec.Decoder;

@Configuration
public class FeignConfig {

	@Bean
	Decoder feignDecoder(ObjectMapper m) {
		return (response, type) -> {
			var bodyString = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
			var javaType = TypeFactory.defaultInstance().constructType(type);
			return m.readValue(bodyString, javaType);
		};
	}
	
}
