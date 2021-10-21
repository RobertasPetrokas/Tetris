import java.util.Random;

public enum TetriminoType {
    tetriminoLine, tetriminoL, tetriminoJ, tetriminoSquare, tetriminoS, tetriminoT, tetriminoZ, tetriminoFallen;

    private static final Random random = new Random();
    
    public static TetriminoType getRandomTetrimino() {
        return TetriminoType.values()[random.nextInt(TetriminoType.values().length - 1)];
    }
}
