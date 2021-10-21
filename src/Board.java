import java.util.ArrayList;

public class Board {

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

    public void setTetrimino(Coordinates coordinates, TetriminoType tetriminoType) {
        this.boardCoords[coordinates.getX()][coordinates.getY()] = tetriminoType;
    }


    public void checkLines() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            boolean isLineFull = true;
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (boardCoords[i][j] == null) {
                    isLineFull = false;
                    break;
                }
            }
            if (isLineFull) {
                removeFullLine(i);
            }
        }
    }

    public void removeFullLine(int fullLineIndex) {
        System.out.println("YO");
        for (int k = fullLineIndex; k > 0; k--) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                boardCoords[k][j] = boardCoords[k - 1][j];
            }
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
