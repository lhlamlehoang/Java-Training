package com.training.JavaTrainingPhase2.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("DRINK")
@NoArgsConstructor
@AllArgsConstructor
public class Drink extends MenuItems{
    private boolean isAlcoholic;
}


