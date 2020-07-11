package ui;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import models.GameState;

@SuppressWarnings("serial")
public class SlidersPanel extends JPanel {
  GameState state;

  public SlidersPanel(GameState state) {
    this.state = state;

    JSlider m_slider = new JSlider(JSlider.HORIZONTAL, 1, 15, state.getM());
    m_slider.setSnapToTicks(true);
    m_slider.setMinorTickSpacing(1);
    m_slider.setMajorTickSpacing(5);
    m_slider.setPaintTicks(true);
    m_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setM(m_slider.getValue());
      }
    });

    JSlider n_slider = new JSlider(JSlider.HORIZONTAL, 20, 40, state.getN());
    n_slider.setSnapToTicks(true);
    n_slider.setMinorTickSpacing(1);
    n_slider.setMajorTickSpacing(5);
    n_slider.setPaintTicks(true);
    n_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setN(n_slider.getValue());
      }
    });

    JSlider s_slider = new JSlider(JSlider.HORIZONTAL, 20, 40, state.getN());
    n_slider.setSnapToTicks(true);
    s_slider.setMinorTickSpacing(1);
    s_slider.setMajorTickSpacing(5);
    s_slider.setPaintTicks(true);
    s_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setS(s_slider.getValue());
      }
    });

    setLayout(new GridLayout(3, 1));
    setBackground(Color.PINK);
    add(m_slider);
    add(n_slider);
    add(s_slider);
  }
}
