package de.axa.contentdelivery.aoc.adventofcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.axa.contentdelivery.aoc.adventofcode.factory.TicTacToeFactory;

// The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors. 
// The second column is what you are going to play: X for Rock, Y for Paper, and Z for Scissors
// A Y - Paper
// B X
// C Z
// -> Result should be 15 
// The winner of the whole tournament is the player with the highest score. 
// Your total score is the sum of your scores for each round. 
// The score for a single round is 
// - the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors) plus
// - the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
@Component
public class Day2 {

    @Autowired
    private static TicTacToeFactory factory;

    private final static Logger LOG = LoggerFactory.getLogger(Day1.class);

    public static void solve() {
        // What would your total score be if everything goes exactly according to your strategy guide?
        
    }
}
