package com.robsoncrafstman.testes.java.domain;

public class Lance {

	private final Usuario usuario;
	private final double valor;

	public Lance(final Usuario usuario, final double valor) {
		this.usuario = usuario;
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public double getValor() {
		return this.valor;
	}

}
