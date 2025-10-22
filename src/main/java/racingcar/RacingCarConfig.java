package racingcar;

import racingcar.controller.RacingCarController;
import racingcar.model.RandomValueGenerator;
import racingcar.model.RandomValueGeneratorImpl;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConfig {
    private final InputView inputView;
    private final OutputView outputView;
    private final RandomValueGenerator randomValueGenerator;

    public RacingCarConfig() {
        this.inputView = new ConsoleInputView();
        this.outputView = new ConsoleOutputView();
        this.randomValueGenerator = new RandomValueGeneratorImpl();
    }

    public RacingCarController createController() {
        return new RacingCarController(inputView, outputView, randomValueGenerator);
    }
}
