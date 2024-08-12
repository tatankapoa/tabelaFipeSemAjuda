package com.cammac.tabelaFipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cammac.tabelaFipe.control.Menu;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();
		menu.exibeMenu();
	}
}
