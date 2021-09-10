package games;

import java.awt.*;
import javax.swing.*;

public abstract class GamePanel extends JPanel implements Runnable{

    protected static final int GAME_WIDTH = 1000;
    protected static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    protected static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    protected static final int BALL_DIAMETER = 20;
    protected static final int PADDLE_WIDTH = 25;
    protected static final int PADDLE_HEIGHT = 100;

    protected Thread gameThread;
    protected Image image;
    protected Graphics graphics;


}
