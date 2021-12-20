package main;

import main.Interfaces.IBoard;
import main.Interfaces.IGameRules;
import main.Interfaces.IWindowsRenderer;
import main.tetriminoes.Tetrimino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame implements KeyListener {

    private final IBoard board = new Board();
    private final IWindowsRenderer windowsRenderer = new WindowsRenderer(board);
    private final IGameRules gameRules = new GameRules(board);
    private Tetrimino tetrimino = gameRules.initRandomTetrimino();

    private boolean isGameOver = false;

    public Main() throws Exception {
        int WINDOW_WIDTH = 400;
        int WINDOW_HEIGHT = 743;
        super.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        super.pack();
        super.setVisible(true);
        super.addKeyListener(this);
        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        board.setTetriminoCoordinates(tetrimino);
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try {
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if (isGameOver) {
            windowsRenderer.paintGameOver(g);
            return;
        }
        windowsRenderer.paint(g);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int userCommand = e.getKeyChar();

        board.clearCurrentPos(tetrimino);

        gameRules.processUserCommand(tetrimino, userCommand);

        board.setTetriminoCoordinates(tetrimino);

        if (tetrimino.isFallen()) {
            board.boardCheck();
            tetrimino = gameRules.initRandomTetrimino();
            board.setTetriminoCoordinates(tetrimino);
            if (gameRules.hasFallen(tetrimino)) {
                isGameOver = true;
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }


}
