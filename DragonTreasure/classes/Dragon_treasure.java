package classes;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Dragon_treasure {
    public void setupGame() {
        
        Monster monster = new Monster("Monster", 5, 1); // Skapa ett monster med 5 HP och 1 attackskada
        Monster dragon = new Monster("Dragon", 20, 5);

        //Skapar beskrivningar för samtliga rum
        Room outsideCave = new Room("You stand outside a cave. It smells of sulfur.\n");
        Room torchRoom = new Room("\n\n\n\n\nYou are inside the cave. It's lit by a few torches.\n");
        Room treasureRoom = new Room("\n\n\n\n\nyou are in the treasure room!\n"); 
        Room keyRoom = new Room("\n\n\n\n\nYou look around and see a skeleton sitting against the western wall\n"
        + "It has a key hanging around its neck, maybe it could be useful!\n");
        Room swordRoom = new Room("\n\n\n\n\nYou enter a large room that seems to have been abandoned ages ago \n"
        + "You catch a glimpt of something shiny poking out of the eastern wall. Is that a sword?!\n");
        Room exitCave = new Room("\n\n\n\n\nYou have succesfully left the cave\nYou win!");

        Room monsterRoom = new Room(monster);
        Room dragonRoom = new Room(dragon);

        Items sword = new Items("Long Sword", "sword", 10); // Skapar ett svärd
        swordRoom.addItem(sword); // Lägger till svärdet i "swordRoom"
        Items silverKey = new Items("Silver Key", "key", 0);
        keyRoom.addItem(silverKey);
        Items goldKey = new Items("Gold Key", "key", 0);
        dragonRoom.addItem(goldKey);


        //Skapar dörrar till samtliga rum samt identifierar vilket rum varje dörr leder till
        outsideCave.addDoor("e", torchRoom, false, null);
        torchRoom.addDoor("w", outsideCave, false, null);
        torchRoom.addDoor("n", treasureRoom, true, "Silver Key");
        torchRoom.addDoor("s", keyRoom, false, null);
        treasureRoom.addDoor("s", torchRoom, false, null);
        treasureRoom.addDoor("e", monsterRoom, false, null);
        keyRoom.addDoor("n", torchRoom, false, null);
        keyRoom.addDoor("e", swordRoom, false, null);
        swordRoom.addDoor("w", keyRoom, false, null);
        swordRoom.addDoor("s", dragonRoom, false, null);
        swordRoom.addDoor("n", monsterRoom, false, null);
        dragonRoom.addDoor("n", swordRoom, false, null);
        monsterRoom.addDoor("e", exitCave, true, "Gold Key");
        monsterRoom.addDoor("w", treasureRoom, false, null);
        monsterRoom.addDoor("s", swordRoom, false, null);
        

        Player player = new Player(outsideCave, 10);

        //Meddelande för spelets start
        System.out.println("Welcome to Dragon Treasure!");
        System.out.print("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        System.out.println("\nWelcome, " + playerName + ", to your treasure hunt.");
        System.out.println("\nYour stats are: \nAtk - " + player.getAttackPower() + ". HP - " + player.getHealthPoints());
        System.out.println("\nType 'inv' at any time to view your inventory\nType 'stats' at any time to view your stats\n\n");
        
        //Förklarar för spelaren att den har möjlighet att lämna spelet, detta loopas för varje gång spelaren byter rum
        while (true) {
            Room currentRoom = player.getCurrentRoom(); // Hämta spelarens nuvarande rum
            
            if ((player.getCurrentRoom() != monsterRoom) && (player.getCurrentRoom() != dragonRoom)) { // Work around, annars blir roomDesc Null och crashar programmet
                player.getCurrentRoom().doNarrative(player);
            }
            player.getCurrentRoom().initiateFight(player);

            // Anropar enterRoom-metoden för att hantera föremål i rummet
            enterRoom(player, currentRoom);

            System.out.println("\nEnter the direction to move (or 'q' to quit):");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("q")) {
                System.out.println("Thanks for playing. Goodbye!");
                break;
            }

            // Spelaren ska kunna se sitt "inventory"
            if (input.equals("inv")) {
                ArrayList<Items> inventory = player.getInventory();
                if (inventory.isEmpty()) {
                    System.out.println("Your inventory is empty.");
                } else {
                    System.out.println("Your inventory contains:");
                    for (Items item : inventory) {
                        System.out.println(item.getName());
                    }
                }
            }
            if (input.equals("stats")) {
                System.out.println("Atk - " + player.getAttackPower() + ". HP - " + player.getHealthPoints());
            }
        
            //Uppdaterar spelarens nuvarande rum baserat på angiven riktning
            player.setCurrentRoom(player.getCurrentRoom().navigate(input, player));

        }

        
        
        scanner.close();
    }


    public void enterRoom(Player player, Room room) {
        // Skapa en iterator för att säkert iterera över föremålen
        Iterator<Items> iterator = room.getItems().iterator();
    
        while (iterator.hasNext()) {
            Items item = iterator.next();
            System.out.println("A " + item.getName() + " has been added to your inventory!");
            player.addItem(item);
            iterator.remove(); // Ta bort föremålet från rummet säkert
            break; // Bryt loopen efter att ha hanterat ett föremål
        }
    }
}
