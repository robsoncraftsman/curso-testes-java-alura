package com.robsoncraftsman.alura.testes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatematicaMalucaTest {

	public static final int MENOS_TRINTA = -30;
	public static final int MENOS_DEZ = -10;
	public static final int ZERO = 0;
	public static final int CINCO = 5;
	public static final int DEZ = 10;
	public static final int ONZE = 11;
	public static final int VINTE = 20;
	public static final int TRINTA = 30;
	public static final int TRINTA_E_UM = 31;
	public static final int MIL = 1000;

	@Test
	void deveMultiplicarPorDoisNumerosMenoresIguaisADez() {
		final var matematicaMaluca = new MatematicaMaluca();
		final var multiplicador = 2;
		Assertions.assertEquals(DEZ * multiplicador, matematicaMaluca.contaMaluca(DEZ));
		Assertions.assertEquals(CINCO * multiplicador, matematicaMaluca.contaMaluca(CINCO));
		Assertions.assertEquals(ZERO, matematicaMaluca.contaMaluca(ZERO));
		Assertions.assertEquals(MENOS_DEZ * multiplicador, matematicaMaluca.contaMaluca(MENOS_DEZ));
		Assertions.assertEquals(MENOS_TRINTA * multiplicador, matematicaMaluca.contaMaluca(MENOS_TRINTA));
	}

	@Test
	void deveMultiplicarPorTresNumerosMaioresQueDezEMenoresIguaisATrinta() {
		final var matematicaMaluca = new MatematicaMaluca();
		final var multiplicador = 3;
		Assertions.assertEquals(ONZE * multiplicador, matematicaMaluca.contaMaluca(ONZE));
		Assertions.assertEquals(VINTE * multiplicador, matematicaMaluca.contaMaluca(VINTE));
		Assertions.assertEquals(TRINTA * multiplicador, matematicaMaluca.contaMaluca(TRINTA));
	}

	@Test
	void deveMultiplicarPorQuatroNumerosMaioresQueTrinta() {
		final var matematicaMaluca = new MatematicaMaluca();
		final var multiplicador = 4;
		Assertions.assertEquals(TRINTA_E_UM * multiplicador, matematicaMaluca.contaMaluca(TRINTA_E_UM));
		Assertions.assertEquals(MIL * multiplicador, matematicaMaluca.contaMaluca(MIL));
	}

}
