package main;

import main.Interfaces.IBoard;
import main.Interfaces.IWindowsRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

public class WindowsRenderer extends JFrame implements IWindowsRenderer {


    private final ArrayList<BufferedImage> tetriminoImages = new ArrayList<>();
    private final BufferedImage borderImage;
    private final BufferedImage emptySpace;
    private final IBoard board;

    private final String gameOverText = "GAME OVER!";
    private final int IMG_SIZE_PX = 32;
    private final int LEFT_BORDER_X = 8;
    private final int RIGHT_BORDER_X = 360;
    private final int TOP_BORDER_Y = 31;
    private final int BOTTOM_BORDER_Y = 703;
    private final int RAW_BORDER_POS_X = 24;
    private final int RAW_BORDER_POS_Y = 1;
    private final int RAW_BOARD_POS_X = 40;
    private final int RAW_BOARD_POS_Y = 63;
    private final int BORDER_HEIGHT = 22;
    private final int BORDER_WIDTH = 12;
    private final Coordinates gameOverTextCoordinates = new Coordinates(75, 300);


    public WindowsRenderer(IBoard board) throws Exception {
        this.board = board;
        borderImage = ImageIO.read(new FileInputStream("src/main/sprites/tetriminoBorder.png"));
        emptySpace = ImageIO.read(new FileInputStream("src/main/sprites/tetriminoEmpty.png"));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoLine.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoL.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoJ.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoSquare.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoS.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoT.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoZ.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("src/main/sprites/tetriminoFallen.png")));
    }


    public void paintBorderImg(Graphics g, Coordinates coordinates) {
        g.drawImage(borderImage, coordinates.getX(), coordinates.getY(), IMG_SIZE_PX, IMG_SIZE_PX, null);
    }

    public void paintEmptyImg(Graphics g, Coordinates coordinates) {
        g.drawImage(emptySpace, coordinates.getX(), coordinates.getY(), IMG_SIZE_PX, IMG_SIZE_PX, null);
    }

    public void paintTetrimino(Graphics g, Coordinates coordinates, BufferedImage tetriminoImage) {
        g.drawImage(tetriminoImage, coordinates.getX(), coordinates.getY(), IMG_SIZE_PX, IMG_SIZE_PX, null);
    }


    public void paintBorder(Graphics g) {
        for (int i = 1; i <= BORDER_WIDTH; i++) {
            paintBorderImg(g, new Coordinates(i * IMG_SIZE_PX - RAW_BORDER_POS_X, TOP_BORDER_Y));
            paintBorderImg(g, new Coordinates(i * IMG_SIZE_PX - RAW_BORDER_POS_X, BOTTOM_BORDER_Y));
        }
        for (int i = 1; i <= BORDER_HEIGHT; i++) {
            paintBorderImg(g, new Coordinates(LEFT_BORDER_X, i * IMG_SIZE_PX - RAW_BORDER_POS_Y));
            paintBorderImg(g, new Coordinates(RIGHT_BORDER_X, i * IMG_SIZE_PX - RAW_BORDER_POS_Y));
        }
    }

    public void paint(Graphics g) {
        this.paintBorder(g);
        this.paintBoard(g);
    }


    public void paintBoard(Graphics g) {
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                if (board.getElement(new Coordinates(i, j)) == null) {
                    paintEmptyImg(g, new Coordinates(RAW_BOARD_POS_X + (j * IMG_SIZE_PX), i * IMG_SIZE_PX + RAW_BOARD_POS_Y));
                    continue;
                }
                paintTetrimino(g, new Coordinates(RAW_BOARD_POS_X + (j * IMG_SIZE_PX), i * IMG_SIZE_PX + RAW_BOARD_POS_Y), tetriminoImages.get(board.getElement(new Coordinates(i, j)).ordinal()));
            }
        }
    }

    public void paintGameOver(Graphics g) {
        g.setFont(g.getFont().deriveFont(40f));
        g.setColor(Color.RED);
        g.drawString(gameOverText, gameOverTextCoordinates.getX(), gameOverTextCoordinates.getY());
    }
}
