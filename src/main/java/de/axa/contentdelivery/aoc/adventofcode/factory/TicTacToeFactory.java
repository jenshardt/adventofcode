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

import de.axa.contentdelivery.aoc.adventofcode.model.Game;

// The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors. 
// The second column is what you are going to play: X for Rock, Y for Paper, and Z for Scissors
// A Y
// B X
// C Z
// -> Result should be 15 

@Component
public class TicTacToeFactory {

    private List<Game> gameList;

    private final static Logger LOG = LoggerFactory.getLogger(TicTacToeFactory.class);

    public TicTacToeFactory(@Value("classpath:day2_input.txt") Resource inputResource) throws Exception {
        gameList = new ArrayList<Game>();
        
        // Create Strategy Matrix
		LOG.info("Start creating strategy list ...");
        try (Stream<String> lines = Files.lines(inputResource.getFile().toPath())) {
            lines.forEach(line -> createGame(line));
        } 
		LOG.info("Finished creating strategy list");
    }

    public List<Game> getGameList() {
        return gameList;
    }

    private void createGame(String line) {

        
    }


}
