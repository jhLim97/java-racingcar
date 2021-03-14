package racingcar.contoller;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;

import racingcar.domain.Round;
import racingcar.dto.InputManagement;

import racingcar.view.ResultView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCarController {

    private RacingGame racingGame;
    private InputManagement inputManagement;

    public RacingCarController(RacingGame racingGame, InputManagement inputManagement) {
        this.racingGame = racingGame;
        this.inputManagement = inputManagement;
    }

    public List<Car> initRacingCars() {
        return Arrays.stream(inputManagement.getCarNames())
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public boolean hasNextRound(Round round) {
        return inputManagement.getCountRound() >= round.getRound();
    }

    public void startGame() {
        Cars carGroup = new Cars(initRacingCars());
        racingGame.init(carGroup);

        Round round = new Round();
        while (hasNextRound(round)) {
            racingGame.playRacing();
            racingGame.recordEachRoundPosition(round);
            round.update();
        }

        new ResultView().printResult(racingGame.getFinalResult(), racingGame.getWinners()
                                                                                .getCars());
    }
}
