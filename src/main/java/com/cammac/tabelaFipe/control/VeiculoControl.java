package com.cammac.tabelaFipe.control;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cammac.tabelaFipe.model.Dados;
import com.cammac.tabelaFipe.model.FipeVeiculo;
import com.cammac.tabelaFipe.model.Modelos;
import com.cammac.tabelaFipe.model.TipoVeiculo;
import com.cammac.tabelaFipe.servico.ATeclado;
import com.cammac.tabelaFipe.servico.VeiculoService;

public class VeiculoControl extends ATeclado {
	
	private final VeiculoService service = new VeiculoService();
	private Modelos modelos;
	
	public void exibeMenu(Scanner leitura ,TipoVeiculo tipoVeiculo) {

		List<Dados> marcas = service.listaMarcas(tipoVeiculo);
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

			var inputOpcao = this.inputTeclado(leitura, "Entre com a opção desejada: ");
		    var opcao = this.validaLeituraNumerica(inputOpcao);

			switch (opcao) { 
				
					case 0: {
						System.out.println("Retornando ao menu principal....");
						this.limpaScanner(leitura);
						sair = false;
						break;
					}
					case 1: {
						marcas.forEach(m-> { System.out.println("Código: " + m.codigo() + " Marca: " + m.nome());});
						this.limpaScanner(leitura);
						break;
					}
					case 2:{ 	
						var inputLetras = this.inputTeclado(leitura, "Por favor, digite as letras iniciais de alguma marca de carro:");
						List<Dados> marcasPorLetraIniciais = marcas.stream().filter(a -> a.nome().toUpperCase().contains(inputLetras.toUpperCase())).collect(Collectors.toList());
						if (!marcasPorLetraIniciais.isEmpty()) {
							marcasPorLetraIniciais.forEach(m-> { System.out.println("Código: " + m.codigo() + " Marca: " + m.nome());});
						} else {
							System.out.println("Letras iniciais informadas["+inputLetras+"] da marca do veículo não localizado.");
						}
						this.limpaScanner(leitura);
						break;
					}
					case 3:{						
						var inputMarca = this.inputTeclado(leitura, "Por favor, digite o código da marca do veículo: ");
						Optional<Dados> marcaPorCodigo= marcas.stream().filter(m -> m.codigo().equals(inputMarca)).findAny();
						if (marcaPorCodigo.isPresent()) {							
							System.out.println("Código: " + marcaPorCodigo.get().codigo() + " Marca: " + marcaPorCodigo.get().nome());
						} else {
							System.out.println("Código da marca["+inputMarca+"] do veículo não localizado.");
						}
						this.limpaScanner(leitura);
						break;
					}
					case 4:{
						var inputMarca = this.inputTeclado(leitura,"Por favor, insira o código da marca para visualizar os modelos disponíveis desse veículos :");
						modelos = service.obtemModelos(tipoVeiculo, inputMarca);
						if (modelos !=null && modelos.lista() != null && !modelos.lista().isEmpty()) {
							modelos.lista().forEach(modelo-> {System.out.println("Código do Modelo: " + modelo.codigo() + " Modelo: " + modelo.nome());});
						} else {
							System.out.println("Código da marca["+inputMarca+"] do veículo não localizado.");
						}
						this.limpaScanner(leitura);
						break;
					}
					case 5:{
						if (modelos == null) {
							System.out.println("Ação não permitida. Por favor, realize a opção 4 primeiro.");
							break;
						}
						var inputLetras = this.inputTeclado(leitura,"Por favor, digite as letras iniciais do modelo do veículo:");

						List<Dados> modelosPorLetraIniciais = modelos.lista().stream().filter(m-> m.nome().toLowerCase().contains(inputLetras.toLowerCase())).collect(Collectors.toList());
						if (!modelosPorLetraIniciais.isEmpty()) {
							modelosPorLetraIniciais.forEach(modelo->{System.out.println("Código Modelo: " + modelo.codigo() + " Modelo: " + modelo.nome() );});
						} else {
							System.out.println("Letras iniciais informadas["+inputLetras+"] do modelo do veículo não localizado.");
						}
						this.limpaScanner(leitura);
						break;
					}
					case 6: {
						if (modelos == null) {
							System.out.println("Ação não permitida. Por favor, realize a opção 4 primeiro.");
							break;
						}						
						var inputCodigoModelo = this.inputTeclado(leitura,"Por favor, digite o código do modelo do veículo: ");
								
						Optional<Dados> modeloPorCodigo= modelos.lista().stream().filter(m -> m.codigo().equals(inputCodigoModelo)).findAny();
						if (modeloPorCodigo.isPresent()) {							
							System.out.println("Código Modelo: " + modeloPorCodigo.get().codigo() + " Modelo: " + modeloPorCodigo.get().nome() );
						} else {
							System.out.println("Código do modelo["+inputCodigoModelo+"] do veículo não localizado.");
						}
						this.limpaScanner(leitura);
						break;
					}
					case 7: {
						
						if (modelos == null) {
							System.out.println("Ação não permitida. Por favor, realize a opção 4 primeiro.");
							break;
						}	
						var inputCodigoMarca = this.inputTeclado(leitura, "Por favor, digite o código da marca do veículo: ");						
						var inputCodigoModelo =  this.inputTeclado(leitura, "Por favor, digite o código do modelo do veículo: ");
						
						List<Dados> listaAnoModelo = service.listaAnosModelo(tipoVeiculo, inputCodigoMarca, inputCodigoModelo);
						if (listaAnoModelo != null && !listaAnoModelo.isEmpty()) {
							List<FipeVeiculo> lista =  (listaAnoModelo.stream()
									  								  .map(l-> service.obtemFipe(tipoVeiculo, inputCodigoMarca, inputCodigoModelo ,l.codigo()))
									  								  .collect(Collectors.toList())).stream().map(f-> new FipeVeiculo(f)).collect(Collectors.toList());
							
							Optional<Dados> optModelo = modelos.lista().stream().filter(m-> m.codigo().equals(inputCodigoModelo)).findAny();
							if (optModelo.isPresent()) {
								System.out.println("Lista de Modelos: Codigo:"+  optModelo.get().codigo() + " Descrição " + optModelo.get().nome());
								lista.forEach(System.out::println);
							}
							
						} else {
							System.out.println("Código da marca["+inputCodigoMarca+"] e modelo["+inputCodigoModelo+"] do veículo informado não foi localizado.");
						}
						this.limpaScanner(leitura);
						break;
					}
					
					default:
						 System.out.println("Opção inválida["+inputOpcao+"]. Tente novamente.");
						 this.limpaScanner(leitura);
						 break;
				}
				
			} while (sair);
	}
}
