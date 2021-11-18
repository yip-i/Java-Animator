package model;

/**
 * This class represents a colour using 3 integers that correspond to red, green and blue.
 */
public class ColourRGB {
  private int red;
  private int green;
  private int blue;

  /**
   * This method constructs the ColourRGB class through 3 parameters. An integer value for
   * red, green and blue is used to represent a colour.
   *
   * @param red   The integer value of red in this colour.
   * @param green The integer value of green in this colour.
   * @param blue  The integer value of blue in this colour.
   * @throws IllegalArgumentException If the value for any colour is outside of the valid range.
   */
  public ColourRGB(int red, int green, int blue) throws IllegalArgumentException {
    if ((red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255)) {
      throw new IllegalArgumentException("Each colour must be between 0 and 255 " + "(inclusive).");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * This method will return the integer representing red in this colour.
   *
   * @return The integer representing red in this colour.
   */
  public int getRed() {
    return red;
  }

  /**
   * This method will return the integer representing green in this colour.
   *
   * @return The integer representing green in this colour.
   */
  public int getGreen() {
    return green;
  }

  /**
   * This method will return the integer representing blue in this colour.
   *
   * @return The integer representing blue in this colour.
   */
  public int getBlue() {
    return blue;
  }

  /**
   * This method will return a string representation of this colour object using the colour values
   * of red, green and blue in this order.
   *
   * @return A string representation of this colour object using the * colour values of red, green
   *          and blue in this order.
   */
  public String toString() {
    return "(" + red + "," + green + "," + blue + ")";
  }
}
