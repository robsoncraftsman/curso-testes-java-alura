package com.robsoncraftsman.alura.testes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.robsoncraftsman.alura.testes.domain.Lance;
import com.robsoncraftsman.alura.testes.domain.Usuario;

public class FiltroDeLancesTest {

	@Test
	public void deveSelecionarLancesMaioresQuer500EMenoresQue700() {
		final var joao = new Usuario(1, "João");

		final var filtro = new FiltroDeLances();
		final var resultado = filtro.filtra(Arrays.asList(
			new Lance(joao, 600),
			new Lance(joao, 500),
			new Lance(joao, 700),
			new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(600, resultado.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveSelecionarLancesMaioresQue1000EMenoresQue3000() {
		final var joao = new Usuario(1, "João");

		final var filtro = new FiltroDeLances();
		final var resultado = filtro.filtra(Arrays.asList(
			new Lance(joao, 2000),
			new Lance(joao, 1000),
			new Lance(joao, 3000),
			new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(2000, resultado.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveSelecionarLancesMaioresQue5000() {
		final var joao = new Usuario(1, "João");

		final var filtro = new FiltroDeLances();
		final var resultado = filtro.filtra(Arrays.asList(
			new Lance(joao, 1000),
			new Lance(joao, 3000),
			new Lance(joao, 5000),
			new Lance(joao, 6000)));

		assertEquals(1, resultado.size());
		assertEquals(6000, resultado.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveEliminarMenoresQue500() {
		final var joao = new Usuario(1, "João");

		final var filtro = new FiltroDeLances();
		final var resultado = filtro.filtra(Arrays.asList(
			new Lance(joao, 0),
			new Lance(joao, 500)));

		assertEquals(0, resultado.size());
	}

	@Test
	public void deveEliminarEntre700E1000() {
		final var joao = new Usuario(1, "João");

		final var filtro = new FiltroDeLances();
		final var resultado = filtro.filtra(Arrays.asList(
			new Lance(joao, 700),
			new Lance(joao, 800),
			new Lance(joao, 900),
			new Lance(joao, 1000)));

		assertEquals(0, resultado.size());
	}

	@Test
	public void deveEliminarEntre3000E5000() {
		final var joao = new Usuario(1, "João");

		final var filtro = new FiltroDeLances();
		final var resultado = filtro.filtra(Arrays.asList(
			new Lance(joao, 3000),
			new Lance(joao, 4000),
			new Lance(joao, 5000)));

		assertEquals(0, resultado.size());
	}

}
