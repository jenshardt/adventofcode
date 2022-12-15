package de.axa.contentdelivery.aoc.adventofcode;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.axa.contentdelivery.aoc.adventofcode.model.Rucksack;

@Component
public class Day3 {

    private static List<Rucksack> rucksackList;
    private static Resource input;
    private static List<String> groupBags;
    private static List<Integer> groupBagsValues;

    private final static Logger LOG = LoggerFactory.getLogger(Day3.class);

    public Day3(@Value("classpath:day3_input.txt") Resource inputResource) {
        input = inputResource;
    }

    public static void solve() throws Exception {
        // Create List of rucksacks
        rucksackList = new ArrayList<Rucksack>();
        try (Stream<String> lines = Files.lines(input.getFile().toPath())) {
            lines.forEach(line -> addNewRucksack(line));
        }

        // Find the item type that appears in both compartments of each rucksack.
        // What is the sum of the priorities of those item types?
        Integer sumOfPriorities = rucksackList.stream().collect(Collectors.summingInt(Rucksack::getValue));
        LOG.info("Part 1 - The sum of all items is {}", sumOfPriorities);

        // Find the item type that corresponds to the badges of each three-Elf group.
        // What is the sum of the priorities of those item types?
        groupBags = new ArrayList<String>();
        groupBagsValues = new ArrayList<Integer>();
        try (Stream<String> lines = Files.lines(input.getFile().toPath())) {
            lines.forEach(line -> calculateGroupResult(line));
        }

        int groupBagsValue = groupBagsValues.stream().mapToInt(Integer::intValue).sum();
        LOG.info("Part 2 - All groups have a total of {} item types WHICH IS WRONG!!!", groupBagsValue);
    }

    private static void calculateGroupResult(String line) {
        LOG.info("We have the line {}", line);
        if (groupBags.size() == 2) {
            char ch = ' ';
            LOG.info("Group finished, starting calculating");
            // find common element by iterating over the current line and comparing with all chars of the previous 2
            for (int i = 0; i < line.length() - 1; i++) {
                ch = line.charAt(i);
                if (groupBags.get(0).indexOf(ch) != -1 &&
                        groupBags.get(1).indexOf(ch) != -1) {
                    break;
                }
            }

            // calculate value
            if (Character.isLowerCase(ch)) {
                int count = (int)(ch) - 96;
                LOG.info("Item is {}, value is {}", ch, count);
                groupBagsValues.add(count);
            } else {
                int count = (int)(ch) - 64 + 26;
                LOG.info("Item is {}, value is {}", ch, count);
                groupBagsValues.add(count);
            }

            // empty group
            groupBags.clear();
        } else {
            groupBags.add(line);
        }
    }

    private static void addNewRucksack(String line) {
        Rucksack rucksack = new Rucksack();

        // Split into two parts
        int middle = line.length() / 2;
        rucksack.setFirstCompartment(line.substring(0, middle));
        rucksack.setSecondCompartment(line.substring(middle));

        // find item in both parts
        for (int i = 0; i < middle; i++) {
            char ch = rucksack.getFirstCompartment().charAt(i);
            if (rucksack.getSecondCompartment().indexOf(ch) != -1) {
                rucksack.setSharedItem(ch);
                /*LOG.info("We have {} and {}, the common item is {}",
                        rucksack.getFirstCompartment(),
                        rucksack.getSecondCompartment(),
                        rucksack.getSharedItem()); */
                break;
            }
        }

        // calculate value
        // Lowercase item types a through z have priorities 1 through 26.
        // Uppercase item types A through Z have priorities 27 through 52.
        int value = rucksack.getSharedItem();
        if (97 <= value && value <= 122) {
            value -= 96;
        }
        if (65 <= value && value <= 90) {
            value -= 38;
        }
        rucksack.setValue(value);

        // add to list
        rucksackList.add(rucksack);
    }
}
