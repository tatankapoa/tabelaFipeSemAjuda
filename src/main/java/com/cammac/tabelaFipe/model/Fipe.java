package com.cammac.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Fipe(
			@JsonAlias("TipoVeiculo") Integer tipoVeiculo,
			@JsonAlias("Valor") String valor,
			@JsonAlias("Marca") String marca,
			@JsonAlias("AnoModelo") Integer anoModelo,
			@JsonAlias("Combustivel") String combustivel,
			@JsonAlias("CodigoFipe") String codigoFipe,
			@JsonAlias("MesReferencia") String mesReferencia,
			@JsonAlias("SiglaCombustivel") String siglaCombustivel
		) {
}
