package edu.westga.cs3152.acronymbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DictionaryBuilder builds a dictionary of words
 * and their synonyms from a flat file.
 * 
 * @author RJ Hill
 *
 */
public class DictionaryBuilder {
	
	//the file that will be used to build the dictionary
	private File theFile;
	
	//a dictionary of words and their synonyms
	private Map<String, ArrayList<String>> userDictionary;
	
	/**
	 * Instantiate the userDictionary hashmap.
	 */
	public DictionaryBuilder ()
	{
		this.userDictionary = new HashMap<String, ArrayList<String>>();
	}
	
	/**
	 * Instantiate the userDictionary hashmap with a text file.
	 */
	public DictionaryBuilder (File theFile)
	{
		this.theFile = theFile;
		this.userDictionary = new HashMap<String, ArrayList<String>>();
		this.buildDictionary();
	}
	
	/**
	 * Add a word with synonyms to this dictionary
	 * 
	 * Precondition: word != null && synonyms.isEmpty() != true && !this.contains(word)
	 * 
	 * @param word The word to be added to this dictionary
	 * @param synonyms The ArrayList of synonym Strings to be added to this dictionary
	 */
	public void addWordWithSynonymList (String word, ArrayList<String> synonyms)
	{
		if (word == null || synonyms.isEmpty() || this.contains(word)) {
			return;
		}
		// add word to the collection of Strings
		userDictionary.put(word, synonyms);
	}
	
	/**
	 * Remove a word from this dictionary
	 * 
	 * Precondition: word != null && this.contains(word)
	 * 
	 * @param word The word to be removed from this dictionary   
	 */	
	public void remove (String word)
	{
		if (word == null || !this.contains(word)) {
			return;
		}
		// remove word from the collection of Strings
		this.userDictionary.remove(word);
	}

	/**
	 * Return a boolean indicating if the word parameter, word, is contained 
	 * in this dictionary.  
	 * 
	 * @param word The word to check if is contained in this dictionary
	 * @return true iff the specified word is contained in this dictionary   
	 */
	public boolean contains (String word)
	{	
		// check whether word is contained in the collection of words
		return this.userDictionary.containsKey(word);
	}
	
	/**
	 * Return an ArrayList of synonyms as a value with word as a key
	 * 
	 * Precondition: word != null && this.contains(word)
	 * 
	 * @param word The word to get the ArrayList of synonyms for
	 * @return an ArrayList of synonyms contained in this dictionary
	 */
	public ArrayList<String> getValues (String word)
	{
		if (word == null || !this.contains(word)) {
			return null;
		}
		return this.userDictionary.get(word);
	}
	
	/**
	 * Return an integer value indicating the size of the dictionary
	 * 
	 * @return the size of the dictionary
	 */
	public int getSize ()
	{
		return this.userDictionary.size();
	}
	
	/**
	 * Parse the input synonyms file and put each word into the dictionary.  The
	 * string-value of each word is used as the key, and an ArrayList<String> of
	 * synonyms is placed in the corresponding value.
	 */
	private void buildDictionary () {
			
		try {
			
			String line = "";
			
			FileInputStream inStream = new FileInputStream(this.theFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			while ((line = reader.readLine()) != null) {
				
				ArrayList<String> synonyms = new ArrayList<String>();
				String[] wordStr = line.split(" ");
				String word = new String(wordStr[0]);
				String[] synonymStr = line.split(" ");;
				for (String synonym : synonymStr) {
					synonyms.add(synonym);
				}
				
				this.addWordWithSynonymList(word, synonyms);
			}
		} catch (FileNotFoundException ex) {
			
			System.err.println("Could not locate the file " + this.theFile.getName() 
					+ " to build the dictionary from.\nPlease make sure the file exists.");
			
		} catch (IOException e) {
			
			System.err.println("An I/O exception has occured when attempting the parse the file.");
		}
	}

}