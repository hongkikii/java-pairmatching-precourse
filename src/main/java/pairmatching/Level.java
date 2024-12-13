package pairmatching;

import java.util.EnumSet;
import java.util.Optional;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static boolean isNotContained(String name) {
        Optional<Level> matchedLevel = EnumSet.allOf(Level.class).stream()
                .filter(level -> level.name.equals(name))
                .findFirst();
        if(matchedLevel.isPresent()) {
            return false;
        }
        return true;
    }

    public static Level getBy(String name) {
        return EnumSet.allOf(Level.class).stream()
                .filter(level -> level.name.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
