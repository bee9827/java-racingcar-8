package racingcar.model;

import java.util.List;
import racingcar.controller.dto.RacingCarDto;
import racingcar.model.exception.RacingCarErrorMessage;
import racingcar.model.exception.RacingCarExceptionFactory;

public class RacingGame {
    private static final int MIN_COUNT = 1;
    private final List<RacingCar> racingCars;
    private final Integer maxAttemptCount;
    private int attemptCount = 0;

    public RacingGame(List<RacingCar> racingCars, Integer maxAttemptCount) {
        validateNonDuplicate(racingCars);
        validateCount(maxAttemptCount);
        this.racingCars = List.copyOf(racingCars);
        this.maxAttemptCount = maxAttemptCount;
    }

    public static RacingGame from(List<String> racingCarNames, Integer tryCount) {
        List<RacingCar> racingCars = toRacingCars(racingCarNames);
        return new RacingGame(racingCars, tryCount);
    }

    private static List<RacingCar> toRacingCars(List<String> carNames) {
        return carNames.stream()
                .map(RacingCar::new)
                .toList();
    }

    public boolean canMove() {
        return attemptCount < maxAttemptCount;
    }

    public List<RacingCarDto> moves(final RandomValueGenerator randomValueGenerator) {
        if (!canMove()) {
            throw RacingCarExceptionFactory.stateOf(
                    RacingCarErrorMessage.INVALID_STATE_ATTEMPT_COUNT, maxAttemptCount, attemptCount);
        }
        racingCars.forEach(racingCar ->
                racingCar.move(randomValueGenerator.generate()));

        attemptCount++;
        return toDto(racingCars);
    }

    public List<RacingCarDto> getWinners() {
        RacingCar maxLocationCar = getMaxLocationCar();
        List<RacingCar> winners = racingCars.stream()
                .filter(racingCar -> racingCar.compareTo(maxLocationCar) == 0)
                .toList();

        return toDto(winners);
    }

    private void validateNonDuplicate(List<RacingCar> racingCars) {
        if (getDistinctSize(racingCars) != racingCars.size()) {
            throw RacingCarExceptionFactory.of(RacingCarErrorMessage.DUPLICATE_CAR_NAME, racingCars);
        }
    }

    private void validateCount(Integer totalCount) {
        if (totalCount == null || totalCount < MIN_COUNT) {
            throw new IllegalArgumentException(
                    RacingCarExceptionFactory.of(RacingCarErrorMessage.INVALID_ATTEMPT_COUNT, totalCount));
        }
    }

    private int getDistinctSize(List<RacingCar> racingCars) {
        return racingCars.stream()
                .distinct()
                .toList()
                .size();
    }

    private RacingCar getMaxLocationCar() {
        return racingCars.stream()
                .max(RacingCar::compareTo)
                .orElse(null);
    }

    private List<RacingCarDto> toDto(List<RacingCar> racingCars) {
        return racingCars.stream()
                .map(RacingCarDto::from)
                .toList();
    }
}
