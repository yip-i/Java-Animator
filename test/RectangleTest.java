
import model.AbstractShape;
import model.Rectangle;
import model.ShapeType;
import model.TimeInterval;
import model.ColourRGB;
import model.Position2D;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;



/**
 * This is a JUnit test for the Rectangle class that extends the super class AbstractShape.
 */
public class RectangleTest {

  /**
   * This tests the constructor of the Rectangle class by passing in randomly selected valid
   * combinations of numbers. No exceptions should be thrown.
   */
  @Test
  public void constructorTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      assertTrue(rectangleShape instanceof Rectangle);
    }
  }

  /**
   * This tests the constructor of the Rectangle class by passing in randomly selected invalid
   * combinations of numbers. The rectangle class cannot use non-positive widths or height.
   * An exception should always be thrown.
   */
  @Test
  public void constructorExceptionTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));

      width = Math.abs(random.nextInt(1000));
      height = Math.abs(random.nextInt(1000));

      if (i % 2 == 0) {
        width = - width;
      } else {
        height = - height;
      }

      try {
        rectangleShape = new Rectangle(name, duration, position, colour, width, height);
        fail("An exceptions should have been thrown.");
      } catch (IllegalArgumentException e) {
        assertTrue(e instanceof IllegalArgumentException);
      }
    }
  }

  /**
   * This tests the correct start position is returned by the getStartPosition method. The position
   * used in the constructor should be the same one returned by this method call.
   */
  @Test
  public void getStartPositionTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      assertEquals(rectangleShape.getStartPosition(), position);
    }
  }

  /**
   * This tests the correct start colour is returned by the getStartColour method. The colour
   * used in the constructor should be the same one returned by this method call.
   */
  @Test
  public void getStartColourTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      assertEquals(rectangleShape.startColour(), colour);
    }
  }

  /**
   * This tests the correct shape type is returned by the getShapeType method. The shape
   * type used to create the object should be the same one that corresponds to this class.
   */
  @Test
  public void getShapeTypeTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      assertEquals(rectangleShape.getShapeType(), ShapeType.RECTANGLE);
    }
  }

  /**
   * This tests the correct name of the shape is returned by the getName method. The name
   * used to construct this rectangle object should be the same one returned by this method call.
   */
  @Test
  public void getNameTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      assertEquals(rectangleShape.getName(), name);
    }
  }

  /**
   * This tests the correct time interval is returned by the getDuration method. The position
   * used in the constructor should be the same one returned by this method call.
   */
  @Test
  public void getDurationTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      assertEquals(rectangleShape.getDuration(), duration);
    }
  }

  /**
   * This tests the correct width and height are returned by the getWidth and getHeight methods.
   * The width and height used in the constructor should be the same ones returned by this
   * method call.
   */
  @Test
  public void getWidthHeightTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;

    // When we used abstract shape as the variable type, the object could not use
    // the Rectangle specific methods.
    //AbstractShape rectangleShape;
    Rectangle rectangleShape;


    int startTime;
    int endTime;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256),
              random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)),
              Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      assertEquals(rectangleShape.getWidth(), width, 0.001);
      assertEquals(rectangleShape.getHeight(), height, 0.001);
    }
  }

  /**
   * This tests that the correct comparison is made when using the compareTo method. If the shape
   * invoking the method has a start time that occurs after the argument passed into it the value
   * should be 1, if they are the same start time the value should be 0 and if it occurs before the
   * value will be -1. This logic is tested for the corresponding relations and values here.
   */
  @Test
  public void compareToTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;

    String name2 = "Rectangle_2";
    TimeInterval duration2;
    Position2D position2;
    ColourRGB colour2;
    double width2;
    double height2;
    AbstractShape rectangleShape2;
    int startTime2;
    int endTime2;

    int difference;
    int compareToValue;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));
      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000) + 1);

      startTime2 = Math.abs(random.nextInt(10000));
      endTime2 = startTime + Math.abs(random.nextInt(10000));
      duration2 = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour2 = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position2 = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width2 = Math.abs(random.nextInt(1000) + 1);
      height2 = Math.abs(random.nextInt(1000) + 1);

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);
      rectangleShape2 = new Rectangle(name, duration, position, colour, width, height);

      difference = rectangleShape.getDuration().getStart()
          - rectangleShape2.getDuration().getStart();
      if (difference > 0) {
        compareToValue = 1;
      } else if (difference < 0) {
        compareToValue = -1;
      } else {
        compareToValue = 0;
      }

      assertEquals(rectangleShape.compareTo(rectangleShape2), compareToValue);
    }
  }

  /**
   * The toString method for the rectangle class is tested here. The string returned by this method
   * should should involve all of the parameters used to construct the object and correspond to
   * text file format examples provided.
   */
  @Test
  public void toStringTest() {

    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;
    String shapeInfo;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000)) + 1;

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);

      shapeInfo = "Name:" + name + "\n" + "Type: Rectangle\n" + "Lower left corner: "
              + position.toString() + String.format(" Width: %.1f, Height: %.1f",
              (double) width, (double) height) + "\nColour:"
              + colour.toString() + "\n"
              + String.format("Appears at t=%d\nDisappears at t=%d\n",
              duration.getStart(), duration.getEnd());

      assertEquals(rectangleShape.toString(), shapeInfo);
    }
  }

  /**
   * The toSVG method for the rectangle class is tested here. The string returned by this method
   * should should involve all of the parameters used to construct the object and correspond to
   * SVG file format examples provided.
   */
  @Test
  public void getSVGTest() {
    Random random = new Random();
    String name = "Rectangle_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int width;
    int height;
    AbstractShape rectangleShape;
    int startTime;
    int endTime;
    String shapeSVG;
    int speed;


    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      speed = Math.abs(random.nextInt(100));
      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      width = Math.abs(random.nextInt(1000) + 1);
      height = Math.abs(random.nextInt(1000)) + 1;

      rectangleShape = new Rectangle(name, duration, position, colour, width, height);

      shapeSVG = "<rect id=" + "\"" + name + "\" " + "x=\"" + Math
              .round(position.getX()) + "\" " + "y=\"" + Math
              .round(position.getY()) + "\" " + "width=\"" + Math
              .round(width) + "\" " + "height=\"" + Math
              .round(height) + "\" fill=\"rgb" + colour
              .toString() + "\" visibility=\"hidden\" >\n";
      shapeSVG += "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin=\""
              + (duration.getStart() * speed) + "ms\" dur=\""
              + (duration.difference() * speed) + "ms\" /> \n";

      assertEquals(rectangleShape.getSVG(speed), shapeSVG);
    }
  }
}
