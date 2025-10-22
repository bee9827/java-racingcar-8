package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomValueGeneratorImpl implements RandomValueGenerator {
    @Override
    public int generate() {
        return Randoms.pickNumberInRange(RacingCar.MIN_VALUE, RacingCar.MAX_VALUE);
    }
}
