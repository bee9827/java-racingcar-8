package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    @Test
    void getWinners() {
        List<RacingCar> racingCars = List.of(
                new RacingCar("1번"),
                new RacingCar("2번"),
                new RacingCar("3번")
        );
        RacingGame racingGame = new RacingGame(racingCars, new TempGenerator());

        racingGame.moves();
        List<RacingCarDto> winners = racingGame.getWinners();

        assertThat(winners).hasSize(3);
        assertThat(winners.getFirst().location()).isEqualTo(1);
    }

    private static class TempGenerator implements RandomValueGenerator {
        @Override
        public int generate() {
            return RacingCar.MOVE_THRESHOLD;
        }
    }
}
