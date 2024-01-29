package codSoft;

import java.util.Scanner;

public class Student_Grade_Calculator_Task_2 {
    public static void main(String[] args) {
        calculateGrades();
    }

    private static void calculateGrades() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n######################################");
        System.out.println("###### STUDENT GRADE CALCULATOR ######");
        System.out.println("######################################\n\n");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        int[] subjectMarks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            subjectMarks[i] = scanner.nextInt();
            totalMarks += subjectMarks[i];
        }
        double averagePercentage = (double) totalMarks / numSubjects;

        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);

        char grade = calculateGrade(averagePercentage);

        System.out.println("Grade: " + grade);

        scanner.close();
    }

    private static char calculateGrade(double averagePercentage) {
        char grade;

        if (averagePercentage >= 85) {
            grade = 'A';
        } else if (averagePercentage >= 73) {
            grade = 'B';
        } else if (averagePercentage >= 60) {
            grade = 'C';
        } else if (averagePercentage >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        return grade;
    }
}
