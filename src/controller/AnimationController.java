package controller;

import model.AnimatedMovieModelInterface;
import view.AnimatorViewInterface;
import view.ViewType;

/**
 * Class representing the controller for our movie implementation.
 */
public class AnimationController implements AnimationControllerInterface {

  private AnimatorViewInterface view;

  /**
   * Constructor for the controller Animated movie that uses an AnimatorViewInterface as
   * a parameter.
   *
   * @param view The view appropriate for this animation.
   */
  public AnimationController(AnimatorViewInterface view) {
    this.view = view;
  }

  @Override
  public void playAnimation(AnimatedMovieModelInterface movie, int speed) {

    if (view.getViewType() == ViewType.SVG) {
      view.display(movie.getSVG(speed));
    } else if (view.getViewType() == ViewType.TEXT) {
      view.display(movie.toString());
    } else if (view.getViewType() == ViewType.VISUAL) {
      this.view.updateData(movie.getShapeStateList());
      this.view.setDelay(speed);
      this.view.setCanvasSize(movie.getCanvasWidth(), movie.getCanvasHeight());
    } else {
      throw new IllegalArgumentException("This ViewType is not supported.");
    }

  }
}
