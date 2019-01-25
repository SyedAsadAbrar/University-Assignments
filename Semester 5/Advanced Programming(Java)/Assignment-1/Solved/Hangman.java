/*
Haider Nadeem Mirza
16L-4045
Section A
l164045@lhr.nu.edu.pk

Syed Asad Abrar
16L-4292
Section A
l164292@lhr.nu.edu.pk

This program is the console implementation of the children's game, Hangman. It uses
a random class variable to choose a word from a HangmanLexicon class and gives the user
8 lives to pick a letter, after which the user either wins or loses depending on
their choices.
*/
/*
    Test Cases:
        {Word: ------- 
            
        Input: a
        Output:
        Your guess: a
        There are no A's in the word.
        The word now looks like this: -------
        You have 7 guesses left.
        Please input your guessed letter.

        Input: q
        Output:
        Your guess: q
        There are no Q's in the word.
        The word now looks like this: -------
        You have 6 guesses left.
        Please input your guessed letter.

        Input: w
        Output:
        Your guess: w
        There are no W's in the word.
        The word now looks like this: -------
        You have 5 guesses left.
        Please input your guessed letter.

        Input: e
        Output:
        Your guess: e
        That guess is correct.
        The word now looks like this: -----E-
        You have 5 guesses left.
        Please input your guessed letter.

        Input: r
        Output:
        Your guess: r
        That guess is correct.
        The word now looks like this: -----ER
        You have 5 guesses left.
        Please input your guessed letter.

        Input: c
        Output:
        Your guess: c
        There are no C's in the word.
        The word now looks like this: -----ER
        You have 4 guesses left.
        Please input your guessed letter.

        Input: o
        Output:
        Your guess: o
        There are no O's in the word.
        The word now looks like this: -----ER
        You have 3 guesses left.
        Please input your guessed letter.

        Input: p
        Output:
        Your guess: p
        There are no P's in the word.
        The word now looks like this: -----ER
        You have 2 guesses left.
        Please input your guessed letter.

        Input: f
        Output:
        Your guess: f
        There are no F's in the word.
        The word now looks like this: -----ER
        You have 1 guesses left.
        Please input your guessed letter.

        Input: s
        Output:
        Your guess: s
        That guess is correct.
        The word now looks like this: S----ER
        You have 1 guesses left.
        Please input your guessed letter.

        Input: l
        Output:
        Your guess: l
        That guess is correct.
        The word now looks like this: SL---ER
        You have 1 guesses left.
        Please input your guessed letter.

        Input: d
        Output:
        Your guess: d
        There are no D's in the word.
        You're completely hung.
        The word was: SLITHER.
        You lose.
        }
*/
package AP_Assignment_1;
import java.util.*;
import java.io.*;
/**
 *
 * @author Asad
 */
public class Hangman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //variables declaration and intialization
        String guessedLetter;
        String chosenWord;
        int remainingLength;
        int index = (int)(Math.random()*11);
        int guesses = 8;
        StringBuffer guessedWord = new StringBuffer();
        HangmanLexicon lexicon = new HangmanLexicon();
        Scanner input = new Scanner(System.in);
        boolean gameStatus = true;
        
        //Initial printed statemen
        System.out.println("Welcome to Hangman!");
        
        //getting a random word from lexicon class
        chosenWord = lexicon.getWord(index);
        
        //used to decide if user has won
        remainingLength = chosenWord.length();
        
        //inserting appropriate amount of dashes to output on screen
        for (int i = 0; i < chosenWord.length(); i++) {
            guessedWord.insert(i, "-");
        }
        
        while(gameStatus)
        {
            //outputting the word with or without blanks depending on user guesses
            System.out.println("The word now looks like this: " + guessedWord);
            
            //outputting the number of lives left
            System.out.println("You have " + Integer.toString(guesses) + " guesses left.");
            
            //prompting user to input and checking if user inputted a letter only,
            //otherwise they are prompted to input again.
            System.out.println("Please input your guessed letter.");
            guessedLetter = input.nextLine();
            while(guessedLetter.length()>1 || !(guessedLetter.matches("[a-zA-Z]")))
            {
                System.out.println("The guess is illegal, please enter a single letter.");
                guessedLetter = input.nextLine();
            }
            
            //echoing input
            System.out.println("Your guess: " + guessedLetter);

            //to change lower case input to uppercase
            guessedLetter = guessedLetter.toUpperCase();
            
            //in the case of correct guess
            if(chosenWord.indexOf(guessedLetter)>=0)
            {
                //telling user about their correct choice
                System.out.println("That guess is correct.");
                index = chosenWord.indexOf(guessedLetter);
                
                if(guessedWord.indexOf(guessedLetter) == -1)
                {                 
                    //in case of a guessed letter coming more than once in a word
                    while(chosenWord.indexOf(guessedLetter, index)>=0)
                    {

                        //replacing the dashes with letter(s)
                        guessedWord.replace(chosenWord.indexOf(guessedLetter, index), chosenWord.indexOf(guessedLetter, index) + 1, guessedLetter);
                        remainingLength--;

                        //if remaining length is 0, the user has successfully guessed
                        //the word and won the game
                        if(remainingLength == 0)
                        {
                            System.out.println("You guessed the word: " + guessedWord + ".");
                            System.out.println("You win.");
                            gameStatus = false;
                            break;
                        }

                        //in case of multiple occurences of a letter in a word,
                        //moving to the next index in the string where letter
                        //is present
                        index = chosenWord.indexOf(guessedLetter, index + 1);
                        if(index == -1)
                            break;
                    }
                }
            }
            else
            {
                //in case of wrong guess, number of available guesses would
                //be decremented by one
                System.out.println("There are no " + guessedLetter + "'s in the word.");
                guesses--;
                
                //if number of guesses turn 0, the user loses
                if(guesses == 0)
                {
                    System.out.println("You're completely hung.");
                    System.out.println("The word was: " + chosenWord + ".");
                    System.out.println("You lose.");
                    gameStatus = false;
                    break;
                }
            }
        }
    }
}

class HangmanLexicon {
	
	/** Returns the number of words in the lexicon. */
	
	public int getWordCount() {
		return 11;
	}

	
	/** Returns the word at the specified index. */

	public String getWord(int index) {
		switch (index) {

		case 0:
			return "BUOY";
		case 1:
			return "COMPUTER";
		case 2:
			return "CONNOISSEUR";
		case 3:
			return "DEHYDRATE";
		case 4:
			return "FUZZY";
		case 5:
			return "HUBBUB";
		case 6:
			return "KEYHOLE";
		case 7:
			return "QUAGMIRE";
		case 8:
			return "SLITHER";
		case 9:
			return "ZIRCON";
                case 10:
                        return "DERATION";
		default:
			return new String("Illegal index");
		}
	}
}

