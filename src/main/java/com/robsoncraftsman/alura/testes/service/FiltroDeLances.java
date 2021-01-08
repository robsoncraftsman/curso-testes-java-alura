package com.robsoncraftsman.alura.testes.service;

import java.util.ArrayList;
import java.util.List;

import com.robsoncraftsman.alura.testes.domain.Lance;

public class FiltroDeLances {

	public List<Lance> filtra(final List<Lance> lances) {
		final var resultado = new ArrayList<Lance>();

		for (final Lance lance : lances) {
			if ((lance.getValor() > 500) && (lance.getValor() < 700)) {
				resultado.add(lance);
			} else if ((lance.getValor() > 1000) && (lance.getValor() < 3000)) {
				resultado.add(lance);
			} else if (lance.getValor() > 5000) {
				resultado.add(lance);
			}
		}

		return resultado;
	}
}
