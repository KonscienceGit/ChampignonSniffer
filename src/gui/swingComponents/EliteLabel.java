package gui.swingComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static resources.Constants.backScreenEliteColor;
import static resources.Constants.textEliteColor;

public class EliteLabel extends JLabel {
    static final int fontSize = 28;

    public EliteLabel() {
        super();
        setThings();
    }

    public EliteLabel(String string) {
        super(string);
        setThings();
    }

    private void setThings(){
        this.setForeground(textEliteColor);
        this.setBackground(backScreenEliteColor);
        //First border is the line around, second border is the margin around the text
        //this.setBorder(new CompoundBorder(BorderFactory.createLineBorder(blackEliteColor, 3), new EmptyBorder(10,10,10,10)));
        this.setBorder(new EmptyBorder(10,0,10,0));
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(new Font("Dosis", Font.PLAIN, fontSize));
    }

    public static int getFontSize() {
        return fontSize;
    }
}
