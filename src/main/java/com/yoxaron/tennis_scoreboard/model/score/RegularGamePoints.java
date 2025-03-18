package com.yoxaron.tennis_scoreboard.model.score;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegularGamePoints {

    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("Ad");

    private final String displayValue;

    public RegularGamePoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException("Cannot call next() on ADVANTAGE");
        } else {
            return RegularGamePoints.values()[this.ordinal() + 1];
        }
    }
}
