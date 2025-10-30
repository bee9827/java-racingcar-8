package racingcar.model;

import java.util.Objects;
import java.util.regex.Pattern;
import racingcar.model.exception.RacingCarErrorMessage;
import racingcar.model.exception.RacingCarExceptionFactory;

public class Name {
    private static final Pattern NAME_REGEX = Pattern.compile("^[A-Za-z0-9가-힣-]{1,5}$");

    private final String name;

    public Name(String name) {
        validateNameFormat(name);
        this.name = name;
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
        Name name1 = (Name) object;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
