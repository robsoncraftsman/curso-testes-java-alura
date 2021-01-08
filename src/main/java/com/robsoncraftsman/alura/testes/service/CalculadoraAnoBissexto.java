package com.robsoncraftsman.alura.testes.service;

public class CalculadoraAnoBissexto {

	private CalculadoraAnoBissexto() {

	}

	public static boolean ehBissexto(final int ano) {
		return ((ano % 400) == 0) || (((ano % 4) == 0) && ((ano % 100) != 0));
	}

}
