import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

enum GameState {
    RUNNING, PAUSED, EXIT
}

public class TetrisWindow extends Frame {
    /**
     *
     */
    private static final long serialVersionUID = -7765414404380680330L;
    private static final double MS_PER_UPDATE = 200;
    GameState currentState = GameState.RUNNING;
    TetrisMainCanvas canvas = new TetrisMainCanvas();

    private void init() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                currentState = GameState.EXIT;
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

                if (keyCode == KeyEvent.VK_ESCAPE) {
                    togglePausedState();
                }
            }
        });
        setTitle("Assignment 2");
        setBackground(Color.white);
        setSize(600, 800);
        add(canvas);
        setVisible(true);
    }

    private void togglePausedState() {
        if (currentState == GameState.PAUSED) {
            currentState = GameState.RUNNING;
        } else if (currentState == GameState.RUNNING) {
            currentState = GameState.PAUSED;
        }
    }

    public void run() {
        init();

        double lastTime = System.currentTimeMillis();
        double lag = 0.0;

        while (currentState != GameState.EXIT) {
            double current = System.currentTimeMillis();
            double elapsed = current - lastTime;
            lastTime = current;
            lag += elapsed;
            switch (currentState) {
                case RUNNING -> {
                    while (lag >= MS_PER_UPDATE) {
                        canvas.updateStep();
                        canvas.repaint();
                        lag -= MS_PER_UPDATE;
                    }
                }
                case PAUSED -> {
                }
                case EXIT -> throw new UnsupportedOperationException("Unimplemented case: " + currentState);
                default -> throw new IllegalArgumentException("Unexpected value: " + currentState);
            }
        }
    }
}
