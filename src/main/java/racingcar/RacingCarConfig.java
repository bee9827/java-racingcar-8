package racingcar;

import racingcar.controller.RacingCarController;
import racingcar.model.NumberGenerator;
import racingcar.model.RandomNumberGenerator;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConfig {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public RacingCarConfig() {
        this.inputView = getConsoleInputView();
        this.outputView = getConsoleOutputView();
        this.numberGenerator = new RandomNumberGenerator();
    }

    private ConsoleOutputView getConsoleOutputView() {
        return new ConsoleOutputView();
    }

    private ConsoleInputView getConsoleInputView() {
        return new ConsoleInputView();
    }

    public RacingCarController createController() {
        return new RacingCarController(inputView, outputView, numberGenerator);
    }
}
