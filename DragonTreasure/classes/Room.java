package classes;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Room {
    private String roomDesc;
    private Monster monster;
    private HashMap<String, Door> doors; // Lagring av dörrar
    private ArrayList<Items> items = new ArrayList<>(); // Lagring av föremål
    private Scanner scanner = new Scanner(System.in);
    private boolean visited = false;
    

    public Room(String var1) {
        this.roomDesc = var1;
        this.doors = new HashMap<String, Door>(); // Initialisera doors här
        this.items = new ArrayList<>();
    }


    public void addDoor(String var1, Room var2, boolean isLocked, String keyName) {
        this.doors.put(var1, new Door(var1, var2, isLocked, keyName));
    }

    // Metod för att få en dörr baserat på riktning
    public Door getDoor(String var1) {
        return doors.get(var1);
    }

    // Metod för att lägga till ett föremål i ett rum
    public void addItem(Items item) {
        items.add(item);
    }

    // Metod för att ta bort ett föremål från ett rum
    public void removeItem(Items itemToRemove) {
        Iterator<Items> iterator = items.iterator();
        while (iterator.hasNext()) {
            Items item = iterator.next();
            if (item.equals(itemToRemove)) {
                iterator.remove();
                break;
            }
        }
    }

    // Metod för att hämta lista av föremål
    public ArrayList<Items> getItems() {
        return items;
    }

    public void doNarrative(Player player) {
        if (this.roomDesc.contains("treasure") && visited == false) {
            System.out.println(
            "                  _.--.\n"+
            "              _.-'_:-'||\n"+
            "          _.-'_.-::::'||\n"+
            "     _.-:'_.-::::::'  ||\n"+
            "   .'`-.-:::::::'     ||\n"+
            "  /.'`;|:::::::'      ||_\n"+
            " ||   ||::::::'      _.;._'-._\n"+
            " ||   ||:::::'   _.-!oo @.!-._'-.\n"+
            " \'.  ||:::::.-!() oo @!()@.-'_.||\n"+
            "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n"+
            "     `>'-.!@%()@'@_%-'_.-o _.|'||\n"+
            "      ||-._'-.@.-'_.-' _.-o  |'||\n"+
            "      |'-._   || |'|_.-'_.-' |'||\n"+
            "      || '-.]=|| |'|      o  |'||\n"+
            "      ||      || |'|        _| ';\n"+
            "      ||=[ '-._.-\\U/.-'    o |'||\n"+
            "      ||      || |'|    _.-'_.-'\n"+
            "      '-._'-.|| |' `_.-'\n"+
            "           '-.||_/.-'\n" + "You found the treasure! Congratulations!\n");
            visited = true; // Så att den inte visas varje gång
        }
        
        else {
            System.out.println(this.roomDesc);
            System.out.println("Doors available:");
            for (String direction : this.doors.keySet()) {
                System.out.println(" - " + direction);
            }
        }
    }



    // Konstruktor som tar emot ett Monster objekt
    public Room(Monster monster) {
        this.monster = monster;
        this.doors = new HashMap<>();
    }

    // Metod för att hämta monster som finns i rummet
    public Monster getMonster() {
        return this.monster;
    }

    // Metod för att kontrollera om det finns ett monster i rummet
    public boolean hasMonster() {
        return this.monster != null;
        
    }

    // Kontroll för att öppna dörrar
    public Room navigate(String direction, Player player) {
        if (this.doors.containsKey(direction)) {
            Door door = this.doors.get(direction);
            if (door.isLocked()) {
                player.useKey(door); // Försöker låsa upp dörren 
            }
            if (!door.isLocked()) {
                return door.getDestination(); // Om dörren är upplåst, navigera till det nya rummet
            } 
            else 
            {
                return this; // Om dörren fortfarande är låst - stanna i samma rum
            }
        } 
        else {
            System.out.println("There is no door in that direction.");
            return this;
        }
    }

    // Initilaiserar en fight med ett monster
    public void initiateFight(Player player) {
        if (hasMonster()) {
            String monsterName = this.monster.getName();
            if (monsterName.equals("Dragon")) {
                System.out.println(
                        "                                       \\/\n" +
                        "                                       ^`'.\n" + 
                        "                                       ^   `'.\n" + 
                        "             (                         ^      `'.\n" + 
                        "           )  )        \\/              ^         `'.\n" + 
                        "         (   ) @       /^              ^            `'.\n" + 
                        "       )  )) @@  )    /  ^             ^               `'.\n" + 
                        "      ( ( ) )@@      /    ^            ^                  `'.\n" + 
                        "    ))  ( @@ @ )    /      ^           ^                     `'.\n" + 
                        "   ( ( @@@@@(@     /       |\\_/|,      ^                        `'.\n" + 
                        "  )  )@@@(@@@     /      _/~/~/~|C     ^                           `'.\n" + 
                        "((@@@(@@@@@(     /     _(@)~(@)~/\\C    ^                              `'.\n" + 
                        "  ))@@@(@@)@@   /     /~/~/~/~/`\\~`C   ^             __.__               `'.\n" +
                        "   )@@@@(@@)@@@(     (o~/~o)^,) \\~ \\C  ^          .' -_'-'\"...             `.\n" + 
                        "    ( (@@@)@@@(@@@@@@_~^~^~,-/\\~ \\~ \\C/^        /`-~^,-~-`_~-^`;_           `.\n" + 
                        "      @ )@@@(@@@@@@@   \\^^^/  (`^\\.~^ C^.,  /~^~^~^/_^-_`~-`~-~^\\- /`'-./`'-. ;\n" + 
                        "       (@ (@@@@(@@      `''  (( ~  .` .,~^~^-`-^~`/'^`-~ _`~-`_^-~\\         ^^\n" + 
                        "           @jgs@             (((` ~ .-~-\\ ~`-_~`-/_-`~ `- ~-_- `~`;\n" + 
                        "          /                 /~((((` . ~-~\\` `  ~ |:`-_-~_~`  ~ _`-`;\n" + 
                        "         /                 /~-((((((`.\\-~-\\ ~`-`~^\\- ^_-~ ~` -_~-_`~`;\n" + 
                        "        /                 /-~-/(((((((`\\~-~\\~`^-`~`\\ -~`~\\-^ -_~-_`~-`;\n" + 
                        "       /                 /~-~/  `((((((|-~-|((`.-~.`Y`_,~`\\ `,- ~-_`~-`;\n" + 
                        "      /              ___/-~-/     `\"\"\"\"|~-~|\"''    /~-^ .'    `:~`-_`~-~`;\n" + 
                        "     /         _____/  /~-~/           |-~-|      /-~-~.`      `:~^`-_`^-:\n" + 
                        "    /    _____/        ((((            ((((      (((((`           `:~^-_~-`;\n" + 
                        "    \\___/                                                          `:_^-~`;\n" + 
                        "                                                                    `:~-^`:\n" + 
                        "                                                                  ,`~-~`,`\n" + 
                        "                                                                 ,\"`~.,'\n" + 
                        "                                                               ,\"-`,\"`\n" + 
                        "                                                             ,\"_`,\"\n" + 
                        "                                                            ,\",\"`\n" + 
                        "                                                         ;~-~_~~;\n" + 
                        "                                                          '. ~.'\n" +
                        "YOU HAVE ENCOUNTERED THE DRAGON!");
            }
            else {
                System.out.println("A monster appears!");
            }

            // Stridslogik
            while (true) {
                System.out.println("Press 'a' to attack the monster: ");
                String userChoice = scanner.nextLine(); // Läser användarens input

                if (userChoice.equals("a")) {
                    player.attack(this.monster);
                    System.out.println("Monster's HP after attack: " + this.monster.getHealthPoints());
            
                    if (!this.monster.isAlive()) {
                        System.out.println("Monster defeated!");
                        this.monster = null; // Tar bort monstret från rummet
            
                        System.out.println("Doors available:");
                        for (String direction : this.doors.keySet()) {
                            System.out.println(" - " + direction);
                        }
                        break; // Avsluta loopen
                    }
            
                    this.monster.attack(player);
                    System.out.println("Player's HP after attack: " + player.getHealthPoints() + "\n");
            
                    if (!player.isAlive()) {
                        System.out.println("You have been defeated by the monster!");
                        break; // Avbryter loopen om spelaren dör
                    }
                } 
                
                else {
                    System.out.println("Invalid command. Please try again.");
                }
            }
            
            
    
        }
        
    }
    
}

