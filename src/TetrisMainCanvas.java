import shapes.*;
import shapes.Shape;
import utils.GraphicsUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class TetrisMainCanvas extends Canvas {
    int canvasSquareWidth = 10;     // 10 squares wide
    int canvasSquareHeight = 20;    // 20 squares high
    int uLength = 30;               // Length of each unit square

    int xPos;       // top-left of the main canvas
    int yPos;       // top-right of the main canvas
    int xPosMax;    // bottom-left of the main canvas
    int yPosMax;    // bottom-right of the main canvas
    int centerX = (canvasSquareWidth / 2) - 1;

    int width = xPosMax - xPos;
    int height = yPosMax - yPos;

    ArrayList<shapes.Shape> shapes;
    Shape activeShape;
    Shape nextShape;

    Random rng = new Random();

    public TetrisMainCanvas() {
        xPos = 10;
        yPos = 10;
        shapes = new ArrayList<>();
        activeShape = pickNextShape();

        setSize(uLength * canvasSquareWidth, uLength * canvasSquareHeight);
    }

    /**
     * Updates the entire canvas
     */
    public void updateStep() {
        // Move active square down by 1
        activeShape.decYPosition();

        if (isCollided(activeShape)) {
            System.out.println("Collision detected");
            if (!activeShape.isCollidedWithFloor()) {
                activeShape.incYPosition(); // Corrected position
            }
            shapes.add(activeShape);
            activeShape = pickNextShape();
        }
    }

    private boolean isCollided(Shape activeShape) {
        boolean collided = false;
        for (Shape shape : shapes) {
            collided |= (activeShape.isCollidedWith(shape));
        }
        System.out.println("Collided = " + collided);
        return collided || activeShape.isCollidedWithFloor();
    }

    /**
     * Spawns new shape
     */
    private Shape pickNextShape() {
        int nextShapeId = ThreadLocalRandom.current().nextInt(0, 4);
        switch (nextShapeId) {
            case 0 -> {
                return new SquareShape(centerX, canvasSquareHeight - 1);
            }
            case 1 -> {
                return new TShape(centerX, canvasSquareHeight - 1);
            }
            case 2 -> {
                return new LineShape(centerX, canvasSquareHeight - 1);
            }
            case 3 -> {
                return new LShape(centerX, canvasSquareHeight - 1);
            }
            default -> {
                throw new IndexOutOfBoundsException("Shape not found");
            }
        }
    }

    /**
     * Draws the entire canvas
     */
    public void paint(Graphics g) {
//        System.out.println("Paint call");
        GraphicsUtils.drawBorder(xPos, yPos, width, height, 5, g);

        // Draw the debug grid lines
        for (int x = 0; x < canvasSquareWidth; x++) {
            for (int y = 0; y < canvasSquareHeight; y++) {
                drawUnit(new UnitSquare(x, y, Color.WHITE), g);
            }
        }

        drawShape(activeShape, g);

        for (Shape shape : shapes) {
            drawShape(shape, g);
        }
    }

    private void drawShape(Shape shape, Graphics g) {
        for (UnitSquare square : shape.getSquares()) {
            drawUnit(square, g);
        }
    }

    /**
     * Draws a unit square
     *
     * @param square
     * @param g
     */
    private void drawUnit(UnitSquare square, Graphics g) {
        if (square.getCanvasX() > canvasSquareWidth ||
                square.getCanvasX() < 0 ||
                square.getCanvasY() > canvasSquareHeight ||
                square.getCanvasY() < 0) {
            throw new IndexOutOfBoundsException("Square position out of bounds of Canvas: (" + square.getCanvasX() + ", " + square.getCanvasY() + ")");
        }

        int x = xPos + (square.getCanvasX() * uLength);
        int maxY = (canvasSquareHeight * uLength);
        int y = maxY - (yPos + (square.getCanvasY() * uLength)); // Invert the y axis

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


