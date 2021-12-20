package main.Interfaces;

import main.Coordinates;
import main.tetriminoes.Tetrimino;

import java.util.ArrayList;

public interface IGameRules {

    Tetrimino initRandomTetrimino();

    void processUserCommand(Tetrimino tetrimino, int userCommand);

    boolean hasFallen(Tetrimino tetrimino);

    boolean canMove(ArrayList<Coordinates> coordinates, int x, int y);

    boolean canRotate(Tetrimino tetrimino);


}
