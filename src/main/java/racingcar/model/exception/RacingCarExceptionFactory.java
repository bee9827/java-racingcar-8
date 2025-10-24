package racingcar.model.exception;

public class RacingCarExceptionFactory {
    public static RacingCarException of(RacingCarErrorMessage message, Object... args) {
        String formattedMessage = message.format(args);
        return new RacingCarException(formattedMessage);
    }

    public static RacingCarStateException stateOf(RacingCarErrorMessage message, Object... args) {
        String formattedMessage = message.format(args);
        return new RacingCarStateException(formattedMessage);
    }
}
