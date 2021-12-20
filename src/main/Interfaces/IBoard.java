package main.Interfaces;

import main.Coordinates;
import main.TetriminoType;
import main.tetriminoes.Tetrimino;

public interface IBoard {
    TetriminoType getElement(Coordinates coordinates);

    TetriminoType getNextElement(Coordinates coordinates);

    void setTetrimino(Coordinates coordinates, TetriminoType tetriminoType);

    void boardCheck();

    int getBoardHeight();

    int getBoardWidth();

    void clearCurrentPos(Tetrimino tetrimino);

    void setTetriminoCoordinates(Tetrimino tetrimino);


}
