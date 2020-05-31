package shapes;

import interfaces.Paintable;
import utils.GraphicsUtils;

import java.awt.*;

public class UnitSquare implements Paintable {
    int length;
    int xPos; // x-pos of the topLeft corner
    int yPos; // y-pos of the topLeft corner
    Color color; // Color of the square

    public UnitSquare(int xPos, int yPos, int length, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.length = length;
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(xPos, yPos, length, length);
        GraphicsUtils.drawBorder(xPos, yPos, length, length, 2, g);
    }
}
