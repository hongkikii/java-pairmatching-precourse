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
                function = parse(functionCandidate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return function;
    }

    private String parse(String functionCandidate) {
        if(functionCandidate.equals("1") || functionCandidate.equals("2")
        || functionCandidate.equals("3") || functionCandidate.equals("Q")) {
            return functionCandidate;
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
    }
}
