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
                int idx = 0;
                Section section = null;
                section = inputView.readSection();
                outer:
                while(true) {
                    if (idx > 0) {
                        section = inputView.readPartSection();
                    }
                    if(pairInfo.isContained(section)) {
                        while(true) {
                            try {
                                boolean isNegative = inputView.readIsNegativeAnswer();
                                if (isNegative) {
                                    idx++;
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
                while(true) {
                    try {
                        Section section = inputView.readSection();
                        outputView.show(pairInfo, section);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if (function.equals("3")) {
//                pairInfo = new PairInfo();
//                outputView.showResetComplete();
            }
        }
    }
}
