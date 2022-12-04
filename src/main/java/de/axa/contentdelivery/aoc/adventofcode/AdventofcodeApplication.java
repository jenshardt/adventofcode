package de.axa.contentdelivery.aoc.adventofcode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventofcodeApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(AdventofcodeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
		Day1.solve();
		Day2.solve();
	}
}
