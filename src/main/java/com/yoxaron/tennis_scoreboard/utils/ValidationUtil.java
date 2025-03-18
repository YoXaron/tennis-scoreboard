package com.yoxaron.tennis_scoreboard.utils;

public class ValidationUtil {

    public static void checkNames(String name1, String name2) {
        if (name1 == null || name2 == null) {
            throw new IllegalStateException("Name cannot be null");
        }

        if (name1.equals(name2)) {
            throw new IllegalStateException("Name cannot be the same");
        }
    }
}
