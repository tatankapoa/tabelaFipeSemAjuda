package com.cammac.tabelaFipe.servico;

import java.util.Scanner;

public abstract class ATeclado {
	
	public void limpaScanner(Scanner leitura) {
		System.out.println("Pressione Enter para continuar...");	               
		leitura.nextLine();
	}
	
	public String inputTeclado(Scanner leitura , String msg) {
		System.out.println(msg);
		return leitura.nextLine();
	}
	
	public Integer validaLeituraNumerica(String valorLido) {
		try {
			return Integer.valueOf(valorLido);
		} catch (NumberFormatException e) {			
			return -1;
		}
	}
}
