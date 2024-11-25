import java.text.NumberFormat;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    final static byte MONTH_IN_YEARS = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
// Mortgage Calculator
        // Principal
        double principal = readNumber("Principal ($1k - $1M): ", 1000, 1000000);
        // Annual Interest Rate
        double rate = readNumber("Please Enter The Current Interest Rate: ", 1, 30);
        // Period (Years)
        int years = (int) readNumber("Please Enter Period (Years): ", 1, 30);

        // Print Result
        printMortgage(principal, rate, years);
        printPaymentSchedule(years, principal, rate);

    }

    private static void printMortgage(double principal, double rate, int years) {
        double mortgage = calculateMortgage(principal, rate, years);
        // Format it with currency
        String mortgageFormat = NumberFormat.getCurrencyInstance().format(mortgage);
        // Print the result Mortgage
        System.out.println();
        System.out.print("MORTGAGE");
        System.out.println("--------");
        System.out.print("Monthly Payments:  " + mortgageFormat);
    }

    private static void printPaymentSchedule(int years, double principal, double rate) {
        // Print Payment Schedule
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (int month = 1; month <= years * MONTH_IN_YEARS; month++){
           double balance = calculateBalance(principal, rate, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.println(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateMortgage(
            double principal,
            double annualInterest,
            int years) {
        // Calculate the mortgage
        double monthlyRate = (annualInterest / PERCENT) / MONTH_IN_YEARS;
        double totalNumberofPayments = years * MONTH_IN_YEARS;
        double mortgage;
        mortgage = principal * (
                (monthlyRate * Math.pow(1 + monthlyRate, totalNumberofPayments))
                        / (Math.pow(1 + monthlyRate, totalNumberofPayments) - 1));
        return mortgage;
    }

   public static double calculateBalance(
           double principal,
           double annualInterest,
           int years,
           int numberOfPaymentsMade
   ) {

       // Calculate the mortgage
       double monthlyRate = (annualInterest / PERCENT) / MONTH_IN_YEARS;
       double totalNumberofPayments = years * MONTH_IN_YEARS;

       double balance;
       balance = principal
               * (Math.pow(1 + monthlyRate, totalNumberofPayments) -
               Math.pow(1 + monthlyRate, numberOfPaymentsMade)) / (Math.pow(1 + monthlyRate, totalNumberofPayments) - 1);

       return balance;
   }
}