public class Calc {
    private double answer;
    public Calculator () {
    }

    public String equation (String equa) {
        if (equa.indexOf ("/") != -1 && equa.indexOf("*") != -1) {
            equa = equationWithIn (equa, "/");
        }
    }

    public static void equationWithIn (String equation, String sign) {
        boolean equationFinished = false;
        String left = "";
        String right = "";
        for (int i = 1; equationFinished != false; i ++) {
            if (isNumeric(equation.substring (equation.indexOf(sign) - i)) && isNumeric(equation.substring(equation.indexOf(sign) + i))) {
                left = equation.substring (equation.indexOf(sign) - i) + left;
                right += equation.substring (equation.indexOf(sign) + i);
            }
            else {
                equationFinished = true;
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
