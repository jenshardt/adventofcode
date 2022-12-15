package de.axa.contentdelivery.aoc.adventofcode.model;

public class Rucksack {
    private String firstCompartment;
    private String secondCompartment;
    private char sharedItem;
    private Integer value;

    public String getFirstCompartment() {
        return firstCompartment;
    }
    public void setFirstCompartment(String firstCompartment) {
        this.firstCompartment = firstCompartment;
    }
    public String getSecondCompartment() {
        return secondCompartment;
    }
    public void setSecondCompartment(String secondCompartment) {
        this.secondCompartment = secondCompartment;
    }
    public char getSharedItem() {
        return sharedItem;
    }
    public void setSharedItem(char sharedItem) {
        this.sharedItem = sharedItem;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    
}
