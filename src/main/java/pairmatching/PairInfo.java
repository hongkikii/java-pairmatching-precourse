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
        return Collections.unmodifiableList(value.get(findKey(section)));
    }

    public void reMatch(Section section) {
        int tryCount = 1;
        List<Pair> oldPairs = value.get(findKey(section));
        outer:
        while (tryCount <= 3) {
            List<Pair> newPairs = makePairs(findKey(section));
            for(Pair oldPair : oldPairs) {
                for(Pair newPair : newPairs) {
                    if(oldPair.containSameCrewWith(newPair)) {
                        tryCount++;
                        continue outer;
                    }
                }
            }
            value.put(findKey(section), newPairs);
            return;
        }
        throw new IllegalArgumentException("[ERROR] 매칭에 실패했습니다.");
    }

    public void match(Section section) {
        value.put(section, makePairs(section));
    }

    public Section findKey(Section compare) {
        for(Section section : value.keySet()) {
            if (section.isEqual(compare)) {
                return section;
            }
        }
        return null;
    }

    private List<Pair> makePairs(Section section) {
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
