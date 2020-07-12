package models;

/**
 * Container object for all game state to be passed between UI Components..
 */
public class GameState {
  private int m, n, s, fs, currentState;
  private int canvasSquareWidth, canvasSquareHeight, uLength;
  public final static int PLAY = 0;
  public final static int PAUSED = 1;
  public final static int QUIT = 2;

  public GameState() {
    m = 1; // Range 1-15
    n = 20; // Range 20-40
    s = 1; // Range 0.1-1.0 (1-10)

    canvasSquareHeight = 20;
    canvasSquareWidth = 10;
    uLength = 40;
    currentState = GameState.PLAY;
  }

  public int getuLength() {
    return uLength;
  }

  public void setuLength(int uLength) {
    this.uLength = uLength;
  }

  public int getCanvasSquareHeight() {
    return canvasSquareHeight;
  }

  public void setCanvasSquareHeight(int canvasSquareHeight) {
    this.canvasSquareHeight = canvasSquareHeight;
  }

  public int getCanvasSquareWidth() {
    return canvasSquareWidth;
  }

  public void setCanvasSquareWidth(int canvasSquareWidth) {
    this.canvasSquareWidth = canvasSquareWidth;
  }

  public int getCurrentState() {
    return currentState;
  }

  public void setCurrentState(int currentState) {
    this.currentState = currentState;
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
