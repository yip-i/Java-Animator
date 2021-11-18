package model;

/**
 * Class represents a two dimensional position.
 */
public class Position2D {
  private int x;
  private int y;

  /**
   * Constructor for the two dimensional position.
   *
   * @param x X coordinate of the point.
   * @param y Y coordinate of the point.
   */
  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get x position.
   *
   * @return X position.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Get Y position.
   *
   * @return Y position.
   */
  public int getY() {
    return this.y;
  }

  public String toString() {
    return String.format("(%.1f,%.1f)", (double) this.x, (double) this.y);
  }

}
