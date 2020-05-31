import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

enum GameState {RUNNING, PAUSED, EXIT};

public class TetrisWindow extends Frame {
    GameState currentState = GameState.RUNNING;

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
        setLayout(new GridLayout(1, 2));
        setSize(600, 800);
        add(new TetrisMainCanvas());
        setVisible(true);
    }

    private void togglePausedState() {
        System.out.println("Escape key pressed");
        if (currentState == GameState.PAUSED) {
            System.out.println("\trunning");
            currentState = GameState.RUNNING;
        } else if (currentState == GameState.RUNNING) {
            System.out.println("\tpausing");
            currentState = GameState.PAUSED;
        }
    }


    public void run() {
        init();
//        while (currentState == GameState.RUNNING ||
//                currentState == GameState.PAUSED) {
//            repaint();
//        }
    }
}
