import models.shapes.Shape;
import models.shapes.*;
import utils.GraphicsUtils;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class TetrisMainCanvas extends Canvas {
    boolean drawPauseButton = false;
    Dimension d = getSize();

    int canvasSquareWidth = 10;     // 10 squares wide
    int canvasSquareHeight = 20;    // 20 squares high
    float heightRatio = d.height / 800.0f;
    float widthRatio = d.width / 600.0f;
    int uLength = Math.round(30 * heightRatio);               // Length of each unit square

    int xPos = 10;       // top-left of the main canvas
    int yPos = 10;       // top-right of the main canvas
    int xPosMax = (int)(xPos + (canvasSquareWidth * uLength) * widthRatio);    // bottom-left of the main canvas
    int yPosMax = (int)(yPos + (canvasSquareHeight * uLength) * heightRatio);    // bottom-right of the main canvas
    int centerX = (canvasSquareWidth / 2) - 1;

    int width = xPosMax - xPos;
    int height = yPosMax - yPos;

    int level = 1;
    int lines = 0;
    int score = 0;
    float initialAspectRatio = 0.75f; // 600 / 800

    ArrayList<models.shapes.Shape> shapes;
    Shape activeShape;
    Shape nextShape;

    public TetrisMainCanvas() {
        d = getSize();
        xPos = 10;
        yPos = 10;
        heightRatio = (d.height / 800.0f) + 0.0275f;
        widthRatio = d.width / 600.0f;
        uLength = Math.round(30 * heightRatio);               // Length of each unit square
        xPosMax = Math.round((xPos + (canvasSquareWidth * uLength)) * widthRatio);    // bottom-left of the main canvas
        yPosMax = Math.round((yPos + (canvasSquareHeight * uLength)) * heightRatio);    // bottom-right of the main canvas
        width = xPosMax - xPos;
        height = yPosMax - yPos;

        shapes = new ArrayList<>();

        // The following section is for demo only
        activeShape = new IShape(centerX, canvasSquareHeight - 1);
        nextShape = pickNextShape();
        shapes.add(new JShape(centerX, 2));
        shapes.add(new OShape(centerX-3, 1));
        shapes.add(new ZShape(centerX+3, 1));
        shapes.add(new SShape(centerX+3, 3));
        shapes.add(new LShape(centerX+3, 6));
        shapes.add(new TShape(4, 3));


        // TODO: Enable the commented out section for playable game
//        activeShape = pickNextShape();
//        nextShape = pickNextShape();
//        pauseButton.setBounds(xPos + (width / 2), yPos + (height / 2), width, height);
//        add(pauseButton);

        setSize(uLength * canvasSquareWidth, uLength * canvasSquareHeight);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                recalculateDimensions();
            }
        });
    }

    void recalculateDimensions() {
        System.out.println("Recalculating those dimensions");
        d = getSize();
        xPos = 10;
        yPos = 10;
        heightRatio = (d.height / 800.0f) + 0.0275f;
        widthRatio = d.width / 600.0f;
        float aspectRatio = (float)d.width / d.height;

        if (Math.abs(aspectRatio - initialAspectRatio) < 0.04) {
            System.out.println("Aye, we're in the aspect ratio");
            uLength = Math.round(30 * heightRatio) + 1;               // Length of each unit square
            xPosMax = Math.round((xPos + (canvasSquareWidth * uLength)) * widthRatio);    // bottom-left of the main canvas
            yPosMax = Math.round((yPos + (canvasSquareHeight * uLength)) * heightRatio);    // bottom-right of the main canvas
            width = xPosMax - xPos;
            height = yPosMax - yPos;
        }
    }

    /**
     * Updates the entire canvas
     */
    public void updateStep() {
//        // Move active square down by 1
//        activeShape.decYPosition();
//
        Point currentMousePos = MouseInfo.getPointerInfo().getLocation();

        drawPauseButton = isWithinCanvas(
                currentMousePos.x - this.getLocationOnScreen().x,
                currentMousePos.y - this.getLocationOnScreen().y);

//
//        if (isCollided(activeShape)) {
//            System.out.println("Collision detected");
//            if (!activeShape.isCollidedWithFloor()) {
//                activeShape.incYPosition(); // Corrected position
//            }
//            shapes.add(activeShape);
//            activeShape = nextShape;
//            nextShape = pickNextShape();
//        }
    }

    private boolean isWithinCanvas(double x, double y) {
        return (x >= xPos && x <= xPosMax && y >= yPos && y <= yPosMax);
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
        int nextShapeId = ThreadLocalRandom.current().nextInt(0, 7);
        switch (nextShapeId) {
            case 0 -> {
                return new OShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 1 -> {
                return new TShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 2 -> {
                return new IShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 3 -> {
                return new LShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 4 -> {
                return new JShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 5 -> {
                return new SShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 6 -> {
                return new ZShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            default -> throw new IndexOutOfBoundsException("Shape not found");
        }
    }

    /**
     * Draws the entire canvas
     */
    public void paint(Graphics g) {
//        System.out.println("Paint call");
        Graphics2D g2 = (Graphics2D) g;
        GraphicsUtils.drawBorder(xPos, yPos, width, height, 5, g);
        System.out.println(widthRatio + " " + heightRatio);
        System.out.println("xPosMax: " + xPosMax + " yPosMax: " + yPosMax + " xPos: " + xPos + " yPos " + yPos + " uLength: " + uLength);

        // Draw the debug grid lines
//        for (int x = 0; x < canvasSquareWidth; x++) {
//            for (int y = 0; y < canvasSquareHeight; y++) {
//                drawUnit(new UnitSquare(x, y, Color.WHITE), g);
//            }
//        }

        drawShape(activeShape, g);

        for (Shape shape : shapes) {
            drawShape(shape, g);
        }

        // Draw preview screen
        GraphicsUtils.drawBorder(xPosMax + 50, yPos, uLength * 5, uLength * 5, 5, g);
        drawShape(nextShape, g);

        if (drawPauseButton) {
            drawButton( "PAUSE",
                    xPos + (width / 2),
                    yPos + (height / 2),
                    Color.BLUE, g);
        }

        // Draw score labels
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g2.drawString("Level: " + level, xPosMax + 50, yPos + (height / 2) - 40);
        g2.drawString("Lines: " + lines, xPosMax + 50, yPos + (height / 2));
        g2.drawString("Score: " + score, xPosMax + 50, yPos + (height / 2) + 40);

        drawButton( "QUIT",
                xPosMax + 100,
                yPos + (height - 20),
                Color.GRAY, g);
    }

    private void drawButton(String label, int x, int y, Color color, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString(label, x - (label.length() * 10), y);
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        g.drawLine(x-(label.length() * 15), y - 24, x+(label.length() * 10), y-24);
        g.drawLine(x+(label.length() * 10), y - 24, x+(label.length() * 10), y+24);
        g.drawLine(x+(label.length() * 10), y + 24, x-(label.length() * 15), y+24);
        g.drawLine(x-(label.length() * 15), y + 24, x-(label.length() * 15), y-24);
    }

    private void drawShape(Shape shape, Graphics g) {
        for (UnitSquare square : shape.getSquares()) {
            drawUnit(square, g);
        }
    }

    /**
     * Draws a unit square
     *
     * @param square Square to draw
     * @param g Graphics instance
     */
    private void drawUnit(UnitSquare square, Graphics g) {
        int x = xPos + (square.getCanvasX() * uLength);
        int maxY = (canvasSquareHeight * uLength) - yPos;
        int y = maxY - (yPos + (square.getCanvasY() * uLength)); // Invert the y axis

        g.setColor(square.getColor());
        g.fillRect(x, y, uLength, uLength);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.black);

        g.drawLine(x, y, x + uLength, y);
        g.drawLine(x + uLength, y, x + uLength, y + uLength);
        g.drawLine(x + uLength, y + uLength, x, y + uLength);
        g.drawLine(x, y + uLength, x, y);
    }
}
