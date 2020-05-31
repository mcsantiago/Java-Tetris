package shapes;

import java.awt.*;

public class SquareShape extends Shape {
    Color color = Color.blue;

    public SquareShape(int xPos, int yPos) {
        super(xPos, yPos);
        squares.add(new UnitSquare(xPos, yPos, 30, color));
        squares.add(new UnitSquare(xPos + 30, yPos, 30, color));
        squares.add(new UnitSquare(xPos + 30, yPos + 30, 30, color));
        squares.add(new UnitSquare(xPos, yPos + 30, 30, color));
    }

    @Override
    public void update(int millis) {

    }
}
