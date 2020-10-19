package com.robsoncraftsman.alura.testes.domain.expection;

import com.robsoncraftsman.alura.testes.domain.Usuario;

public class UsuarioNaoPodeDarDoisLancesSeguidosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoPodeDarDoisLancesSeguidosException(final Usuario usuario) {
		super(String.format("%s não pode dar dois lances seguidos", usuario));
	}

}
