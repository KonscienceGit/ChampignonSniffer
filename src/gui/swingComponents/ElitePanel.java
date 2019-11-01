package gui.swingComponents;

import javax.swing.*;

import static resources.Constants.*;

public class ElitePanel extends JPanel{

    public ElitePanel(){
        this.setForeground(textEliteColor);
        this.setBackground(backScreenEliteColor);
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(eliteFontPlain28);
    }
}
