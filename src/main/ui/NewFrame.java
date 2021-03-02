package ui;

import javax.swing.*;
import java.awt.*;

//This class represents an object that constructs a personalized new frame
public class NewFrame extends JFrame {

    //Constructs a new instance of a NewFrame with features common to all JFrames in the project
    public NewFrame(String s) {
        this.setTitle(s);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.green);
        this.getContentPane().setBackground(Color.lightGray);
        this.setVisible(false);
    }
}
