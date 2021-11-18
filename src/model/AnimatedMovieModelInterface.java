package model;

import java.util.ArrayList;

/**
 * Interface representing the animated movie model.
 */
public interface AnimatedMovieModelInterface {

  /**
   * Add shape object to the animation.
   *
   * @param shape Shape to be added to the animation.
   */
  public void addShape(AbstractShape shape);

  /**
   * Add animation to the movie.
   *
   * @param event Event to be added to the movie.
   * @throws IllegalArgumentException If the shape is not in the movie,
   *                                  also throws exception when event conflicts with another event.
   */
  public void addAnimation(AbstractAnimationEvent event) throws IllegalArgumentException;

  /**
   * Method to add shape state to our model.
   * @param shapeState Shape state that is being added.
   */
  public void addShapeState(ShapeState shapeState);

  /**
   * Get the list of shape states.
   * @return List of shape states.
   */
  public ArrayList<ShapeState> getShapeStateList();

  /**
   * Get the list of abstract shapes.
   * @return The list of abstract shapes that we store in our model.
   */
  public ArrayList<AbstractShape> getShapeOrderList();

  /**
   * Get the list of abstract animation events.
   * @return List of abstract animation events.
   */
  public ArrayList<AbstractAnimationEvent> getAnimationOrderList();

  /**
   * This method will set the bounds of an animation using 4 parameters to define the playable
   * area.
   *
   * @param xBound The x or horizontal location of the starting area.
   * @param yBound The y or vertical location of the starting area.
   * @param width  The width or horizontal distance between the starting and end bound.
   * @param height The height or vertical distance between the starting and end bound.
   */
  public void setBounds(int xBound, int yBound, int width, int height);

  /**
   * This method will return a string that contains all the shapes and animation events that
   * occur in this movie/animation. It uses an integer value to set the speed of the movie
   * in ticks per second.
   *
   * @param speed An integer value representing the speed of the movie in ticks per second.
   * @return The SVG string that represents this movie/animation.
   */
  public String getSVG(int speed);

  /**
   * Gets the width of the canvas for the movie.
   *
   * @return Width of the canvas.
   */
  public int getCanvasWidth();

  /**
   * Gets the height of the canvas for the movie.
   *
   * @return Height of the canvas.
   */
  public int getCanvasHeight();

}
