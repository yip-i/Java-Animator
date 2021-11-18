package controller;

import model.AnimatedMovieModelInterface;

/**
 * Represents the controller for the Animated movie.
 */
public interface AnimationControllerInterface {

  /**
   * Plays the animation.
   *
   * @param movie Model for the animated movie.
   * @param speed This is an integer value for the playback speed in ticks per second.
   */
  public void playAnimation(AnimatedMovieModelInterface movie, int speed);

}
