package ui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import models.GameState;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {
  GameState state;
  Font headerFont = new Font("Arial", Font.PLAIN, 32);
  Font labelFont = new Font("Arial", Font.PLAIN, 24);

  public SettingsPanel(GameState state) {
    this.state = state;

    DiscreteSlider m_slider = new DiscreteSlider(JSlider.HORIZONTAL, 1, 15, state.getM(), 1, 5);
    m_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setM(m_slider.getValue());
      }
    });
    ConversionPanel m_panel = new ConversionPanel();
    JLabel m_label = new JLabel("M:");
    m_label.setFont(labelFont);
    m_panel.setBackground(Color.WHITE);
    m_panel.add(m_label);
    m_panel.add(Box.createRigidArea(new Dimension(20, 0)));
    m_panel.add(m_slider);

    DiscreteSlider n_slider = new DiscreteSlider(JSlider.HORIZONTAL, 20, 40, state.getN(), 1, 10);
    n_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setN(n_slider.getValue());
      }
    });
    ConversionPanel n_panel = new ConversionPanel();
    JLabel n_label = new JLabel("N");
    n_label.setFont(labelFont);
    n_panel.setBackground(Color.WHITE);
    n_panel.add(n_label);
    n_panel.add(Box.createRigidArea(new Dimension(20, 0)));
    n_panel.add(n_slider);

    DiscreteSlider s_slider = new DiscreteSlider(JSlider.HORIZONTAL, 0, 100, state.getN(), 5, 10);
    s_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setS(s_slider.getValue());
      }
    });
    ConversionPanel s_panel = new ConversionPanel();
    JLabel s_label = new JLabel("S");
    s_label.setFont(labelFont);
    s_panel.setBackground(Color.WHITE);
    s_panel.add(s_label);
    s_panel.add(Box.createRigidArea(new Dimension(20, 0)));
    s_panel.add(s_slider);

    JLabel settingsLabel = new JLabel("Settings");
    settingsLabel.setFont(headerFont);

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(settingsLabel);
    add(m_panel);
    add(n_panel);
    add(s_panel);
    // add(buttonPanel);
  }
}
