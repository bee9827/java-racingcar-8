package racingcar.view;

import static racingcar.util.Parser.splitByDefaultDelimiter;
import static racingcar.util.Parser.toInteger;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class ConsoleInputView implements InputView {
    @Override
    public List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return splitByDefaultDelimiter(Console.readLine());
    }

    @Override
    public Integer readTryCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        return toInteger(Console.readLine());
    }

    @Override
    public void close() {
        Console.close();
    }
}
