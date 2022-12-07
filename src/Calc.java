public class Calc {
    private double answer = 2;
    private boolean roundToInt = false;
    private String equationS;
    private String equation = "(1+2-3)*4/5";
    public Calc () {
    }

    public Calc (String equation) {
        this.equation = equation;
    }

    public Calc (String equation, boolean roundToInt) {
        this.equation = equation;
        this.roundToInt = roundToInt;
    }

    public void simplifyParenthesis () {
        while (equation.contains("(") && equation.contains(")")) {
            String equa = equation.substring(equation.indexOf ("(") + 1, equation.indexOf(")"));
            while(equa.contains("(") && equa.contains(")")) {
                equa = equa.substring(equa.indexOf("(") + 1, equa.indexOf (")"));
            }
            while(!equa.contains("+") && !(equa.contains("-") && isNumeric(equa.substring(equa.indexOf("-") + 1))) && !equa.contains("*") && !equa.contains("/")) {
                if (equa.contains("/")) {
                    equation = equation.replace("(" + equa + ")",equationWithIn(equa, "/") + "");
                }
                if (equa.contains("*")) {
                    equation = equation.replace("(" + equa + ")",equationWithIn(equa, "*") + "");
                }
                if (equa.contains("-") && !hasNegative(equa)) {
                    equation = equation.replace("(" + equa + ")",equationWithIn(equa, "-") + "");
                }
                if (equa.contains("+")) {
                    equation = equation.replace("(" + equa + ")",equationWithIn(equa, "+") + "");
                }
            }
        }
        equationS = equation;
    }

    public double equationWithIn (String equation, String sign) {
        boolean equationFinished = false;
        String left = "";
        String right = "";
        for (int i = 1; equationFinished == false && equation.indexOf(sign) - i > 0; i ++) {
            String L = equation.substring (equation.indexOf(sign) - i,equation.indexOf(sign) - i + 1);
            if (isNumeric(L)) {
                left = L + left;
            }
            else {
                equationFinished = true;
            }
        }
        equationFinished = false;
        for (int i = 1; equationFinished == false && equation.indexOf(sign) + i < equation.length() - 1; i ++) {
            String R = equation.substring(equation.indexOf(sign) + i, equation.indexOf(sign) + i + 1);
            if (isNumeric(R)) {
                right = right + R;
            }
            else {
                equationFinished = true;
            }
        }
        return equationSolver(toDouble(left), toDouble(right), sign);
    }

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
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public double toDouble (String str) {
        return (Double.parseDouble(str));
    }

    public boolean hasNegative (String equation) {
        boolean has = false;
        for (int i = 0; i < equation.length(); i++) {
            equation = equation.substring(equation.indexOf("-") + 1);
            int index = equation.indexOf("-");
            if (index != -1 && !isNumeric(equation.substring(index - 1, index))) {
                has = true;
            }
        }
        return has;
    }

    public double calculateFinal () {
        while(!equation.contains("+") && !(equation.contains("-") && isNumeric(equation.substring(equation.indexOf("-") + 1))) && !equation.contains("*") && !equation.contains("/")) {
            if (equation.contains("/")) {
                answer += equationWithIn(equation, "/");
            }
            if (equation.contains("*")) {
                answer += equationWithIn(equation, "*");
            }
            if (equation.contains("-") && !hasNegative(equation)) {
                answer += equationWithIn(equation, "-");
            }
            if (equation.contains("+")) {
                answer += equationWithIn(equation, "+");
            }
        }
        if (roundToInt) {
            answer = Math.round(answer);
        }
        return answer;
    }

    public String toString() {
        String toString = equationS + "\n" + answer;
        return toString;
    }

    public static boolean validEquation(String equation) {
        for (int i = 0; i < equation.length(); i ++) {
            if (isNumeric(equation.substring(i, i + 1))) {
                return true;
            }
        }
        return false;
    }
}