package com.robsoncrafstman.testes.java.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.robsoncrafstman.testes.java.domain.Lance;
import com.robsoncrafstman.testes.java.domain.Leilao;
import com.robsoncrafstman.testes.java.domain.Usuario;

public class AvaliadorTest {

	private Avaliador avaliador;
	private Leilao leilao;
	private Usuario joao;
	private Usuario maria;
	private Usuario pedro;

	@BeforeEach
	private void prepararDadosTeste() {
		this.avaliador = new Avaliador();
		this.leilao = new Leilao("Carro novo");
		this.joao = new Usuario(1, "João");
		this.maria = new Usuario(2, "Maria");
		this.pedro = new Usuario(3, "Pedro");

	}

	@AfterEach
	private void limparDadosTeste() {
		this.avaliador = null;
		this.leilao = null;
		this.joao = null;
		this.maria = null;
		this.pedro = null;
	}

	private void popularLeilaoComUmLance() {
		this.leilao.lance(new Lance(this.joao, 2700));
	}

	private void popularLeilaoComLancesCrescentes() {
		this.leilao.lance(new Lance(this.joao, 2000));
		this.leilao.lance(new Lance(this.pedro, 3500));
		this.leilao.lance(new Lance(this.maria, 5000));
		this.leilao.lance(new Lance(this.joao, 5100));
		this.leilao.lance(new Lance(this.maria, 5200));
	}

	private void popularLeilaoComLancesAleatorios() {
		this.leilao.lance(new Lance(this.pedro, 2500));
		this.leilao.lance(new Lance(this.maria, 4500));
		this.leilao.lance(new Lance(this.joao, 3000));
		this.leilao.lance(new Lance(this.maria, 4200));
		this.leilao.lance(new Lance(this.joao, 4100));
	}

	private void popularLeilaoComLancesDecrescentes() {
		this.leilao.lance(new Lance(this.pedro, 3000));
		this.leilao.lance(new Lance(this.joao, 2500));
		this.leilao.lance(new Lance(this.maria, 2100));
		this.leilao.lance(new Lance(this.joao, 1500));
		this.leilao.lance(new Lance(this.maria, 1200));
	}

	@Test
	public void deveRetornarSomaLances() {
		popularLeilaoComLancesCrescentes();
		final var somaLances = this.avaliador.obterSomaDosLances(this.leilao);
		assertEquals(20800, somaLances, 0.00001);
	}

	@Test
	public void deveRetornaZeroNaSomaDosLancesComLeilaoSemLances() {
		final var somaLances = this.avaliador.obterSomaDosLances(this.leilao);
		assertEquals(0, somaLances, 0.00001);
	}

	@Test
	public void deveRetornarMediaLances() {
		popularLeilaoComLancesCrescentes();
		final var mediaLances = this.avaliador.obterMediaDosLances(this.leilao);
		assertEquals(4160, mediaLances, 0.00001);
	}

	@Test
	public void deveRetornarZeroNaMediaLancesComLeilaoSemLances() {
		final var mediaLances = this.avaliador.obterMediaDosLances(this.leilao);
		assertEquals(0, mediaLances, 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesCrescentes() {
		popularLeilaoComLancesCrescentes();
		final var menorLance = this.avaliador.obterMenorLance(this.leilao);
		assertEquals(2000, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesAleatorios() {
		popularLeilaoComLancesAleatorios();
		final var menorLance = this.avaliador.obterMenorLance(this.leilao);
		assertEquals(2500, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComLancesDecrescentes() {
		popularLeilaoComLancesDecrescentes();
		final var menorLance = this.avaliador.obterMenorLance(this.leilao);
		assertEquals(1200, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMenorLanceComApenasUmLance() {
		popularLeilaoComUmLance();
		final var menorLance = this.avaliador.obterMenorLance(this.leilao);
		assertEquals(2700, menorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarNullNoMenorLanceComLeilaoSemLances() {
		final var menorLance = this.avaliador.obterMenorLance(this.leilao);
		assertNull(menorLance);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesCrescentes() {
		popularLeilaoComLancesCrescentes();
		final var maiorLance = this.avaliador.obterMaiorLance(this.leilao);
		assertEquals(5200, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesAleatorios() {
		popularLeilaoComLancesAleatorios();
		final var maiorLance = this.avaliador.obterMaiorLance(this.leilao);
		assertEquals(4500, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComLancesDecrescentes() {
		popularLeilaoComLancesDecrescentes();
		final var maiorLance = this.avaliador.obterMaiorLance(this.leilao);
		assertEquals(3000, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarMaiorLanceComApenasUmLance() {
		popularLeilaoComUmLance();
		final var maiorLance = this.avaliador.obterMaiorLance(this.leilao);
		assertEquals(2700, maiorLance.getValor(), 0.00001);
	}

	@Test
	public void deveRetornarNullNoMaiorLanceComLeilaoSemLances() {
		final var avaliador = new Avaliador();
		final var maiorLance = avaliador.obterMaiorLance(this.leilao);
		assertNull(maiorLance);
	}

	@Test
	public void deveRetornarTresMaioresLances() {
		popularLeilaoComLancesCrescentes();
		final var maioresLances = this.avaliador.obterTresMaioresLances(this.leilao);
		assertEquals(3, maioresLances.size());
		assertEquals(5200, maioresLances.get(0).getValor(), 0.00001);
		assertEquals(5100, maioresLances.get(1).getValor(), 0.00001);
		assertEquals(5000, maioresLances.get(2).getValor(), 0.00001);
	}

	@Test
	public void devePedirTresMaioresLancesComApenasUmLanceERetornarApenasLanceExistente() {
		popularLeilaoComUmLance();
		final var maioresLances = this.avaliador.obterTresMaioresLances(this.leilao);
		assertEquals(1, maioresLances.size());
		assertEquals(2700, maioresLances.get(0).getValor(), 0.00001);
	}

	@Test
	public void devePedirTresMaioresLancesComLeilaoSemLancesERetornarListaVazia() {
		final var maioresLances = this.avaliador.obterTresMaioresLances(this.leilao);
		assertEquals(0, maioresLances.size());
	}

}
