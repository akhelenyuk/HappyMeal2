package entity;

import java.io.Serializable;

public class Meal implements Serializable{
    private int id;
    private int userId;
//    private Date date;
    private int productId;
    private int weight;
    private MealNumber mealNumber;

    public Meal(int userId, int productId, int weight, int mealNumber) {
        this.userId = userId;
        this.productId = productId;
        this.weight = weight;
        this.mealNumber = new MealNumber(mealNumber);
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getWeight() {
        return weight;
    }

    public MealNumber getMealNumber() {
        return mealNumber;
    }
}
