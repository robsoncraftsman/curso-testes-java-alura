package com.robsoncrafstman.testes.java.domain.expection;

import com.robsoncrafstman.testes.java.domain.Usuario;

public class UsuarioNaoPodeDarMaisQueCincoLancesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoPodeDarMaisQueCincoLancesException(final Usuario usuario) {
		super(String.format("%s não pode dar mais que cinco lances", usuario));
	}

}
