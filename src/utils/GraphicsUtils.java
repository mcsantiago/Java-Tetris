package utils;

import java.awt.*;

public class GraphicsUtils {
    /**
     * Draws a border around a defined rectangle
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param stroke
     * @param g
     */
    public static void drawBorder(int x, int y, int width, int height, int stroke, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.black);
        g2.setStroke(new BasicStroke(stroke));
        g.drawLine(x, y, x + width, y);
        g.drawLine(x + width, y, x + width, y + height);
        g.drawLine(x + width, y + height, x, y + height);
        g.drawLine(x, y + height, x, y);
    }
}
