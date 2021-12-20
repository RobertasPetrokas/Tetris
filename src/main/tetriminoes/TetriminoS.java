package main.tetriminoes;

import main.Coordinates;
import main.Interfaces.IGameRules;
import main.TetriminoType;

import java.util.ArrayList;
import java.util.Arrays;

public class TetriminoS extends Tetrimino {

    private final ArrayList<Coordinates> startingCoords = new ArrayList<Coordinates>(Arrays.asList(new Coordinates(0, 4), new Coordinates(0, 5), new Coordinates(1, 3), new Coordinates(1, 4)));

    public TetriminoS(IGameRules gameRules) {
        super(gameRules);
        this.coordinates = startingCoords;
        this.tetriminoType = TetriminoType.tetriminoS;
        this.rotationCoords = new int[][][]{{{0, 1}, {0, 2}, {1, 0}, {1, 1}}, {{1, 1}, {2, 1}, {0, 0}, {1, 0}}};

    }

}
