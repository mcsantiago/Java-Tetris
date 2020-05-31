package shapes;

import java.awt.*;

public class LShape extends Shape {
    Color color = Color.green;

    public LShape(int xPos, int yPos) {
        super(xPos, yPos);
        squares.add(new UnitSquare(xPos, yPos, 30, color));
        squares.add(new UnitSquare(xPos, yPos + 30, 30, color));
        squares.add(new UnitSquare(xPos, yPos + 60, 30, color));
        squares.add(new UnitSquare(xPos + 30, yPos + 60, 30, color));
    }

    @Override
    public void update(int millis) {
        
    }
}
