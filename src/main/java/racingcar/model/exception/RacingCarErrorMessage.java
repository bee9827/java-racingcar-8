package racingcar.model.exception;

import java.util.IllegalFormatException;

public enum RacingCarErrorMessage {
    INVALID_POSITION("유효하지 않은 위치입니다: %d"),
    INVALID_NAME("유효하지 않은 이름입니다: %s"),
    DUPLICATE_CAR_NAME("중복된 자동차 이름입니다: %s"),
    INVALID_STATE_RANDOM_VALUE("randomValue: %d ~ %d 사이의 값만 허용됩니다."),
    INVALID_STATE_ARGS("형식에 맞춰 인자를 입력해 주세요 [예외: %s]");

    private final String template;

    RacingCarErrorMessage(String template) {
        this.template = template;
    }

    public String format(Object... args) {
        try {
            return String.format(template, args);
        } catch (IllegalFormatException e) {
            String message = INVALID_STATE_ARGS.format(this);
            throw new RacingCarStateException(message);
        }
    }
}
