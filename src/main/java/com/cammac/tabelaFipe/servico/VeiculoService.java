package com.cammac.tabelaFipe.servico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cammac.tabelaFipe.model.Dados;
import com.cammac.tabelaFipe.model.Fipe;
import com.cammac.tabelaFipe.model.Modelos;
import com.cammac.tabelaFipe.model.TipoVeiculo;

public class VeiculoService {

	private final String URL_API_PADRAO = "https://parallelum.com.br/fipe/api/v1/";
	private ConsumoApi consumo = new ConsumoApi();
	private ConvertDados conversor = new ConvertDados();

	public List<Dados> listaMarcas(TipoVeiculo veiculo) {
		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() +"/marcas");		
		Dados[] lista = conversor.obterDados(json, Dados[].class);
		return  new ArrayList<>(Arrays.asList(lista));
	}

	public Modelos obtemModelos(TipoVeiculo veiculo , String codigoMarca) {
		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() + "/marcas/" + codigoMarca + "/modelos");
		if (json.contains("error")) {
			new ArrayList<>();
		}
		return conversor.obterDados(json, Modelos.class);
	}

	public List<Dados> listaAnosModelo(TipoVeiculo veiculo , String codigoMarca , String codigoModelo) {

		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos"); 
		if (json.contains("error")) {
			return new ArrayList<>();
		}
		Dados[] lista = conversor.obterDados(json, Dados[].class);
		return  new ArrayList<>(Arrays.asList(lista));
	}

	public Fipe obtemFipe(TipoVeiculo veiculo , String codigoMarca , String codigoModelo , String codigoFipe) {
		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() + "/marcas/" + codigoMarca + "/modelos/"+ codigoModelo + "/anos/" + codigoFipe); 
		if (json.contains("error")) {
			new ArrayList<>();
		}
		Fipe fipe = conversor.obterDados(json, Fipe.class);
		return fipe;
	}
	
}
