package br.com.pondionstracker.bh.realtime.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "bus_line")
@Entity
@Data
public class BusLine {

	@Id
	@Column(name = "id_linha")
	private Integer idLinha;
	
	@Column(name = "codigo_linha")
	private String codigoLinha;
	
	@Column(name = "descricao_linha")
	private String descricaoLinha;
	
}
