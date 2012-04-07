package edu.westga.cs3152.ab.model;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import edu.westga.cs3152.ab.model.DictionaryBuilder;

/**
 * @author RJ Hill
 *
 */
public class DictionaryBuilderTest {


	ArrayList<String> synonyms;
	DictionaryBuilder builder;
	String word;
	
	//Testing add():
	
	@Test
	public void shouldNotContainAnyWordsWhenGivenNullWordList() {
		setup();
		builder.addWordWithSynonymList(word, synonyms);
		
		assertFalse(builder.contains(word));
	}
	
	@Test
	public void shouldContainCorrectWordAndSynonymsWhenGivenWordAndSynonymsAsAString() {
		setup();
		word = "aardvark";
		this.addSynonymsFromString("ant_bear");
		builder.addWordWithSynonymList(word, synonyms);
		
		assertTrue(builder.contains(word) && builder.getValues(word).equals(synonyms));
	}
	
	@Test
	public void shouldContainOnlyOneWordWhenAddMultipleEqualWords() {
		setup();
		word = "aardvark";
		this.addSynonymsFromString("ant_bear");
		builder.addWordWithSynonymList(word, synonyms);
		builder.addWordWithSynonymList(word, synonyms);
		builder.addWordWithSynonymList(word, synonyms);
		
		assertTrue(builder.contains(word) && builder.getSize() == 1 && builder.getValues(word).equals(synonyms));
	}
	
	//Testing getValues()
	
	@Test
	public void shouldHaveMultipleSynonymsForWordWhenAddedWithMultipleSynonyms() {
		setup();
		word = "aardvark";
		this.addSynonymsFromString("ant_bear");
		this.addSynonymsFromString("anteater");
		this.addSynonymsFromString("orycteropus_afer");
		builder.addWordWithSynonymList(word, synonyms);
		
		assertTrue(builder.contains(word) && builder.getValues(word).equals(synonyms));
	}
	
	//Testing contains()
	
	@Test
	public void shouldNotContainAnyWordsWhenGivenNullSynonyms() {
		setup();
		word = "aardvark";
		builder.addWordWithSynonymList(word, synonyms);
		
		assertFalse(builder.contains(word));
	}
	
	@Test
	public void shouldContainThreeWordsWhenAddThreeWords() {
		setup();
		ArrayList<String> synonyms2 = new ArrayList<String>();
		ArrayList<String> synonyms3 = new ArrayList<String>();
		
		word = "aardvark";
		String word2 = "abalone";
		String word3 = "abandoned";
		this.addSynonymsFromString("ant_bear");
		synonyms2.add("ear-shell");
		synonyms3.add("derelict");
		
		builder.addWordWithSynonymList(word, synonyms);
		builder.addWordWithSynonymList(word2, synonyms2);
		builder.addWordWithSynonymList(word3, synonyms3);
		
		assertTrue(builder.contains(word) && builder.contains(word2) && builder.contains(word3) && builder.getSize() == 3);
	}
	
	//Testing remove()
	
	@Test
	public void shouldRemoveCorrectWordAndSynonymsWhenGivenWordToRemove() {
		setup();
		
		ArrayList<String> synonyms2 = new ArrayList<String>();
		ArrayList<String> synonyms3 = new ArrayList<String>();
		
		word = "aardvark";
		String word2 = "abalone";
		String word3 = "abandoned";
		this.addSynonymsFromString("ant_bear");
		synonyms2.add("ear-shell");
		synonyms3.add("derelict");
		
		builder.addWordWithSynonymList(word, synonyms);
		builder.addWordWithSynonymList(word2, synonyms2);
		builder.addWordWithSynonymList(word3, synonyms3);
		
		boolean didContainWord = builder.contains(word2);
		builder.remove(word2);
		
		assertTrue(!builder.contains(word2) && didContainWord);
	}
	
	@Test
	public void shouldNotRemoveAnyWordWhenRemovingWordThatIsNotInDictionary() {
		setup();
		word = "aardvark";
		String word2 = "abalone";
		this.addSynonymsFromString("ant_bear");
		builder.addWordWithSynonymList(word, synonyms);
		builder.remove(word2);
		
		assertTrue(builder.contains(word) && builder.getSize() == 1);
	}
	
	//Testing file import
	
	@Test
	public void shouldHaveThreeUserIdsEachWithCorrectUserRoles() {
		File theFile = new File("./synonyms.txt");
		builder = new DictionaryBuilder(theFile);
		
		boolean containsFirstAndRandomAndLastWord = builder.contains("aardvark") && builder.contains("independently") && builder.contains("yard");
		boolean containsFirstAndLastSynonyms = true;
		
		assertTrue(containsFirstAndRandomAndLastWord && containsFirstAndLastSynonyms);
	}
	
	//Helper methods
	
	public void setup() {
		synonyms = new ArrayList<String>();
		builder = new DictionaryBuilder();
	}
	
	public void addSynonymsFromString(String synonyms) {
		String[] synonymStr = synonyms.split(" ");
		for (String synonym : synonymStr) {
			this.synonyms.add(synonym);
		}
	}
	
}

