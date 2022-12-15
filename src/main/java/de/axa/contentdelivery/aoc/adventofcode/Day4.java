package de.axa.contentdelivery.aoc.adventofcode;

import java.nio.file.Files;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.axa.contentdelivery.aoc.adventofcode.model.Range;

@Component
public class Day4 {

    private static Resource input;

    private static String LINE = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

    private static int pairsWhereOneElfIsNotNeeded = 0;
    private static int pairsWhereThereAreOverlappingSections = 0;

    private final static Logger LOG = LoggerFactory.getLogger(Day4.class);

    public Day4(@Value("classpath:day4_input.txt") Resource inputResource) {
        input = inputResource;
    }

    public static void solve() throws Exception {
        try (Stream<String> lines = Files.lines(input.getFile().toPath())) {
            lines.forEach(line -> analyzeList(line));
        }

        LOG.info("There are {} pairs where one elf is not needed ... ", pairsWhereOneElfIsNotNeeded);
        LOG.info("There are {} pairs where there are overlapping sections ... ", pairsWhereThereAreOverlappingSections);
    }

    private static void analyzeList(String line) {
        String ranges[] = line.split(",");

        Range range1 = createRange(ranges[0]);
        LOG.info("{} {}-{}", createLine(range1), range1.getLowerEnd(), range1.getUpperEnd());
        Range range2 = createRange(ranges[1]);
        LOG.info("{} {}-{}\n", createLine(range2), range2.getLowerEnd(), range2.getUpperEnd());

        if (isOneRangeNotNeeded(range1, range2)){
            LOG.info("There is one elf not needed here ...");
            pairsWhereOneElfIsNotNeeded += 1;
        }

        if (isOverlappingAtAll(range1, range2)) {
            LOG.info("There are overlapping sections here ...");
            pairsWhereThereAreOverlappingSections += 1;
        }
    }

    private static boolean isOverlappingAtAll(Range range1, Range range2) {
        if (range1.getLowerEnd() <= range2.getUpperEnd() &&
            range1.getUpperEnd() >= range2.getLowerEnd()) {
            return true;
        }

        if (range2.getLowerEnd() <= range1.getUpperEnd() &&
            range2.getUpperEnd() >= range1.getLowerEnd()) {
            return true;
        }

        return false;
    }

    private static boolean isOneRangeNotNeeded(Range range1, Range range2) {
        if (range1.getLowerEnd() >= range2.getLowerEnd() &&
                range1.getUpperEnd() <= range2.getUpperEnd()) {
            return true;
        }

        if (range1.getLowerEnd() <= range2.getLowerEnd() &&
                range1.getUpperEnd() >= range2.getUpperEnd()) {
            return true;
        }

        return false;
    }

    private static String createLine(Range range) {
        StringBuilder temp = new StringBuilder(LINE);
        for(int i = 0; i < 99; i++) {
            if (i < range.getLowerEnd()-1 || i > range.getUpperEnd()-1) {
                temp.setCharAt(i, '.');
            }
        }

        return temp.toString();
    }

    private static Range createRange(String in) {
        Range r = new Range();
        String[] temp = in.split("-");
        if (temp.length == 2) {
            r.setLowerEnd(Integer.parseInt(temp[0]));
            r.setUpperEnd(Integer.parseInt(temp[1]));
        }

        return r;
    }
}
