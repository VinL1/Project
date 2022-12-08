/**
 * The Calc class represents the calculator/calculations for the given equation.
 * Each calculator has its own unique answer, rounding style, sign, and equation.
 */
public class Calc {

    /**
     * The variable answer contains the final answer to the equation
     */
    private double answer = 0;

    /**
     * The variable roundToInt presents if the answer is to be rounded to the nearest integer
     */
    private boolean roundToInt = false;

    /**
     * The variable negative determines the sign of the answer
     */
    private boolean negative = false;

    /**
     * The variable equation is the equation to the solved
     */
    private String equation = "1+2-3*4/5";

    /**
     * The variable localEquation is an instance variable to assist in simplifying the equation
     */
    private String localEquation;

    /**
     * Constructor class. This creates a new calculator with default preset parameters.
     */
    public Calc () {
    }

    /**
     * Constructor class. This creates a new calculator with an equation parameter.
     * @param equation represents the equation of the calculator.
     */
    public Calc (String equation) {
        this.equation = equation;
    }

    /**
     * Constructor class. This creates a new calculator with an equation and rounding parameter.
     * @param equation represents the equation of the calculator.
     * @param roundToInt shows if the answer is going to be rounded to an integer.
     */
    public Calc (String equation, boolean roundToInt) {
        this.equation = equation;
        this.roundToInt = roundToInt;
    }

    /**
     * The equationWithIn method seeks for the first equation that exists inside the actual equation
     * that contains the given sign, using another method to solve it.
     * @param equation represents the active equation that you want to simplify.
     * @param sign represents the sign of the equations you will simplify.
     * @return the answer to the first equation that contains the sign parameter.
     */
    public double equationWithIn (String equation, String sign) {
        boolean equationFinished = false;
        String left = "";
        boolean lNegative = false;
        double le = 0;
        String right = "";
        boolean rNegative = false;
        double ri = 0;

        for (int i = 1; !equationFinished && equation.indexOf(sign) - i > -1; i++) { /*parsing numbers left of sign */
            String L = equation.substring(equation.indexOf(sign) - i, equation.indexOf(sign) - i + 1);
            if (isNumeric(L) || L.equals(".") || L.equals("-")) {
                if (L.equals("-")) {lNegative = true;}
                left = L + left;
            } else {equationFinished = true;}
        }
        equationFinished = false;
        for (int i = 1; !equationFinished && equation.indexOf(sign) + i < equation.length(); i++) { /*parsing numbers right of sign*/
            String R = equation.substring(equation.indexOf(sign) + i, equation.indexOf(sign) + i + 1);
            if (isNumeric(R) || R.equals(".") || R.equals("-")) {
                if (R.equals("-")) {rNegative = true;}
                right = right + R;
            } else {equationFinished = true;}
        }
        localEquation = left + sign + right;
        le = toDouble(left);
        ri = toDouble(right);

        if (rNegative && lNegative) { /* deals with negatives in the answer */
            this.equation = equation.replace("-" + left + sign + "-" + right, localEquation);
        }
        else if (rNegative) {
            this.equation = equation.replace("-" + right, right);
            ri = ri * -1;
        }
        else if (lNegative) {
            this.equation = equation.replace("-" + left, left);
            le = le * -1;
        }
        return equationSolver(le, ri, sign); /*uses equationSolver to solve the equation*/
    }

    /**
     * The equationSolver method essentially solves the equation that is given to it,
     * with num1 and num2 being on the left and right side of the math operator.
     * @param num1 the number on the left of the operator
     * @param num2 the number on the right of the operator
     * @param sign the math operator that is performed on num1 and num2
     * @return the result of the math operator being performed on num1 and num2
     */
    public double equationSolver (double num1, double num2, String sign) {
        double answer = 0;
        if (sign.equals("+")) {
            answer = num1 + num2;
        }
        if (sign.equals("-")) {
            answer = num1 - num2;
        }
        if (sign.equals("*")) {
            answer = num1 * num2;
        }
        if (sign.equals("/")) {
            answer = num1 / num2;
        }
        return answer;
    }

    /**
     * The method isNumeric checks if the given string is numeric/is a number
     * @param str the string being checked
     * @return a boolean that represents whether the string is a number
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * The method toDouble turns the given string into a double
     * @param str the string being turned into a double
     * @return the "double value" of the string
     */
    public double toDouble (String str) {
        return (Double.parseDouble(str));
    }

    /**
     * the method calculateFinal continously simplifies the actual equation until the answer is reached
     */
    public void calculateFinal () {
        double setter;

        while (equation.contains ("/")) {
            setter = equationWithIn(equation, "/");
            equation = equation.replace(localEquation, setter + "");
        }
        while (equation.contains ("*")) {
            setter = equationWithIn(equation, "*");
            equation = equation.replace(localEquation, setter + "");
        }
        while (equation.substring(1).contains ("-") && isNumeric(equation.substring(equation.substring(1).indexOf("-") + 1))) {
            setter = equationWithIn(equation, "-");
            equation = equation.replace(localEquation, setter + "");
        }
        while (equation.contains ("+")) {
            setter = equationWithIn(equation, "+");
            equation = equation.replace(localEquation, setter + "");
        }
        answer = toDouble(equation);
        if (roundToInt) {
            answer = Math.round(answer);
            answer = (int) answer;
        }
        if (negative) {
            answer = answer * -1;
        }
        this.answer = answer;
    }

    /**
     * The method toString allows for the class variables to be printed in the client class
     * @return the formatted string that is able to be printed in the client class
     */
    public String toString() {
        String toString = "The answer to your equation is: " + answer + "\nThe absolute value for the answer is : " + Math.abs(answer);
        return toString;
    }

    /**
     * The method validEquation checks if the given equation is solvable with the calculator
     * @param equation the equation that is being checked for solvability
     * @return a boolean representing the equation's ability to be solved.
     */
    public static boolean validEquation(String equation) {
       try {
           Calc test = new Calc (equation);
           test.calculateFinal();
       }  catch (NumberFormatException e) {
           return false;
       }
       return true;
    }
}