package gui.swingComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static tools.Constants.BACK_SCREEN_ELITE_COLOR;
import static tools.Constants.TEXT_ELITE_COLOR;

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
        this.setForeground(TEXT_ELITE_COLOR);
        this.setBackground(BACK_SCREEN_ELITE_COLOR);
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
