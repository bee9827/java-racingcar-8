package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int generate() {
        return Randoms.pickNumberInRange(RacingCar.MIN_VALUE, RacingCar.MAX_VALUE);
    }
}
