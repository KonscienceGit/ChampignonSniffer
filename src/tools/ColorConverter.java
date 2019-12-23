package tools;

import java.awt.*;

public class ColorConverter {

    /**
     * Compute color based on temperature.
     * @param temp the temperature in °C or °K
     * @return the RGB color.
     */
    public static Color getColor(float temp){
        temp /= 100;

        float red;
        if(temp <= 66){
            red = 255;
        } else {
            red = temp - 60;
            red = (float) (329.698727446 * Math.pow(red, -0.1332047592));
        }

        if (red < 0) red = 0;
        if (red > 255) red = 255;

        float green;
        if (temp <= 66){
            green = temp;
            green = (float) (99.4708025861 * Math.log(green) - 161.1195681661);
        }else{
            green = temp - 60;
            green = (float) (288.1221695283 * Math.pow(green, -0.0755148492));
        }

        if (green < 0) green = 0;
        if (green > 255) green = 255;

        float blue;
        if (temp >= 66) blue = 255;
        else{
            if (temp <= 19) blue = 0;
            else{
                blue = temp - 10;
                blue = (float) (138.5177312231 * Math.log(blue) - 305.0447927307);
            }
        }

        if (blue < 0) blue = 0;
        if (blue > 255) blue = 255;

        return new Color((int)red, (int)green, (int)blue);
    }
}
