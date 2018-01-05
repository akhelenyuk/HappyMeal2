package entity;

import java.io.Serializable;

public class MealNumber implements Serializable {
   private int id;
   private String name;

   public MealNumber(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }
}
