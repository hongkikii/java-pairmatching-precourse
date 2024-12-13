package pairmatching;

import java.util.List;

public class OutputView {
    public void show(PairInfo pairInfo, Section section) {
        List<Pair> pairs = pairInfo.get(section);
        if(pairs == null) {
            System.out.println("[ERROR] 매칭 이력이 없습니다.");
            System.out.println();
            return;
        }
        System.out.println();
        System.out.println("페어 매칭 결과입니다.");
        for(Pair pair : pairs) {
            System.out.println(pair.getFormatted());
        }
        System.out.println();
    }

//    public void showResetComplete() {
//        System.out.println();
//        System.out.println("초기화 되었습니다.");
//    }
}
