package com.robsoncraftsman.alura.testes.domain;

import java.util.Objects;

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
		return Objects.hash(id);
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
		return (this.id == other.id);
	}

	@Override
	public String toString() {
		return "Usuário [id=" + this.id + ", nome=" + this.nome + "]";
	}

}
