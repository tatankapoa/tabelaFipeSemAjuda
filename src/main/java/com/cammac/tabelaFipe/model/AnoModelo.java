package com.cammac.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public record AnoModelo(@JsonAlias("codigo") String codigo,
					    @JsonAlias("nome") String nome
		                ) {

}
