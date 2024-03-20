package com.redhat.developers.entity;

import jakarta.json.bind.annotation.JsonbCreator;

public class FruityVice {

    private final String name;
    private final Nutritions nutritions;

    public FruityVice(String name, Nutritions nutritions) {
        this.name = name;
        this.nutritions = nutritions;
    }

    @JsonbCreator
    public static FruityVice of(String name, Nutritions nutritions) {
        return new FruityVice(name, nutritions);
    }

    public String getName() {
        return name;
    }

    public Nutritions getNutritions() {
        return nutritions;
    }

    public static class Nutritions {

        private final double carbohydrates;

        private final double calories;

        Nutritions(double carbohydrates, double calories) {
            this.carbohydrates = carbohydrates;
            this.calories = calories;
        }

        @JsonbCreator
        public static Nutritions of(double carbohydrates, double calories) {
            return new Nutritions(carbohydrates, calories);
        }

        public double getCarbohydrates() {
            return carbohydrates;
        }

        public double getCalories() {
            return calories;
        }

    }
}
