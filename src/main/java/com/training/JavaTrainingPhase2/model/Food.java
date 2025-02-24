package com.training.JavaTrainingPhase2.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("FOOD")
@NoArgsConstructor
@AllArgsConstructor
public class Food extends MenuItems{
    private Integer foodType; // 0: breakfast, 1: Lunch, 2: Dinner
}
