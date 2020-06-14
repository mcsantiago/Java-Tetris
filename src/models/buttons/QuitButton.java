package models.buttons;

import java.awt.*;

public class QuitButton extends Button {
  public QuitButton() {
    this.label = "QUIT";
    this.color = Color.GRAY;
  }

  @Override
  public void onClick() {
    System.exit(0);
  }
}
