package br.com.pondionstracker.bh.realtime.configs;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pondionstracker.bh.realtime.models.BusLine;
import lombok.SneakyThrows;

@Configuration
public class BuslineConfig {

	@Bean
	@SneakyThrows
	Map<Integer, BusLine> getBusLine(ResourceLoader resourceLoader, ObjectMapper mapper) {
		return mapper.readValue(resourceLoader.getResource("classpath:configs/bus_line.json").getInputStream(), 
				new TypeReference<List<BusLine>>() {})
				.stream()
				.collect(Collectors.toMap(BusLine::getIdLinha, Function.identity()));
	}

}
