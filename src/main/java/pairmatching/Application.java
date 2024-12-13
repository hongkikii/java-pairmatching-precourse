package pairmatching;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PairInfo pairInfo = new PairInfo();
        while(true) {
            String function = inputView.readFunction();

            if(function.equals("Q")) {
                return;
            }
            if(function.equals("1")) {
                Section section = null;
                outer:
                while(true) {
                    section = inputView.readSection();
                    if(pairInfo.isContained(section)) {
                        while(true) {
                            try {
                                boolean isNegative = inputView.readIsNegativeAnswer();
                                if (isNegative) {
                                    continue outer;
                                }
                                pairInfo.reMatch(section);
                                break outer;
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    pairInfo.match(section);
                    break;
                }
                outputView.show(pairInfo, section);
            }
            if (function.equals("2")) {

            }
            if (function.equals("3")) {
                pairInfo = new PairInfo();
            }
        }
    }
}
