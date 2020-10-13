package com.robsoncrafstman.testes.java.domain.expection;

import com.robsoncrafstman.testes.java.domain.Usuario;

public class UsuarioNaoPodeDarDoisLancesSeguidosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoPodeDarDoisLancesSeguidosException(final Usuario usuario) {
		super(String.format("%s não pode dar dois lances seguidos", usuario));
	}

}
