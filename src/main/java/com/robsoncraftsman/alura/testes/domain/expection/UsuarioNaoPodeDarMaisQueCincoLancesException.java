package com.robsoncraftsman.alura.testes.domain.expection;

import com.robsoncraftsman.alura.testes.domain.Usuario;

public class UsuarioNaoPodeDarMaisQueCincoLancesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoPodeDarMaisQueCincoLancesException(final Usuario usuario) {
		super(String.format("%s não pode dar mais que cinco lances", usuario));
	}

}
