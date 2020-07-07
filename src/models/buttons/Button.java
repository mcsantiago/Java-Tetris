package models.buttons;

import interfaces.IClickable;

import java.awt.*;

public abstract class Button implements IClickable {
  int x;
  int y;
  String label;
  boolean isVisible;
  Color color;
  Font buttonFont = new Font("Arial", Font.BOLD, 23);

  public boolean isVisible() {
    return isVisible;
  }

  public void setVisible(boolean visible) {
    isVisible = visible;
  }

  public boolean isWithinButton(double x, double y) {
    return (x >= this.x - (label.length() * 15) && x <= this.x + (label.length() * 10) && y >= this.y - 24
        && y <= this.y + 24);
  }

  public void draw(int x, int y, Graphics g) {
    this.x = x;
    this.y = y;

    g.setColor(Color.WHITE);
    g.fillRect(this.x - (label.length() * 15), this.y - 24, label.length() * 25, 48);

    Graphics2D g2 = (Graphics2D) g;
    g.setColor(color);
    g.setFont(buttonFont);
    g2.drawString(label, x - (label.length() * 10), y);
    ((Graphics2D) g).setStroke(new BasicStroke(1));
    g.drawLine(x - (label.length() * 15), y - 24, x + (label.length() * 10), y - 24);
    g.drawLine(x + (label.length() * 10), y - 24, x + (label.length() * 10), y + 24);
    g.drawLine(x + (label.length() * 10), y + 24, x - (label.length() * 15), y + 24);
    g.drawLine(x - (label.length() * 15), y + 24, x - (label.length() * 15), y - 24);
  }
}
