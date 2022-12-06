public class Calc {
    private double answer = 0;
    private boolean roundToInt;
    private String equationS;
    public Calc () {
    }

    public Calc (boolean roundToInt) {
        this.roundToInt = roundToInt;
    }

    public void simplifyParenthesis (String equation) {
        while (equation.contains("(") && equation.contains(")")) {
            String equa = equation.substring(equation.indexOf ("(") + 1, equation.indexOf(")"));
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
        for (int i = 1; equationFinished == false; i ++) {
            String L = equation.substring (equation.indexOf(sign) - i);
            if (hasNegative(equation.substring(equation.indexOf(L) - 2))) {
                L = "-" + L;
            }
            String R = equation.substring(equation.indexOf(sign) + i);
            if (hasNegative(equation.substring(equation.indexOf(R) - 2))) {
                R = "-" + R;
            }
            if (isNumeric(L) && isNumeric(R)) {
                left = L + left;
                right += R;
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

    public double calculateFinal (String equation) {
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
        return answer;
    }

    public String toString() {
        String toString = "The simplified equation would be: " + equationS;
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
