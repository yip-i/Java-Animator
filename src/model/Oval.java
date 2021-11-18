package model;

/**
 * Class representing an oval.
 */
public class Oval extends AbstractShape {

  private int xRadius;
  private int yRadius;

  /**
   * Abstract shape class constructor.
   *
   * @param name          Name of the shape.
   * @param duration      Time interval of when the shape appears and disappears.
   * @param startPosition Lower left corner that the shape starts at.
   * @param colour        Colour the shape starts as.
   * @param xRadius       Horizontal radius of the oval.
   * @param yRadius       Vertical radius of the oval.
   * @throws IllegalArgumentException If Width or height less than 0.
   */
  public Oval(String name, TimeInterval duration, Position2D startPosition, ColourRGB colour,
      int xRadius, int yRadius) throws IllegalArgumentException {
    super(name, ShapeType.OVAL, duration, startPosition, colour);

    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Oval Width or height cannot be less than 0");
    }

    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Get the vertical radius of the oval.
   *
   * @return Vertical Radius of the oval.
   */
  public int getYRadius() {
    return this.yRadius;
  }

  /**
   * Get the horizontal radius of the oval.
   *
   * @return Horizontal radius of the oval.
   */
  public int getXRadius() {
    return this.xRadius;
  }

  @Override
  public String toString() {
    return "Name:" + this.name + "\n" + "Type: Oval\n" + "Centre: " + this.startPosition
        .toString() + String.format(" X radius: %.1f, Y Radius: %.1f", (double) this.xRadius,
        (double) this.yRadius) + "\nColour:" + this.startColour().toString() + "\n" + String
        .format("Appears at t=%d\nDisappears at t=%d\n", this.duration.getStart(),
            this.duration.getEnd());

  }

  @Override
  public String getSVG(int speed) {
    String output = "";
    output += "<ellipse id=" + "\"" + this.name + "\" " + "cx=\"" + Math
        .round(this.startPosition.getX()) + "\" " + "cy=\"" + Math
        .round(this.startPosition.getY()) + "\" " + "rx=\"" + Math
        .round(this.xRadius) + "\" " + "ry=\"" + Math
        .round(this.yRadius) + "\" fill=\"rgb" + this.startColour
        .toString() + "\" visibility=\"hidden\" > \n";
    output += "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin=\""
        + (this.duration.getStart() * speed) + "ms\" dur=\"" + (this.duration.difference() * speed)
        + "ms\" />";

    return output;

  }
}


