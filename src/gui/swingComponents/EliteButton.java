package gui.swingComponents;

import javax.swing.*;

import static resources.Constants.*;

public class EliteButton extends JButton {
    public EliteButton() {
        this.setForeground(textEliteColor);
        this.setBackground(backScreenEliteColor);
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(eliteFontPlain28);
    }

    public EliteButton(String string) {
        super(string);
        this.setForeground(textEliteColor);
        this.setBackground(backScreenEliteColor);
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(eliteFontPlain28);
    }
}
