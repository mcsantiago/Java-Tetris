import shapes.*;
import utils.GraphicsUtils;

import java.awt.*;
import java.util.ArrayList;


public class TetrisMainCanvas extends Canvas {
    int canvasSquareWidth = 10;     // 10 squares wide
    int canvasSquareHeight = 20;    // 20 squares high
    int uLength = 30;               // Length of each unit square

    int xPos;       // top-left of the main canvas
    int yPos;       // top-right of the main canvas
    int xPosMax;    // bottom-left of the main canvas
    int yPosMax;    // bottom-right of the main canvas
    int width = xPosMax - xPos;
    int height = yPosMax - yPos;

    ArrayList<shapes.Shape> shapes;

    public TetrisMainCanvas() {
        xPos = 10;
        yPos = 10;
        shapes = new ArrayList<>();
        shapes.add(new SquareShape(10, 10));
        shapes.add(new LineShape(10,70));
        shapes.add(new TShape(10,100));
        shapes.add(new LShape(10,130));

        setSize(uLength * canvasSquareWidth, uLength * canvasSquareHeight);
    }


    public void paint(Graphics g) {
        System.out.println("Paint call");
        GraphicsUtils.drawBorder(xPos, yPos, width, height, 5, g);

        // Draw the debug grid lines
        for (int x = 0; x < canvasSquareWidth; x++) {
            for (int y = 0; y < canvasSquareHeight; y++) {
                new UnitSquare(
                        xPos + (x * uLength),
                        yPos + (y * uLength),
                        uLength,
                        Color.white).paint(g);
            }
        }

        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).paint(g);
        }
    }
}


