package models.shapes;

import java.awt.*;

public class SShape extends Shape {
  public SShape(int xPos, int yPos) {
    super(xPos, yPos);
    squares.add(new UnitSquare(xPos, yPos, getColor()));
    squares.add(new UnitSquare(xPos + 1, yPos, getColor()));
    squares.add(new UnitSquare(xPos, yPos - 1, getColor()));
    squares.add(new UnitSquare(xPos-1, yPos-1, getColor()));
  }

  @Override
  public Color getColor() {
    return Color.green;
  }
}
