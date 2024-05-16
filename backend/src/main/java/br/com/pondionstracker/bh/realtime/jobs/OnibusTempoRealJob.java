package br.com.pondionstracker.bh.realtime.jobs;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.pondionstracker.bh.realtime.clients.PBHClient;
import br.com.pondionstracker.bh.realtime.models.BusEntryCoord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnibusTempoRealJob {

	private final PBHClient pbhClient;
	
	private final SimpMessagingTemplate simpleMessagingTemplate;
	
	@Scheduled(fixedRate = 25000)
	public void sendMessage() {
		var dados = pbhClient.getDadosTempoReal();
		log.info("Buscou {} registros", dados.size());
		var entries = dados
				.stream()
				.map(e -> BusEntryCoord.builder()
						.lat(e.getLt())
						.lon(e.getLg())
						.vehicleId(e.getNv())
						.build())
				.toList();
		simpleMessagingTemplate.convertAndSend("/topic/bus", entries);
	}
	
}
