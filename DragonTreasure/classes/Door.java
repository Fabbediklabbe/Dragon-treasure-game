package classes;

public class Door {
   private String direction;
   private Room destination;
   private boolean isLocked;
   private String keyName; // Namnet på nyckeln som kan låsa upp denna dörr


   public Door(String var1, Room var2, boolean isLocked, String keyName) {
      this.direction = var1;
      this.destination = var2;
      this.isLocked = isLocked;
      this.keyName = keyName;
   }


   public String getDirection() {
      return this.direction;
   }


   public Room getDestination() {
      return this.destination;
   }

   public boolean isLocked() {
      return isLocked;
   }

   public void unlock(String key) {
      if (this.keyName.equals(key)) {
         this.isLocked = false;
      }
   }

}
