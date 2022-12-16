package de.axa.contentdelivery.aoc.adventofcode.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureStep {
    private int moveFrom;
    private int moveTo;
    private int cratesToMove;
}
