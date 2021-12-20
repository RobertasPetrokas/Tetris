package main.tetriminoes;

import main.Coordinates;
import main.Interfaces.IGameRules;
import main.TetriminoType;

import java.util.ArrayList;
import java.util.Arrays;

public class TetriminoT extends Tetrimino {

    private final ArrayList<Coordinates> startingCoords = new ArrayList<Coordinates>(Arrays.asList(new Coordinates(0, 3), new Coordinates(0, 4), new Coordinates(0, 5), new Coordinates(1, 4)));

    public TetriminoT(IGameRules gameRules) {
        super(gameRules);
        this.coordinates = startingCoords;
        this.tetriminoType = TetriminoType.tetriminoT;
        this.rotationCoords = new int[][][]{{{0, 0}, {0, 1}, {0, 2}, {1, 1}}, {{0, 1}, {1, 1}, {2, 1}, {1, 0}}, {{1, 2}, {1, 1}, {1, 0}, {0, 1}}, {{2, 0}, {1, 0}, {0, 0}, {1, 1}}};

    }

}
