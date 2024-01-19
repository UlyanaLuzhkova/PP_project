package com.src;
import java.util.Objects;

public class Calculator {

    private static String ProcessAlgebraicExpression(String input) {

        int priority = 2;
        String output = input;
        String expression;

        while (priority >= 0) {
            Finder f = new FindCalculateTasks(priority);

            while (f.exists(output)) {
                expression = f.FindFirst(output);
                output = f.ReplaceFirst(output, Counter.Calculate(expression));
            }

            priority = priority - 1;
        }

        return output;
    }

    private static String BracketsDelete(String input) {

        String output = input;

        output = output.substring(1, output.length() - 1);

        return output;

    }

    private static String ProcessBracketsExpression(String input) {

        String output = input;
        String temp;
        String expression;

        Finder bracketsFinder = new FindBrackets();

        while (bracketsFinder.exists(output)) {

            temp = bracketsFinder.FindFirst(output);
            temp = BracketsDelete(temp);

            expression = temp;
            expression = ProcessAlgebraicExpression(expression);
            output = output.replace("(" + temp + ")", expression);

        }

        return output;

    }

    public static String Process(String input) {

        String output = input;
        output = ProcessBracketsExpression(output);
        output = ProcessAlgebraicExpression(output);

        return output;

    }

}

class Counter {

    public static String Calculate(String userArithmeticExpression) {

        String output;

        Finder algebraicFinder = new FindCalculateTasks(-1);

        String left = algebraicFinder.GroupFind(1,userArithmeticExpression);
        left = normaliseNumber(left);

        String right = algebraicFinder.GroupFind(3,userArithmeticExpression);
        right = normaliseNumber(right);

        if(Objects.equals(algebraicFinder.GroupFind(2,userArithmeticExpression), "^")) output = pow(left,right);
        else if(Objects.equals(algebraicFinder.GroupFind(2,userArithmeticExpression), "*")) output = multiplication(left,right);
        else if(Objects.equals(algebraicFinder.GroupFind(2,userArithmeticExpression), "/")) output = division(left,right);
        else if(Objects.equals(algebraicFinder.GroupFind(2,userArithmeticExpression), "-")) output = residual(left,right);
        else  output = addition(left,right);

        return output;
    }

    private static String addition(String left, String right) {
        double result;

        result = Double.parseDouble(left) + Double.parseDouble(right);

        return String.valueOf(result);
    }
    private static String residual(String left, String right) {
        double result;

        result = Double.parseDouble(left) - Double.parseDouble(right);

        return String.valueOf(result);
    }
    private static String multiplication(String left, String right) {
        double result;

        result = Double.parseDouble(left) * Double.parseDouble(right);

        return String.valueOf(result);
    }
    private static String division(String left, String right) {
        double result;

        if(Double.parseDouble(right) != 0) {
            result = Double.parseDouble(left) / Double.parseDouble(right);
        }
        else throw new ArithmeticException("Try to divide by zero");

        return String.valueOf(result);
    }
    private static String pow(String left, String right) {
        double result;

        result = Math.pow(Double.parseDouble(left), Double.parseDouble(right));

        return String.valueOf(result);
    }
    private static String normaliseNumber(String number) {

        boolean ind = false;
        String output = number;
        Finder finder = new FindSymbols("-");

        while(finder.exists(output)) {
            output = finder.ReplaceFirst(output,"");
            ind = !ind;
        }

        if(ind) output = "-" + output;

        return output;
    }

}