package com.robsoncraftsman.alura.java.testes.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CalculadoraAnoBissextoTest {

	@Test
	public void deveRetornatTrueParaAnosBissextos() {
		assertTrue(CalculadoraAnoBissexto.ehBissexto(400));
		assertTrue(CalculadoraAnoBissexto.ehBissexto(800));
		assertTrue(CalculadoraAnoBissexto.ehBissexto(1996));
		assertTrue(CalculadoraAnoBissexto.ehBissexto(2000));
		assertTrue(CalculadoraAnoBissexto.ehBissexto(2020));
		assertTrue(CalculadoraAnoBissexto.ehBissexto(2400));
	}

	@Test
	public void deveRetornatFalseParaAnosNaoBissextos() {
		assertFalse(CalculadoraAnoBissexto.ehBissexto(100));
		assertFalse(CalculadoraAnoBissexto.ehBissexto(200));
		assertFalse(CalculadoraAnoBissexto.ehBissexto(1000));
		assertFalse(CalculadoraAnoBissexto.ehBissexto(2001));
		assertFalse(CalculadoraAnoBissexto.ehBissexto(2010));
		assertFalse(CalculadoraAnoBissexto.ehBissexto(2300));
	}

}
