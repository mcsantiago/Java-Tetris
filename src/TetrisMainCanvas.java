import models.buttons.PauseButton;
import models.buttons.QuitButton;
import models.shapes.Shape;
import models.shapes.*;
import utils.GraphicsUtils;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class TetrisMainCanvas extends Canvas {
    boolean drawPauseButton = false;
    Dimension d;

    int canvasSquareWidth = 10;     // 10 squares wide
    int canvasSquareHeight = 20;    // 20 squares high
    float heightRatio;
    float widthRatio;
    int uLength;               // Length of each unit square

    int xPos;       // top-left of the main canvas
    int yPos;       // top-right of the main canvas
    int canvasXMax;    // bottom-left of the main canvas
    int canvasYMax;    // bottom-right of the main canvas
    int centerX = (canvasSquareWidth / 2) - 1;

    int width;
    int height;

    int level = 1;
    int lines = 0;
    int score = 0;

    Font buttonFont = new Font("Arial", Font.BOLD, 23);
    Font defaultFont = new Font("Arial", Font.PLAIN, 16);

    PauseButton pauseButton = new PauseButton();
    QuitButton quitButton = new QuitButton();

    ArrayList<models.shapes.Shape> shapes;
    Shape activeShape;
    Shape nextShape;

    public TetrisMainCanvas() {
        d = new Dimension(600, 800);
        xPos = 10;
        yPos = 10;
        heightRatio = (d.height / 800.0f) + 0.0275f;
        widthRatio = d.width / 600.0f;
        uLength = Math.round(30 * heightRatio);               // Length of each unit square
        canvasXMax = Math.round((xPos + (canvasSquareWidth * uLength)) * widthRatio);    // bottom-left of the main canvas
        canvasYMax = Math.round((yPos + (canvasSquareHeight * uLength)) * heightRatio);    // bottom-right of the main canvas
        width = canvasXMax - xPos;
        height = canvasYMax - yPos;

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
                recalculateSize(recalculateDimensions(getSize()));
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point currentMousePos = MouseInfo.getPointerInfo().getLocation();
                Point p = e.getPoint();
                System.out.println(p);
                if (quitButton.isWithinButton(p.x, p.y)) {
                    System.exit(0);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Point p = e.getLocationOnScreen();
            }
        });
    }

    Dimension recalculateDimensions(Dimension newSize) {
        int bound_width = newSize.width;
        int bound_height = newSize.height;
        int new_width = d.width;
        int new_height = d.height;

        // first check if we need to scale width
        if (d.width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * d.height) / d.width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * d.width) / d.height;
        }

        return new Dimension(new_width, new_height);
    }

    void recalculateSize(Dimension boundary) {
//        System.out.println("Recalculating those dimensions");
        heightRatio = (boundary.height / 800.0f) + 0.0275f;
        widthRatio = boundary.width / 600.0f;

        canvasXMax = Math.round((xPos + (canvasSquareWidth * uLength)) * widthRatio);    // bottom-left of the main canvas
        canvasYMax = Math.round((yPos + (canvasSquareHeight * uLength)) * heightRatio);    // bottom-right of the main canvas
        width = canvasXMax - xPos;
        height = canvasYMax - yPos;
        uLength = Math.round((float) height/ canvasSquareHeight);               // Length of each unit square
    }

    /**
     * Updates the entire canvas
     */
    public void updateStep() {
//        // Move active square down by 1
//        activeShape.decYPosition();
//
        Point currentMousePos = MouseInfo.getPointerInfo().getLocation();

        double relativeMouseX = currentMousePos.x - this.getLocationOnScreen().x;
        double relativeMouseY = currentMousePos.y - this.getLocationOnScreen().y;

        boolean isPauseButtonVisible = isWithinCanvas(relativeMouseX, relativeMouseY);

        pauseButton.setVisible(isPauseButtonVisible);

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
        return (x >= xPos && x <= canvasXMax && y >= yPos && y <= canvasYMax);
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
                return new IShape(canvasSquareWidth + 2, canvasSquareHeight - 3);
            }
            case 3 -> {
                return new LShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 4 -> {
                return new JShape(canvasSquareWidth + 3, canvasSquareHeight - 3);
            }
            case 5 -> {
                return new SShape(canvasSquareWidth + 4, canvasSquareHeight - 3);
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
        Graphics2D g2 = (Graphics2D) g;
        GraphicsUtils.drawBorder(xPos, yPos, width, height, 5, g);
//        System.out.println("widthRatio: " + widthRatio + " heightRatio: " + heightRatio);
//        System.out.println("xPosMax: " + canvasXMax + " yPosMax: " + canvasYMax + " xPos: " + xPos + " yPos " + yPos + " uLength: " + uLength + " width: " + width + " height: " + height);

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
        GraphicsUtils.drawBorder(canvasXMax + 50, yPos, uLength * 5, uLength * 5, 5, g);
        drawShape(nextShape, g);

        if (pauseButton.isVisible()) {
            pauseButton.draw(xPos + (width / 2), yPos + (height / 2), g);
        }

        // Draw score labels
//        System.out.println("Drawing labels");
        g.setColor(Color.BLACK);
//        System.out.println("\tSet color");
        g.setFont(defaultFont);
//        System.out.println("\tSet font");
        g2.drawString("Level: " + level, canvasXMax + 50, yPos + (height / 2) - 40);
//        System.out.println("\tLevel: " + level);
        g2.drawString("Lines: " + lines, canvasXMax + 50, yPos + (height / 2));
//        System.out.println("\tLines: " + lines);
        g2.drawString("Score: " + score, canvasXMax + 50, yPos + (height / 2) + 40);
//        System.out.println("\tScore: " + score);

//        System.out.println("Drawing buttons");
        quitButton.draw(canvasXMax + 100, yPos + (height - 20), g);
    }

    // TODO: This function is to be deprecated in favor of Button classes
    void drawButton(String label, int x, int y, Color color, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(color);
        g.setFont(buttonFont);
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

