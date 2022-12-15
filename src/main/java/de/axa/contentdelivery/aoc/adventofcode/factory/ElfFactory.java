package de.axa.contentdelivery.aoc.adventofcode.factory;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.axa.contentdelivery.aoc.adventofcode.model.Elf;

@Component
public class ElfFactory {

    private static ArrayList<Elf> elfList;

	private final static Logger LOG = LoggerFactory.getLogger(ElfFactory.class);

    public ElfFactory(@Value("classpath:day1_input.txt") Resource inputResource) throws IOException {
		// Read file
        elfList = new ArrayList<Elf>();

        // Create ElfList
        try (Stream<String> lines = Files.lines(inputResource.getFile().toPath())) {
            lines.forEach(line -> setElf(line));
        }
    }

    public static Stream<Elf> getElfStream() {
        return elfList.stream();
    }

	private void setElf(String line) {
		// Startup
		if (elfList.isEmpty()) {
			elfList.add(new Elf(Integer.valueOf(line)));
		}
		
		if (line.isBlank()) {
			elfList.add(new Elf());
		} else {
			elfList.get(elfList.size()-1).addFood(Integer.valueOf(line));
		}
	}
}
