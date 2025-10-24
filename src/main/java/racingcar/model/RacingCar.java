package racingcar.model;

import java.util.Objects;
import java.util.regex.Pattern;
import racingcar.model.exception.RacingCarErrorMessage;
import racingcar.model.exception.RacingCarExceptionFactory;

public class RacingCar {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;
    public static final int MOVE_THRESHOLD = 4;
    public static final int DEFAULT_POSITION = 0;

    private static final Pattern NAME_REGEX = Pattern.compile("^[A-Za-z0-9가-힣-]{1,5}$");

    private final String name;
    private Integer position;

    public RacingCar(String name) {
        validateNameFormat(name);
        this.name = name;
        position = DEFAULT_POSITION;
    }

    public RacingCar(String name, Integer position) {
        validateNameFormat(name);
        validateNegativePosition(position);
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public boolean move(int moveValue) {
        validateValueRange(moveValue);
        if (moveValue >= MOVE_THRESHOLD) {
            position++;
        }

        return moveValue >= MOVE_THRESHOLD;
    }

    private void validateValueRange(int randomValue) {
        if (randomValue < MIN_VALUE || randomValue > MAX_VALUE) {
            throw RacingCarExceptionFactory.stateOf(
                    RacingCarErrorMessage.INVALID_STATE_RANDOM_VALUE,
                    MIN_VALUE, MAX_VALUE);
        }
    }

    private void validateNegativePosition(Integer position) {
        if (position < DEFAULT_POSITION) {
            throw RacingCarExceptionFactory.of(RacingCarErrorMessage.INVALID_POSITION, position);
        }
    }

    private void validateNameFormat(String name) {
        if (!NAME_REGEX.matcher(name).matches()) {
            throw RacingCarExceptionFactory.of(RacingCarErrorMessage.INVALID_NAME, name);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        RacingCar racingCar = (RacingCar) object;
        return Objects.equals(name, racingCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "RacingCar{name='%s', position=%d}"
                .formatted(name, position);
    }
}
