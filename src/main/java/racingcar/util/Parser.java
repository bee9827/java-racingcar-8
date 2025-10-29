package racingcar.util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final String DELIMITER = ",";

    private Parser() {
    }

    public static Integer toInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력해 주세요");
        }
    }

    public static List<String> splitByDefaultDelimiter(String names) {
        return Arrays.stream(names.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}
