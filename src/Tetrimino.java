import java.util.ArrayList;

public class Tetrimino implements Cloneable {
    private final int[][][][] tetriminoCoords = {
            {{{0, 0}, {1, 0}, {2, 0}, {3, 0}}, {{0, 0}, {0, 1}, {0, 2}, {0, 3}}}, //tetriminoLine
            {{{0, 2}, {1, 0}, {1, 1}, {1, 2}}, {{2, 1}, {0, 0}, {1, 0}, {2, 0}}, {{1, 0}, {0, 2}, {0, 1}, {0, 0}}, {{0, 0}, {2, 1}, {1, 1}, {0, 1}}}, //tetriminoL
            {{{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 1}, {0, 0}, {1, 0}, {2, 0}}, {{1, 2}, {0, 2}, {0, 1}, {0, 0}}, {{2, 0}, {2, 1}, {1, 1}, {0, 1}}},  //tetriminoJ
            {{{0, 0}, {1, 0}, {0, 1}, {1, 1}}}, //tetriminoSquare
            {{{0, 1}, {0, 2}, {1, 0}, {1, 1}}, {{1, 1}, {2, 1}, {0, 0}, {1, 0}}}, //tetriminoS
            {{{0, 0}, {0, 1}, {0, 2}, {1, 1}}, {{0, 1}, {1, 1}, {2, 1}, {1, 0}}, {{1, 2}, {1, 1}, {1, 0}, {0, 1}}, {{2, 0}, {1, 0}, {0, 0}, {1, 1}}}, //tetriminoT
            {{{0, 0}, {0, 1}, {1, 1}, {1, 2}}, {{0, 1}, {1, 0}, {1, 1}, {2, 0}}} //tetriminoZ
    };
    private final Board board;
    private final GameRules gameRules;
    private TetriminoType tetriminoType;
    private ArrayList<Coordinates> coordinates = new ArrayList<>();
    private boolean isFallen;
    private int orientation;


    public Tetrimino(GameRules gameRules, Board board) {
        this.gameRules = gameRules;
        this.tetriminoType = TetriminoType.getRandomTetrimino();
        this.isFallen = false;
        this.board = board;
        this.orientation = 0;
        int tetriminoTypeIndex = tetriminoType.ordinal();

        for (int i = 0; i < 4; i++) {
            this.coordinates.add(new Coordinates(tetriminoCoords[tetriminoTypeIndex][this.orientation][i][0], tetriminoCoords[tetriminoTypeIndex][this.orientation][i][1] + 3));
        }

    }

    public Tetrimino(Tetrimino t) {
        this.board = t.board;
        this.gameRules = t.gameRules;
        this.coordinates = t.coordinates;
        this.orientation = t.orientation;
        this.tetriminoType = t.tetriminoType;
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

    public int[][][] getThisTetriminoCoords() {
        return tetriminoCoords[this.tetriminoType.ordinal()];
    }

    public int getOrientation() {
        return orientation;
    }

    public TetriminoType getTetriminoType() {
        return this.tetriminoType;
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
        if (this.tetriminoType == TetriminoType.tetriminoSquare) {
            return;
        }

        this.orientation++;

        if (this.orientation == tetriminoCoords[tetriminoType.ordinal()].length) {
            this.orientation = 0;
        }

        if (gameRules.canRotate(this)) {
            rotate(this.getCoordinates());
            return;
        }
        this.orientation--;
    }

    public void rotateLeft() {
        if (this.tetriminoType == TetriminoType.tetriminoSquare) {
            return;
        }

        this.orientation--;

        if (this.orientation < 0) {
            this.orientation = tetriminoCoords[tetriminoType.ordinal()].length - 1;
        }

        if (gameRules.canRotate(this)) {
            rotate(this.getCoordinates());
            return;
        }

        this.orientation++;
    }


    public void rotate(ArrayList<Coordinates> coordinates) {
        int x = board.getHighestX(coordinates);
        int y = board.getLowestY(coordinates);
        for (int i = 0; i < coordinates.size(); i++) {
            coordinates.get(i).setY(tetriminoCoords[tetriminoType.ordinal()][orientation][i][1] + y);
            coordinates.get(i).setX(tetriminoCoords[tetriminoType.ordinal()][orientation][i][0] + x);
        }
    }

}
