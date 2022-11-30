public class Simulator {
    private int balance;
    private double multiplier;
    private String [] inventory = {};
    private String [] animals = {"puppy", "giraffe", "kitten", "whale", "dragonfly", "elephant"};

    public Simulator (int balance) {
        this.balance = balance;
    }
    public Simulator (int balance, double multiplier) {
        this.balance = balance;
        this.multiplier = multiplier;
    }

    public String rollAnimal (int times) {
        for (int i = 1; i <= times; i++) {
            String item = "";
            String name = animals[(int) (Math.random() * animals.length + 1)];
        }
        return "a";
    }

    public String rollRarity (String animal) {
        int rarityI = (int) (Math.random() * 1001);
        String rarityS = "";

        if (rarityI <= 6) {rarityI = 1;
        } else if (rarityI <= 56) {rarityI = 2;
        } else if (rarityI <= 156) {rarityI = 3;
        } else if (rarityI <= 306) {rarityI = 4;
        } else if (rarityI <= 706) {rarityI = 5;
        } else {rarityI = 6;}

        if (rarityI == 1) {rarityS = "Legendary";}
        if (rarityI == 2) {rarityS = "Mythic";}
        if (rarityI == 3) {rarityS = "Epic";}
        if (rarityI == 4) {rarityS = "Rare";}
        if (rarityI == 5) {rarityS = "Common";}
        if (rarityI == 6) {rarityS = "Nothing";}

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].equals(animal)) {
                return (animal + "(" + rarityS + ")" + " [1]");
            } else if (inventory[i].indexOf(animal) != -1) {
                return (animal + "(" + rarityS + ")" + " [" + numOfDup(inventory[i]) + 1 + "]");
            } else {
                return (animal + "(" + rarityS + ")");
            }
        }
        return "a";
    }

    public void nextDay () {
        System.out.println ("A day has passed by.");
        System.out.println ("Your past balance was: " + balance);
        balance += 200;
        System.out.println ("Your balance now is: " + balance);
    }

    public int numOfDup (String name) {
        if (name.indexOf("[") != -1) {
            return (Integer.parseInt(name.substring(name.indexOf("[") + 1, name.indexOf("]"))));
        }
        else {
            return 0;
        }
    }
}
