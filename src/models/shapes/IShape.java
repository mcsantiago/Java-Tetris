package models.shapes;

import java.awt.*;

public class IShape extends Shape {
  public IShape(int xPos, int yPos) {
    super(xPos, yPos);
    squares.add(new UnitSquare(xPos, yPos, getColor()));
    squares.add(new UnitSquare(xPos + 1, yPos, getColor()));
    squares.add(new UnitSquare(xPos + 2, yPos, getColor()));
    squares.add(new UnitSquare(xPos + 3, yPos, getColor()));
  }

  @Override
  public Color getColor() {
    return Color.yellow;
  }
}
