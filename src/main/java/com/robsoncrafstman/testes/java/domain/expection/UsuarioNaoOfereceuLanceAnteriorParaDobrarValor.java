package com.robsoncrafstman.testes.java.domain.expection;

import com.robsoncrafstman.testes.java.domain.Usuario;

public class UsuarioNaoOfereceuLanceAnteriorParaDobrarValor extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoOfereceuLanceAnteriorParaDobrarValor(final Usuario usuario) {
		super(String.format("%s não ofereceu lance anterior para dobrar valor", usuario));
	}

}
