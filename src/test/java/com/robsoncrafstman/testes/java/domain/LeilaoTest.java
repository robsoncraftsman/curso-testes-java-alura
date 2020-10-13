package com.robsoncrafstman.testes.java.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.robsoncrafstman.testes.java.domain.expection.UsuarioNaoOfereceuLanceAnteriorParaDobrarValor;
import com.robsoncrafstman.testes.java.domain.expection.UsuarioNaoPodeDarDoisLancesSeguidosException;
import com.robsoncrafstman.testes.java.domain.expection.UsuarioNaoPodeDarMaisQueCincoLancesException;

public class LeilaoTest {

	private final Usuario joao = new Usuario(1, "João");
	private final Usuario maria = new Usuario(2, "Maria");

	@Test
	public void deveReceberUmLance() {
		final var leilao = new Leilao("Carro Novo");
		assertEquals(0, leilao.getLances().size());

		leilao.lance(this.joao, 200);
		assertEquals(1, leilao.getLances().size());
		assertEquals(200, leilao.getLances().get(0).getValor());
	}

	@Test
	public void deveReceberDoisLances() {
		final var leilao = new Leilao("Carro Novo");
		assertEquals(0, leilao.getLances().size());

		leilao.lance(this.joao, 200);
		leilao.lance(this.maria, 300);
		assertEquals(2, leilao.getLances().size());
		assertEquals(200, leilao.getLances().get(0).getValor());
		assertEquals(300, leilao.getLances().get(1).getValor());
	}

	@Test
	public void naoDevePermitirDoisLancesComMesmoValor() {
		final var valor = 2000d;
		final var leilao = new Leilao("Carro Novo");
		leilao.lance(this.joao, valor);

		final var resultException = assertThrows(IllegalArgumentException.class, () -> {
			leilao.lance(this.maria, valor);
		});

		assertEquals(String.format("Lance no valor de '%f' já foi ofertado", valor), resultException.getMessage());
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		final var leilao = new Leilao("Carro Novo");
		leilao.lance(this.joao, 200);

		final var resultException = assertThrows(UsuarioNaoPodeDarDoisLancesSeguidosException.class, () -> {
			leilao.lance(this.joao, 300);
		});

		final var expectedException = new UsuarioNaoPodeDarDoisLancesSeguidosException(this.joao);
		assertEquals(expectedException.getMessage(), resultException.getMessage());
	}

	@Test
	public void naoDeveAceitarMaisQueCincoLancesDoMesmoUsuario() {
		final var leilao = new Leilao("Carro Novo");

		leilao.lance(this.joao, 200);
		leilao.lance(this.maria, 300);
		leilao.lance(this.joao, 400);
		leilao.lance(this.maria, 500);
		leilao.lance(this.joao, 600);
		leilao.lance(this.maria, 700);
		leilao.lance(this.joao, 800);
		leilao.lance(this.maria, 900);
		leilao.lance(this.joao, 1000);
		leilao.lance(this.maria, 1100);

		final var resultException = assertThrows(UsuarioNaoPodeDarMaisQueCincoLancesException.class, () -> {
			leilao.lance(this.joao, 1200);
		});

		final var expectedException = new UsuarioNaoPodeDarMaisQueCincoLancesException(this.joao);
		assertEquals(expectedException.getMessage(), resultException.getMessage());
		assertEquals(10, leilao.getLances().size());
	}

	@Test
	public void deveDobrarValorUltimoLanceUsuario() {
		final var leilao = new Leilao("Carro Novo");
		leilao.lance(this.joao, 100);
		leilao.lance(this.maria, 200);
		leilao.lance(this.joao, 300);
		leilao.lance(this.maria, 400);

		leilao.dobraLance(this.joao);
		leilao.dobraLance(this.maria);

		assertEquals(6, leilao.getLances().size());
		final var lanceValorEmDobroJoao = leilao.getLances().get(4);
		assertEquals(this.joao, lanceValorEmDobroJoao.getUsuario());
		assertEquals(600, lanceValorEmDobroJoao.getValor());
		final var lanceValorEmDobroMaria = leilao.getLances().get(5);
		assertEquals(this.maria, lanceValorEmDobroMaria.getUsuario());
		assertEquals(800, lanceValorEmDobroMaria.getValor());
	}

	@Test
	public void naoDeveDobrarLanceUsuarioSemLances() {
		final var leilao = new Leilao("Carro Novo");

		final var resultException = assertThrows(UsuarioNaoOfereceuLanceAnteriorParaDobrarValor.class, () -> {
			leilao.dobraLance(this.joao);
		});

		final var expectedException = new UsuarioNaoOfereceuLanceAnteriorParaDobrarValor(this.joao);
		assertEquals(expectedException.getMessage(), resultException.getMessage());
	}

	@Test
	public void naoDevePermitirLanceIgualZero() {
		final var leilao = new Leilao("Carro Novo");

		final var resultException = assertThrows(IllegalArgumentException.class, () -> {
			leilao.lance(this.joao, 0);
		});

		assertEquals("Valor do lance deve ser maior que zero", resultException.getMessage());
	}

	@Test
	public void naoDevePermitirLanceMenorZero() {
		final var leilao = new Leilao("Carro Novo");

		final var resultException = assertThrows(IllegalArgumentException.class, () -> {
			leilao.lance(this.joao, -1);
		});

		assertEquals("Valor do lance deve ser maior que zero", resultException.getMessage());
	}

}
