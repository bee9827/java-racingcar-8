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

        return toDto(racingCars);
    }

    public List<RacingCarDto> getWinners() {
        List<RacingCar> winners = racingCars.stream()
                .filter(racingCar -> racingCar.getPosition() == getMaxLocation())
                .toList();
        
        return toDto(winners);
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

    private int getMaxLocation() {
        return racingCars.stream()
                .map(RacingCar::getPosition)
                .max(Integer::compareTo)
                .orElse(0);
    }

    private List<RacingCarDto> toDto(List<RacingCar> racingCars) {
        return racingCars.stream()
                .map(RacingCarDto::from)
                .toList();
    }
}
