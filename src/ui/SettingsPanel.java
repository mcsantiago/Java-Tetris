package ui;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import models.GameState;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {
  GameState state;

  public SettingsPanel(GameState state) {
    this.state = state;

    DiscreteSlider m_slider = new DiscreteSlider(JSlider.HORIZONTAL, 1, 15, state.getM(), 1, 5);
    m_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setM(m_slider.getValue());
      }
    });

    DiscreteSlider n_slider = new DiscreteSlider(JSlider.HORIZONTAL, 20, 40, state.getN(), 1, 10);
    n_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setN(n_slider.getValue());
      }
    });

    DiscreteSlider s_slider = new DiscreteSlider(JSlider.HORIZONTAL, 1, 100, state.getN(), 1, 10);
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
