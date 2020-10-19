package com.robsoncraftsman.alura.testes.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.robsoncraftsman.alura.testes.domain.Lance;
import com.robsoncraftsman.alura.testes.domain.Leilao;

public class Avaliador {

	public double obterSomaDosLances(final Leilao leilao) {
		return leilao.getLances().stream().mapToDouble(Lance::getValor).sum();
	}

	public double obterMediaDosLances(final Leilao leilao) {
		return leilao.getLances().stream().mapToDouble(Lance::getValor).average().orElse(0);
	}

	public Lance obterMenorLance(final Leilao leilao) {
		return leilao.getLances()
				.stream()
				.min(Comparator.comparing(Lance::getValor))
				.orElse(null);
	}

	public Lance obterMaiorLance(final Leilao leilao) {
		return leilao.getLances()
				.stream()
				.max(Comparator.comparing(Lance::getValor))
				.orElse(null);
	}

	public List<Lance> obterTresMaioresLances(final Leilao leilao) {
		return leilao.getLances()
				.stream()
				.sorted(Comparator.comparing(Lance::getValor).reversed())
				.limit(3)
				.collect(Collectors.toCollection(ArrayList::new));
	}

}
