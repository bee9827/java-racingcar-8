package racingcar;

import java.util.List;

public interface OutputView {
    void printMoveResultInstruction();

    void printMoveResults(List<RacingCarDto> moveResult);

    void printWinners(List<RacingCarDto> winners);
}
