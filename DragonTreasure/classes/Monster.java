package classes;

public class Monster {
    private String name;
    private int healthPoints;
    private int attackPower;
    

    public Monster(String name, int healthPoints, int attackPower) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
    }

    // Metod för att hämta monsterets namn
    public String getName() {
        return this.name;
    }

    // Metod för att monstret ska attackera
    public int attack() {
        return this.attackPower;
    }
    
    public boolean isAlive(){
        return this.healthPoints > 0;
    }

    // Metod för att ta skada
    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            this.healthPoints = 0;
        }
    }

    public void attack(Player player) {
        System.out.println("You take " + this.attackPower + " dmg");
        player.takeDamage(attackPower);
    }

    // Getter för healthPoints
    public int getHealthPoints() {
        return this.healthPoints;
    }
}

