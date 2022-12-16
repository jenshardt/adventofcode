package de.axa.contentdelivery.aoc.adventofcode.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrateTerminal {
    private int terminalRows;
    private List<List<String>> terminal;

    private final static Logger LOG = LoggerFactory.getLogger(CrateTerminal.class);

    public void initTerminal(int amoutOfRows) {
        terminal = new ArrayList<List<String>>();
        
        for (int i = 1; i <= amoutOfRows; i++) {
            terminal.add(new ArrayList<String>());
        }

        terminalRows = amoutOfRows;
    }

    public void fillTerminal(List<String> stackLines) {

        // Read every line
        for (int lineCount = 0; lineCount < stackLines.size(); lineCount++) {
            String currentLine = stackLines.get(lineCount);
            int posInTerminal = 0;

            for (int posInCurrentLine = 1; 
                 posInCurrentLine <= 1+((terminalRows-1)*4); 
                 posInCurrentLine = posInCurrentLine+4) {
                String ch = Character.toString(currentLine.charAt(posInCurrentLine));
                if (!ch.isBlank()) {
                    terminal.get(posInTerminal).add(ch);
                }

                posInTerminal++;
            }
        }
    }

    public void printTerminal() {
        for (int i = 0; i < terminal.size(); i++) {
            LOG.info("Row {} has {} crates: {}", 
                i+1, 
                terminal.get(i).size(), 
                terminal.get(i).toString());
        }
    }

    public void applyProcedures(List<ProcedureStep> steps) {
        Iterator<ProcedureStep> iter = steps.iterator();

        while (iter.hasNext()) {
            ProcedureStep step = iter.next();
            LOG.info("Perfoming move of {} crates from row {} to row {}", step.getCratesToMove(), step.getMoveFrom(), step.getMoveTo());

            int rowFrom = step.getMoveFrom()-1;
            int rowTo = step.getMoveTo()-1;
            int amount = step.getCratesToMove();

            List<String> cratesToMove = new ArrayList<String>();
            // Remove from rowFrom
            for (int i = 0; i < amount; i++) {
                List<String> rowFromList = terminal.get(rowFrom);

                cratesToMove.add(rowFromList.get(rowFromList.size() - 1));
                rowFromList.remove(rowFromList.size() - 1);
            }

            // This one is needed for the CrateMover9001 ... ;-)
            Collections.reverse(cratesToMove);

            // Add to rowTo
            for (int i = 0; i < amount; i++) {
                terminal.get(rowTo).add(cratesToMove.get(i));
            }

            printTerminal();
        }
    }

    public void printSolution() {
        StringBuilder sol = new StringBuilder();

        for (int i = 0; i < terminal.size(); i++) {
            
            List<String> temp = terminal.get(i);
            sol.append(temp.get(temp.size()-1));
        }

        LOG.info("Solution is {}", sol.toString());
    }
}
