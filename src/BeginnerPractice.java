import java.util.*;
import java.time.*;
import java.io.*;

public class BeginnerPractice {
    public static void main (String[] args){
        //calculator();
        //datetime();
        //namelist();
        file();
    }

    private static void calculator (){
        int first;
        int second;
        Scanner sc = new Scanner (System.in);
        System.out.println("First: "); first = sc.nextInt();
        System.out.println("Second: "); second = sc.nextInt();
        System.out.println("Pls enter a operator \"+\" or \"-\": "); String operator = sc.next();
        if (operator.equals("+")){
            int result = first + second;
            System.out.println("Result:" + result);
        }
        else if (operator.equals("-")){
            int result = first - second;
            System.out.println("Result:" + result);
        }
    }

    private static void datetime () {
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println("Date: " + today);
        System.out.println("Time: " + time);

        LocalDate after = today.plusDays(100);
        LocalDate before = today.minusDays(10);
        System.out.println("100 days after:" + after);
        System.out.println("10 days before:" + before);
    }

    private static void namelist(){
        List<String> names = new ArrayList<>(Arrays.asList("John", "Julie", "Julie", "Ann"));
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name: "); String input = sc.next();

        if (names.contains(input)) {
            System.out.println("The name is already in list");
            names.remove(input);
        }
        else {
            System.out.println("The name isn't already in list");
            names.add(input);
        }

        System.out.println("After list: " + names);
    }

    private static void file(){
        try {
            FileWriter writer = new FileWriter("src/output.txt");
            writer.write("Hello");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
