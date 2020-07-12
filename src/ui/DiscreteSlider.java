package ui;

import javax.swing.JSlider;
import java.awt.*;

@SuppressWarnings("serial")
public class DiscreteSlider extends JSlider {
  public DiscreteSlider(int orientation, int min, int max, int currentValue, int majorTicks,
      int minorTicks) {
    super(orientation, min, max, currentValue);

    setMinorTickSpacing(majorTicks);
    setMajorTickSpacing(minorTicks);
    setPaintTicks(true);
    setPaintLabels(true);
    setBackground(Color.WHITE);
  }
}
