package com.robsoncrafstman.testes.java.service;

public class MatematicaMaluca {

	public int contaMaluca(final int numero) {
		if (numero > 30) {
			return numero * 4;
		} else if (numero > 10) {
			return numero * 3;
		} else {
			return numero * 2;
		}
	}
}
