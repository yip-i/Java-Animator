package model;


/**
 * Class representing a shape and it's state at a particular time.
 * Used by the visual view to tell what to animate at a particular time.
 */
public class ShapeState {
  private ShapeType shapeType;
  TimeInterval duration;
  int startX;
  int endX;
  int startY;
  int endY;
  int startWidth;
  int endWidth;
  int startHeight;
  int endHeight;
  int startRed;
  int endRed;
  int startBlue;
  int endBlue;
  int startGreen;
  int endGreen;

  /**
   * Constructor for the shape state class.
   *
   * @param t1 The start time of this transformation.
   * @param x1 The initial x-position of the shape.
   * @param y1 The initial y-position of the shape.
   * @param w1 The initial width of the shape.
   * @param h1 The initial height of the shape.
   * @param r1 The initial red color-value of the shape.
   * @param g1 The initial green color-value of the shape.
   * @param b1 The initial blue color-value of the shape.
   * @param t2 The end time of this transformation.
   * @param x2 The final x-position of the shape.
   * @param y2 The final y-position of the shape.
   * @param w2 The final width of the shape.
   * @param h2 The final height of the shape.
   * @param r2 The final red color-value of the shape.
   * @param g2 The final green color-value of the shape.
   * @param b2 The final blue color-value of the shape.
   */
  public ShapeState(ShapeType shapeType, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.shapeType = shapeType;
    this.duration = new TimeInterval(t1, t2);
    this.startX = x1;
    this.endX = x2;
    this.startY = y1;
    this.endY = y2;
    this.startWidth = w1;
    this.endWidth = w2;
    this.startHeight = h1;
    this.endHeight = h2;
    this.startRed = r1;
    this.endRed = r2;
    this.startBlue = b1;
    this.endBlue = b2;
    this.startGreen = g1;
    this.endGreen = g2;

  }

  /**
   * Get the duration this shape state is active.
   *
   * @return Duration of this shape state.
   */
  public TimeInterval getDuration() {
    return this.duration;
  }

  /**
   * Checks if this shape state needs to be drawn at the current tick.
   *
   * @param tick Tick we are checking.
   * @return True if this shape state needs to be drawn and false otherwise.
   */
  public boolean isShapeDrawn(int tick) {
    return (tick >= duration.getStart()) && (tick <= duration.getEnd());
  }

  /**
   * Gets the tweened shape of this shape state.
   *
   * @param tick Tick we want this shape to be at.
   * @return Tweened state of this shape.
   */
  public AbstractShape tweenShape(int tick) {
    if (this.shapeType == ShapeType.RECTANGLE) {

      return new Rectangle("", duration,
          new Position2D(tweenState(tick, startX, endX), tweenState(tick, startY, endY)),
          new ColourRGB(tweenState(tick, startRed, endRed), tweenState(tick, startGreen, endGreen),
              tweenState(tick, startBlue, endBlue)), tweenState(tick, startWidth, endWidth),
          tweenState(tick, startHeight, endHeight));
    } else {
      return new Oval("", duration,
          new Position2D(tweenState(tick, startX, endX), tweenState(tick, startY, endY)),
          new ColourRGB(tweenState(tick, startRed, endRed), tweenState(tick, startGreen, endGreen),
              tweenState(tick, startBlue, endBlue)), tweenState(tick, startWidth, endWidth),
          tweenState(tick, startHeight, endHeight));

    }

  }

  /**
   * Method that tweens a value.
   *
   * @param tick       Tick we are at.
   * @param startValue Start value that we are tweening.
   * @param endValue   End value that we are tweening.
   * @return Tweened value.
   */
  private int tweenState(int tick, int startValue, int endValue) {
    int a = startValue;
    int b = endValue;
    //Cannot divide by 0. If t end = t start, the tweening will divide by 0.
    if (duration.difference() == 0) {
      return endValue;
    }

    double aRatio = ((double) duration.getEnd() - (double) tick) / ((double) duration
        .getEnd() - (double) duration.getStart());
    double bRatio = ((double) tick - (double) duration.getStart()) / ((double) duration
        .getEnd() - (double) duration.getStart());

    int output = (int) Math.round(a * aRatio + b * bRatio);
    return output;
  }

  /**
   * Gets the type of shape that this shape state is representing.
   *
   * @return Type of shape this is supposed to be.
   */
  public ShapeType getShapeType() {
    return this.shapeType;
  }
}
