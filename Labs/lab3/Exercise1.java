package labs.lab3;

public class Exercise1 {
    public static void main(String[] args) {
        int grade = Integer.parseInt(args[0]);
        if (grade >= 60) {
            System.out.println("You pass the exam.");
        } else {
            System.out.println("You failed in the exam.");
        }

        double gpa = 0.0;
        if (90 <= grade && grade <= 100) {
            gpa = 4.0;
        } else if (80 <= grade && grade <= 89) {
            gpa = 3.0;
        } else if (70 <= grade && grade <= 79) {
            gpa = 2.0;
        } else if (60 <= grade && grade <= 69) {
            gpa = 1.0;
        } else {
            gpa = 0.0;
        }

        System.out.printf("Your score is %.2f, the GPA is %.1f", (double)grade, gpa);

    }
}
