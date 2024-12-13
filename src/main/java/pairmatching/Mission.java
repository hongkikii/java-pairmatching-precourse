package pairmatching;

import java.util.EnumSet;
import java.util.Optional;

public enum Mission {
    CAR("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    BASEBALL("숫자야구게임", Level.LEVEL1),

    BASKET("장바구니", Level.LEVEL2),
    PURCHASE("결제", Level.LEVEL2),
    SUBWAY("지하철노선도", Level.LEVEL2),

    IMPROVE("성능개선", Level.LEVEL4),
    DEPLOYMENT("배포", Level.LEVEL4);

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static boolean isNotContained(String name, Level level) {
        Optional<Mission> matchedMission = EnumSet.allOf(Mission.class).stream()
                .filter(mission -> mission.name.equals(name) && mission.level.equals(level))
                .findFirst();
        if (matchedMission.isPresent()) {
            return false;
        }
        return true;
    }

    public static Mission getBy(String name, Level level) {
        return EnumSet.allOf(Mission.class).stream()
                .filter(mission -> mission.name.equals(name) && mission.level.equals(level))
                .findFirst()
                .orElseThrow();
    }
}
