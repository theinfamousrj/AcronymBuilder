package edu.westga.cs3152.ab.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.westga.cs3152.ab.controller.AcronymFactory;

/**
 * @author RJ Hill
 *
 */
public class AcronymFactoryTest {
	
	AcronymFactory factory;

	@Test
	public void shouldContain4WordsWhenInstantiatedWith4Words() {
		setup();
		this.factory.buildAcronyms();
		assertTrue(this.factory.countWords()==4);
	}
	
	@Test
	public void shouldContain972SynonymsWhenInstantiatedWith4Words() {
		setup();
		this.factory.buildAcronyms();
		assertTrue(this.factory.countTotal()==972);
	}
	
	public void setup() {
		String words = "generate passion mathematics science";
		this.factory = new AcronymFactory(words);
	}

}
