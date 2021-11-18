package model;

/**
 * Class representing the Rectangle.
 */
public class Rectangle extends AbstractShape {

  private int width;
  private int height;

  /**
   * Abstract shape class constructor.
   *
   * @param name          Name of the shape.
   * @param duration      Time interval of when the shape appears and disappears.
   * @param startPosition Lower left corner that the shape starts at.
   * @param colour        Colour the shape starts as.
   * @param width         Width of the rectangle.
   * @param height        Height of the rectangle.
   * @throws IllegalArgumentException If Width or height less than 0.
   */
  public Rectangle(String name, TimeInterval duration, Position2D startPosition, ColourRGB colour,
      int width, int height) throws IllegalArgumentException {
    super(name, ShapeType.RECTANGLE, duration, startPosition, colour);

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width or height cannot be less than 0");
    }

    this.width = width;
    this.height = height;
  }

  /**
   * Get the height of the rectangle.
   *
   * @return Height of the rectangle.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the width of the rectangle.
   *
   * @return Width of the rectangle.
   */
  public int getWidth() {
    return width;
  }

  @Override public String toString() {
    return "Name:" + this.name + "\n" + "Type: Rectangle\n" + "Lower left corner: "
        + this.startPosition.toString() + String.format(" Width: %.1f, Height: %.1f",
        (double) this.width, (double) this.height) + "\nColour:"
        + this.startColour().toString() + "\n"
        + String.format("Appears at t=%d\nDisappears at t=%d\n",
        this.duration.getStart(), this.duration.getEnd());

  }

  @Override public String getSVG(int speed) {
    String output = "";
    output += "<rect id=" + "\"" + this.name + "\" " + "x=\"" + Math
        .round(this.startPosition.getX()) + "\" " + "y=\"" + Math
        .round(this.startPosition.getY()) + "\" " + "width=\"" + Math
        .round(this.width) + "\" " + "height=\"" + Math
        .round(this.height) + "\" fill=\"rgb" + this.startColour
        .toString() + "\" visibility=\"hidden\" >\n";
    output += "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin=\""
        + (this.duration.getStart() * speed) + "ms\" dur=\""
        + (this.duration.difference() * speed) + "ms\" /> \n";
    return output;
  }
}
