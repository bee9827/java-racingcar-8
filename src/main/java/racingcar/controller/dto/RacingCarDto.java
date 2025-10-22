package racingcar.controller.dto;

import racingcar.model.RacingCar;

public record RacingCarDto(
        String name,
        Integer location
) {
    public static RacingCarDto from(RacingCar racingCar) {
        return new RacingCarDto(racingCar.getName(), racingCar.getLocation());
    }
}
