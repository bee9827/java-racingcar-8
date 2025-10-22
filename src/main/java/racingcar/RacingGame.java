package racingcar;

import java.util.List;

public class RacingGame {
    private final List<RacingCar> racingCars;
    private final RandomValueGenerator randomValueGenerator;

    public RacingGame(List<RacingCar> racingCars, RandomValueGenerator randomValueGenerator) {
        this.racingCars = racingCars;
        this.randomValueGenerator = randomValueGenerator;
    }

    public List<RacingCarDto> moves() {
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
