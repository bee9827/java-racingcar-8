package racingcar;

import java.util.List;

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
        RacingGame racingGame = startGame(inputView.readCarNames());
        playGame(inputView.readTryCount(), racingGame);
        endGame(racingGame);
    }

    private RacingGame startGame(List<String> carNames) {
        List<RacingCar> racingCars = toRacingCars(carNames);
        return new RacingGame(racingCars, randomValueGenerator);
    }

    private void playGame(Integer moveCount, RacingGame racingGame) {
        outputView.printMoveResultInstruction();
        for (int i = 0; i < moveCount; i++) {
            List<RacingCarDto> moveResult = racingGame.moves();
            outputView.printMoveResults(moveResult);
        }
    }

    private void endGame(RacingGame racingGame) {
        List<RacingCarDto> winners = racingGame.getWinners();
        outputView.printWinners(winners);
    }

    private List<RacingCar> toRacingCars(List<String> carNames) {
        return carNames.stream()
                .map(RacingCar::new)
                .toList();
    }
}
