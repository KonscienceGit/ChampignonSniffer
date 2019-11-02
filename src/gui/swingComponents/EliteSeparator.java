package gui.swingComponents;

import javax.swing.*;
import java.awt.*;

import static tools.Constants.BLACK_ELITE_COLOR;
import static tools.Constants.TEXT_ELITE_COLOR;

public class EliteSeparator extends JSeparator {
    public EliteSeparator(int arg) {
        super(arg);
        if (arg == 1){/*Vertical*/
            setMaximumSize(new Dimension(1,2048));
        }else if(arg == 0){/*Horizontal*/
            setMaximumSize(new Dimension(4096,1));
        }

        setBackground(BLACK_ELITE_COLOR);
        setForeground(TEXT_ELITE_COLOR);
    }
}
