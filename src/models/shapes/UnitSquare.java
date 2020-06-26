package models.shapes;

import java.awt.*;

public class UnitSquare {
  int canvasX; // x-pos of the topLeft corner (Canvas Units)
  int canvasY; // y-pos of the topLeft corner (Canvas Units)
  Color color; // Color of the square

  public UnitSquare(int canvasX, int canvasY, Color color) {
    this.canvasX = canvasX;
    this.canvasY = 19 - canvasY; // TODO: A bit of a hack to invert y axis.. should refactor
    this.color = color;
  }

  public int getCanvasX() {
    return canvasX;
  }

  public int getCanvasY() {
    return canvasY;
  }

  public Color getColor() {
    return color;
  }

  public void incCanvasXBy(int i) {
    this.canvasX += i;
  }

  public void incCanvasYBy(int i) {
    this.canvasY += i;
  }
}
