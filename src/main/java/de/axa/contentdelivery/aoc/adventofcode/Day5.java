package de.axa.contentdelivery.aoc.adventofcode;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.axa.contentdelivery.aoc.adventofcode.model.CrateTerminal;
import de.axa.contentdelivery.aoc.adventofcode.model.ProcedureStep;

@Component
public class Day5 {

    private static Resource input;
    private static boolean stackCompleted = false;
    private static List<String> stackLines = new ArrayList<String>();
    private static List<ProcedureStep> steps = new ArrayList<ProcedureStep>();
    private static CrateTerminal crateTerminal = new CrateTerminal();

    private final static Logger LOG = LoggerFactory.getLogger(Day5.class);

    public Day5(@Value("classpath:day5_input.txt") Resource inputResource) {
        input = inputResource;
    }

    public static void solve() throws Exception {
        // Read stack of crates and rearrangement procedure
        try (Stream<String> lines = Files.lines(input.getFile().toPath())) {
            lines.forEach(line -> createInput(line));
        }
        
        // Iterate over the procedure
        crateTerminal.applyProcedures(steps);

        // Find out which crates are on top
        crateTerminal.printSolution();
    }

    private static void createInput(String line) {
        if (line.isEmpty()) {
            stackCompleted = true;
        } else {
            if (!stackCompleted) { // line belongs to the stacks input
                processStacksInput(line);
            } else { // line belongs to the rearrangement procedure
                processProcedureInput(line);
            }
        }
    }

    private static void processProcedureInput(String line) {
        LOG.info("Procedure input {}", line);
        String[] chunks = line.split(" ");
        ProcedureStep step = new ProcedureStep();
        step.setCratesToMove(Integer.parseInt(chunks[1]));
        step.setMoveFrom(Integer.parseInt(chunks[3]));
        step.setMoveTo(Integer.parseInt(chunks[5]));

        steps.add(step);
    }

    private static void processStacksInput(String line) {
        if (line.contains("[")) {
            LOG.info("Procedure input {}", line);
            stackLines.add(line);
        } else {
            Collections.reverse(stackLines);
            int totalLines = line.replaceAll("[\\s]", "").length();
            LOG.info("We have {} lines to stack crates", totalLines);

            crateTerminal.initTerminal(totalLines); 
            crateTerminal.fillTerminal(stackLines);
            crateTerminal.printTerminal();
        }
    }
}
