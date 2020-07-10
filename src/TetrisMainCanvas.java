import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import models.buttons.GameOverLabel;
import models.buttons.PauseButton;
import models.buttons.QuitButton;
import models.shapes.*;
import models.shapes.Shape;
import utils.GraphicsUtils;

@SuppressWarnings("serial")
public class TetrisMainCanvas extends DoubleBuffer {
  private Dimension d;

  private int canvasSquareWidth = 10; // 10 squares wide
  private int canvasSquareHeight = 20; // 20 squares high
  private float heightRatio;
  private float widthRatio;
  private int uLength; // Length of each unit square
  private int prevULength;

  private int xPos; // top-left of the main canvas
  private int yPos; // top-right of the main canvas
  private int canvasXMax; // bottom-left of the main canvas
  private int canvasYMax; // bottom-right of the main canvas
  private int centerX = (canvasSquareWidth / 2) - 1;

  private int width;
  private int height;

  private int level = 1;
  private int lines = 0;
  private int score = 0;

  private Font defaultFont = new Font("Arial", Font.PLAIN, 16);

  private PauseButton pauseButton = new PauseButton();
  private QuitButton quitButton = new QuitButton();
  private GameOverLabel gameOverLabel = new GameOverLabel();

  private ArrayList<models.shapes.Shape> shapes;
  private Shape activeShape;
  private Shape nextShape;

  private int M = 1, N = 1, S = 1, FS = 1;
  private int fallSpeed = 400; // ms
  private float currentLag = 0;

  private boolean gameOver = false;

  public TetrisMainCanvas() {
    d = new Dimension(600, 778);
    xPos = 10;
    yPos = 10;
    heightRatio = (d.height / 800.0f) + 0.0275f;
    widthRatio = d.width / 600.0f;
    prevULength = uLength = d.height / 30; // Length of each unit square
    canvasXMax = Math.round((xPos + (canvasSquareWidth * uLength)) * widthRatio); // bottom-left of
                                                                                  // the main canvas
    canvasYMax = Math.round((yPos + (canvasSquareHeight * uLength)) * heightRatio); // bottom-right
                                                                                    // of the main
    // canvas
    width = canvasXMax - xPos;
    height = canvasYMax - yPos;

    shapes = new ArrayList<>();
    shapes.add(new IShape(0, 0)); // DEBUG
    shapes.add(new IShape(4, 0)); // DEBUG
    shapes.add(new IShape(0, 1)); // DEBUG
    shapes.add(new IShape(4, 1)); // DEBUG
    shapes.add(new IShape(0, 2)); // DEBUG

    // activeShape = pickNextShape();
    // activeShape.setxPos(centerX);
    activeShape = new OShape(8, canvasSquareHeight - 5); // DEBUG
    nextShape = pickNextShape();

    setSize(uLength * canvasSquareWidth, uLength * canvasSquareHeight);
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        recalculateSize(recalculateDimensions(getSize()));
      }
    });

    addMouseWheelListener(new MouseAdapter() {
      /** Mouse wheel controls the rotation of the active shape. */
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
        if (!pauseButton.isVisible()) {
          int notches = e.getWheelRotation();
          System.out.println("Mouse wheel moved");
          if (notches < 0) {
            System.out.println("Mouse wheel moved UP " + -notches + " notch(es)");
            activeShape.rotateClockwise(shapes);
          } else {
            System.out.println("Mouse wheel moved DOWN " + notches + " notch(es)");
            activeShape.rotateCounterClockwise(shapes);
          }
        }
      }
    });

    addMouseListener(new MouseAdapter() {
      /** Mouse click controls the lateral movement of the tetroid. */
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Point p = e.getPoint();
        if (quitButton.isWithinButton(p.x, p.y)) {
          System.exit(0);
        }

        if (!pauseButton.isVisible()) {
          int buttonCode = e.getButton();
          System.out.println("buttonCode: " + buttonCode);
          switch (buttonCode) {
            case MouseEvent.BUTTON1:
              activeShape.moveLeft(shapes);
              break;
            case MouseEvent.BUTTON3:
              activeShape.moveRight(shapes);
              break;
          }
        }
      }
    });
  }

  private Dimension recalculateDimensions(Dimension newSize) {
    int new_width = (newSize.height * d.width) / d.height; // scale width to maintain aspect ratio
    return new Dimension(new_width, newSize.height);
  }

  private void recalculateSize(Dimension boundary) {
    heightRatio = (boundary.height / 800.0f) + 0.0275f;
    widthRatio = boundary.width / 600.0f;
    uLength = (int) (30 * widthRatio); // Length of each unit square
    if (uLength != prevULength) {
      canvasXMax = xPos + (canvasSquareWidth * uLength); // bottom-left of the main canvas
      canvasYMax = yPos + (canvasSquareHeight * uLength); // bottom-right of the main canvas
      width = canvasXMax - xPos;
      height = canvasYMax - yPos;
      prevULength = uLength;
    }
  }

  /** Updates the entire canvas */
  public void updateStep(float lag) {
    if (!gameOver) {
      currentLag += lag;
      Point currentMousePos = MouseInfo.getPointerInfo().getLocation();

      double relativeMouseX = currentMousePos.x - this.getLocationOnScreen().x;
      double relativeMouseY = currentMousePos.y - this.getLocationOnScreen().y;

      boolean isPauseButtonVisible = isWithinCanvas(relativeMouseX, relativeMouseY);

      pauseButton.setVisible(isPauseButtonVisible);

      if (!pauseButton.isVisible()) { // Play state
        if (currentLag > fallSpeed) {
          activeShape.moveDown();
          currentLag = 0;
        }

        if (isCollided(activeShape)) {
          System.out.println("YPos " + activeShape.getYPosition());
          gameOver = activeShape.getYPosition() >= canvasSquareHeight - 1;
          shapes.add(activeShape);

          checkAllLines();

          activeShape = nextShape;
          activeShape.setxPos(centerX);
          nextShape = pickNextShape();
        }
      }
    } else {
      System.out.println("Game over!");
      gameOverLabel.setVisible(true);
    }
  }

  private void updateScores() {
    lines++;
    score += level * M;

    if (lines >= N) {
      level++;
      FS *= (1 + level * S);
      fallSpeed /= (FS * .4); // Scale that shit way down
      System.out.println("FALLSPEED: " + fallSpeed);
    }
  }

  private void dropLine(int row) {
    for (Shape shape : shapes) {
      shape.removeSquaresInRow(row);
    }
    shapes.removeIf(s -> s.getSquares().isEmpty());
  }

  /**
   * Check entire grid for lines and reconciles them
   */
  private void checkAllLines() {
    for (int r = canvasSquareHeight - 1; r >= 0; r--) {
      while (checkLine(r)) {
        System.out.println("Row " + r + " is full!");
        updateScores();
        dropLine(r);
      }
    }
  }

  /**
   * Checks each line in the play area if all squares have been filled This method runs in O(M)
   * Where M = Shapes. This is fine if M dominates the number of squares per shape (no more than 4)
   */
  private boolean checkLine(int row) {
    int bit_vector = 0;

    // a line is complete when first W bits are set
    // assume W is no greater than 32
    int complete_mask = (1 << canvasSquareWidth) - 1;

    for (Shape shape : shapes) {
      byte[] positions = shape.getPositionsInLine(row);
      for (int x = 0; x < positions.length; x++) {
        if (positions[x] > -1) {
          bit_vector |= 1 << positions[x];
        }
      }
    }

    // This case is only true if entire line is filled
    return (bit_vector == complete_mask);
  }

  private boolean isWithinCanvas(double x, double y) {
    return (x >= xPos && x <= canvasXMax && y >= yPos && y <= canvasYMax);
  }

  private boolean isCollided(Shape activeShape) {
    boolean collided = false;
    for (Shape shape : shapes) {
      collided |= (activeShape.isCollidedWithShape(shape));
    }
    return collided || activeShape.isCollidedWithFloor();
  }

  /** Spawns new shape at the preview screen */
  private Shape pickNextShape() {
    int nextShapeId = ThreadLocalRandom.current().nextInt(0, 7);
    switch (nextShapeId) {
      case 0: {
        return new OShape(canvasSquareWidth + 3, canvasSquareHeight - 1);
      }
      case 1: {
        return new TShape(canvasSquareWidth + 3, canvasSquareHeight - 1);
      }
      case 2: {
        return new IShape(canvasSquareWidth + 2, canvasSquareHeight - 1);
      }
      case 3: {
        return new LShape(canvasSquareWidth + 3, canvasSquareHeight - 1);
      }
      case 4: {
        return new JShape(canvasSquareWidth + 3, canvasSquareHeight - 1);
      }
      case 5: {
        return new SShape(canvasSquareWidth + 4, canvasSquareHeight - 1);
      }
      case 6: {
        return new ZShape(canvasSquareWidth + 3, canvasSquareHeight - 1);
      }
      default:
        throw new IndexOutOfBoundsException("Shape not found");
    }
  }

  /** Draws the entire canvas */
  @Override
  public void paintBuffer(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    // Draw score labels
    g.setColor(Color.BLACK);
    g.setFont(defaultFont);
    g2.drawString("Level: " + level, canvasXMax + 50, yPos + (height / 2) - 40);
    g2.drawString("Lines: " + lines, canvasXMax + 50, yPos + (height / 2));
    g2.drawString("Score: " + score, canvasXMax + 50, yPos + (height / 2) + 40);

    GraphicsUtils.drawBorder(xPos, yPos, width, height, 5, g);

    // Draw the debug grid lines
    // for (int x = 0; x < canvasSquareWidth; x++) {
    // for (int y = 0; y < canvasSquareHeight; y++) {
    // drawUnit(new UnitSquare(x, y, Color.WHITE), g);
    // }
    // }

    drawShape(activeShape, g);

    for (Shape shape : shapes) {
      drawShape(shape, g);
    }

    // Draw preview screen
    GraphicsUtils.drawBorder(canvasXMax + 50, yPos, uLength * 5, uLength * 5, 5, g);
    drawShape(nextShape, g);

    if (pauseButton.isVisible()) { // Maybe we can refactor this to be built inside draw?
      pauseButton.draw(xPos + (width / 2), yPos + (height / 2), g);
    }
    if (gameOver) {
      gameOverLabel.draw(xPos + (width / 2), yPos + (height / 2), g);
    }

    quitButton.draw(canvasXMax + 100, yPos + (height - 20), g);
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
   * @param g      Graphics instance
   */
  private void drawUnit(UnitSquare square, Graphics g) {
    int x = xPos + (square.getCanvasX() * uLength);
    int y = yPos + (square.getCanvasY() * uLength);

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
