package com.khelenyuk.model;

import java.io.Serializable;
import java.util.Objects;

public class MealNumber implements Serializable {
   private int id;
   private String name;

   public MealNumber(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealNumber that = (MealNumber) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MealNumber{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
