package view;

import model.ShapeState;

import java.util.ArrayList;

/**
 * Interface for the view for the animated movie.
 */
public interface AnimatorViewInterface {
  /**
   * This method will display the current data that the view has obtained from the controller
   * and output it to the respective form of display (terminal, file or visual view).
   *
   * @param animationSequence This input represents the sequence of shapes and animations
   *                          in a String format.
   */
  public void display(String animationSequence);

  /**
   * This method will display the current data that the view has obtained from the controller
   * and output it to the respective form of display (terminal, file or visual view).
   */
  public void display();

  /**
   * This method will return the respective type of view for the class that invokes it.
   *
   * @return The respective type of view for the class that invokes it.
   */
  public ViewType getViewType();

  /**
   * Updates the shapes states held by the visual view.
   *
   * @param shapeStates Shape state that we are passing to the view.
   */
  public void updateData(ArrayList<ShapeState> shapeStates);

  /**
   * Set the delay of the movie.
   *
   * @param speed Speed we are setting the movie to.
   */
  public void setDelay(int speed);

  /**
   * Set the size of the canvas for the view.
   */
  public void setCanvasSize(int width, int height);
}
