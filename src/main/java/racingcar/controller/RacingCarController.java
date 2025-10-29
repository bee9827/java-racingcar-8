package racingcar.controller;

import java.util.List;
import racingcar.controller.dto.RacingCarDto;
import racingcar.model.RacingGame;
import racingcar.model.RandomValueGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RandomValueGenerator randomValueGenerator;

    public RacingCarController(
            InputView inputView, OutputView outputView, RandomValueGenerator randomValueGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomValueGenerator = randomValueGenerator;
    }

    public void run() {
        RacingGame racingGame = RacingGame.from(inputView.readCarNames(), inputView.readTryCount());
        playGame(racingGame);
        endGame(racingGame);

        inputView.close();
    }

    private void playGame(RacingGame racingGame) {
        outputView.printMoveResultInstruction();
        while (racingGame.canMove()) {
            List<RacingCarDto> moveResult = racingGame.moves(randomValueGenerator);
            outputView.printMoveResults(moveResult);
        }
    }

    private void endGame(RacingGame racingGame) {
        List<RacingCarDto> winners = racingGame.getWinners();
        outputView.printWinners(winners);
    }
}
