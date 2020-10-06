package com.robsoncrafstman.testes.java.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.robsoncrafstman.testes.java.domain.Lance;
import com.robsoncrafstman.testes.java.domain.Leilao;
import com.robsoncrafstman.testes.java.domain.Usuario;

public class AvaliadorTestCase {

	private Leilao createLeilaoTeste() {
		final var joao = new Usuario(1, "João");
		final var maria = new Usuario(2, "Maria");
		final var pedro = new Usuario(3, "Pedro");

		final var leilao = new Leilao("Carro novo");
		leilao.lance(new Lance(joao, 2000));
		leilao.lance(new Lance(maria, 5000));
		leilao.lance(new Lance(pedro, 3500));

		return leilao;
	}

	@Test
	public void naoDevePermitirDoisLancesComMesmoValor() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			final var joao = new Usuario(1, "João");
			final var maria = new Usuario(2, "Maria");

			final var leilao = new Leilao("Carro novo");
			leilao.lance(new Lance(joao, 2000));
			leilao.lance(new Lance(maria, 2000));
		});
	}

	@Test
	public void deveRetornarSomaLances() {
		final var leilao = createLeilaoTeste();
		final var avaliador = new Avaliador();
		final var somaLances = avaliador.obterSomaDosLances(leilao);
		final var somaLancesEsperado = 10500;
		Assertions.assertEquals(somaLancesEsperado, somaLances, 0.00001, "Deve retornar a soma dos lances");
	}

	@Test
	public void deveRetornaZeroNaSomaDosLances() {
		final var leilao = new Leilao("Vazio");
		final var avaliador = new Avaliador();
		final var somaLances = avaliador.obterSomaDosLances(leilao);
		final var somaLancesEsperado = 0;
		Assertions.assertEquals(somaLancesEsperado, somaLances, 0.00001, "Deve retornar zero na soma dos lances");
	}

	@Test
	public void deveRetornarMediaLances() {
		final var leilao = createLeilaoTeste();
		final var avaliador = new Avaliador();
		final var mediaLances = avaliador.obterMediaDosLances(leilao);
		final var mediaLancesEsperado = 3500;
		Assertions.assertEquals(mediaLancesEsperado, mediaLances, 0.00001, "Deve retornar a média dos lances");
	}

	@Test
	public void deveRetornarZeroNaMediaLances() {
		final var leilao = new Leilao("Vazio");
		final var avaliador = new Avaliador();
		final var mediaLances = avaliador.obterMediaDosLances(leilao);
		final var mediaLancesEsperado = 0;
		Assertions.assertEquals(mediaLancesEsperado, mediaLances, 0.00001, "Deve retornar zero na média dos lances");
	}

	@Test
	public void deveRetornarMenorLance() {
		final var leilao = createLeilaoTeste();
		final var avaliador = new Avaliador();
		final var menorLance = avaliador.obterMenorLance(leilao);
		final var menorValorEsperado = 2000;
		Assertions.assertEquals(menorValorEsperado, menorLance.getValor(), 0.00001, "Deve retornar o menor lance");
	}

	@Test
	public void deveRetornarNullNoMenorLance() {
		final var leilao = new Leilao("Vazio");
		final var avaliador = new Avaliador();
		final var menorLance = avaliador.obterMenorLance(leilao);
		Assertions.assertNull(menorLance, "Deve retornar null no menor lance");
	}

	@Test
	public void deveRetornarMaiorLance() {
		final var leilao = createLeilaoTeste();
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		final var maiorValorEsperado = 5000;
		Assertions.assertEquals(maiorValorEsperado, maiorLance.getValor(), 0.00001, "Deve retornar o maior lance");
	}

	@Test
	public void deveRetornarNullNoMaiorLance() {
		final var leilao = new Leilao("Vazio");
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		Assertions.assertNull(maiorLance, "Deve retornar null no maior lance");
	}

}
