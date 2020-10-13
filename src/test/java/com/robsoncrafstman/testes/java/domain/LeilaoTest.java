package com.robsoncrafstman.testes.java.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.robsoncrafstman.testes.java.domain.expection.UsuarioNaoOfereceuLanceAnteriorParaDobrarValor;
import com.robsoncrafstman.testes.java.domain.expection.UsuarioNaoPodeDarDoisLancesSeguidosException;
import com.robsoncrafstman.testes.java.domain.expection.UsuarioNaoPodeDarMaisQueCincoLancesException;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		final var leilao = new Leilao("Carro Novo");
		assertEquals(0, leilao.getLances().size());

		leilao.lance(new Lance(new Usuario(1, "João"), 200));
		assertEquals(1, leilao.getLances().size());
		assertEquals(200, leilao.getLances().get(0).getValor());
	}

	@Test
	public void deveReceberDoisLances() {
		final var leilao = new Leilao("Carro Novo");
		assertEquals(0, leilao.getLances().size());

		leilao.lance(new Lance(new Usuario(1, "João"), 200));
		leilao.lance(new Lance(new Usuario(2, "Maria"), 300));
		assertEquals(2, leilao.getLances().size());
		assertEquals(200, leilao.getLances().get(0).getValor());
		assertEquals(300, leilao.getLances().get(1).getValor());
	}

	@Test
	public void naoDevePermitirDoisLancesComMesmoValor() {
		final var joao = new Usuario(1, "João");
		final var maria = new Usuario(2, "Maria");
		final var leilao = new Leilao("Carro Novo");

		final var valor = 2000d;
		leilao.lance(new Lance(joao, valor));

		final var throwable = assertThrows(IllegalArgumentException.class, () -> {
			leilao.lance(new Lance(maria, valor));
		});

		assertEquals(String.format("Lance no valor de '%f' já foi ofertado", valor), throwable.getMessage());
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		final var leilao = new Leilao("Carro Novo");
		final var joao = new Usuario(1, "João");
		leilao.lance(new Lance(joao, 200));

		final var resultException = assertThrows(UsuarioNaoPodeDarDoisLancesSeguidosException.class, () -> {
			leilao.lance(new Lance(joao, 300));
		});

		final var expectedException = new UsuarioNaoPodeDarDoisLancesSeguidosException(joao);
		assertEquals(expectedException.getMessage(), resultException.getMessage());
	}

	@Test
	public void naoDeveAceitarMaisQueCincoLancesDoMesmoUsuario() {
		final var leilao = new Leilao("Carro Novo");
		final var joao = new Usuario(1, "João");
		final var maria = new Usuario(2, "Maria");

		leilao.lance(new Lance(joao, 200));
		leilao.lance(new Lance(maria, 300));
		leilao.lance(new Lance(joao, 400));
		leilao.lance(new Lance(maria, 500));
		leilao.lance(new Lance(joao, 600));
		leilao.lance(new Lance(maria, 700));
		leilao.lance(new Lance(joao, 800));
		leilao.lance(new Lance(maria, 900));
		leilao.lance(new Lance(joao, 1000));
		leilao.lance(new Lance(maria, 1100));

		final var resultException = assertThrows(UsuarioNaoPodeDarMaisQueCincoLancesException.class, () -> {
			leilao.lance(new Lance(joao, 1200));
		});

		final var expectedException = new UsuarioNaoPodeDarMaisQueCincoLancesException(joao);
		assertEquals(expectedException.getMessage(), resultException.getMessage());
		assertEquals(10, leilao.getLances().size());
	}

	@Test
	public void deveDobrarValorUltimoLanceUsuario() {
		final var joao = new Usuario(1, "João");
		final var maria = new Usuario(2, "Maria");

		final var leilao = new Leilao("Carro Novo");
		leilao.lance(new Lance(joao, 100));
		leilao.lance(new Lance(maria, 200));
		leilao.lance(new Lance(joao, 300));
		leilao.lance(new Lance(maria, 400));

		leilao.dobraLance(joao);
		leilao.dobraLance(maria);

		assertEquals(6, leilao.getLances().size());
		final var lanceValorEmDobroJoao = leilao.getLances().get(4);
		assertEquals(joao, lanceValorEmDobroJoao.getUsuario());
		assertEquals(600, lanceValorEmDobroJoao.getValor());
		final var lanceValorEmDobroMaria = leilao.getLances().get(5);
		assertEquals(maria, lanceValorEmDobroMaria.getUsuario());
		assertEquals(800, lanceValorEmDobroMaria.getValor());
	}

	@Test
	public void naoDeveDobrarLanceUsuarioSemLances() {
		final var joao = new Usuario(1, "João");
		final var leilao = new Leilao("Carro Novo");

		final var resultException = assertThrows(UsuarioNaoOfereceuLanceAnteriorParaDobrarValor.class, () -> {
			leilao.dobraLance(joao);
		});

		final var expectedException = new UsuarioNaoOfereceuLanceAnteriorParaDobrarValor(joao);
		assertEquals(expectedException.getMessage(), resultException.getMessage());
	}

}
