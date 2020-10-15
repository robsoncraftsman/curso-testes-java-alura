package com.robsoncraftsman.alura.java.testes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.robsoncraftsman.alura.java.testes.builder.CriadorDeLeilao;
import com.robsoncraftsman.alura.java.testes.domain.Leilao;
import com.robsoncraftsman.alura.java.testes.domain.Usuario;

public class AvaliadorTest {

	private Avaliador avaliador;
	private Usuario joao;
	private Usuario maria;
	private Usuario pedro;

	@BeforeAll
	public static void testandoBeforeClass() {
		System.out.println("before all");
	}

	@AfterAll
	public static void testandoAfterClass() {
		System.out.println("after all");
	}

	@BeforeEach
	private void prepararDadosTeste() {
		this.avaliador = new Avaliador();
		this.joao = new Usuario(1, "João");
		this.maria = new Usuario(2, "Maria");
		this.pedro = new Usuario(3, "Pedro");

	}

	@AfterEach
	private void limparDadosTeste() {
		this.avaliador = null;
		this.joao = null;
		this.maria = null;
		this.pedro = null;
	}

	private Leilao criarLeilaoSemLances() {
		return new CriadorDeLeilao().para("Carro Novo").constroi();
	}

	private Leilao criarLeilaoComUmLance() {
		return new CriadorDeLeilao().para("Carro Novo")
				.lance(this.joao, 2700)
				.constroi();
	}

	private Leilao criarLeilaoComLancesCrescentes() {
		return new CriadorDeLeilao().para("Carro Novo")
				.lance(this.joao, 2000)
				.lance(this.pedro, 3500)
				.lance(this.maria, 5000)
				.lance(this.joao, 5100)
				.lance(this.maria, 5200)
				.constroi();
	}

	private Leilao criarLeilaoComLancesAleatorios() {
		return new CriadorDeLeilao().para("Carro Novo")
				.lance(this.pedro, 2500)
				.lance(this.maria, 4500)
				.lance(this.joao, 3000)
				.lance(this.maria, 4200)
				.lance(this.joao, 4100)
				.constroi();
	}

	private Leilao criarLeilaoComLancesDecrescentes() {
		return new CriadorDeLeilao().para("Carro Novo")
				.lance(this.pedro, 3000)
				.lance(this.joao, 2500)
				.lance(this.maria, 2100)
				.lance(this.joao, 1500)
				.lance(this.maria, 1200)
				.constroi();
	}

	@Test
	public void deveRetornarSomaLances() {
		final var leilao = criarLeilaoComLancesCrescentes();
		final var somaLances = this.avaliador.obterSomaDosLances(leilao);
		assertEquals(20800, somaLances, 0.00001);
	}

	@Test
	public void deveRetornaZeroNaSomaDosLancesComLeilaoSemLances() {
		final var leilao = criarLeilaoSemLances();
		final var somaLances = this.avaliador.obterSomaDosLances(leilao);
		assertEquals(0, somaLances, 0.00001);
	}

	@Test
	public void deveRetornarMediaLances() {
		final var leilao = criarLeilaoComLancesCrescentes();
		final var mediaLances = this.avaliador.obterMediaDosLances(leilao);
		assertEquals(4160, mediaLances, 0.00001);
	}

	@Test
	public void deveRetornarZeroNaMediaLancesComLeilaoSemLances() {
		final var leilao = criarLeilaoSemLances();
		final var mediaLances = this.avaliador.obterMediaDosLances(leilao);
		assertEquals(0, mediaLances, 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesCrescentes() {
		final var leilao = criarLeilaoComLancesCrescentes();
		final var menorLance = this.avaliador.obterMenorLance(leilao);
		assertEquals(2000, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesAleatorios() {
		final var leilao = criarLeilaoComLancesAleatorios();
		final var menorLance = this.avaliador.obterMenorLance(leilao);
		assertEquals(2500, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesDecrescentes() {
		final var leilao = criarLeilaoComLancesDecrescentes();
		final var menorLance = this.avaliador.obterMenorLance(leilao);
		assertEquals(1200, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComApenasUmLance() {
		final var leilao = criarLeilaoComUmLance();
		final var menorLance = this.avaliador.obterMenorLance(leilao);
		assertEquals(2700, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarNullNoMenorLanceComLeilaoSemLances() {
		final var leilao = criarLeilaoSemLances();
		final var menorLance = this.avaliador.obterMenorLance(leilao);
		assertNull(menorLance);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesCrescentes() {
		final var leilao = criarLeilaoComLancesCrescentes();
		final var maiorLance = this.avaliador.obterMaiorLance(leilao);
		assertEquals(5200, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesAleatorios() {
		final var leilao = criarLeilaoComLancesAleatorios();
		final var maiorLance = this.avaliador.obterMaiorLance(leilao);
		assertEquals(4500, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesDecrescentes() {
		final var leilao = criarLeilaoComLancesDecrescentes();
		final var maiorLance = this.avaliador.obterMaiorLance(leilao);
		assertEquals(3000, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComApenasUmLance() {
		final var leilao = criarLeilaoComUmLance();
		final var maiorLance = this.avaliador.obterMaiorLance(leilao);
		assertEquals(2700, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarNullNoMaiorLanceComLeilaoSemLances() {
		final var leilao = criarLeilaoSemLances();
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(leilao);
		assertNull(maiorLance);
	}

	@Test
	public void deveRetornarTresMaioresLances() {
		final var leilao = criarLeilaoComLancesCrescentes();
		final var maioresLances = this.avaliador.obterTresMaioresLances(leilao);
		assertEquals(3, maioresLances.size());
		assertEquals(5200, maioresLances.get(0).getValor(), 0.00001);
		assertEquals(5100, maioresLances.get(1).getValor(), 0.00001);
		assertEquals(5000, maioresLances.get(2).getValor(), 0.00001);
	}

	@Test
	public void devePedirTresMaioresLancesComApenasUmLanceERetornarApenasLanceExistente() {
		final var leilao = criarLeilaoComUmLance();
		final var maioresLances = this.avaliador.obterTresMaioresLances(leilao);
		assertEquals(1, maioresLances.size());
		assertEquals(2700, maioresLances.get(0).getValor(), 0.00001);
	}

	@Test
	public void devePedirTresMaioresLancesComLeilaoSemLancesERetornarListaVazia() {
		final var leilao = criarLeilaoSemLances();
		final var maioresLances = this.avaliador.obterTresMaioresLances(leilao);
		assertEquals(0, maioresLances.size());
	}

}
