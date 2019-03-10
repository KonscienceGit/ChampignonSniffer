package gui;

import javax.swing.*;
import java.awt.*;

import static resources.Resources.blackEliteColor;

public class EliteSpace extends JSeparator {
    public EliteSpace(int arg) {
        super(arg);
        if (arg == 1){/*Vertical*/
            setPreferredSize(new Dimension(1,2048));
        }else if(arg == 0){/*Horizontal*/
            setPreferredSize(new Dimension(4096,1));
        }

        setBackground(blackEliteColor);
        setForeground(blackEliteColor);
    }
}
