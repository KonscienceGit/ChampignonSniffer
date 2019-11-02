package gui.swingComponents;

import javax.swing.*;

import static tools.Constants.*;

public class EliteTabbedPanel extends JTabbedPane {
    public EliteTabbedPanel() {
        this.setForeground(TEXT_ELITE_COLOR);
        this.setBackground(BACK_SCREEN_ELITE_COLOR);
        this.setOpaque(true);// if false (by default) it doesn't display its background
        this.setFont(ELITE_FONT_PLAIN_28);
    }
}
