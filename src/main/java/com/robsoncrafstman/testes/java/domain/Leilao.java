package com.robsoncrafstman.testes.java.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leilao {

	private final String descricao;
	private final List<Lance> lances;
	private final Map<Double, Lance> valoresLances;

	public Leilao(final String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<>();
		this.valoresLances = new HashMap<>();
	}

	public void lance(final Lance lance) {
		if (this.valoresLances.containsKey(lance.getValor())) {
			throw new IllegalArgumentException("Valor do lance já foi ofertado");
		}
		this.lances.add(lance);
		this.valoresLances.put(lance.getValor(), lance);
	}

	public String getDescricao() {
		return this.descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(this.lances);
	}

}
