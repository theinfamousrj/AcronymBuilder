package edu.westga.cs3152.ab.controller;

import java.io.File;
import java.util.ArrayList;
import edu.westga.cs3152.ab.model.DictionaryBuilder;

/**
 * @author RJ Hill
 *
 */
public class AcronymFactory {
	
	//The file that lists all words and synonyms
	private File theFile = new File("./synonyms.txt");
	
	//DictionaryBuilder for building a dictionary from synonyms.txt
	private DictionaryBuilder theBuilder;
	
	//ArrayLists for storing the acronyms and words
	private ArrayList<String> acronymList;
	private ArrayList<String> wordList;
	
	//String for storing the phrase for analysis
	private String theWords;
	
	/**
	 * Instantiate the ArrayList<String> acronymList,
	 * the DictionaryBuilder theBuilder and String theWords.
	 */
	public AcronymFactory(String words) {
		this.theBuilder = new DictionaryBuilder(this.theFile);
		this.acronymList = new ArrayList<String>();
		this.wordList = new ArrayList<String>();
		this.theWords = words;
	}
	
	/**
	 * Dissects the string of words and adds them to the
	 * ArrayList wordList.
	 */
	private void dissectWords() {
		String[] wordStr = theWords.split(" ");
		for(String word : wordStr) {
			this.wordList.add(word);
		}
	}
	
	/**
	 * Counts the number of words that were added to
	 * the wordList via the users input.
	 * 
	 * @return the number of elements in wordList
	 */
	public int countWords() {
		return this.wordList.size();
	}
	
	/**
	 * Gets the number of synonyms that a word has.
	 * 
	 * @param word the word to get the synonyms for
	 * @return the number of synonyms for the word
	 */
	public int getSynonymCount(String word) {
		return this.theBuilder.getValues(word).size();
	}
	
	/**
	 * Returns the list of acronyms.
	 * 
	 * @return the list of acronyms as an ArrayList<String>
	 */
	public ArrayList<String> getList() {
		return this.acronymList;
	}

}
