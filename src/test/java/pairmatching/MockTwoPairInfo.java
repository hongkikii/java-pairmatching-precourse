package pairmatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockTwoPairInfo extends PairInfo {
    public Map<Section, List<Pair>> value;
    public int tryCount;

    public MockTwoPairInfo() {
        super();
        value = new HashMap<>();
        Section section = new Section(Course.BACKEND, Level.LEVEL1, Mission.CAR);
        value.put(section, List.of(new Pair("향미", "가의"), new Pair("지훈", "종현")));
    }

    @Override
    public Section findKey(Section compare) {
        for(Section section : value.keySet()) {
            if (section.isEqual(compare)) {
                return section;
            }
        }
        return null;
    }

    @Override
    public void match(Section section) {
        Section key = findKey(section);
        if (key == null) {
            key = section;
        }
        List<Pair> newPairs = makePairs(section);
        for (int i = 1; i <= 3; i++) {
            tryCount++;
            if(isNotSamePair(section, newPairs)) {
                value.put(key, newPairs);
                return;
            }
            newPairs = makePairs(section);
        }
        throw new IllegalArgumentException("[ERROR] 매칭에 실패했습니다.");
    }

    @Override
    protected List<Pair> makePairs(Section section) {
        return List.of(new Pair("민수", "종현"), new Pair("가의", "향미"));
    }

    @Override
    protected boolean isNotSamePair(Section section, List<Pair> newPairs) {
        List<List<Pair>> sameLevelPairs = new ArrayList<>();
        for(Section key : value.keySet()) {
            if(key.getLevel().equals(section.getLevel()) && key.getCourse().equals(section.getCourse())) {
                sameLevelPairs.add(value.get(key));
            }
        }
        for(List<Pair> oldPairs : sameLevelPairs) {
            for(Pair oldPair : oldPairs) {
                for(Pair newPair : newPairs) {
                    if(oldPair.containSameCrewWith(newPair)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
