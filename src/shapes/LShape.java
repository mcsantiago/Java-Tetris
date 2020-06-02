package shapes;

import java.awt.*;

public class LShape extends Shape {
    public LShape(int xPos, int yPos) {
        super(xPos, yPos);
        squares.add(new UnitSquare(xPos, yPos, getColor()));
        squares.add(new UnitSquare(xPos, yPos - 1, getColor()));
        squares.add(new UnitSquare(xPos, yPos - 2, getColor()));
        squares.add(new UnitSquare(xPos + 1, yPos - 2, getColor()));
    }

    @Override
    public Color getColor() {
        return Color.green;
    }
}
