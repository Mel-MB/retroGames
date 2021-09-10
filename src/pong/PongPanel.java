package pong;

import games.GamePanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PongPanel extends GamePanel {
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    PongPanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);

        this.setFocusable(true);
        this.addKeyListener(new KListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newPaddles() {
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    private void newBall() {
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),new Random().nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
    }

    private void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
        Toolkit.getDefaultToolkit().sync();
    }



    private void checkCollision() {

        // stop paddles at window edges

        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        // bounce ball off vertical screen limits
        if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        //bounce ball off paddles

        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

    }



    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class KListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }


}
