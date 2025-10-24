package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.controller.dto.RacingCarDto;
import racingcar.model.RacingCar;
import racingcar.model.RacingGame;
import racingcar.model.RandomValueGenerator;

class RacingGameTest {

    @Test
    @DisplayName("new(): [예외] 중복된 이름 이라면 예외를 반환한다.")
    void constructor() {
        List<RacingCar> racingCars = List.of(
                new RacingCar("중복", 0),
                new RacingCar("중복", 0)
        );
        assertThatThrownBy(() -> new RacingGame(racingCars))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("moves(): ")
    void moves() {
        List<RacingCar> racingCars = List.of(
                new RacingCar("car1", 1),
                new RacingCar("car2", 2),
                new RacingCar("car3", 3)
        );
        RacingGame racingGame = new RacingGame(racingCars);

        List<Integer> moveResults = racingGame.moves(new MoveGenerator())
                .stream()
                .map(RacingCarDto::location)
                .toList();

        assertThat(moveResults).hasSize(3);
        assertThat(moveResults.get(0)).isEqualTo(2);
        assertThat(moveResults.get(1)).isEqualTo(3);
        assertThat(moveResults.get(2)).isEqualTo(4);

    }

    @Test
    @DisplayName("getWinners(): 중복 우승자")
    void getWinners() {
        //given
        List<RacingCar> racingCars = List.of(
                new RacingCar("우승자1", 2),
                new RacingCar("우승자2", 2),
                new RacingCar("일반", 0)
        );
        RacingGame racingGame = new RacingGame(racingCars);

        //when
        List<RacingCarDto> winners = racingGame.getWinners();

        //then
        assertThat(winners).hasSize(2);
        assertThat(winners.getFirst().location()).isEqualTo(2);
    }

    private static class MoveGenerator implements RandomValueGenerator {
        @Override
        public int generate() {
            return RacingCar.MOVE_THRESHOLD;
        }
    }
}
