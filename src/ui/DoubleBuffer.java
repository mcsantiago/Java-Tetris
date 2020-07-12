package ui;

import java.awt.*;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DoubleBuffer extends JPanel {
  private int bufferWidth;
  private int bufferHeight;
  private Image bufferImage;
  private Graphics bufferGraphics;

  public DoubleBuffer() {
    super();
  }

  @Override
  public void paint(Graphics g) {
    if (bufferWidth != getSize().width || bufferHeight != getSize().height || bufferImage == null
        || bufferGraphics == null) {
      System.out.println("Resetting buffer");
      resetBuffer();
    }

    if (bufferGraphics != null) {
      bufferGraphics.clearRect(0, 0, bufferWidth, bufferHeight);
      paintBuffer(bufferGraphics);
      g.drawImage(bufferImage, 0, 0, this);
    }
  }

  public void paintBuffer(Graphics g) {
  }

  private void resetBuffer() {
    bufferWidth = getSize().width;
    bufferHeight = getSize().height;

    if (bufferGraphics != null) {
      bufferGraphics.dispose();
      bufferGraphics = null;
    }
    if (bufferImage != null) {
      bufferImage.flush();
      bufferImage = null;
    }

    System.gc();

    bufferImage = createImage(bufferWidth, bufferHeight);
    bufferGraphics = bufferImage.getGraphics();
  }
}
