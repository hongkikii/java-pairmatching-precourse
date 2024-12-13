package pairmatching;

import java.util.List;

public class OutputView {
    public void show(PairInfo pairInfo, Section section) {
        System.out.println();
        System.out.println("페어 매칭 결과입니다.");
        List<Pair> pairs = pairInfo.get(section);
        for(Pair pair : pairs) {
            System.out.println(pair.getFormatted());
        }
        System.out.println();
    }
}
