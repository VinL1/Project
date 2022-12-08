public class test {
    public static void main (String [] args) {
        Calc banana = new Calc ("9*9-9");
        double lol = banana.equationWithIn("9-9*9+1","+");
        String local = "9*9";
        String eq = "9-9*9+1";
        eq = eq.replace (local, 81 + "");
        System.out.println(banana.equationWithIn(eq,"+"));
    }
}
