package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PairInfo {
    private final Map<Section, List<Pair>> value;

    public PairInfo() {
        this.value = new HashMap<>();
    }

    public boolean isContained(Section section) {
        return findKey(section) != null;
    }

    public List<Pair> get(Section section) {
        List<Pair> pairs = value.get(findKey(section));
        if (pairs == null) {
            return null;
        }
        return Collections.unmodifiableList(pairs);
    }

    public void match(Section section) {
        Section key = findKey(section);
        if (key == null) {
            key = section;
        }
        List<Pair> newPairs = makePairs(section);
        for (int i = 1; i <= 3; i++) {
            if(isNotSamePair(section, newPairs)) {
                value.put(key, newPairs);
                return;
            }
            newPairs = makePairs(section);
        }
        throw new IllegalArgumentException("[ERROR] 매칭에 실패했습니다.");
    }

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

    public Section findKey(Section compare) {
        for(Section section : value.keySet()) {
            if (section.isEqual(compare)) {
                return section;
            }
        }
        return null;
    }

    protected List<Pair> makePairs(Section section) {
        List<Pair> pairs = new ArrayList<>();
        List<String> crews = loadCrewName(section);
        List<String> shuffleCrews = Randoms.shuffle(crews);
        for (int i = 0; i < shuffleCrews.size(); i += 2) {
            if (i == shuffleCrews.size() - 1) {
                pairs.getLast().add(shuffleCrews.get(i));
                break;
            }
            String crew1 = shuffleCrews.get(i);
            String crew2 = shuffleCrews.get(i + 1);
            Pair pair = new Pair(crew1, crew2);
            pairs.add(pair);
        }
        return pairs;
    }

    private List<String> loadCrewName(Section section) {
        List<String> crews = new ArrayList<>();
        String path = "src/main/resources/" + section.getCourse().getFileName();
        try {
            Scanner scanner = new Scanner(new File(path));
            while(scanner.hasNext()) {
                String crew = scanner.next();
                crews.add(crew);
            }
        }
        catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일 경로가 잘못되었습니다.");
        }
        return crews;
    }
}
