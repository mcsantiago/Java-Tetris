package shapes;

import java.awt.*;
import java.util.ArrayList;

public abstract class Shape {
  int xPos, yPos;
  ArrayList<UnitSquare> squares;

  public Shape(int xPos, int yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
    squares = new ArrayList<>();
  }

  public abstract Color getColor();

  public void decYPosition() {
    yPos--;
    for (UnitSquare square : squares) {
      square.decCanvasY();
    }
  }

  public ArrayList<UnitSquare> getSquares() {
    return squares;
  }

  public boolean isCollidedWith(Shape shape) {
    for (UnitSquare square : squares) {
      for (UnitSquare other : shape.squares) {
        if ((square.canvasX == other.canvasX) && (square.canvasY == other.canvasY)) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean isCollidedWithFloor() {
    for (UnitSquare square : squares) {
      if (square.getCanvasY() <= 0) {
        return true;
      }
    }
    return false;
  }

  public void incYPosition() {
    yPos++;
    for (UnitSquare square : squares) {
      square.incCanvasY();
    }
  }
}
