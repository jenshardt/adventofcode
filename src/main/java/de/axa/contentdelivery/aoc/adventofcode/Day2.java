package de.axa.contentdelivery.aoc.adventofcode;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.axa.contentdelivery.aoc.adventofcode.factory.ElfFactory;
import de.axa.contentdelivery.aoc.adventofcode.factory.TicTacToeFactory;
import de.axa.contentdelivery.aoc.adventofcode.model.Game;

@Component
public class Day2 {

    private final static Logger LOG = LoggerFactory.getLogger(Day2.class);

    private static Integer totalResult = 0;

    public static void solve() {
        // What would your total score be if everything goes exactly according to your strategy guide?
        Consumer<Game> action = (game) -> addScore(game.getWrongResult());
        TicTacToeFactory.getGameList().iterator().forEachRemaining(action);
        LOG.info("The total score is {}", totalResult);

        // Following the Elf's instructions for the second column, 
        // what would your total score be if everything goes exactly according to your strategy guide?
        totalResult = 0;
        action = (game) -> addScore(game.getCorrectResult());
        TicTacToeFactory.getGameList().iterator().forEachRemaining(action);
        LOG.info("The correct total score is now {}", totalResult);
    }

    private static void addScore(Integer result) {
        totalResult += result;
    }
}
