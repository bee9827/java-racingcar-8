package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputView implements OutputView {
    public static final String LOCATION = "-";

    @Override
    public void printMoveResultInstruction() {
        System.out.println("실행 결과");
    }

    @Override
    public void printMoveResults(List<RacingCarDto> moveResult) {
        moveResult.forEach(this::printMoveResult);
        System.out.println();
    }

    @Override
    public void printWinners(List<RacingCarDto> winners) {
        String winnerNames = getWinnerNames(winners);
        System.out.printf("최종 우승자 : %s", winnerNames);
    }

    private void printMoveResult(RacingCarDto racingCarDto) {
        String racingCarName = racingCarDto.name();
        String location = LOCATION.repeat(racingCarDto.location());
        System.out.printf("%s : %s%n", racingCarName, location);
    }

    private String getWinnerNames(List<RacingCarDto> winners) {
        return winners.stream()
                .map(RacingCarDto::name)
                .collect(Collectors.joining(", "));
    }
}
