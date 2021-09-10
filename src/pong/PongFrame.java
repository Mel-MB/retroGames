package pong;

import games.GameFrame;

public class PongFrame extends GameFrame {


    PongFrame(){
        super();
        super.panel = new PongPanel();
        this.add(panel);
        this.setTitle("Pong Game");

        this.pack();
        this.setVisible(true);

    }
}
