package br.com.pondionstracker.bh.realtime.publihser.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.pondionstracker.bh.realtime.publihser.models.RealTimeEntry;

@FeignClient(name = "pbhClient", url = "https://temporeal.pbh.gov.br/?param=D")
public interface PBHClient {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<RealTimeEntry> getDadosTempoReal();
	
}
