package com.robsoncrafstman.testes.java.domain;

public class Usuario {

	private final int id;
	private final String nome;

	public Usuario(final int id, final String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

}
