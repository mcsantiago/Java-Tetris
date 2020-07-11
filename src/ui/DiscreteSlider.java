package ui;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public class DiscreteSlider extends JSlider {
  public DiscreteSlider(int orientation, int min, int max, int currentValue, int majorTicks,
      int minorTicks) {
    super(orientation, min, max, currentValue);

    setSnapToTicks(true);
    setMinorTickSpacing(majorTicks);
    setMajorTickSpacing(minorTicks);
    setPaintTicks(true);
  }
}
