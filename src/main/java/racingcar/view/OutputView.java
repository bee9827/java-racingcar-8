package racingcar.view;

import java.util.List;
import racingcar.controller.dto.RacingCarDto;

public interface OutputView {
    void printMoveResultInstruction();

    void printMoveResults(List<RacingCarDto> moveResult);

    void printWinners(List<RacingCarDto> winners);
}
