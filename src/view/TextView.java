package view;

import model.ShapeState;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a View in the MVC design architecture, where it will display data in the
 * system.out or by writing to a file. It implements the AnimatorViewInterface.
 */
public class TextView implements AnimatorViewInterface {
  private final Appendable out;
  private final ViewType viewType;

  /**
   * This constructor constructs the TextView using an Appendable that it will write to as it's
   * output.
   *
   * @param out The output appendable object of the TextView.
   */
  public TextView(Appendable out) {
    this.out = out;
    this.viewType = ViewType.TEXT;
  }

  @Override
  public void display(String animationSequence) {
    try {
      this.out.append(animationSequence);

    } catch (IOException e) {
      throw new IllegalArgumentException(
          "This is not a valid input for displaying the " + "data. This input must be a String.");
    }
  }

  @Override public void display() {
    throw new UnsupportedOperationException("This method is not supported by the text view.");
  }

  @Override
  public ViewType getViewType() {
    return this.viewType;
  }

  @Override public void updateData(ArrayList<ShapeState> shapeStates) {
    throw new UnsupportedOperationException("This method is not supported by the text view.");

  }

  @Override public void setDelay(int speed) {
    throw new UnsupportedOperationException();
  }

  @Override public void setCanvasSize(int width, int height) {
    throw new UnsupportedOperationException();
  }
}
