package com.cammac.tabelaFipe.control;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cammac.tabelaFipe.model.AnoModelo;
import com.cammac.tabelaFipe.model.FipeVeiculo;
import com.cammac.tabelaFipe.model.Marca;
import com.cammac.tabelaFipe.model.Modelo;
import com.cammac.tabelaFipe.model.Modelos;
import com.cammac.tabelaFipe.model.TipoVeiculo;
import com.cammac.tabelaFipe.servico.VeiculoService;
import com.cammac.tabelaFipe.util.VeiculoUtil;

public class VeiculoControl {
	
	private final VeiculoService service = new VeiculoService();
	private Modelos modelos;
	
	public void exibeMenu(Scanner leitura ,TipoVeiculo tipoVeiculo) {

		List<Marca> marcas = service.listaMarcas(tipoVeiculo);
		boolean sair = true;
		do {
			System.out.println("Tabela Fipe - " + tipoVeiculo.getDescription());
			System.out.println("[1] - Listar todas as marcas de veículos.");
			System.out.println("[2] - Listar marcas de veículos por letras iniciais.");
			System.out.println("[3] - Buscar marcas de veículos por código.");
			System.out.println("[4] - Listar todos os modelos de veículos pelo código da marca desejada.");
			System.out.println("[5] - Listar modelos de veículos por letras iniciais.");
			System.out.println("[6] - Buscar modelos de veículos por código.");
			System.out.println("[7] - Listar todos os anos dos veículos disponíveis para a marca e modelo desejada.");
			System.out.println("[0] - Voltar para o menu principal");

			var inputOpcao = VeiculoUtil.inputTeclado(leitura, "Entre com a opção desejada: ");
		    var opcao = VeiculoUtil.validaLeituraNumerica(inputOpcao);

			switch (opcao) { 
				
					case 0: {
						System.out.println("Retornando ao menu principal....");
						VeiculoUtil.limpaScanner(leitura);
						sair = false;
						break;
					}
					case 1: {
						marcas.forEach(System.out::println);
						VeiculoUtil.limpaScanner(leitura);
						break;
					}
					case 2:{ 	
						var inputLetras = VeiculoUtil.inputTeclado(leitura, "Por favor, digite as letras iniciais de alguma marca de carro:");
						List<Marca> marcasPorLetraIniciais = marcas.stream().filter(a -> a.nome().toUpperCase().startsWith(inputLetras.toUpperCase())).collect(Collectors.toList());
						if (!marcasPorLetraIniciais.isEmpty()) {
							marcasPorLetraIniciais.forEach(System.out::println);
						} else {
							System.out.println("Letras iniciais informadas["+inputLetras+"] da marca do veículo não localizado.");
						}
						VeiculoUtil.limpaScanner(leitura);
						break;
					}
					case 3:{						
						var inputMarca = VeiculoUtil.inputTeclado(leitura, "Por favor, digite o código da marca do veículo: ");
						var codigoMarca =VeiculoUtil.validaLeituraNumerica(inputMarca);
								
						Optional<Marca> marcaPorCodigo= marcas.stream().filter(m -> m.codigo().equals(codigoMarca)).findAny();
						if (marcaPorCodigo.isPresent()) {
							System.out.println(marcaPorCodigo);
						} else {
							System.out.println("Código da marca["+inputMarca+"] do veículo não localizado.");
						}
						VeiculoUtil.limpaScanner(leitura);
						break;
					}
					case 4:{
						var inputMarca = VeiculoUtil.inputTeclado(leitura,"Por favor, insira o código da marca para visualizar os modelos disponíveis desse veículos :");
						var codigoMarca =VeiculoUtil.validaLeituraNumerica(inputMarca);
						
						modelos = service.obtemModelos(tipoVeiculo, codigoMarca);
						if (modelos !=null && modelos.lista() != null && !modelos.lista().isEmpty()) {
							modelos.lista().forEach(System.out::println);
						} else {
							System.out.println("Código da marca["+inputMarca+"] do veículo não localizado.");
						}
						VeiculoUtil.limpaScanner(leitura);
						break;
					}
					case 5:{
						if (modelos == null) {
							System.out.println("Ação não permitida. Por favor, realize a opção 4 primeiro.");
							break;
						}
						var inputLetras = VeiculoUtil.inputTeclado(leitura,"Por favor, digite as letras iniciais do modelo do veículo:");

						List<Modelo> modelosPorLetraIniciais = modelos.lista().stream().filter(m-> m.nome().toLowerCase().startsWith(inputLetras.toLowerCase())).collect(Collectors.toList());
						if (!modelosPorLetraIniciais.isEmpty()) {
							modelosPorLetraIniciais.forEach(System.out::println);
						} else {
							System.out.println("Letras iniciais informadas["+inputLetras+"] do modelo do veículo não localizado.");
						}
						VeiculoUtil.limpaScanner(leitura);
						break;
					}
					case 6: {
						if (modelos == null) {
							System.out.println("Ação não permitida. Por favor, realize a opção 4 primeiro.");
							break;
						}						
						var inputCodigoModelo = VeiculoUtil.inputTeclado(leitura,"Por favor, digite o código do modelo do veículo: ");
						var codigoModelo = VeiculoUtil.validaLeituraNumerica(inputCodigoModelo);
								
						Optional<Modelo> modeloPorCodigo= modelos.lista().stream().filter(m -> m.codigo().equals(codigoModelo)).findAny();
						if (modeloPorCodigo.isPresent()) {
							System.out.println(modeloPorCodigo);
						} else {
							System.out.println("Código do modelo["+inputCodigoModelo+"] do veículo não localizado.");
						}
						VeiculoUtil.limpaScanner(leitura);
						break;
					}
					case 7: {
						
						var inputCodigoMarca = VeiculoUtil.inputTeclado(leitura, "Por favor, digite o código da marca do veículo: ");
						var codigoMarca =  VeiculoUtil.validaLeituraNumerica(inputCodigoMarca);
						
						var inputCodigoModelo =  VeiculoUtil.inputTeclado(leitura, "Por favor, digite o código do modelo do veículo: ");
						var codigoModelo = VeiculoUtil.validaLeituraNumerica(inputCodigoModelo);
						
						List<AnoModelo> listaAnoModelo = service.listaAnosModelo(tipoVeiculo, codigoMarca, codigoModelo);
						if (listaAnoModelo != null && !listaAnoModelo.isEmpty()) {
							List<FipeVeiculo> lista =  (listaAnoModelo.stream()
									  								  .map(l-> service.obtemFipe(tipoVeiculo, codigoMarca, codigoModelo ,l.codigo()))
									  								  .collect(Collectors.toList())).stream().map(f-> new FipeVeiculo(f)).collect(Collectors.toList());
							lista.forEach(System.out::println);
							
						} else {
							System.out.println("Código da marca["+inputCodigoMarca+"] e modelo["+inputCodigoModelo+"] do veículo informado não foi localizado.");
						}
						VeiculoUtil.limpaScanner(leitura);
						break;
					}
					default:
						 System.out.println("Opção inválida["+inputOpcao+"]. Tente novamente.");
						 VeiculoUtil.limpaScanner(leitura);
						 break;
				}
				
			} while (sair);
	}
}
