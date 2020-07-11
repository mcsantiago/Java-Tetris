import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class SlidersPanel extends JPanel {
  GameState state;

  public SlidersPanel(GameState state) {
    this.state = state;

    JSlider m_slider = new JSlider(JSlider.HORIZONTAL, 1, 15, 7);
    m_slider.setMinorTickSpacing(1);
    m_slider.setMajorTickSpacing(5);
    m_slider.setPaintTicks(true);
    m_slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        state.setM(m_slider.getValue());
      }
    });

    add(m_slider);
  }
}
