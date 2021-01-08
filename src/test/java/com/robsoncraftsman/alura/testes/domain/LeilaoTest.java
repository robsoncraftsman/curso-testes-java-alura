package com.robsoncraftsman.alura.testes.domain;

import static com.robsoncraftsman.alura.testes.matcher.LeilaoMatcher.temLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.robsoncraftsman.alura.testes.domain.expection.UsuarioNaoOfereceuLanceAnteriorParaDobrarValor;
import com.robsoncraftsman.alura.testes.domain.expection.UsuarioNaoPodeDarDoisLancesSeguidosException;
import com.robsoncraftsman.alura.testes.domain.expection.UsuarioNaoPodeDarMaisQueCincoLancesException;

class LeilaoTest {

	private final Usuario joao = new Usuario(1, "João");
	private final Usuario maria = new Usuario(2, "Maria");

	@Test
	void deveReceberUmLance() {
		final var leilao = new Leilao("Carro Novo");
		assertThat(leilao.getLances().size(), is(0));

		final var lance = new Lance(this.joao, 200);
		leilao.lance(lance);

		assertThat(leilao.getLances().size(), is(1));
		assertThat(leilao, temLance(lance));
	}

	@Test
	void deveReceberDoisLances() {
		final var leilao = new Leilao("Carro Novo");
		final var lanceJoao = new Lance(this.joao, 200);
		final var lanceMaria = new Lance(this.maria, 300);
		leilao.lance(lanceJoao);
		leilao.lance(lanceMaria);
		assertThat(leilao.getLances(), hasSize(2));
		assertThat(leilao, temLance(lanceJoao));
		assertThat(leilao, temLance(lanceMaria));
	}

	@Test
	void naoDevePermitirDoisLancesComMesmoValor() {
		final var valor = 2000d;
		final var leilao = new Leilao("Carro Novo");
		leilao.lance(this.joao, valor);

		final var resultException = assertThrows(IllegalArgumentException.class, () -> {
			leilao.lance(this.maria, valor);
		});

		assertThat(resultException.getMessage(), is(String.format("Lance no valor de '%f' já foi ofertado", valor)));
	}

	@Test
	void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		final var leilao = new Leilao("Carro Novo");
		leilao.lance(this.joao, 200);

		final var resultException = assertThrows(UsuarioNaoPodeDarDoisLancesSeguidosException.class, () -> {
			leilao.lance(this.joao, 300);
		});

		final var expectedException = new UsuarioNaoPodeDarDoisLancesSeguidosException(this.joao);
		assertThat(resultException.getMessage(), is(expectedException.getMessage()));
	}

	@Test
	void naoDeveAceitarMaisQueCincoLancesDoMesmoUsuario() {
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
		assertThat(resultException.getMessage(), is(expectedException.getMessage()));
		assertThat(leilao.getLances(), hasSize(10));
	}

	@Test
	void deveDobrarValorUltimoLanceUsuario() {
		final var leilao = new Leilao("Carro Novo");
		leilao.lance(this.joao, 100);
		leilao.lance(this.maria, 200);
		leilao.lance(this.joao, 300);
		leilao.lance(this.maria, 400);

		leilao.dobraLance(this.joao);
		leilao.dobraLance(this.maria);

		assertThat(leilao.getLances().size(), is(6));
		final var lanceValorEmDobroJoao = leilao.getLances().get(4);
		assertThat(lanceValorEmDobroJoao.getUsuario(), sameInstance(this.joao));
		assertThat(lanceValorEmDobroJoao.getValor(), is(600d));
		final var lanceValorEmDobroMaria = leilao.getLances().get(5);
		assertThat(lanceValorEmDobroMaria.getUsuario(), sameInstance(this.maria));
		assertThat(lanceValorEmDobroMaria.getValor(), is(800d));
	}

	@Test
	void naoDeveDobrarLanceUsuarioSemLances() {
		final var leilao = new Leilao("Carro Novo");

		final var resultException = assertThrows(UsuarioNaoOfereceuLanceAnteriorParaDobrarValor.class, () -> {
			leilao.dobraLance(this.joao);
		});

		final var expectedException = new UsuarioNaoOfereceuLanceAnteriorParaDobrarValor(this.joao);
		assertThat(resultException.getMessage(), is(expectedException.getMessage()));
	}

	@Test
	void naoDevePermitirLanceIgualZero() {
		final var leilao = new Leilao("Carro Novo");

		final var resultException = assertThrows(IllegalArgumentException.class, () -> {
			leilao.lance(this.joao, 0);
		});

		assertThat(resultException.getMessage(), is("Valor do lance deve ser maior que zero"));
	}

	@Test
	void naoDevePermitirLanceMenorZero() {
		final var leilao = new Leilao("Carro Novo");

		final var resultException = assertThrows(IllegalArgumentException.class, () -> {
			leilao.lance(this.joao, -1);
		});

		assertThat(resultException.getMessage(), is("Valor do lance deve ser maior que zero"));
	}

}
