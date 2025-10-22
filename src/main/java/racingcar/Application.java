package racingcar;

import racingcar.controller.RacingCarController;

public class Application {
    public static void main(String[] args) {
        RacingCarConfig racingCarConfig = new RacingCarConfig();
        RacingCarController controller = racingCarConfig.createController();
        controller.run();
    }
}
