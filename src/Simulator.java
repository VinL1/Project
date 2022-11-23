public class Simulator {
    private int balance;
    private double multiplier;
    private String [] inventory = {};
    private String [] animals = {"dog", "cat", "goldfish", "whale", "unicorn", "dragon", "fly", "cockroach"};

    public Simulator (int balance) {
        this.balance = balance;
    }
    public Simulator (int balance, double multiplier) {
        this.balance = balance;
        this.multiplier = multiplier;
    }

    public String roll (int times) {
        for (int i = 1; i <= times; i ++) {
            String item = "";
            String name = animals[(int) (Math.random () * animals.length + 1)];
            int rarityI = (int) (Math.random () * 1001);
            String rarityS = "";

            if (rarityI <= 6) {rarityI = 1;}
            else if (rarityI <= 56) {rarityI = 2;}
            else if (rarityI <= 156) {rarityI = 3;}
            else if (rarityI <= 306) {rarityI = 4;}
            else if (rarityI <= 706) {rarityI = 5;}
            else {rarityI = 6;}

            if (rarityI == 1) {rarityS = "Legendary";}
            if (rarityI == 2) {rarityS = "Mythic";}
            if (rarityI == 3) {rarityS = "Epic";}
            if (rarityI == 4) {rarityS = "Rare";}
            if (rarityI == 5) {rarityS = "Common";}
            if (rarityI == 6) {rarityS = "Nothing";}

            for (int i = 0; i < inventory.length; i ++) {
                if (inventory [i].equals(name)) {
                    return (name + "(" + rarityS + ")" + " [1]");
                }
                else if (inventory[i].indexOf(name) != -1) {
                    return (name + "(" + rarityS + ")" + " [" + detectDup(inventory[i]) + 1 + "]");
                }
                else {
                    return (name + "(" + rarityS + ")");
                }
            }
        }
    }

    public void nextDay () {
        System.out.println ("Your savings were $" + balance);
        balance -= 10;
        System.out.println ("A day has past, and you're now at $" + balance);
    }

    public int detectDup (String name) {
        return (Integer.parseInt(name.substring(name.indexOf("[") + 1, name.indexOf("]"))));
    }

    public void 
}
