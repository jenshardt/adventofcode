package de.axa.contentdelivery.aoc.adventofcode;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.axa.contentdelivery.aoc.adventofcode.factory.ElfFactory;
import de.axa.contentdelivery.aoc.adventofcode.model.Elf;

@Component
public class Day1 {
    
    @Autowired
    private static ElfFactory factory;

    private final static Logger LOG = LoggerFactory.getLogger(Day1.class);

    public static void solve() {
        // Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
        Comparator<Elf> elfComparator = Comparator.comparing(Elf::getFood);
        Optional<Elf> topElf = factory.getElfStream().sorted(elfComparator.reversed()).collect(Collectors.maxBy(elfComparator));

        if (topElf.isPresent()) {
            LOG.info("The elf with the most food has {} food", topElf.get().getFood());
        } else {
            LOG.error("not expected ...");
        }

        // Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
        Integer foodTop3Elfes = factory.getElfStream().sorted(elfComparator.reversed()).limit(3).collect(Collectors.summingInt(Elf::getFood));
        LOG.info("The top 3 elfes have {} food together", foodTop3Elfes);
    }
}
