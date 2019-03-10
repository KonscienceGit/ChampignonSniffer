package gui;

import javax.swing.*;
import java.awt.*;

import static resources.Resources.blackEliteColor;
import static resources.Resources.textEliteColor;

public class EliteSeparator extends JSeparator {
    public EliteSeparator(int arg) {
        super(arg);
        if (arg == 1){/*Vertical*/
            setMaximumSize(new Dimension(1,2048));
        }else if(arg == 0){/*Horizontal*/
            setMaximumSize(new Dimension(4096,1));
        }

        setBackground(blackEliteColor);
        setForeground(textEliteColor);
    }
}
