package com.robsoncrafstman.testes.java.service;

import java.util.Comparator;

import com.robsoncrafstman.testes.java.domain.Lance;
import com.robsoncrafstman.testes.java.domain.Leilao;

public class Avaliador {

	public double obterSomaDosLances(final Leilao leilao) {
		return leilao.getLances().stream().mapToDouble(Lance::getValor).sum();
	}

	public double obterMediaDosLances(final Leilao leilao) {
		return leilao.getLances().stream().mapToDouble(Lance::getValor).average().orElse(0);
	}

	public Lance obterMenorLance(final Leilao leilao) {
		final var menorLance = leilao.getLances()
				.stream()
				.min(Comparator.comparing(Lance::getValor))
				.orElse(null);
		return menorLance;
	}

	public Lance obterMaiorLance(final Leilao leilao) {
		final var menorLance = leilao.getLances()
				.stream()
				.max(Comparator.comparing(Lance::getValor))
				.orElse(null);
		return menorLance;
	}

}
