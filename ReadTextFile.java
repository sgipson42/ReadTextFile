import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
public class ReadTextFile {
	public static Scanner nameInput= new Scanner(System.in);
	public static void main(String[] args) {
		String data="";
		int charCount=0;
		int wordCount=0;
		
		//allow user to specify txt file
		System.out.println("Enter file name:"); //AsYouLikeIt
		String fileName = nameInput.next();

		//reads the file
		try{ 
			Scanner fileInput = new Scanner(new File(fileName));
			while(fileInput.hasNext()){
					data = data + " " + fileInput.next(); //adds everything to the string word by word (counting punctuation)
			}
			fileInput.close();
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		//replace anything not a letter with spaces
		data= data.replaceAll("[^a-zA-Z]", " ");
		
		//Map letters to their frequency
		Map<String, Integer> words= new TreeMap<String, Integer>();
		Map<String, Integer> chars=new TreeMap<String, Integer>();
		
		//use splits to get individual words
		String wordList[]= data.split(" "); 
		for (String word: wordList) {

			//check empty indexes not counted as words or characters
			if (!word.equals("")) {
				//count words
				wordCount++;
				//chars: check if map contains the string key, if so, increment value
				String charList[]=word.split("");
				for (String ch: charList) {
					//count characters
					charCount++;
					if (chars.containsKey(ch.toLowerCase()) == true) {
						//get value associated with character, increment it, put it as new value
						chars.put(ch.toLowerCase(), chars.get(ch.toLowerCase())+1); 
					} else //if word has not yet been seen, add it to the map
						chars.put(ch.toLowerCase(),  1);
					} 
				//words: check if map contains the string key, if so, increment value
				if (words.containsKey(word.toLowerCase()) == true) {
					words.put(word.toLowerCase(), words.get(word.toLowerCase())+1);
				} else //if word has not yet been seen, add it to the map
					words.put(word.toLowerCase(),  1);
			}
		}
		System.out.println("Number of total words: " + wordCount);
		System.out.println("Number of total characters: " + charCount);
		
		//finding char frequency
		Iterator<String> itrChar=chars.keySet().iterator();
		Map<Integer, TreeSet<String>> charsReversed= new TreeMap<Integer, TreeSet<String>>();
		while(itrChar.hasNext()) {
			String c= itrChar.next();
			if (charsReversed.containsValue(c)) {//String is now value
				charsReversed.get(chars.get(c)).add(c);
			} else {
				charsReversed.put(chars.get(c), new TreeSet<String>());
				charsReversed.get(chars.get(c)).add(c);
			}
		}
		//sort and print characters with respective frequencies
		ArrayList<Integer> frequencyChar= new ArrayList<Integer>(charsReversed.keySet()); 
		Collections.sort(frequencyChar);
		System.out.println( "List of characters, most to least frequent: ");
		for (int i=frequencyChar.size()-1; i>=0; i--) {
			for (String key: chars.keySet()) {
				//frequency.get(i) is an integer key
				//words.get(key) is an integer value
				if (frequencyChar.get(i)==chars.get(key) && key!= "") {
					System.out.println(key + ": "+ chars.get(key)); //prints String key associated with integer value
				}
			}
		}
		//finding word frequency
		Iterator<String> itr= words.keySet().iterator(); //.iterator() returns an iterator over the elements in set
		Map<Integer, TreeSet<String>> wordsReversed= new TreeMap<Integer, TreeSet<String>>();
		while(itr.hasNext()) {
			String w=itr.next();
			if (wordsReversed.containsValue(w)) {//String is now value
				wordsReversed.get(words.get(w)).add(w);
			} else {
				wordsReversed.put(words.get(w), new TreeSet<String>());
				wordsReversed.get(words.get(w)).add(w);
			}
		}
		//sort and print words with respective frequencies
		ArrayList<Integer> frequency= new ArrayList<Integer>(wordsReversed.keySet()); 
		Collections.sort(frequency);
		System.out.println( "List of words, most to least frequent: ");
		for (int i=frequency.size()-1; i>=0; i--) {
			for (String key: words.keySet()) {
				//frequency.get(i) is an integer key
				//words.get(key) is an integer value
				if (frequency.get(i)==words.get(key) && key!= "") {
					System.out.println(key + ": "+ words.get(key)); //prints String key associated with integer value
				}
			}
		}
	}
}


