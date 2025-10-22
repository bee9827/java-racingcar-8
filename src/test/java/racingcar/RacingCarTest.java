package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import racingcar.model.RacingCar;

class RacingCarTest {
    @DisplayName("new(): ")
    @Nested
    class Constructor {
        @DisplayName("생성에 성공한다.")
        @ParameterizedTest
        @CsvSource({
                "1",
                "한글이름",
                "En",
                "p-1",
                "최대길이5"
        })
        void construct(String name) {
            String realName = new RacingCar(name).getName();

            assertThat(realName).isEqualTo(name);
        }

        @DisplayName("예외: 이름 형식이 맞지 않는다면 예외를 반환한다.")
        @ParameterizedTest
        @EmptySource
        @CsvSource({
                "5글자이상임",
                "\\",
        })
        void invalidNameLength(String name) {
            assertThatThrownBy(() -> new RacingCar(name))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("move(): ")
    class Move {
        @DisplayName("4 이상의 값이면 전진한다.")
        @ParameterizedTest
        @CsvSource({
                "0,false",
                "3,false",
                "4,true",
                "9,true"

        })
        void move(int randomValue, boolean isMoved) {
            RacingCar racingCar = new RacingCar("car");

            assertThat(racingCar.move(randomValue))
                    .isEqualTo(isMoved);
        }

        @DisplayName("예외: 0~9 사이의 값이 아니면 예외를 반환한다.")
        @ParameterizedTest
        @CsvSource({
                "-1",
                "10"
        })
        void invalidMoveValue(int moveValue) {
            RacingCar racingCar = new RacingCar("car");
            assertThatThrownBy(() -> racingCar.move(moveValue))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
