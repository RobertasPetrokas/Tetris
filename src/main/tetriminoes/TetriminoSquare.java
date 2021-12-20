package main.tetriminoes;

import main.Coordinates;
import main.Interfaces.IGameRules;
import main.TetriminoType;

import java.util.ArrayList;
import java.util.Arrays;

public class TetriminoSquare extends Tetrimino {

    private final ArrayList<Coordinates> startingCoords = new ArrayList<Coordinates>(Arrays.asList(new Coordinates(0, 3), new Coordinates(1, 3), new Coordinates(0, 4), new Coordinates(1, 4)));

    public TetriminoSquare(IGameRules gameRules) {
        super(gameRules);
        this.coordinates = startingCoords;
        this.tetriminoType = TetriminoType.tetriminoSquare;
    }


    @Override
    public void rotateRight() {
        //
    }

    @Override
    public void rotateLeft() {
        //
    }
}
