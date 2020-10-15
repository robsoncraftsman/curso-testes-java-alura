package com.robsoncraftsman.alura.java.testes.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.robsoncraftsman.alura.java.testes.domain.Lance;
import com.robsoncraftsman.alura.java.testes.domain.Leilao;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {

	private final Lance lance;

	public LeilaoMatcher(final Lance lance) {
		this.lance = lance;
	}

	@Override
	public void describeTo(final Description description) {
		description.appendText("Leilão com lance " + this.lance.getValor());
	}

	@Override
	protected boolean matchesSafely(final Leilao item) {
		return item.getLances().contains(this.lance);
	}

	public static Matcher<Leilao> temLance(final Lance lance) {
		return new LeilaoMatcher(lance);
	}
}
