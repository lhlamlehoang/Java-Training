package com.training.javatrainingphase2.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("DRINK")
@NoArgsConstructor
public class Drink extends MenuItems{
    private boolean isAlcoholic;

    public Drink(Long id, String name, String description, double price, String note, byte[] img, int status, boolean isAlcoholic){
        super(id, name, description, price, note, img, status);
        this.isAlcoholic = isAlcoholic;
    }

    public Drink(Long id){
        super(id);
    }

    public boolean getAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }
}


