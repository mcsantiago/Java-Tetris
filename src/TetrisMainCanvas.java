import shapes.Shape;
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
        shapes.add(new SquareShape(0, 0));
        shapes.add(new LineShape(0, 2));
        shapes.add(new TShape(0, 3));
        shapes.add(new LShape(0, 4));

        setSize(uLength * canvasSquareWidth, uLength * canvasSquareHeight);
    }

    /**
     * Updates the entire canvas
     */
    public void update() {

    }

    /**
     * Draws the entire canvas
     */
    public void paint(Graphics g) {
        System.out.println("Paint call");
        GraphicsUtils.drawBorder(xPos, yPos, width, height, 5, g);

        // Draw the debug grid lines
        for (int x = 0; x < canvasSquareWidth; x++) {
            for (int y = 0; y < canvasSquareHeight; y++) {
                drawUnit(new UnitSquare(x, y, Color.WHITE), g);
            }
        }

        for (Shape shape : shapes) {
            for (UnitSquare square : shape.getSquares()) {
                drawUnit(square, g);
            }
        }
    }

    /**
     * Draws a unit square
     * @param square
     * @param g
     */
    private void drawUnit(UnitSquare square, Graphics g) {
        if (square.getCanvasX() > canvasSquareWidth ||
                square.getCanvasX() < 0 ||
                square.getCanvasY() > canvasSquareHeight ||
                square.getCanvasY() < 0) {
            throw new IndexOutOfBoundsException("Canvas position out of bounds");
        }

        int x = xPos + (square.getCanvasX() * uLength);
        int y = yPos + (square.getCanvasY() * uLength);

        g.setColor(square.getColor());
        g.fillRect(x, y, uLength, uLength);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.black);

        g.drawLine(x, y, x + 30, y);
        g.drawLine(x + 30, y, x + 30, y + 30);
        g.drawLine(x + 30, y + 30, x, y + 30);
        g.drawLine(x, y + 30, x, y);
    }
}


