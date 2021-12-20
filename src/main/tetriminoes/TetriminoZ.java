package main.tetriminoes;

import main.Coordinates;
import main.Interfaces.IGameRules;
import main.TetriminoType;

import java.util.ArrayList;
import java.util.Arrays;

public class TetriminoZ extends Tetrimino {

    private final ArrayList<Coordinates> startingCoords = new ArrayList<Coordinates>(Arrays.asList(new Coordinates(0, 3), new Coordinates(0, 4), new Coordinates(1, 4), new Coordinates(1, 5)));

    public TetriminoZ(IGameRules gameRules) {
        super(gameRules);
        this.coordinates = startingCoords;
        this.tetriminoType = TetriminoType.tetriminoZ;
        this.rotationCoords = new int[][][]{{{0, 0}, {0, 1}, {1, 1}, {1, 2}}, {{0, 1}, {1, 0}, {1, 1}, {2, 0}}};
    }


}
