package pairmatching;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Pair {
    private final List<String> crews;

    public Pair(String crew1, String crew2) {
        crews = new ArrayList<>();
        crews.add(crew1);
        crews.add(crew2);
    }

    public String getFormatted() {
        StringBuilder sb = new StringBuilder();
        for (String name : crews) {
            sb.append(name).append(" : ");
        }
        sb.delete(sb.length() - 3, sb.length());
        return sb.toString();
    }

    public List<String> getCrews() {
        return crews;
    }

    public void add(String crew3) {
        crews.add(crew3);
    }

    public boolean containSameCrewWith(Pair anotherPair) {
        List<String> anotherCrews = anotherPair.getCrews();
        List<String> matchingCrews = crews.stream()
                        .filter(crew -> anotherCrews.stream()
                        .anyMatch(Predicate.isEqual(crew)))
                        .toList();
        return matchingCrews.size() >= 2;
    }
}
