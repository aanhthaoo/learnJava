import java.util.*;
public class Main {
    public static void main(String[] args) {
        Random ran = new Random();
        int number = ran.nextInt(100) + 1;
        int guess;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a number between 1 and 100: ");
        do {
            guess = scanner.nextInt();
            if (guess > number) {
                System.out.println("The number is smaller");
            } else if (guess < number) {
                System.out.println("The number ís larger");
            } else {
                System.out.println("Correct");
            }
        }while (guess != number);

        scanner.close();
    }
}