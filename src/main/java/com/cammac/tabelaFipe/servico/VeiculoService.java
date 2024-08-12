package com.cammac.tabelaFipe.servico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cammac.tabelaFipe.model.AnoModelo;
import com.cammac.tabelaFipe.model.Fipe;
import com.cammac.tabelaFipe.model.Marca;
import com.cammac.tabelaFipe.model.Modelos;
import com.cammac.tabelaFipe.model.TipoVeiculo;

public class VeiculoService {

	private final String URL_API_PADRAO = "https://parallelum.com.br/fipe/api/v1/";
	private ConsumoApi consumo = new ConsumoApi();
	private ConvertDados conversor = new ConvertDados();

	public List<Marca> listaMarcas(TipoVeiculo veiculo) {
		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() +"/marcas");		
		Marca[] lista = conversor.obterDados(json, Marca[].class);
		return  new ArrayList<>(Arrays.asList(lista));
	}

	public Modelos obtemModelos(TipoVeiculo veiculo , Integer codigoMarca) {
		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() + "/marcas/" + codigoMarca + "/modelos");
		if (json.contains("error")) {
			new ArrayList<>();
		}
		return conversor.obterDados(json, Modelos.class);
	}

	public List<AnoModelo> listaAnosModelo(TipoVeiculo veiculo , Integer codigoMarca , Integer codigoModelo) {

		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos"); 
		if (json.contains("error")) {
			return new ArrayList<>();
		}
		AnoModelo[] lista = conversor.obterDados(json, AnoModelo[].class);
		return  new ArrayList<>(Arrays.asList(lista));
	}

	public Fipe obtemFipe(TipoVeiculo veiculo , Integer codigoMarca , Integer codigoModelo , String codigoFipe) {
		var json = consumo.obterDados(URL_API_PADRAO + veiculo.getDescription() + "/marcas/" + codigoMarca + "/modelos/"+ codigoModelo + "/anos/" + codigoFipe); 
		if (json.contains("error")) {
			new ArrayList<>();
		}
		Fipe fipe = conversor.obterDados(json, Fipe.class);
		return fipe;
	}
	
}
