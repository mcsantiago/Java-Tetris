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
    ConversionPanel mPanel = new ConversionPanel();
    JLabel mLabel = new JLabel("M:");
    mLabel.setFont(labelFont);
    mPanel.setBackground(Color.WHITE);
    mPanel.add(mLabel);
    mPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    mPanel.add(m_slider);

    DiscreteSlider n_slider = new DiscreteSlider(JSlider.HORIZONTAL, 20, 40, state.getN(), 1, 10);
    n_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setN(n_slider.getValue());
      }
    });
    ConversionPanel nPanel = new ConversionPanel();
    JLabel nLabel = new JLabel("N:");
    nLabel.setFont(labelFont);
    nPanel.setBackground(Color.WHITE);
    nPanel.add(nLabel);
    nPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    nPanel.add(n_slider);

    DiscreteSlider s_slider = new DiscreteSlider(JSlider.HORIZONTAL, 0, 100, state.getN(), 5, 10);
    s_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setS(s_slider.getValue());
      }
    });
    ConversionPanel sPanel = new ConversionPanel();
    JLabel sLabel = new JLabel("S:");
    sLabel.setFont(labelFont);
    sPanel.setBackground(Color.WHITE);
    sPanel.add(sLabel);
    sPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    sPanel.add(s_slider);

    DiscreteSlider widthSlider =
        new DiscreteSlider(JSlider.HORIZONTAL, 10, 20, state.getCanvasSquareWidth(), 5, 1);
    widthSlider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setCanvasSquareWidth(widthSlider.getValue());
      }
    });
    ConversionPanel widthPanel = new ConversionPanel();
    JLabel widthLabel = new JLabel("Canvas Width:");
    widthLabel.setFont(labelFont);
    widthPanel.setBackground(Color.WHITE);
    widthPanel.add(widthLabel);
    widthPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    widthPanel.add(widthSlider);

    DiscreteSlider heightSlider =
        new DiscreteSlider(JSlider.HORIZONTAL, 20, 30, state.getCanvasSquareHeight(), 5, 1);
    heightSlider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setCanvasSquareHeight(heightSlider.getValue());
      }
    });
    ConversionPanel heightPanel = new ConversionPanel();
    JLabel heightLabel = new JLabel("Canvas Height:");
    heightLabel.setFont(labelFont);
    heightPanel.setBackground(Color.WHITE);
    heightPanel.add(heightLabel);
    heightPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    heightPanel.add(heightSlider);

    DiscreteSlider unitLengthSlider =
        new DiscreteSlider(JSlider.HORIZONTAL, 20, 60, state.getuLength(), 10, 5);
    unitLengthSlider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setuLength(unitLengthSlider.getValue());
      }
    });
    ConversionPanel unitLengthPanel = new ConversionPanel();
    JLabel unitLabel = new JLabel("Square Length:");
    unitLabel.setFont(labelFont);
    unitLengthPanel.setBackground(Color.WHITE);
    unitLengthPanel.add(unitLabel);
    unitLengthPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    unitLengthPanel.add(unitLengthSlider);

    JLabel settingsLabel = new JLabel("Settings");
    settingsLabel.setFont(headerFont);

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(settingsLabel);
    add(mPanel);
    add(nPanel);
    add(sPanel);
    add(widthPanel);
    add(heightPanel);
    add(unitLengthPanel);
  }
}
