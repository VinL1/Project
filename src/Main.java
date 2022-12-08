import java.util.Scanner;
public class Main {
    public static void main (String [] args) {
        String equation;
        String round;
        boolean roundYN = false;
        Scanner input = new Scanner (System.in);

        System.out.println ("---------------Hello!---------------\n--Welcome to the simple arithmetic calculator.--");
        System.out.println ("Please type in your equation (using only +, -, *, /): ");
        equation = input.nextLine();
        while (!Calc.validEquation(equation)) {
            System.out.println ("\\*\\*Please type in a valid equation!*/*/");
            System.out.println ("Please type in your equation: ");
            equation = input.nextLine();
        }
        System.out.println ("Got it!");
        System.out.println ("Would you like for me to round your answer to the nearest integer? (y/n)");
        round = input.nextLine();
        while (!round.equals ("y") && !round.equals ("n")) {
            System.out.println ("Please decide if you want to round the answer to the nearest integer! (y/n)");
            round = input.nextLine();
        }
        if (round.equals ("y")) {
            roundYN = true;
        }
        Calc calculator = new Calc(equation, roundYN);
        System.out.println ("Alright!");
        calculator.calculateFinal();
        System.out.println (calculator);
        System.out.print ("-----------Have a great day!----------");
    }
}
