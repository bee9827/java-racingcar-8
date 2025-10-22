package racingcar.model;

import java.util.List;
import racingcar.controller.dto.RacingCarDto;

public class RacingGame {
    private final List<RacingCar> racingCars;

    public RacingGame(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public List<RacingCarDto> moves(final RandomValueGenerator randomValueGenerator) {
        racingCars.forEach(racingCar ->
                racingCar.move(randomValueGenerator.generate()));

        return racingCars.stream()
                .map(RacingCarDto::from)
                .toList();
    }

    public List<RacingCarDto> getWinners() {
        Integer maxLocation = getMaxLocation();

        return racingCars.stream()
                .filter(racingCar -> racingCar.getLocation() == maxLocation)
                .map(RacingCarDto::from)
                .toList();
    }

    private Integer getMaxLocation() {
        return racingCars.stream()
                .map(RacingCar::getLocation)
                .max(Integer::compareTo)
                .orElse(0);
    }
}
