package codSoft;

import java.util.Random;
import java.util.Scanner;

public class Number_Game_Task_1 {
    public static void main(String[] args) {
        playNumberGame();
        
    }
    private static void playNumberGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        int maxAttempts = 10;
        int roundsPlayed = 0;
        int totalAttempts = 0;
        
    	System.out.println("\n   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    	System.out.println("  $$$$$$ <GUESS THE NUMBER > $$$$$");
    	System.out.println("   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n");
    	
        while (true) {
        
            System.out.print("Guess the number between " + lowerBound + " and " + upperBound + ": ");
            int userGuess = scanner.nextInt();

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number " + targetNumber + "!");
                totalAttempts += maxAttempts - (maxAttempts - 1);
                roundsPlayed++;

                System.out.print("Do you want to play again? (yes/no): ");
                String playAgain = scanner.next().toLowerCase();
                if (!playAgain.equals("yes")) {
                    System.out.println("Game over! You played " + roundsPlayed + " round(s) and your total score is " + totalAttempts + ".");
                    break;
                } else {
                    targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    maxAttempts = 10;
                    continue;
                }

            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.\n");
            } else {
                System.out.println("Too high! Try again.\n");
            }

           
            maxAttempts--;

            if (maxAttempts == 0) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
                roundsPlayed++;
                System.out.print("Do you want to play again? (yes/no): ");
                String playAgain = scanner.next().toLowerCase();
                if (!playAgain.equals("yes")) {
                    System.out.println("Game over! You played " + roundsPlayed + " round(s) and your total score is " + totalAttempts + ".");
                    break;
                } else {
                    targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    maxAttempts = 10;
                }
            }
        }
        scanner.close();
    }
}
