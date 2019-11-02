package gui.swingComponents;

import javax.swing.*;

import static tools.Constants.*;

public class EliteButton extends JButton {
    public EliteButton() {
        this.setForeground(TEXT_ELITE_COLOR);
        this.setBackground(BACK_SCREEN_ELITE_COLOR);
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(ELITE_FONT_PLAIN_28);
    }

    public EliteButton(String string) {
        super(string);
        this.setForeground(TEXT_ELITE_COLOR);
        this.setBackground(BACK_SCREEN_ELITE_COLOR);
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(ELITE_FONT_PLAIN_28);
    }
}
