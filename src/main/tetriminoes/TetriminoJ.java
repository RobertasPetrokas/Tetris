package main.tetriminoes;

import main.Coordinates;
import main.Interfaces.IGameRules;
import main.TetriminoType;

import java.util.ArrayList;
import java.util.Arrays;

public class TetriminoJ extends Tetrimino {

    private final ArrayList<Coordinates> startingCoords = new ArrayList<>(Arrays.asList(new Coordinates(0, 3), new Coordinates(1, 3), new Coordinates(1, 4), new Coordinates(1, 5)));
    //private final int[][][] rotationCoords = {{{0, 2}, {1, 0}, {1, 1}, {1, 2}}, {{2, 1}, {0, 0}, {1, 0}, {2, 0}}, {{1, 0}, {0, 2}, {0, 1}, {0, 0}}, {{0, 0}, {2, 1}, {1, 1}, {0, 1}}};


    public TetriminoJ(IGameRules gameRules) {
        super(gameRules);
        this.coordinates = startingCoords;
        this.tetriminoType = TetriminoType.tetriminoJ;
        this.rotationCoords = new int[][][]{{{0, 2}, {1, 0}, {1, 1}, {1, 2}}, {{2, 1}, {0, 0}, {1, 0}, {2, 0}}, {{1, 0}, {0, 2}, {0, 1}, {0, 0}}, {{0, 0}, {2, 1}, {1, 1}, {0, 1}}};

    }


}
