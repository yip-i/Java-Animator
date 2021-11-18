package model;

/**
 * This class represents methods that are common and similar among AnimationEvent classes. It also
 * uses a default constructor for common variables.
 */
public abstract class AbstractAnimationEvent implements Comparable<AbstractAnimationEvent> {
  protected AbstractShape shape;
  protected TimeInterval duration;
  protected AnimationType animation;

  /**
   * Constructor for the Abstract Animation Event.
   * @param shape Shape the animation is working on.
   * @param duration Duration the animation will happen between.
   * @param animation Type of animation that it is.
   */
  public AbstractAnimationEvent(AbstractShape shape, TimeInterval duration,
      AnimationType animation) {
    if (!duration.isBetween(shape.getDuration())) {
      throw new IllegalArgumentException("The time duration of the animation must be between the "
          + "appearance and disappearance time of the shape.");
    }

    this.shape = shape;
    this.duration = duration;
    this.animation = animation;
  }


  /**
   * This method will return the string representation of parameters defining this *
   * AbstractAnimationEvent object.
   *
   * @return The string representation of parameters defining this * * AbstractAnimationEvent
   *     object.
   */
  public abstract String toString();

  /**
   * This method will return the Shape object of this AbstractAnimationEvent object.
   *
   * @return The shape object of this object.
   */
  public AbstractShape getShape() {
    return shape;
  }

  /**
   * This method will return the TimeInterval object of this AbstractAnimationEvent.
   *
   * @return The TimeInterval object of this AbstractAnimationEvent
   */
  public TimeInterval getDuration() {
    return duration;
  }

  /**
   * This method will return the AnimationType object of this AbstractAnimationEvent object.
   *
   * @return The AnimationType object of this AbstractAnimationEvent object.
   */
  public AnimationType getAnimation() {
    return animation;
  }

  /**
   * Comparing this animation event to another animation event.
   * @param otherAnimationEvent Animation event we are comparing it to.
   * @return
   */
  @Override
  public int compareTo(AbstractAnimationEvent otherAnimationEvent) {

    int difference = this.duration.getStart() - otherAnimationEvent.duration.getStart();

    if (difference > 0) {
      return 1;
    } else if (difference < 0) {
      return -1;
    } else {
      return 0;
    }
  }

  /**
   * Check if two animations conflict with each other.
   * If they are on the same shape, happen at the same time and are the same type of event.
   * @param otherAnimationEvent Other animation event we are comparing this one too.
   * @return True if it does conflict with, otherwise returns false.
   */
  public boolean conflictWith(AbstractAnimationEvent otherAnimationEvent) {
    if (this.shape.equals(otherAnimationEvent.getShape())) {
      if (this.animation == otherAnimationEvent.getAnimation()) {
        if (this.duration.overlaps(otherAnimationEvent.getDuration())) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * Abstract class for getting the animation event SVG representation.
   * @param speed Speed the animation is happening in.
   * @return String of the SVG representation of the animation.
   */
  public abstract String getSVG(int speed);

}
