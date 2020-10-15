package com.robsoncraftsman.alura.java.testes.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robsoncraftsman.alura.java.testes.domain.expection.UsuarioNaoOfereceuLanceAnteriorParaDobrarValor;
import com.robsoncraftsman.alura.java.testes.domain.expection.UsuarioNaoPodeDarDoisLancesSeguidosException;
import com.robsoncraftsman.alura.java.testes.domain.expection.UsuarioNaoPodeDarMaisQueCincoLancesException;

public class Leilao {

	private final String descricao;
	private final List<Lance> lances;
	private final Map<Double, Lance> valoresLances;

	public Leilao(final String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<>();
		this.valoresLances = new HashMap<>();
	}

	private long countNroLances(final Usuario usuario) {
		return getLances().stream().filter(l -> l.getUsuario().equals(usuario)).count();
	}

	private Lance getUltimoLance() {
		final var nroLances = this.lances.size();
		if (nroLances > 0) {
			return this.lances.get(nroLances - 1);
		} else {
			return null;
		}
	}

	private Lance getMarorLanceUsuario(final Usuario usuario) {
		return getLances().stream()
				.filter(l -> l.getUsuario().equals(usuario))
				.max(Comparator.comparing(Lance::getValor))
				.orElse(null);
	}

	private void validaLanceValorDuplicado(final Lance lance) {
		final var valor = lance.getValor();
		if (this.valoresLances.containsKey(valor)) {
			throw new IllegalArgumentException(String.format("Lance no valor de '%f' já foi ofertado", valor));
		}
	}

	private void validaUsuarioDiferenteUltimoLance(final Lance lance) {
		final var ultimoLance = getUltimoLance();
		final var usuario = lance.getUsuario();
		if ((ultimoLance != null) && ultimoLance.getUsuario().equals(usuario)) {
			throw new UsuarioNaoPodeDarDoisLancesSeguidosException(usuario);
		}
	}

	private void validaSeUsuarioJaOfereceuCincoLances(final Lance lance) {
		final var usuario = lance.getUsuario();
		if (countNroLances(usuario) >= 5) {
			throw new UsuarioNaoPodeDarMaisQueCincoLancesException(usuario);
		}
	}

	private void validaValorLance(final Lance lance) {
		if (lance.getValor() <= 0) {
			throw new IllegalArgumentException("Valor do lance deve ser maior que zero");
		}
	}

	public void lance(final Lance lance) {
		validaValorLance(lance);
		validaLanceValorDuplicado(lance);
		validaUsuarioDiferenteUltimoLance(lance);
		validaSeUsuarioJaOfereceuCincoLances(lance);
		this.lances.add(lance);
		this.valoresLances.put(lance.getValor(), lance);
	}

	public void lance(final Usuario usuario, final double valor) {
		lance(new Lance(usuario, valor));
	}

	public void dobraLance(final Usuario usuario) {
		final var maiorLanceUsuario = getMarorLanceUsuario(usuario);
		if (maiorLanceUsuario != null) {
			final var lanceValorEmDobro = new Lance(maiorLanceUsuario.getUsuario(), maiorLanceUsuario.getValor() * 2);
			lance(lanceValorEmDobro);
		} else {
			throw new UsuarioNaoOfereceuLanceAnteriorParaDobrarValor(usuario);
		}
	}

	public String getDescricao() {
		return this.descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(this.lances);
	}

}
