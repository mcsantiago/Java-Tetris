package ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import models.GameState;


@SuppressWarnings("serial")
public class TetrisWindow extends JFrame {
  private static final double MS_PER_UPDATE = 32; // 30 FPS
  private GameState state = new GameState();
  private TetrisMainCanvas canvas = new TetrisMainCanvas(state);
  private SettingsPanel panel = new SettingsPanel(state);
  private boolean inSettings = false;

  private void init() {
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("System exiting");
        System.exit(0);
      }
    });

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int keyCode = e.getKeyCode();
        System.out.println("Key pressed! " + keyCode);
      }
    });


    setTitle("Assignment 3");
    setBackground(Color.white);
    setSize(1200, 800);
    setLayout(new GridLayout(1, 2));
    add(canvas);
    add(panel);
    setVisible(true);
  }

  public void run() {
    init();

    double lastTime = System.currentTimeMillis();
    double lag = 0.0;

    while (true) {
      double current = System.currentTimeMillis();
      double elapsed = current - lastTime;
      lastTime = current;
      lag += elapsed;
      while (lag >= MS_PER_UPDATE) {
        canvas.updateStep((float) lag);
        canvas.repaint();
        lag -= MS_PER_UPDATE;
      }
    }
  }
}
