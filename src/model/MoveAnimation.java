package model;

/**
 * Class representing the move animation.
 */
public class MoveAnimation extends AbstractAnimationEvent {

  private Position2D start;
  private Position2D end;

  /**
   * Constructor for the move animation.
   * @param start    Start position of the shape at the beginning of the animation.
   * @param end      End position of the shape at the end of the animation.
   * @param shape    Shape the animation is working on.
   * @param duration Duration the animation will happen between.
   */
  public MoveAnimation(AbstractShape shape, TimeInterval duration, Position2D start,
      Position2D end) {
    super(shape, duration, AnimationType.MOVE);
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "Shape " + shape.getName() + " moves from " + start.toString() + " to " + end
        .toString() + " from t=" + duration.getStart() + " to t=" + duration.getEnd() + "\n";
  }

  //TODO multiply difference by speed and add ms to the end of start and difference.

  @Override
  public String getSVG(int speed) {
    String output = "";

    switch (shape.getShapeType()) {
      case OVAL:
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"cx\" from=\"" + Math
            .round(this.start.getX()) + "\" to=\"" + this.end.getX() + "\" fill=\"freeze\"/> \n";
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"cy\" from=\"" + Math
            .round(this.start.getY()) + "\" to=\"" + this.end.getY() + "\" fill=\"freeze\"/> \n";
        break;
      case RECTANGLE:
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"x\" from=\"" + Math
            .round(this.start.getX()) + "\" to=\"" + this.end.getX() + "\" fill=\"freeze\"/> \n";
        output += "<animate attributeType=\"xml\" begin=\"" + (this.duration
            .getStart() * speed) + "ms\" dur=\"" + (this.duration
            .difference() * speed) + "ms\" attributeName=\"y\" from=\"" + Math
            .round(this.start.getY()) + "\" to=\"" + this.end.getY() + "\" fill=\"freeze\"/> \n";
        break;
      default:
        break;
    }
    return output;
  }
}
