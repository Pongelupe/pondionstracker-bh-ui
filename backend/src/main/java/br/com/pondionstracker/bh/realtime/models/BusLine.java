package br.com.pondionstracker.bh.realtime.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class BusLine {

	@JsonProperty(value = "id_linha")
	private Integer idLinha;
	
	@JsonProperty(value = "codigo_linha")
	private String codigoLinha;
	
	@JsonProperty(value = "descricao_linha")
	private String descricaoLinha;
	
}
