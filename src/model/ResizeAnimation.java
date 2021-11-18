package model;

/**
 * Class representing the resize animation.
 */
public class ResizeAnimation extends AbstractAnimationEvent {

  private double startWidth;
  private double startHeight;
  private double endWidth;
  private double endHeight;

  /**
   * Constructor for the the resize animation.
   *
   * @param duration    Duration the animation is happening in.
   * @param shape       Shape the animation is working on.
   * @param startWidth  Start width for the shape.
   * @param startHeight Start height for the shape.
   * @param endWidth    End width of the shape.
   * @param endHeight   End height of the shape.
   */
  public ResizeAnimation(AbstractShape shape, TimeInterval duration, double startWidth,
      double startHeight, double endWidth, double endHeight) {
    super(shape, duration, AnimationType.RESIZE);
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.endWidth = endWidth;
    this.endHeight = endHeight;

  }

  @Override public String toString() {
    String result = "";

    switch (shape.getShapeType()) {
      case RECTANGLE:
        result = "Shape " + shape.getName() + " scales from " + String
            .format("Width: %.1f, Height: %.1f to Width: %.1f, " + "Height: %.1f from t=%d to t=%d",
                startWidth, startHeight, endWidth, endHeight, duration.getStart(),
                duration.getEnd()) + "\n";
        return result;
      case OVAL:
        result = "Shape " + shape.getName() + " scales from " + String.format(
            "X radius: %.1f, Y radius: %.1f to X radius: %.1f, "
                + "Y radius: %.1f from t=%d to t=%d",
            startWidth, startHeight, endWidth, endHeight, duration.getStart(),
            duration.getEnd()) + "\n";
        return result;

      default:
        result = "";
    }
    return result;

  }

  @Override public String getSVG(int speed) {
    String output = "";

    switch (shape.getShapeType()) {
      case OVAL:
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"rx\" from=\"" + Math
            .round(this.startWidth) + "\" to=\"" + this.endWidth + "\" fill=\"freeze\"/> \n";
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"ry\" from=\"" + Math
            .round(this.startHeight) + "\" to=\"" + this.endHeight + "\" fill=\"freeze\"/> \n";
        break;
      case RECTANGLE:
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"width\" from=\"" + Math
            .round(this.startWidth) + "\" to=\"" + this.endWidth + "\" fill=\"freeze\"/> \n";
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"height\" from=\"" + Math
            .round(this.startHeight) + "\" to=\"" + this.endHeight + "\" fill=\"freeze\"/> \n";
        break;
      default:
        break;
    }
    return output;
  }
}
