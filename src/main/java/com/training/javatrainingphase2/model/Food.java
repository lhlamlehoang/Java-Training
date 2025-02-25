package com.training.javatrainingphase2.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("FOOD")
@NoArgsConstructor
public class Food extends MenuItems{
    private Integer foodType; // 0: breakfast, 1: Lunch, 2: Dinner


    public Food(Long id, String name, String description, double price, String note, byte[] img, int status, Integer foodType) {
        super(id, name, description, price, note, img, status);
        this.foodType = foodType;
    }

    public Food(Long id){
        super(id);
    }

    public Integer getFoodType() {
        return foodType;
    }

    public void setFoodType(Integer foodType) {
        this.foodType = foodType;
    }
}
