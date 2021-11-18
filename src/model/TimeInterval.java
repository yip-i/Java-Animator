package model;

/**
 * Class representing a time interval.
 */
public class TimeInterval {
  int start;
  int end;

  /**
   * Constructor for the time interval.
   *
   * @param start Start tick of the time interval.
   * @param end   End tick of the time interval.
   * @throws IllegalArgumentException If start greater than end.
   */
  public TimeInterval(int start, int end) throws IllegalArgumentException {
    if (start > end) {
      throw new IllegalArgumentException("Start time cannot be greater than end time" + ".");
    }
    this.start = start;
    this.end = end;

  }

  /**
   * Get start time of interval.
   *
   * @return Integer representing start time.
   */
  public int getStart() {
    return this.start;
  }

  /**
   * Get end time of the interval.
   *
   * @return Integer representing the end time.
   */
  public int getEnd() {
    return this.end;
  }

  /**
   * This method will compare two time intervals with one another and determine if they overlap.
   *
   * @param other The other TimeInterval to compare.
   * @return True if the time intervals overlap and false otherwise.
   */
  public boolean overlaps(TimeInterval other) {
    return (this.start <= other.getStart() && other.getStart() < this.end) || (this.start < other
        .getEnd() && other.getEnd() <= this.end);
  }

  /**
   * This method will determine if another time interval is encapsulated within the other.
   *
   * @param other The other TimeInterval to compare.
   * @return True is the other time interval is completely within the other and false otherwise.
   */
  public boolean isBetween(TimeInterval other) {
    return (this.start >= other.getStart() && other.getEnd() >= this.end);
  }

  /**
   * Set end of the time duration.
   *
   * @param end New end of the time duration.
   */
  public void setEnd(int end) {
    this.end = end;
  }

  /**
   * Difference between start and end.
   *
   * @return Difference between start and end.
   */
  public int difference() {
    return this.end - this.start;
  }

  @Override public String toString() {
    return "time " + "t=" + start + " to t=" + end;
  }
}
