package racingcar.model;

import java.util.List;
import racingcar.controller.dto.RacingCarDto;
import racingcar.model.exception.RacingCarErrorMessage;
import racingcar.model.exception.RacingCarExceptionFactory;

public class RacingGame {
    private final List<RacingCar> racingCars;

    public RacingGame(List<RacingCar> racingCars) {
        validateNonDuplicate(racingCars);
        this.racingCars = racingCars;
    }

    public static RacingGame from(List<String> racingCarNames) {
        List<RacingCar> racingCars = toRacingCars(racingCarNames);
        return new RacingGame(racingCars);
    }

    private static List<RacingCar> toRacingCars(List<String> carNames) {
        return carNames.stream()
                .map(RacingCar::new)
                .toList();
    }

    public List<RacingCarDto> moves(final RandomValueGenerator randomValueGenerator) {
        racingCars.forEach(racingCar ->
                racingCar.move(randomValueGenerator.generate()));

        return racingCars.stream()
                .map(RacingCarDto::from)
                .toList();
    }

    public List<RacingCarDto> getWinners() {
        int maxLocation = getMaxLocation();

        return racingCars.stream()
                .filter(racingCar -> racingCar.getPosition() == maxLocation)
                .map(RacingCarDto::from)
                .toList();
    }

    private int getDistinctSize(List<RacingCar> racingCars) {
        return racingCars.stream()
                .distinct()
                .toList()
                .size();
    }

    private void validateNonDuplicate(List<RacingCar> racingCars) {
        if (getDistinctSize(racingCars) != racingCars.size()) {
            throw RacingCarExceptionFactory.of(RacingCarErrorMessage.DUPLICATE_CAR_NAME, racingCars);
        }
    }

    private Integer getMaxLocation() {
        return racingCars.stream()
                .map(RacingCar::getPosition)
                .max(Integer::compareTo)
                .orElse(0);
    }
}
