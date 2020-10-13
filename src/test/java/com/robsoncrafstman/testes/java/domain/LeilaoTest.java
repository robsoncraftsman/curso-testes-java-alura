package com.robsoncrafstman.testes.java.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		final var leilao = new Leilao("Leilão com um lance");
		assertEquals(0, leilao.getLances().size());

		leilao.lance(new Lance(new Usuario(1, "João"), 200));
		assertEquals(1, leilao.getLances().size());
		assertEquals(200, leilao.getLances().get(0).getValor());
	}

	@Test
	public void deveReceberDoisLances() {
		final var leilao = new Leilao("Leilão com um lance");
		assertEquals(0, leilao.getLances().size());

		leilao.lance(new Lance(new Usuario(1, "João"), 200));
		leilao.lance(new Lance(new Usuario(2, "Maria"), 300));
		assertEquals(2, leilao.getLances().size());
		assertEquals(200, leilao.getLances().get(0).getValor());
		assertEquals(300, leilao.getLances().get(1).getValor());
	}

}
