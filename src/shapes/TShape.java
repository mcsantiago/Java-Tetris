package shapes;

import java.awt.*;

public class TShape extends Shape {
    Color color = Color.RED;

    public TShape(int xPos, int yPos) {
        super(xPos, yPos);
        squares.add(new UnitSquare(xPos, yPos, 30, color));
        squares.add(new UnitSquare(xPos + 30, yPos, 30, color));
        squares.add(new UnitSquare(xPos + 60, yPos, 30, color));
        squares.add(new UnitSquare(xPos + 30, yPos + 30, 30, color));
    }

    @Override
    public void update(int millis) {

    }
}
