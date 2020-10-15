package com.robsoncraftsman.alura.java.testes.builder;

import com.robsoncraftsman.alura.java.testes.domain.Lance;
import com.robsoncraftsman.alura.java.testes.domain.Leilao;
import com.robsoncraftsman.alura.java.testes.domain.Usuario;

public class CriadorDeLeilao {

	private Leilao leilao;

	public CriadorDeLeilao() {
	}

	public CriadorDeLeilao para(final String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}

	public CriadorDeLeilao lance(final Usuario usuario, final double valor) {
		this.leilao.lance(new Lance(usuario, valor));
		return this;
	}

	public Leilao constroi() {
		return this.leilao;
	}

}
