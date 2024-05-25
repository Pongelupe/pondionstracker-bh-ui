package br.com.pondionstracker.bh.realtime.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusEntryCoord {

	private String lat;

	private String lon;

	private String vehicleId;
	
	private String descricao;
	
	private LocalDateTime data;
	
}
