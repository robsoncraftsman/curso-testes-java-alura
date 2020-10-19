package com.robsoncraftsman.alura.testes.domain.expection;

import com.robsoncraftsman.alura.testes.domain.Usuario;

public class UsuarioNaoOfereceuLanceAnteriorParaDobrarValor extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoOfereceuLanceAnteriorParaDobrarValor(final Usuario usuario) {
		super(String.format("%s não ofereceu lance anterior para dobrar valor", usuario));
	}

}
