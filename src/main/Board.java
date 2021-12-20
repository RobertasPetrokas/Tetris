package main;

import main.Interfaces.IBoard;
import main.tetriminoes.Tetrimino;

import java.util.ArrayList;

public class Board implements IBoard {

    private final TetriminoType[][] boardCoords;
    private final int BOARD_HEIGHT;
    private final int BOARD_WIDTH;

    public Board() {
        this.boardCoords = new TetriminoType[][]{
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},

        };
        this.BOARD_HEIGHT = boardCoords.length;
        this.BOARD_WIDTH = boardCoords[0].length;
    }

    public TetriminoType getElement(Coordinates coordinates) {
        return this.boardCoords[coordinates.getX()][coordinates.getY()];
    }

    public TetriminoType getNextElement(Coordinates coordinates) {
        return this.boardCoords[coordinates.getX() + 1][coordinates.getY()];
    }


    public void setTetrimino(Coordinates coordinates, TetriminoType tetriminoType) {
        this.boardCoords[coordinates.getX()][coordinates.getY()] = tetriminoType;
    }


    public void boardCheck() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            if (isLineFull(i)) {
                moveOneLineDown(i);
            }
        }
    }

    public boolean isLineFull(int i) {
        for (int j = 0; j < BOARD_WIDTH; j++) {
            if (boardCoords[i][j] == null) {
                return false;
            }
        }
        return true;
    }

    public void moveOneLineDown(int fullLineIndex) {
        for (int k = fullLineIndex; k > 0; k--) {
            if (BOARD_WIDTH >= 0) System.arraycopy(boardCoords[k - 1], 0, boardCoords[k], 0, BOARD_WIDTH);
        }
    }


    public int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public void clearCurrentPos(Tetrimino tetrimino) {

        ArrayList<Coordinates> tetriminoCoordinates = tetrimino.getCoordinates();
        for (Coordinates coordinates : tetriminoCoordinates) {
            setTetrimino(coordinates, null);
        }
    }

    public void setTetriminoCoordinates(Tetrimino tetrimino) {

        ArrayList<Coordinates> tetriminoCoordinates = tetrimino.getCoordinates();
        for (Coordinates coordinates : tetriminoCoordinates) {
            setTetrimino(coordinates, tetrimino.getTetriminoType());
        }
    }
}
