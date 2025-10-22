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
        try {
            return Integer.parseInt(s);
        }catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력해 주세요");
        }
    }

    private List<String> getList(String names) {
        return Arrays.stream(names.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}
