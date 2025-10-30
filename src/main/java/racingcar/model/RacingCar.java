package racingcar.model;

import java.util.Objects;
import racingcar.model.exception.RacingCarErrorMessage;
import racingcar.model.exception.RacingCarExceptionFactory;

public class RacingCar implements Comparable<RacingCar> {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;
    public static final int MOVE_THRESHOLD = 4;
    public static final int DEFAULT_POSITION = 0;

    private final Name name;
    private Integer position;

    public RacingCar(String name) {
        this.name = new Name(name);
        position = DEFAULT_POSITION;
    }

    public RacingCar(String name, Integer position) {
        validateNegativePosition(position);
        this.name = new Name(name);
        this.position = position;
    }

    public String getName() {
        return name.toString();
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

    @Override
    public int compareTo(RacingCar o) {
        return position.compareTo(o.position);
    }
}
