package com.cammac.tabelaFipe.model;

public enum TipoVeiculo {
	
	CARRO(1,"carros"),
	MOTO(2,"motos"),
	CAMINHAO(3,"caminhoes");

	private final Integer value;

	private final String description;
	
	private TipoVeiculo(Integer value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public static TipoVeiculo obtemTipoVeiculo(Integer value) {
		for (TipoVeiculo dominio : TipoVeiculo.values()) {

			if (dominio.getValue().equals(value)) {
				return dominio;
			}
		}

		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
}
