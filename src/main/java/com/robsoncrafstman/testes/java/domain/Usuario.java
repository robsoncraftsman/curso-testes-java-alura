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

	@Override
	public int hashCode() {
		final var prime = 31;
		var result = 1;
		result = (prime * result) + this.id;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final var other = (Usuario) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Usuário [id=" + this.id + ", nome=" + this.nome + "]";
	}

}
