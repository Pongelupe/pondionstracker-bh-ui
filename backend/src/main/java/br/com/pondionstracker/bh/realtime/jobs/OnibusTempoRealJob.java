package br.com.pondionstracker.bh.realtime.jobs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.pondionstracker.bh.realtime.clients.PBHClient;
import br.com.pondionstracker.bh.realtime.models.BusEntryCoord;
import br.com.pondionstracker.bh.realtime.models.RealTimeEntry;
import br.com.pondionstracker.bh.realtime.repository.BusLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnibusTempoRealJob {

	private final PBHClient pbhClient;
	
	private final SimpMessagingTemplate simpleMessagingTemplate;
	
	private final BusLineRepository busLineRepository;
	
	private final record Onibus(String nv, String descricao, RealTimeEntry e) {}
	
	@Scheduled(fixedRate = 25000)
	public void sendMessage() {
		var formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		var dados = pbhClient.getDadosTempoReal();
		log.info("Buscou {} registros", dados.size());
		var entries = dados
				.stream()
				.map(e -> prepareDescricao(e.getNv(), e))
				.filter(Optional<Onibus>::isPresent)
				.map(Optional<Onibus>::get)
				.map(e -> {
					var entry = e.e;
					return BusEntryCoord.builder()
							.lat(entry.getLt())
							.lon(entry.getLg())
							.vehicleId(e.nv())
							.descricao(e.descricao)
							.data(LocalDateTime.parse(entry.getHr(), formatter))
							.build();
				})
				.limit(15000)
				.collect(Collectors.groupingBy(e -> e.getVehicleId()));
		log.info("Registros filtrados {}", entries.size());
		simpleMessagingTemplate.convertAndSend("/topic/bus", entries);
	}

	private Optional<Onibus> prepareDescricao(String nv, RealTimeEntry entry) {
		return busLineRepository.findById(Integer.parseInt(nv))
				.map(e -> new Onibus(nv, e.getCodigoLinha() + " - " + e.getDescricaoLinha(), entry));
	}
	
}
