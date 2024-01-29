package codSoft;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz_Application_with_Timer_Task_4 {
    private static final int QUESTION_TIME_LIMIT = 30; 
    private static int score = 0;

    public static void main(String[] args) {
        startQuiz();
    }
    private static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        String[] questions = {
            "The ratio of two numbers is 3:8 and their difference is 115. The smaller of the two numbers is :",
            "What is the most basic level of storage ?",
            "Which one of the following rivers originates in Brahmagiri range of Western Ghats ?",
            "What is data structure ?",
            "Where was the electricity supply first introduced in India ?"
        };
        String[][] options = {
            {"[1]. 69", "[2]. 184", "[3]. 194", "[4]. 59"},
            {"[1]. SAN", "[2]. DAS", "[3]. NAS", "[4]. ISCSI"},
            {"[1]. Tapti", "[2]. Pennar", "[3]. Cauvery", "[4]. Krishna"},
            {"[1]. A Way to store and organize data ", "[2]. A collection of algoritham", "[3]. A Programming language", "[4]. A type of computer hardware"},
            {"[1]. Mumbai", "[2]. Dehradun", "[3]. Chennai", "[4]. Darjeeling"}
        };
        int[] correctAnswers = {1, 2, 3, 1, 4}; 
        System.out.println("##########################################");
        System.out.println("!!!!!Welcome to the Quiz Competition !!!!!");
        System.out.println("##########################################\n");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

           
            for (String option : options[i]) {
                System.out.println(option);
            }

     
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\n The allotted time has expired, let's proceed to the following question.");
                    evaluateAnswer(-1, correctAnswers[i]);; 
                }
            }, QUESTION_TIME_LIMIT * 1000);
            
            System.out.print("Your answer (enter the corresponding option number): ");
            int userAnswer = scanner.nextInt();
            timer.cancel();
            evaluateAnswer(userAnswer, correctAnswers[i]);
        }  
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + " out of " + questions.length);
        scanner.close();
    }
    private static void evaluateAnswer(int userAnswer, int correctAnswer) {
        if (userAnswer == correctAnswer) {
            System.out.println("Correct!\n");
            score++;
        } else if (userAnswer == -1) {
            System.out.println("Time's up! The correct answer was option " + (correctAnswer + 1) + ".\n");
        } else {
            System.out.println("Incorrect! The correct answer was option " + (correctAnswer + 1) + ".\n");
        }
    }
}
