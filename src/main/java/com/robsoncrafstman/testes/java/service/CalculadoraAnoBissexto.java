package com.robsoncrafstman.testes.java.service;

public class CalculadoraAnoBissexto {

	public static boolean ehBissexto(final int ano) {
		return ((ano % 400) == 0) || (((ano % 4) == 0) && ((ano % 100) != 0));
	}

}
