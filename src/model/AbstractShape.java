package model;

import java.util.ArrayList;

/**
 * Abstract class representing a shape.
 */
public abstract class AbstractShape implements Comparable<AbstractShape> {

  protected String name;
  protected final ShapeType shape;
  protected TimeInterval duration;
  protected Position2D startPosition;
  protected ColourRGB startColour;
  protected ArrayList<AbstractAnimationEvent> animationEventArrayList;

  /**
   * Abstract shape class constructor.
   *
   * @param name          Name of the shape.
   * @param shape         Type of the shape.
   * @param duration      Time interval of when the shape appears and disappears.
   * @param startPosition Position that the shape starts at.
   * @param colour        Colour the shape starts as.
   */
  public AbstractShape(String name, ShapeType shape, TimeInterval duration,
      Position2D startPosition, ColourRGB colour) {
    //Check that time is not negative and that start is smaller than end.
    if (duration.getStart() < 0) {
      throw new IllegalArgumentException("Time cannot be negative");
    }
    this.name = name;
    this.shape = shape;
    this.duration = duration;
    this.startPosition = startPosition;
    this.startColour = colour;
  }

  /**
   * This method will return the starting position of this object as a Position2D object.
   *
   * @return The starting position of this object as a Position2D object.
   */
  public Position2D getStartPosition() {
    return this.startPosition;
  }

  /**
   * This method will return the starting colour of this object as a ColourRGB object.
   *
   * @return The starting colour of this object as a ColourRGB object.
   */
  public ColourRGB startColour() {
    return startColour;
  }

  /**
   * This method will return the object's enumerator ShapeType.
   *
   * @return The object's enumerator ShapeType.
   */
  public ShapeType getShapeType() {
    return shape;
  }

  /**
   * This method will return the object's name as a String.
   *
   * @return The object's name as a String.
   */
  public String getName() {
    return name;
  }

  /**
   * This method will return the object's duration, which represents the time that it will appear,
   * then disappear.
   *
   * @return The object's duration, which represents the time that it will * appear, then disappear.
   */
  public TimeInterval getDuration() {
    return this.duration;
  }

  /**
   * Compares the start time of two abstract shapes.
   * @param abstractShapeObject Abstract shape that we are comparing this to.
   * @return Returns 1 if this starts
   */
  @Override
  public int compareTo(AbstractShape abstractShapeObject) {
    int difference = this.duration.getStart() - abstractShapeObject.duration.getStart();
    if (difference > 0) {
      return 1;
    } else if (difference < 0) {
      return -1;
    } else {
      return 0;
    }
  }

  /**
   * Returns a string representation of the shape.
   *
   * @return String representation of the shape.
   */
  public abstract String toString();

  /**
   * SVG representation of the beginning of the shape.
   *
   * @return SVG representation of the beginning of the shape.
   */
  public abstract String getSVG(int speed);
}
