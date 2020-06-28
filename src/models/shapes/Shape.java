package models.shapes;

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

  public void setxPos(int xPos) {
    int dx = xPos - this.xPos;
    System.out.println("currentXPos: " + this.xPos + " destinationXPos: " + xPos + " dx: " + dx);
    this.xPos += dx;
    for (UnitSquare square : squares) {
      square.incCanvasXBy(dx);
    }
  }

  public void decYPosition() {
    yPos--;
    for (UnitSquare square : squares) {
      square.incCanvasYBy(-1);
    }
  }

  public void incYPosition() {
    yPos++;
    for (UnitSquare square : squares) {
      square.incCanvasYBy(1);
    }
  }

  public void decXPosition() {
    // Have to do this to check the xPosition of each square...
    for (UnitSquare square : squares) {
      if (square.getCanvasX() <= 0) {
        return;
      }
    }
    xPos--;
    for (UnitSquare square : squares) {
      square.incCanvasXBy(-1);
    }
  }

  public void incXPosition() {
    // Have to do this to check the xPosition of each square...
    for (UnitSquare square : squares) {
      if (square.getCanvasX() > 8) {
        return;
      }
    }
    System.out.println("xPos: " + xPos);
    xPos++;
    for (UnitSquare square : squares) {
      square.incCanvasXBy(1);
    }
  }

  public ArrayList<UnitSquare> getSquares() {
    return squares;
  }

  public boolean isCollidedWith(Shape shape) {
    for (UnitSquare square : squares) {
      for (UnitSquare other : shape.squares) {
        if ((square.getCanvasX() == other.getCanvasX()) && (square.getCanvasY() == other.getCanvasY())) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean isCollidedWithFloor() {
    for (UnitSquare square : squares) {
      if (square.getCanvasY() >= 19) {
        System.out.println(this.getClass() + " collided with floor");
        return true;
      }
    }
    return false;
  }
}
