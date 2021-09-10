package games;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    protected GamePanel panel;

    protected GameFrame(){
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}
