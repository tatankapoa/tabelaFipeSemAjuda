package com.cammac.tabelaFipe.servico;

public interface IConvertDados {
	
	<T> T obterDados (String json , Class<T> classe);
	
	
	
}
