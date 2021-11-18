package model;

/**
 * Class representing the colour change animation.
 * This class extends abstract animation event.
 */
public class ColourChangeAnimation extends AbstractAnimationEvent {

  private ColourRGB startColour;
  private ColourRGB endColour;

  /**
   * Constructor for the Abstract Animation Event.
   *
   * @param startColour Colour of the shape at the start of the animation.
   * @param endColour   Colour of the shape at the end of the animation.
   * @param shape       Shape the animation is working on.
   * @param duration    Duration the animation will happen between.
   */
  public ColourChangeAnimation(AbstractShape shape, TimeInterval duration, ColourRGB startColour,
      ColourRGB endColour) {
    super(shape, duration, AnimationType.CHANGE_COLOUR);
    this.startColour = startColour;
    this.endColour = endColour;

  }

  @Override
  public String toString() {
    return "Shape " + shape.getName() + " changes colour from " + startColour
        .toString() + " to " + endColour.toString() + " from t=" + duration
        .getStart() + " to t=" + duration.getEnd() + "\n";
  }

  @Override
  public String getSVG(int speed) {
    String output = "";

    output += "<animate attributeName=\"fill\" from=\"rgb" + this.startColour
        .toString() + "\" to=\"rgb" + this.endColour.toString() + "\" begin=\"" + (this.duration
        .getStart() * speed) + "ms\" dur=\"" + (this.duration
        .difference() * speed) + "ms\" fill=\"freeze\"/> \n";

    return output;

  }
}
