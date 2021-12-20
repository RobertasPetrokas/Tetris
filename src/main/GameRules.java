package main;

import main.Interfaces.IBoard;
import main.Interfaces.IGameRules;
import main.tetriminoes.*;

import java.util.ArrayList;

public class GameRules implements IGameRules {

    private final IBoard board;

    public GameRules(IBoard board) {
        this.board = board;
    }

    public Tetrimino initRandomTetrimino() {
        switch (TetriminoType.getRandomTetrimino().ordinal()) {
            case 0 -> {
                return new TetriminoLine(this);
            }
            case 1 -> {
                return new TetriminoL(this);
            }
            case 2 -> {
                return new TetriminoJ(this);
            }
            case 3 -> {
                return new TetriminoSquare(this);
            }
            case 4 -> {
                return new TetriminoS(this);
            }
            case 5 -> {
                return new TetriminoT(this);
            }
            case 6 -> {
                return new TetriminoZ(this);
            }
        }
        return null;
    }

    public void processUserCommand(Tetrimino tetrimino, int userCommand) {
        if (userCommand == 's')
            tetrimino.moveDown();
        if (userCommand == 'a')
            tetrimino.moveLeft();
        if (userCommand == 'd')
            tetrimino.moveRight();
        if (userCommand == 'q')
            tetrimino.rotateLeft();
        if (userCommand == 'e')
            tetrimino.rotateRight();
    }


    public boolean hasFallen(Tetrimino tetrimino) {

        for (Coordinates coordinate : tetrimino.getCoordinates()) {
            if (coordinate.getX() == board.getBoardHeight() - 1 || board.getNextElement(coordinate) == TetriminoType.tetriminoFallen) {
                tetrimino.setFallen();
                return true;
            }
        }
        return false;
    }


    public boolean canMove(ArrayList<Coordinates> coordinates, int x, int y) {

        for (Coordinates coordinate : coordinates) {
            if (coordinate.getX() + x >= board.getBoardHeight() || coordinate.getY() + y < 0 || coordinate.getY() + y >= board.getBoardWidth() || board.getElement(new Coordinates(coordinate.getX() + x, coordinate.getY() + y)) != null) {
                return false;
            }
        }
        return true;
    }

    public boolean canRotate(Tetrimino tetrimino) {

        ArrayList<Coordinates> coordinates = tetrimino.getCoordinates();
        int[][][] blockCoords = tetrimino.getRotationCoords();
        int orientation = tetrimino.getOrientation();
        int highestX = tetrimino.getHighestX(coordinates);
        int lowestY = tetrimino.getLowestY(coordinates);

        for (int i = 0; i < coordinates.size(); i++) {
            int blockX = blockCoords[orientation][i][0];
            int blockY = blockCoords[orientation][i][1];
            if (blockX + highestX >= board.getBoardHeight() || blockY + lowestY >= board.getBoardWidth()) {
                return false;
            }
            if (board.getElement(new Coordinates(blockX + highestX, blockY + lowestY)) != null) {
                return false;
            }
        }
        return true;
    }


}
