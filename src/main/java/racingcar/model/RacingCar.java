package racingcar.model;

import java.util.regex.Pattern;

public class RacingCar {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;
    public static final int MOVE_THRESHOLD = 4;

    private static final int DEFAULT_LOCATION = 0;
    private static final Pattern NAME_REGEX = Pattern.compile("^[A-Za-z0-9가-힣-]{1,5}$");

    private final String name;
    private Integer location;

    public RacingCar(String name) {
        validateNameFormat(name);
        this.name = name;
        location = DEFAULT_LOCATION;
    }

    public RacingCar(String name, Integer location) {
        validateNameFormat(name);
        validateNegativeLocation(location);
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }

    public boolean move(int moveValue) {
        validateValueRange(moveValue);
        if (moveValue >= MOVE_THRESHOLD) {
            location++;
        }

        return moveValue >= MOVE_THRESHOLD;
    }

    private void validateValueRange(int randomValue) {
        if (randomValue < MIN_VALUE || randomValue > MAX_VALUE) {
            throw new IllegalStateException("randomValue: %d ~ %d 사이의 값만 허용됩니다.".formatted(MIN_VALUE, MAX_VALUE));
        }
    }

    private void validateNegativeLocation(Integer location) {
        if (location < DEFAULT_LOCATION) {
            throw new IllegalStateException("유효하지 않은 위치 입니다. : %d ".formatted(location));
        }
    }

    private void validateNameFormat(String name) {
        if (!NAME_REGEX.matcher(name).matches()) {
            throw new IllegalArgumentException("유효하지 않은 이름 형식입니다.");
        }
    }
}
