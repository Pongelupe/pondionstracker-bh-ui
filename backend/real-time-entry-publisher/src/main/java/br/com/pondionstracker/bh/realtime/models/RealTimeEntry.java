package br.com.pondionstracker.bh.realtime.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RealTimeEntry {

	@JsonProperty("EV")
	private String ev;
	
	@JsonProperty("HR")
	private String hr;
	
	@JsonProperty("LT")
	private String lt;
	
	@JsonProperty("LG")
	private String lg;
	
	@JsonProperty("NV")
	private String nv;
	
	@JsonProperty("VL")
	private String vl;
	
	@JsonProperty("NL")
	private String nl;
	
	@JsonProperty("dg")
	private String dg;
	
	@JsonProperty("SV")
	private String sv;
	
	@JsonProperty("DT")
	private String dt;
	
}
