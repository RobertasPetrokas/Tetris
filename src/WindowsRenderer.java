import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

public class WindowsRenderer extends JFrame {


    private final ArrayList<BufferedImage> tetriminoImages = new ArrayList<>();
    private final BufferedImage borderImage;
    private final BufferedImage emptySpace;
    private final Board board;


    public WindowsRenderer(Board board) throws Exception {
        this.board = board;
        borderImage = ImageIO.read(new FileInputStream("tetriminoBorder.png"));
        emptySpace = ImageIO.read(new FileInputStream("tetriminoEmpty.png"));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoLine.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoL.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoJ.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoSquare.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoS.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoT.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoZ.png")));
        tetriminoImages.add(ImageIO.read(new FileInputStream("tetriminoFallen.png")));
    }


    public void paintBorderImg(Graphics g, Coordinates coordinates) {
        g.drawImage(borderImage, coordinates.getX(), coordinates.getY(), 32, 32, null);
    }

    public void paintEmptyImg(Graphics g, Coordinates coordinates) {
        g.drawImage(emptySpace, coordinates.getX(), coordinates.getY(), 32, 32, null);
    }

    public void paintTetrimino(Graphics g, Coordinates coordinates, BufferedImage tetriminoImage) {
        g.drawImage(tetriminoImage, coordinates.getX(), coordinates.getY(), 32, 32, null);
    }

    public void paintBorder(Graphics g) {
        for (int i = 1; i <= 12; i++) {
            paintBorderImg(g, new Coordinates(i * 32 - 24, 31));
            paintBorderImg(g, new Coordinates(i * 32 - 24, 703));
        }
        for (int i = 1; i <= 22; i++) {
            paintBorderImg(g, new Coordinates(8, i * 32 - 1));
            paintBorderImg(g, new Coordinates(360, i * 32 - 1));
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
                    paintEmptyImg(g, new Coordinates(40 + (j * 32), i * 32 + 63));
                    continue;
                }
                paintTetrimino(g, new Coordinates(40 + (j * 32), i * 32 + 63), tetriminoImages.get(board.getElement(new Coordinates(i, j)).ordinal()));
            }
        }
    }

    public void paintGameOver(Graphics g) {
        g.setFont(g.getFont().deriveFont(40f));
        g.setColor(Color.RED);
        g.drawString("GAME OVER!", 75, 300);
    }
}
