package main.Tests;

import main.Board;
import main.Coordinates;
import main.GameRules;
import main.Interfaces.IBoard;
import main.Interfaces.IGameRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;


class GameRulesTest {
    private final IBoard _board = new Board();
    private IGameRules _gameRules;

    @BeforeEach
    void setup() {
        _gameRules = new GameRules(_board);
    }

    @Test
    void can_Move_Should_Return_False_If_Tetrimino_Reaches_Borders() {
        assertFalse(_gameRules.canMove(new ArrayList<>(Arrays.asList(new Coordinates(19, 3), new Coordinates(19, 4), new Coordinates(19, 5), new Coordinates(19, 6))), 1, 0));
    }


}