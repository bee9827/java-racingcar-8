package racingcar.model.exception;

public enum RacingCarErrorMessage {
    INVALID_POSITION("유효하지 않은 위치입니다: %d"),
    INVALID_NAME("유효하지 않은 이름입니다: %s"),
    DUPLICATE_CAR_NAME("중복된 자동차 이름입니다: %s"),
    ;

    private final String template;

    RacingCarErrorMessage(String template) {
        this.template = template;
    }

    public String format(Object... args) {
        return String.format(template, args);
    }
}
