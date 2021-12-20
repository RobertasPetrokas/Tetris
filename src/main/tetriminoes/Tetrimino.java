package main.tetriminoes;

import main.Coordinates;
import main.Interfaces.IGameRules;
import main.TetriminoType;

import java.util.ArrayList;


public abstract class Tetrimino {
    private final IGameRules gameRules;
    protected int[][][] rotationCoords;
    protected ArrayList<Coordinates> startingCoords;
    protected ArrayList<Coordinates> coordinates;
    protected TetriminoType tetriminoType;
    private boolean isFallen;
    private int orientation;


    public Tetrimino(IGameRules gameRules) {
        this.gameRules = gameRules;
        this.isFallen = false;
        this.orientation = 0;
    }


    public boolean isFallen() {
        return isFallen;
    }

    public void setFallen() {
        this.isFallen = true;
        this.tetriminoType = TetriminoType.tetriminoFallen;
    }

    public ArrayList<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }


    public int[][][] getRotationCoords() {
        return rotationCoords;
    }

    public IGameRules getGameRules() {
        return gameRules;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public TetriminoType getTetriminoType() {
        return this.tetriminoType;
    }

    public void setTetriminoType(TetriminoType tetriminoType) {
        this.tetriminoType = tetriminoType;
    }

    public void moveDown() {
        if (gameRules.hasFallen(this))
            return;
        if (gameRules.canMove(this.getCoordinates(), 1, 0))
            for (Coordinates coordinate : coordinates) {
                coordinate.setX(coordinate.getX() + 1);
            }
    }

    public void moveRight() {
        if (gameRules.hasFallen(this))
            return;
        if (gameRules.canMove(this.getCoordinates(), 0, 1))
            for (Coordinates coordinate : this.coordinates) {
                coordinate.setY(coordinate.getY() + 1);
            }
    }

    public void moveLeft() {
        if (gameRules.hasFallen(this))
            return;
        if (gameRules.canMove(this.getCoordinates(), 0, -1))
            for (Coordinates coordinate : this.coordinates) {
                coordinate.setY(coordinate.getY() - 1);
            }
    }


    public void rotateRight() {
        this.orientation++;

        if (this.orientation == rotationCoords.length) {
            this.orientation = 0;
        }

        if (gameRules.canRotate(this)) {
            rotate(this.getCoordinates());
            return;
        }
        this.orientation--;
    }

    public void rotateLeft() {
        this.orientation--;

        if (this.orientation < 0) {
            this.orientation = rotationCoords.length - 1;
        }

        if (gameRules.canRotate(this)) {
            rotate(this.getCoordinates());
            return;
        }

        this.orientation++;
    }


    public void rotate(ArrayList<Coordinates> coordinates) {
        int x = getHighestX(coordinates);
        int y = getLowestY(coordinates);
        for (int i = 0; i < coordinates.size(); i++) {
            coordinates.get(i).setY(rotationCoords[orientation][i][1] + y);
            coordinates.get(i).setX(rotationCoords[orientation][i][0] + x);
        }
    }


    public int getHighestX(ArrayList<Coordinates> coordinates) {
        int HighestX = coordinates.get(0).getX();
        for (Coordinates coordinate : coordinates) {
            if (coordinate.getX() < HighestX) {
                HighestX = coordinate.getX();
            }
        }
        return HighestX;
    }

    public int getLowestY(ArrayList<Coordinates> coordinates) {
        int lowestY = coordinates.get(0).getY();
        for (Coordinates coordinate : coordinates) {
            if (coordinate.getY() < lowestY) {
                lowestY = coordinate.getY();
            }
        }
        return lowestY;
    }

}
