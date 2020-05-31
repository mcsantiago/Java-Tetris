package shapes;

import interfaces.Paintable;
import interfaces.Updateable;

import java.awt.*;
import java.util.ArrayList;

public abstract class Shape implements Paintable, Updateable {
    int xPos, yPos;
    ArrayList<UnitSquare> squares;

    public Shape(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        squares = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).paint(g);
        }
    }
}

