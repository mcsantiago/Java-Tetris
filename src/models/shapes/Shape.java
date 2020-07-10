package models.shapes;

import java.awt.*;
import java.util.ArrayList;

public abstract class Shape {
  protected int xPos, yPos;
  protected ArrayList<UnitSquare> squares;

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

  public void moveUp() {
    yPos--;
    for (UnitSquare square : squares) {
      square.incCanvasYBy(-1);
    }
  }

  public void moveDown() {
    yPos++;
    for (UnitSquare square : squares) {
      square.incCanvasYBy(1);
    }
  }

  public void moveLeft(java.util.List<Shape> shapes) {
    // Have to do this to check the xPosition of each square...
    for (UnitSquare square : squares) {
      if (square.getCanvasX() <= 0) {
        return;
      }
      for (Shape shape : shapes) {
        for (UnitSquare other : shape.squares) {
          if (square.getCanvasX() - 1 == other.getCanvasX()
              && square.getCanvasY() == other.getCanvasY()) {
            return; // Don't run into other shapes
          }
        }
      }
    }

    xPos--;
    for (UnitSquare square : squares) {
      square.incCanvasXBy(-1);
    }
  }

  public void moveRight(java.util.List<Shape> shapes) {
    // Have to do this to check the xPosition of each square...
    for (UnitSquare square : squares) {
      if (square.getCanvasX() > 8) {
        return;
      }
      for (Shape shape : shapes) {
        for (UnitSquare other : shape.squares) {
          if (square.getCanvasX() + 1 == other.getCanvasX()
              && square.getCanvasY() == other.getCanvasY()) {
            return; // Don't run into other shapes
          }
        }
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

  public boolean isCollidedWithShape(Shape shape) {
    for (UnitSquare square : squares) {
      for (UnitSquare other : shape.squares) {
        if ((square.getCanvasX() == other.getCanvasX())
            && (square.getCanvasY() == other.getCanvasY())) {
          System.out.println(this.getClass() + " collided with shape");
          moveUp(); // Collision resolution
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

  /** Rotates the shape clockwise */
  public void rotateClockwise(java.util.List<Shape> shapes) {
    UnitSquare pivot = squares.get(1);
    int pivotX = pivot.getCanvasX();
    int pivotY = pivot.getCanvasY();

    // Check if any squares outside of pivot are against right wall. If so, then we are at risk of
    // clipping out. Abort!

    for (UnitSquare square : squares) {
      if (square != pivot) {
        if (square.getCanvasX() == 19) {
          return;
        }
      }
    }

    for (UnitSquare square : squares) {
      if (square != pivot) {
        // Translate
        int newCanvasX = square.getCanvasX() - pivotX;
        int newCanvasY = square.getCanvasY() - pivotY;

        // Rotate 90
        int tmpX = newCanvasX;
        newCanvasX = -newCanvasY;
        newCanvasY = tmpX;


        square.setCanvasX(newCanvasX + pivotX);
        square.setCanvasY(newCanvasY + pivotY);
      }
    }
  }

  /** Rotates the shape counter-clockwise */
  public void rotateCounterClockwise(java.util.List<Shape> shapes) {
    UnitSquare pivot = squares.get(1);
    int pivotX = pivot.getCanvasX();
    int pivotY = pivot.getCanvasY();

    // Check if any squares outside of pivot are against right wall. If so, then we are at risk of
    // clipping out. Abort!

    for (UnitSquare square : squares) {
      if (square != pivot) {
        if (square.getCanvasX() == 0) {
          return;
        }
      }
    }

    for (UnitSquare square : squares) {
      if (square != pivot) {
        // Translate
        int newCanvasX = square.getCanvasX() - pivotX;
        int newCanvasY = square.getCanvasY() - pivotY;

        // Rotate 90
        int tmpX = newCanvasX;
        newCanvasX = newCanvasY;
        newCanvasY = -tmpX;


        square.setCanvasX(newCanvasX + pivotX);
        square.setCanvasY(newCanvasY + pivotY);
      }
    }
  }

  public byte[] getPositionsInLine(int row) {
    byte[] positions = new byte[squares.size()];
    for (int i = 0; i < positions.length; i++) {
      positions[i] = -1;
    }

    for (int i = 0; i < squares.size(); i++) {
      UnitSquare square = squares.get(i);
      if (square.getCanvasY() == row) {
        positions[i] = (byte) square.getCanvasX();
      }
    }

    return positions;
  }

  public void removeSquaresInRow(int row) {
    squares.removeIf(s -> s.getCanvasY() == row);
    boolean isAboveLine = squares.stream().anyMatch(s -> s.getCanvasY() < row);
    if (isAboveLine)
      moveDown();
  }
}
