package de.axa.contentdelivery.aoc.adventofcode.factory;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import de.axa.contentdelivery.aoc.adventofcode.model.Game;

@Component
public class TicTacToeFactory {

    private static List<Game> gameList;

    private final static Logger LOG = LoggerFactory.getLogger(TicTacToeFactory.class);

    private static final String SPACE = " ";

    public TicTacToeFactory(@Value("classpath:day2_input.txt") Resource inputResource) throws Exception {
        gameList = new ArrayList<Game>();

        // Create Strategy Matrix
        LOG.info("Start creating strategy list ...");
        try (Stream<String> lines = Files.lines(inputResource.getFile().toPath())) {
            lines.forEach(line -> createGame(line));
        }
        LOG.info("Finished creating strategy list with {} games", gameList.size());
    }

    public static List<Game> getGameList() {
        return gameList;
    }

    private void createGame(String line) {
        Game game = new Game();

        String[] splitted = StringUtils.split(line, SPACE);
        if (splitted != null && splitted.length == 2) {
            game.setMyMove(splitted[0]);
            game.setOpponentMove(splitted[1]);
            game.setWrongResult(calculateWrongResult(splitted[0], splitted[1]));
            game.setCorrectResult(calculateCorrectResult(splitted[0], splitted[1]));
        }

        gameList.add(game);
    }

    /*
     * The first column is what your opponent is going to play: A for Rock, B for
     * Paper, and C for Scissors.
     * The second column is what you are going to play: X for Rock, Y for Paper, and
     * Z for Scissors
     * I win:
     * A Y
     * B Z
     * C X
     * 
     * Draw:
     * A X
     * B Y
     * C Z
     * 
     * The score for a single round is
     * - the score for the shape you selected (1 for Rock / X, 2 for Paper / Y, and
     * 3 for Scissors / Z) plus
     * - the score for the outcome of the round (0 if you lost, 3 if the round was a
     * draw, and 6 if you won).
     */
    private Integer calculateWrongResult(String opponent, String me) {
        Integer result = 0;
        result += calculateGameScore(opponent, me);
        result += calculateMyShape(me);
        
        return result;
    }

    /*second column says how the round needs to end: 
     - X means you need to lose, 
     - Y means you need to end the round in a draw, and 
     - Z means you need to win.
     */
    private Integer calculateCorrectResult(String opponent, String gameResult) {
        Integer result = 0;

        // Decide about my move
        String myMove = "";
        if ("X".equals(gameResult)) {
            // we lose
            if ("A".equals(opponent)) {
                myMove = "Z";
            } else if ("B".equals(opponent)) {
                myMove = "X";
            } else {
                myMove = "Y";
            }
        } else if ("Y".equals(gameResult)) {
            // draw
            result += 3;
            if ("A".equals(opponent)) {
                myMove = "X";
            } else if ("B".equals(opponent)) {
                myMove = "Y";
            } else {
                myMove = "Z";
            }
        } else {
            // we win
            result += 6;
            if ("A".equals(opponent)) {
                myMove = "Y";
            } else if ("B".equals(opponent)) {
                myMove = "Z";
            } else {
                myMove = "X";
            }
        }
        result += calculateMyShape(myMove);

        return result;
    }

    private Integer calculateGameScore(String opponent, String me) {
        if (("A".equals(opponent) && "Y".equals(me)) ||
                ("B".equals(opponent) && "Z".equals(me)) ||
                ("C".equals(opponent) && "X".equals(me))) {
            return 6;
        }

        if (("A".equals(opponent) && "X".equals(me)) ||
                ("B".equals(opponent) && "Y".equals(me)) ||
                ("C".equals(opponent) && "Z".equals(me))) {
            return 3;
        }
        
        return  0;
    }

    private Integer calculateMyShape(String me) {
        if ("X".equals(me)) {
            return 1;
        } else if ("Y".equals(me)) {
            return 2;
        } else {
            return 3;
        }
    }
}
