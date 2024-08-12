package com.cammac.tabelaFipe.control;

import java.util.Scanner;

import com.cammac.tabelaFipe.model.TipoVeiculo;
import com.cammac.tabelaFipe.util.VeiculoUtil;

public class Menu {
	
	private Scanner leitura  =new Scanner(System.in);
	
	public void exibeMenu() {
		
		boolean sair = true;
		VeiculoControl veiculoControl = new VeiculoControl();
		do {

			System.out.println("Tabela Fipe");
			System.out.println("[1] - Carros");
			System.out.println("[2] - Motos");
			System.out.println("[3] - Caminhoes");
			System.out.println("[0] - Sair");
			
			var inputOpcao = VeiculoUtil.inputTeclado(leitura, "Entre com a opção desejada: ");
		    var opcao = VeiculoUtil.validaLeituraNumerica(inputOpcao);
			
			switch (opcao) { 
				case 1: {
					veiculoControl.exibeMenu(leitura,TipoVeiculo.CARRO);
					break;
				}
				case 2: {
					veiculoControl.exibeMenu(leitura,TipoVeiculo.MOTO);
					break;
				}
				case 3: {
					veiculoControl.exibeMenu(leitura,TipoVeiculo.CAMINHAO);					
					break;
				}
				case 0: {
					System.out.println("Sair....");
					sair = false;
					break;
				}
				default:
					 System.out.println("Opção inválida. Tente novamente.");					 
			}
			
		} while (sair);
		leitura.close();
	}
	

}
