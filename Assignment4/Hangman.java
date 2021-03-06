/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.*;

public class Hangman extends ConsoleProgram {
	private HangmanCanvas canvas;
	
	private final int numGuesses = 8;
	private char[] wordArray;
	
	public void run() {
		println("Welcome to Hangman!");
		canvas.reset();
		String secretWord = getWord();
		playHangman(secretWord);
	}
	private String getWord(){
		RandomGenerator rgen = new RandomGenerator();
		int num = rgen.nextInt(0,9);
		String secretWord = new HangmanLexicon().getWord(num);
		return secretWord;
	}
	private void playHangman(String secretWord) {
		int rounds = numGuesses;
		String spaces = "";

		wordArray = new char[secretWord.length()];
		for(int i = 0; i < secretWord.length(); i++) {
			wordArray[i] = (char)'-';
			spaces += (char)'-';
		}
		canvas.displayWord(spaces);
		
		while(true) {
			if (hasBlank(spaces) == false) {//case won game: no more blanks
				println("You guessed the word: " + spaces );
				println("You win.");
				break;
			}
			else if (rounds==0) {//case lose game: no more guesses left
				println("You're completely hung.");
				println("The word was: " + secretWord);
				println("You lose.");
				break;
	    }
			else {//case still playing
				println("The Word now looks like this: " + spaces );
				canvas.displayWord(spaces);
				println("You have " + rounds + " guesses left.");
				String userGuess = readLine("Your guess: ");
				int num = userGuess.length();
				if(num > 1) {//user enters more than one character
					println("Please enter a single letter from A to Z.");
				}
				else if (num == 1) {
					char ch = userGuess.charAt(0);//get character
					if (Character.isLetter(ch) == true) {//if character is a letter
						ch = Character.toUpperCase(ch);
						if(hasChar(secretWord, ch) == true && hasBlank(spaces) == true) {
							for (int i = 0; i < secretWord.length(); i++) {
								if (secretWord.charAt(i) == ch) {
									wordArray[i] = (char)ch;
								}
								spaces = fillBlank(secretWord, ch);
							}
						}
						else if (hasChar(secretWord, ch) == false && hasBlank(spaces) == true) {
							println("There are no " + ch + "'s in the word.");
							canvas.noteIncorrectGuess(ch);
							rounds--;
						}
						else {//if character is not a letter (a number or a symbol)
							println("Please enter a single letter from A to Z.");
						}
			    	}
			    }
	    	}
    	}
    }
    
    private boolean hasBlank(String spaces) {
    	//checks to see if there are any blanks left
    	int count = 0;
    	for (int i = 0; i < spaces.length(); i++) {
    		if (spaces.charAt(i) == '-') {
    			count++;
    		}
    	}
    	if (count > 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    private boolean hasChar(String secretWord, char ch) {
    	//checks to see if the user guessed any letter of the secret word
    	int count = 0;
    	for (int i = 0; i < secretWord.length(); i++) {
    		if (secretWord.charAt(i) == ch) {
    			count++;//increases count if any letters in the string match the secret word
    		}
    	}
    	if (count > 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    private String fillBlank(String secretWord, char ch) {
    	//fills the user's guess in the proper position
    	String result = "";
    	for (int i = 0; i < secretWord.length(); i++) {
    		result += (char)wordArray[i];
    	}
    	return result;
    }
    
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }

}
