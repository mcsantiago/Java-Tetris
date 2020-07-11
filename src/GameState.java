
/**
 * Container object for all game state to be passed between UI Components..
 */
public class GameState {
  private int m, n, s, fs;

  public GameState() {
    m = 1; // Range 1-15
    n = 20; // Range 20-40
    s = 1; // Range 0.1-1.0 (1-10)
  }

  public int getM() {
    return m;
  }

  public int getFs() {
    return fs;
  }

  public void setFs(int fs) {
    this.fs = fs;
  }

  public int getS() {
    return s;
  }

  public void setS(int s) {
    this.s = s;
  }

  public int getN() {
    return n;
  }

  public void setN(int n) {
    this.n = n;
  }

  public void setM(int m) {
    this.m = m;
  }

}
