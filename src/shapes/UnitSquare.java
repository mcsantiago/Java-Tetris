package shapes;

import java.awt.*;

public class UnitSquare {
    int canvasX; // x-pos of the topLeft corner (Canvas Units)
    int canvasY; // y-pos of the topLeft corner (Canvas Units)
    Color color; // Color of the square

    public UnitSquare(int canvasX, int canvasY, Color color) {
        this.canvasX = canvasX;
        this.canvasY = canvasY;
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

    /** Decrements CanvasX by 1 */
    public void incCanvasX() {
        this.canvasX++;
    }

    /** Increments CanvasY by 1 */
    public void incCanvasY() {
        this.canvasY++;
    }

    /** Decrements CanvasY by 1 */
    public void decCanvasY() {
        this.canvasY--;
    }
}
