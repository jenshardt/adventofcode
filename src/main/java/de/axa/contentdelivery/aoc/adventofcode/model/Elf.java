package de.axa.contentdelivery.aoc.adventofcode.model;

public class Elf {
    private Integer food;

    public Elf() {
        food = 0;
    }

    public Elf(int newFood) {
        food = newFood;
    }

    public Integer getFood() {
        return food;
    }

    public void addFood(Integer newFood) {
        food += newFood;
    }
}
