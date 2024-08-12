package com.cammac.tabelaFipe.util;

import java.util.Scanner;

public class VeiculoUtil {
	
	public static void limpaScanner(Scanner leitura) {
		System.out.println("Pressione Enter para continuar...");	               
		leitura.nextLine();
	}
	
	public static String inputTeclado(Scanner leitura , String msg) {
		System.out.println(msg);
		return leitura.nextLine();
	}
	
	public static Integer validaLeituraNumerica(String valorLido) {
		try {
			return Integer.valueOf(valorLido);
		} catch (NumberFormatException e) {			
			return -1;
		}
	}
	
//	public static Integer validaLeituraNumerica(Scanner leitura,String mensagem) {
//		try {
//			System.out.println(mensagem);
//			var opcao = leitura.nextInt();
//			leitura.nextLine();
//			return opcao;
//
//		} catch (InputMismatchException e) {
//			leitura.nextLine();
//			return -1;
//		}
//	}

}
