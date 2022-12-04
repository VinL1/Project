public class Calc {
    private double answer = 0;
    private boolean roundToInt;
    public Calc () {
    }

    public Calc (boolean roundToInt) {
        this.roundToInt = roundToInt;
    }

    public String equation (String equa) {
        while(!equa.contains("+") && !(equa.contains("-") && isNumeric(equa.substring(equa.indexOf("-") + 1))) && !equa.contains("*") && !equa.contains("/")) {
            if (equa.contains("*")) {
                answer += equationWithIn(equa, "*");
            }
            if (equa.contains("/")) {
                answer += equationWithIn(equa, "/");
            }
            if (equa.contains("-") && !hasNegative(equa)) {
                answer += equationWithin(equa, "-");
            }
            if (equa.contains("+")) {
                answer += equationWithIn(equa, "+");
            }
        }
    }

    public double equationWithIn (String equation, String sign) {
        boolean equationFinished = false;
        String left = "";
        String right = "";
        for (int i = 1; equationFinished != false; i ++) {
            String L = equation.substring (equation.indexOf(sign) - i);
            String R = equation.substring(equation.indexOf(sign) + i);
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
        for (int i = 0; i < equation.length(); i ++) {
            equation = equation.substring(equation.indexOf("-") + 1);
            int index = equation.indexOf("-");
            if (index != -1 && !isNumeric(equation.substring(index - 1, index))) {
                has = true;
            }
        }
        return has;
    }
}
