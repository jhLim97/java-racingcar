package racingcar.view;

import racingcar.dto.InputManagement;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);
    private static final int MAX_LENGTH = 5;

    public String[] inputNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.");
        System.out.println("<주의> 자동차 이름은 5자를 초과할 수 없습니다.");

        return scanner.nextLine().split(",");
    }

    public int inputCountRound() {
        System.out.println("시도할 회수는 몇 회 인가요?");
        return scanner.nextInt();
    }

    public boolean isInValidNames(String[] carNames) {
        return Arrays.stream(carNames)
                .filter(carName -> carName.length() > MAX_LENGTH)
                .count() > 0;
    }

    public void input() {
        String[] carNames;

        do {
            carNames = inputNames();
        } while (isInValidNames(carNames));

        new InputManagement(carNames, inputCountRound());
    }
}
