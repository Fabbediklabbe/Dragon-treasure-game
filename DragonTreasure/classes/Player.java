package classes;

import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private int healthPoints;
    private int attackPower;
    private ArrayList<Items> inventory;

    public Player(Room var1, int healthPoints) {
        this.currentRoom = var1;
        this.healthPoints = 10; // Bas HP
        this.attackPower = 1;  // Bas atk
        this.inventory = new ArrayList<Items>();
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
            System.out.println("You died!");
        }
    }

    // Metod för att öka HP om man vill lägga till en potion eller nått
    public void heal(int amount) {
        this.healthPoints += amount;
    }
        
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room var1) {
        this.currentRoom = var1;
    }

    public boolean isAlive(){
        return this.healthPoints > 0;
    }

    public void attack(Monster monster) { // Attack logik
        System.out.println("You dealt " + this.attackPower + " dmg!");
        monster.takeDamage(attackPower);
    }

    // Metod för att lägga till ett föremål i spelarens inventory
    public void addItem(Items item) {
        inventory.add(item);
        if (item.getType().equals("sword")) {
            attackPower += item.getEffect();
        }
    }

    // Metod för att använda en nyckel på en dörr
    public void useKey(Door door) {
        for (Items item : inventory) {
            if (item.getType().equals("key") && door.isLocked()) {
                door.unlock(item.getName());
                if (!door.isLocked()) {
                    System.out.println("Door unlocked!");
                    return;
                }
            }
        }
        System.out.println("You do not have a key that fits this lock.");
    }

    // Metod för att använda ett föremål från spelarens inventory
    public void useItem(String itemName) {
        for (Items item : inventory) {
            if (item.getName().equals(itemName)) {
                item.useItem(this);
                // inventory.remove(item);
                break;
            }
        }
    }

    // Getters och setters
    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}

