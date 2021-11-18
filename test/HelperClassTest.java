import model.ColourRGB;
import model.Position2D;
import model.TimeInterval;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;



/**
 * Class to test the helper functions.
 */
public class HelperClassTest {

  /**
   * Test for the position class.
   */
  @Test public void testPosition2D() {
    String expected;
    Position2D position2D;
    for (int x = 1; x < 500; x++) {
      for (int y = 1; y < 500; y++) {
        position2D = new Position2D(x, y);
        assertEquals(x, position2D.getX());
        assertEquals(y, position2D.getY());
      }
    }
  }

  /**
   * Test for the colour helper class.
   */
  @Test public void testColourRGB() {
    ColourRGB colour;
    String expected;
    for (int r = 0; r < 256; r++) {
      for (int g = 0; g < 256; g++) {
        for (int b = 0; b < 256; b++) {
          colour = new ColourRGB(r, g, b);
          assertEquals(r, colour.getRed());
          assertEquals(g, colour.getGreen());
          assertEquals(b, colour.getBlue());
          expected = String.format("%d,%d,%d)", r, g, b);
        }
      }
    }
  }


  /**
   * Test colour class fails as expected. When all three colours are out of range.
   */
  @Test public void testColourRGBFail() {
    ColourRGB colour;
    String expected;
    for (int r = 256; r < 510; r++) {
      for (int g = 256; g < 510; g++) {
        for (int b = 256; b < 510; b++) {
          try {
            colour = new ColourRGB(r, g, b);
            fail();
          } catch (IllegalArgumentException exception) {
            expected = "Each colour must be between 0 and 255 " + "(inclusive).";
            assertEquals(expected, exception.getMessage());
          }
        }
      }
    }
  }

  /**
   * Test colour class fails as expected. When all red is out of bounds.
   */
  @Test public void testColourRGBFail2() {
    ColourRGB colour;
    String expected;
    for (int r = 256; r < 510; r++) {
      for (int g = 0; g < 255; g++) {
        for (int b = 0; b < 255; b++) {
          try {
            colour = new ColourRGB(r, g, b);
            fail();
          } catch (IllegalArgumentException exception) {
            expected = "Each colour must be between 0 and 255 " + "(inclusive).";
            assertEquals(expected, exception.getMessage());
          }
        }
      }
    }
  }

  /**
   * Test colour class fails as expected. When all green is out of bounds.
   */
  @Test public void testColourRGBFail3() {
    ColourRGB colour;
    String expected;
    for (int r = 0; r < 255; r++) {
      for (int g = 256; g < 510; g++) {
        for (int b = 0; b < 255; b++) {
          try {
            colour = new ColourRGB(r, g, b);
            fail();
          } catch (IllegalArgumentException exception) {
            expected = "Each colour must be between 0 and 255 " + "(inclusive).";
            assertEquals(expected, exception.getMessage());
          }
        }
      }
    }
  }

  /**
   * Test colour class fails as expected. When all blue is out of bounds.
   */
  @Test public void testColourRGBFail4() {
    ColourRGB colour;
    String expected;
    for (int r = 0; r < 255; r++) {
      for (int g = 0; g < 255; g++) {
        for (int b = 256; b < 510; b++) {
          try {
            colour = new ColourRGB(r, g, b);
            fail();
          } catch (IllegalArgumentException exception) {
            expected = "Each colour must be between 0 and 255 " + "(inclusive).";
            assertEquals(expected, exception.getMessage());
          }
        }
      }
    }
  }

  /**
   * Test time interval class.
   */
  @Test public void testTimeInterval() {
    TimeInterval duration;
    for (int start = 0; start < 1000; start++) {
      for (int end = start; end < start + 1000; end++) {
        duration = new TimeInterval(start, end);
        assertEquals(start, duration.getStart());
        assertEquals(end, duration.getEnd());
        assertEquals(end - start, duration.difference());
        assertTrue(duration.isBetween(new TimeInterval(start,end)));
      }
    }

  }

  /**
   * Test the set end method of the time interval class.
   */
  @Test public void testTimeInterval1() {
    TimeInterval duration;
    for (int start = 0; start < 1000; start++) {
      for (int end = start; end < start + 1000; end++) {
        duration = new TimeInterval(start, start);
        assertEquals(start, duration.getStart());
        assertEquals(start, duration.getStart());
        assertEquals(start - start, duration.difference());
        duration.setEnd(end);
        assertEquals(end, duration.getEnd());
        assertEquals(end - start, duration.difference());
      }
    }

  }

}
