package com.cammac.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelo(@JsonAlias("codigo") Integer codigo,
					 @JsonAlias("nome") String nome
		   ) {

	@Override
	public String toString() {
		return "Modelo [" + (codigo != null ? "codigo=" + codigo + ", " : "") + (nome != null ? "nome=" + nome : "")
				+ "]";
	}

}
