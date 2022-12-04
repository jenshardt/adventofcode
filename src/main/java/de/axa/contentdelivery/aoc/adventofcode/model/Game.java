package de.axa.contentdelivery.aoc.adventofcode.model;

public class Game {
    private String myMove;
    private String opponentMove;
    private Integer result;

    public String getMyMove() {
        return myMove;
    }
    
    public void setMyMove(String myMove) {
        this.myMove = myMove;
    }

    public String getOpponentMove() {
        return opponentMove;
    }

    public void setOpponentMove(String opponentMove) {
        this.opponentMove = opponentMove;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }    
}
