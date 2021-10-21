import java.util.ArrayList;

public class GameRules {

    private final Board board;

    public GameRules(Board board) {
        this.board = board;
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
            if (coordinate.getX() == 19 || board.getNextElement(coordinate) == TetriminoType.tetriminoFallen) {
                tetrimino.setFallen();
                return true;
            }
        }
        return false;
    }


    public boolean canMove(ArrayList<Coordinates> coordinates, int x, int y) {

        for (Coordinates coordinate : coordinates) {
            if (coordinate.getX() + x >= 20 || coordinate.getY() + y < 0 || coordinate.getY() + y >= 10 || board.getElement(new Coordinates(coordinate.getX() + x, coordinate.getY() + y)) != null) {
                return false;
            }
        }
        return true;
    }

    public boolean canRotate(Tetrimino tetrimino) {

        ArrayList<Coordinates> coordinates = tetrimino.getCoordinates();
        int[][][] blockCoords = tetrimino.getThisTetriminoCoords();
        int orientation = tetrimino.getOrientation();
        int highestX = board.getHighestX(coordinates);
        int lowestY = board.getLowestY(coordinates);

        for (int i = 0; i < coordinates.size(); i++) {
            int blockX = blockCoords[orientation][i][0];
            int blockY = blockCoords[orientation][i][1];
            if (blockX + highestX >= 20 || blockY + lowestY >= 10) {
                return false;
            }
            if (board.getElement(new Coordinates(blockX + highestX, blockY + lowestY)) != null) {
                return false;
            }
        }
        return true;
    }


}
