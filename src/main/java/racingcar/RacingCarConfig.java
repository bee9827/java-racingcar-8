package racingcar;

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
