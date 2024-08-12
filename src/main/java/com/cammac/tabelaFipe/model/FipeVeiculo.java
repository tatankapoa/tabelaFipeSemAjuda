package com.cammac.tabelaFipe.model;

import java.math.BigDecimal;

public class FipeVeiculo {
	
	private String codigoFipe;
	private TipoVeiculo tipoVeiculo;
	private BigDecimal valor;
	private String marca;
	private Integer anoModelo;
	private String combustivel;
	private String mesReferencia;
	private String siglaCombustivel;
	
	public FipeVeiculo(Fipe fipe) {
		super();
		this.codigoFipe = fipe.codigoFipe();
		this.tipoVeiculo = TipoVeiculo.obtemTipoVeiculo(fipe.tipoVeiculo());
		 
		try {
			this.valor = new BigDecimal(fipe.valor().replace(".", "").replace(",", ".").replace("R$", "").trim());
		} catch (NumberFormatException e) {
			this.valor = BigDecimal.ZERO;
		}

		this.marca = fipe.marca();
		
		this.anoModelo = fipe.anoModelo();
		this.combustivel = fipe.combustivel();
		this.mesReferencia = fipe.mesReferencia();
		this.siglaCombustivel = fipe.siglaCombustivel();
	}
	
	public String getCodigoFipe() {
		return codigoFipe;
	}
	public void setCodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	public String getSiglaCombustivel() {
		return siglaCombustivel;
	}
	public void setSiglaCombustivel(String siglaCombustivel) {
		this.siglaCombustivel = siglaCombustivel;
	}
	@Override
	public String toString() {
		return "FipeVeiculo [" + (codigoFipe != null ? "codigoFipe=" + codigoFipe + ", " : "")
				+ (tipoVeiculo != null ? "tipoVeiculo=" + tipoVeiculo + ", " : "")
				+ (valor != null ? "valor=" + valor + ", " : "") + (marca != null ? "marca=" + marca + ", " : "")
				+ (anoModelo != null ? "anoModelo=" + anoModelo + ", " : "")
				+ (combustivel != null ? "combustivel=" + combustivel + ", " : "")
				+ (mesReferencia != null ? "mesReferencia=" + mesReferencia + ", " : "")
				+ (siglaCombustivel != null ? "siglaCombustivel=" + siglaCombustivel : "") + "]";
	}

}
