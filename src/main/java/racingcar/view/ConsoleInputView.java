package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class ConsoleInputView implements InputView {
    public static final String DELIMITER = ",";

    @Override
    public List<String> readCarNames() {
        return getList(Console.readLine());
    }

    @Override
    public Integer readTryCount() {
        return toInteger(Console.readLine());
    }

    private Integer toInteger(String s) {
        return Integer.parseInt(s);
    }

    private List<String> getList(String names) {
        return Arrays.stream(names.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}
