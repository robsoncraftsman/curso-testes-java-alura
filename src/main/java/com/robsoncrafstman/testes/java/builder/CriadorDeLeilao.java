package com.robsoncrafstman.testes.java.builder;

import com.robsoncrafstman.testes.java.domain.Lance;
import com.robsoncrafstman.testes.java.domain.Leilao;
import com.robsoncrafstman.testes.java.domain.Usuario;

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
