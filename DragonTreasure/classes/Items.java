package classes;

public class Items {
    private String name;
    private String type;
    private int effect;

    // Constructor för vapen
    public Items(String name, String type, int effect) {
        this.name = name;
        this.type = type;
        this.effect = effect;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getEffect() {
        return effect;
    }

    // Metod för att använda vapnet
    public void useItem(Player player) {

        if (this.type.equals("sword")) {
            player.setAttackPower(player.getAttackPower() + this.effect);
        }
    }
}
