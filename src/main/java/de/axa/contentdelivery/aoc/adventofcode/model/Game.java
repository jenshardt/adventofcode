package de.axa.contentdelivery.aoc.adventofcode.model;

public class Game {
    private String myMove;
    private String opponentMove;
    private Integer wrongResult;
    private Integer correctResult;

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

    public Integer getWrongResult() {
        return wrongResult;
    }

    public void setWrongResult(Integer result) {
        this.wrongResult = result;
    }    

    public Integer getCorrectResult() {
        return correctResult;
    }

    public void setCorrectResult(Integer result) {
        this.correctResult = result;
    }    
}
