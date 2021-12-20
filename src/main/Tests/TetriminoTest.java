package main.Tests;

import main.Board;
import main.GameRules;
import main.Interfaces.IBoard;
import main.Interfaces.IGameRules;
import main.TetriminoType;
import main.tetriminoes.Tetrimino;
import main.tetriminoes.TetriminoJ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TetriminoTest {
    private final IBoard _board = new Board();
    private final IGameRules _gameRules = new GameRules(_board);
    private Tetrimino _tetrimino;

    @BeforeEach
    void setup() {
        _tetrimino = new TetriminoJ(_gameRules);
    }

    @Test
    public void set_Fallen_Should_Change_Tetrimino_Type_To_Fallen() {
        _tetrimino.setFallen();
        assertEquals(TetriminoType.tetriminoFallen, _tetrimino.getTetriminoType());
    }

    @Test
    public void rotate_Right_Should_Increase_Orientation() {
        int orientation = _tetrimino.getOrientation();
        _tetrimino.rotateRight();
        assertEquals(orientation + 1, _tetrimino.getOrientation());
    }


}