package gui;

import javax.swing.*;

import static resources.Resources.*;

public class ElitePanel extends JPanel{

    public ElitePanel(){
        this.setForeground(textEliteColor);
        this.setBackground(backScreenEliteColor);
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(eliteFontPlain28);
    }
}
