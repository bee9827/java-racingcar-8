package racingcar;

import java.util.regex.Pattern;

public class RacingCar {
    public static final int DEFAULT_LOCATION = 0;
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;

    private static final int MOVE_THRESHOLD = 4;
    private static final Pattern NAME_REGEX = Pattern.compile("^[A-Za-z0-9가-힣-]{1,5}$");

    private final String name;
    private int location;

    public RacingCar(String name) {
        validateNameFormat(name);
        this.name = name;
        location = DEFAULT_LOCATION;
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
            throw new IllegalArgumentException("%d ~ %d 사이의 값을 입력해 주세요".formatted(MIN_VALUE, MAX_VALUE));
        }
    }

    private void validateNameFormat(String name) {
        if (!NAME_REGEX.matcher(name).matches()) {
            throw new IllegalArgumentException("유효하지 않은 이름 형식입니다.");
        }
    }
}
