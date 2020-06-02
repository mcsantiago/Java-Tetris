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

    public ArrayList<UnitSquare> getSquares() {
        return squares;
    }
}

