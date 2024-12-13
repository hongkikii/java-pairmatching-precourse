package pairmatching;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readFunction() {
        String function = null;
        while(function == null) {
            System.out.println("기능을 선택하세요.");
            System.out.println("1. 페어 매칭");
            System.out.println("2. 페어 조회");
            System.out.println("3. 페어 초기화");
            System.out.println("Q. 종료");

            try {
                String functionCandidate = Console.readLine();
                function = parseFunction(functionCandidate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return function;
    }

    private String parseFunction(String functionCandidate) {
        if(functionCandidate.equals("1") || functionCandidate.equals("2")
        || functionCandidate.equals("3") || functionCandidate.equals("Q")) {
            return functionCandidate;
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
    }

    public Section readSection() {
        Section section = null;
        while (section == null) {
            System.out.println("#############################################");
            System.out.println("과정: 백엔드 | 프론트엔드");
            System.out.println("미션:");
            System.out.println("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
            System.out.println("  - 레벨2: 장바구니 | 결제 | 지하철노선도");
            System.out.println("  - 레벨3:");
            System.out.println("  - 레벨4: 성능개선 | 배포");
            System.out.println("  - 레벨5:");
            System.out.println("############################################");
            System.out.println("과정, 레벨, 미션을 선택하세요.");
            System.out.println("ex) 백엔드, 레벨1, 자동차경주");

            try {
                String sectionCandidate = Console.readLine();
                section = parseSection(sectionCandidate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return section;
    }

    private Section parseSection(String sectionCandidate) {
        String[] split = sectionCandidate.split(", ");
        if (split.length != 3) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
        }
        if (Course.isNotContained(split[0])) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
        }
        if (Level.isNotContained(split[1])) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
        }
        Level level = Level.getBy(split[1]);
        if (Mission.isNotContained(split[2], level)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
        }
        return new Section(Course.getBy(split[0]), level, Mission.getBy(split[2], level));
    }

    public boolean readIsNegativeAnswer() {
        while (true) {
            System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
            System.out.println("네 | 아니오");

            String answerCandidate = Console.readLine();
            if(answerCandidate.equals("네")) {
                return false;
            }
            if(answerCandidate.equals("아니오")) {
                return true;
            }
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
        }
    }
}
