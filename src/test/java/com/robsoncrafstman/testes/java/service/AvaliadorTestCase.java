package com.robsoncrafstman.testes.java.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.robsoncrafstman.testes.java.domain.Lance;
import com.robsoncrafstman.testes.java.domain.Leilao;
import com.robsoncrafstman.testes.java.domain.Usuario;

public class AvaliadorTestCase {

	private Leilao newLeilaoSemLances() {
		return new Leilao("Vazio");
	}

	private Leilao newLeilaoComUmLance() {
		final var joao = new Usuario(1, "João");

		final var leilao = new Leilao("Carro novo");
		leilao.lance(new Lance(joao, 2700));

		return leilao;
	}

	private Leilao newLeilaoComLancesCrescentes() {
		final var joao = new Usuario(1, "João");
		final var maria = new Usuario(2, "Maria");
		final var pedro = new Usuario(3, "Pedro");

		final var leilao = new Leilao("Carro novo");
		leilao.lance(new Lance(joao, 2000));
		leilao.lance(new Lance(pedro, 3500));
		leilao.lance(new Lance(maria, 5000));
		leilao.lance(new Lance(joao, 5100));
		leilao.lance(new Lance(maria, 5200));

		return leilao;
	}

	private Leilao newLeilaoComLancesAleatorios() {
		final var joao = new Usuario(1, "João");
		final var maria = new Usuario(2, "Maria");
		final var pedro = new Usuario(3, "Pedro");

		final var leilao = new Leilao("Carro novo");
		leilao.lance(new Lance(pedro, 2500));
		leilao.lance(new Lance(maria, 4500));
		leilao.lance(new Lance(joao, 3000));
		leilao.lance(new Lance(maria, 4200));
		leilao.lance(new Lance(joao, 4100));

		return leilao;
	}

	private Leilao newLeilaoComLancesDecrescentes() {
		final var joao = new Usuario(1, "João");
		final var maria = new Usuario(2, "Maria");
		final var pedro = new Usuario(3, "Pedro");

		final var leilao = new Leilao("Carro novo");
		leilao.lance(new Lance(pedro, 3000));
		leilao.lance(new Lance(joao, 2500));
		leilao.lance(new Lance(maria, 2100));
		leilao.lance(new Lance(joao, 1500));
		leilao.lance(new Lance(maria, 1200));

		return leilao;
	}

	@Test
	public void naoDevePermitirDoisLancesComMesmoValor() {
		assertThrows(IllegalArgumentException.class, () -> {
			final var joao = new Usuario(1, "João");
			final var maria = new Usuario(2, "Maria");

			final var leilao = new Leilao("Carro novo");
			leilao.lance(new Lance(joao, 2000));
			leilao.lance(new Lance(maria, 2000));
		});
	}

	@Test
	public void deveRetornarSomaLances() {
		final var leilao = newLeilaoComLancesCrescentes();
		final var avaliador = new Avaliador();
		final var somaLances = avaliador.obterSomaDosLances(leilao);
		assertEquals(20800, somaLances, 0.00001);
	}

	@Test
	public void deveRetornaZeroNaSomaDosLancesComLeilaoSemLances() {
		final var leilao = newLeilaoSemLances();
		final var avaliador = new Avaliador();
		final var somaLances = avaliador.obterSomaDosLances(leilao);
		assertEquals(0, somaLances, 0.00001);
	}

	@Test
	public void deveRetornarMediaLances() {
		final var leilao = newLeilaoComLancesCrescentes();
		final var avaliador = new Avaliador();
		final var mediaLances = avaliador.obterMediaDosLances(leilao);
		assertEquals(4160, mediaLances, 0.00001);
	}

	@Test
	public void deveRetornarZeroNaMediaLancesComLeilaoSemLances() {
		final var leilao = newLeilaoSemLances();
		final var avaliador = new Avaliador();
		final var mediaLances = avaliador.obterMediaDosLances(leilao);
		assertEquals(0, mediaLances, 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesCrescentes() {
		final var leilao = newLeilaoComLancesCrescentes();
		final var avaliador = new Avaliador();
		final var menorLance = avaliador.obterMenorLance(leilao);
		assertEquals(2000, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesAleatorios() {
		final var leilao = newLeilaoComLancesAleatorios();
		final var avaliador = new Avaliador();
		final var menorLance = avaliador.obterMenorLance(leilao);
		assertEquals(2500, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesDecrescentes() {
		final var leilao = newLeilaoComLancesDecrescentes();
		final var avaliador = new Avaliador();
		final var menorLance = avaliador.obterMenorLance(leilao);
		assertEquals(1200, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComApenasUmLance() {
		final var leilao = newLeilaoComUmLance();
		final var avaliador = new Avaliador();
		final var menorLance = avaliador.obterMenorLance(leilao);
		assertEquals(2700, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarNullNoMenorLanceComLeilaoSemLances() {
		final var leilao = newLeilaoSemLances();
		final var avaliador = new Avaliador();
		final var menorLance = avaliador.obterMenorLance(leilao);
		assertNull(menorLance);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesCrescentes() {
		final var leilao = newLeilaoComLancesCrescentes();
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		assertEquals(5200, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesAleatorios() {
		final var leilao = newLeilaoComLancesAleatorios();
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		assertEquals(4500, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesDecrescentes() {
		final var leilao = newLeilaoComLancesDecrescentes();
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		assertEquals(3000, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComApenasUmLance() {
		final var leilao = newLeilaoComUmLance();
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		assertEquals(2700, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarNullNoMaiorLanceComLeilaoSemLances() {
		final var leilao = newLeilaoSemLances();
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		assertNull(maiorLance);
	}

	@Test
	public void deveRetornarTresMaioresLances() {
		final var leilao = newLeilaoComLancesCrescentes();
		final var avaliador = new Avaliador();
		final var maioresLances = avaliador.obterTresMaioresLances(leilao);
		assertEquals(3, maioresLances.size());
		assertEquals(5200, maioresLances.get(0).getValor(), 0.00001);
		assertEquals(5100, maioresLances.get(1).getValor(), 0.00001);
		assertEquals(5000, maioresLances.get(2).getValor(), 0.00001);
	}

	@Test
	public void devePedirTresMaioresLancesComApenasUmLanceERetornarApenasLanceExistente() {
		final var leilao = newLeilaoComUmLance();
		final var avaliador = new Avaliador();
		final var maioresLances = avaliador.obterTresMaioresLances(leilao);
		assertEquals(1, maioresLances.size());
		assertEquals(2700, maioresLances.get(0).getValor(), 0.00001);
	}

	@Test
	public void devePedirTresMaioresLancesComLeilaoSemLancesERetornarListaVazia() {
		final var leilao = newLeilaoSemLances();
		final var avaliador = new Avaliador();
		final var maioresLances = avaliador.obterTresMaioresLances(leilao);
		assertEquals(0, maioresLances.size());
	}

}
