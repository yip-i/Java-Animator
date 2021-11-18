import model.AbstractShape;
import model.Rectangle;
import model.Oval;
import model.ShapeType;
import model.TimeInterval;
import model.ColourRGB;
import model.Position2D;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * This is a JUnit test for the Oval class that extends the super class AbstractShape.
 */
public class OvalTest {

  /**
   * This tests the constructor of the Oval class by passing in randomly selected valid
   * combinations of numbers. No exceptions should be thrown.
   */
  @Test public void constructorTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      assertTrue(ovalShape instanceof Oval);
    }
  }

  /**
   * This tests the constructor of the Oval class by passing in randomly selected invalid
   * combinations of numbers. The oval class cannot use a non-positive x radius or y radius.
   * An exception should always be thrown.
   */
  @Test public void constructorExceptionTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));

      xRadius = Math.abs(random.nextInt(1000));
      yRadius = Math.abs(random.nextInt(1000));

      if (i % 2 == 0) {
        xRadius = -xRadius;
      } else {
        yRadius = -yRadius;
      }

      try {
        ovalShape = new Rectangle(name, duration, position, colour, xRadius, yRadius);
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
  @Test public void getStartPositionTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      assertEquals(ovalShape.getStartPosition(), position);
    }
  }

  /**
   * This tests the correct start colour is returned by the getStartColour method. The colour
   * used in the constructor should be the same one returned by this method call.
   */
  @Test public void getStartColourTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      assertEquals(ovalShape.startColour(), colour);
    }
  }

  /**
   * This tests the correct shape type is returned by the getShapeType method. The shape
   * type used to create the object should be the same one that corresponds to this class.
   */
  @Test public void getShapeTypeTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      assertEquals(ovalShape.getShapeType(), ShapeType.OVAL);
    }
  }

  /**
   * This tests the correct name of the shape is returned by the getName method. The name
   * used to construct this rectangle object should be the same one returned by this method call.
   */
  @Test public void getNameTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      assertEquals(ovalShape.getName(), name);
    }
  }

  /**
   * This tests the correct time interval is returned by the getDuration method. The position
   * used in the constructor should be the same one returned by this method call.
   */
  @Test public void getDurationTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      assertEquals(ovalShape.getDuration(), duration);
    }
  }

  /**
   * This tests the correct x radius and y radius are returned by the getXRadius and
   * getYRadius methods. The width and height used in the constructor should be the same ones
   * returned by this method call.
   */
  @Test public void getRadiusTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;

    // When we used abstract shape as the variable type, the object could not use
    // the Oval specific methods.
    //AbstractShape ovalShape;
    Oval ovalShape;

    int startTime;
    int endTime;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      assertEquals(ovalShape.getXRadius(), xRadius, 0.001);
      assertEquals(ovalShape.getYRadius(), yRadius, 0.001);
    }
  }

  /**
   * This tests that the correct comparison is made when using the compareTo method. If the shape
   * invoking the method has a start time that occurs after the argument passed into it the value
   * should be 1, if they are the same start time the value should be 0 and if it occurs before the
   * value will be -1. This logic is tested for the corresponding relations and values here.
   */
  @Test public void compareToTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;

    String name2 = "Oval_2";
    TimeInterval duration2;
    Position2D position2;
    ColourRGB colour2;
    double xRadius2;
    double yRadius2;
    AbstractShape ovalShape2;
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
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000) + 1);

      startTime2 = Math.abs(random.nextInt(10000));
      endTime2 = startTime + Math.abs(random.nextInt(10000));
      duration2 = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour2 = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position2 = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius2 = Math.abs(random.nextInt(1000) + 1);
      yRadius2 = Math.abs(random.nextInt(1000) + 1);

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);
      ovalShape2 = new Oval(name, duration, position, colour, xRadius, yRadius);

      difference = ovalShape.getDuration().getStart() - ovalShape2.getDuration().getStart();
      if (difference > 0) {
        compareToValue = 1;
      } else if (difference < 0) {
        compareToValue = -1;
      } else {
        compareToValue = 0;
      }

      assertEquals(ovalShape.compareTo(ovalShape2), compareToValue);
    }
  }

  /**
   * The toString method for the Oval class is tested here. The string returned by this method
   * should should involve all of the parameters used to construct the object and correspond to
   * text file format examples provided.
   */
  @Test public void toStringTest() {

    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
    int startTime;
    int endTime;
    String shapeInfo;

    for (int i = 0; i < 1000; i++) {
      startTime = Math.abs(random.nextInt(10000));
      endTime = startTime + Math.abs(random.nextInt(10000));

      duration = new TimeInterval(startTime, startTime + Math.abs(random.nextInt()));
      colour = new ColourRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      position = new Position2D(Math.abs(random.nextInt(1000)), Math.abs(random.nextInt(1000)));
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000)) + 1;

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);

      shapeInfo = "Name:" + name + "\n" + "Type: Oval\n" + "Centre: " + position.toString() + String
          .format(" X radius: %.1f, Y Radius: %.1f", (double) xRadius,
              (double) yRadius) + "\nColour:" + colour.toString() + "\n" + String
          .format("Appears at t=%d\nDisappears at t=%d\n", duration.getStart(), duration.getEnd());


      assertEquals(ovalShape.toString(), shapeInfo);
    }
  }

  /**
   * The toSVG method for the rectangle class is tested here. The string returned by this method
   * should should involve all of the parameters used to construct the object and correspond to
   * SVG file format examples provided.
   */
  @Test public void getSVGTest() {
    Random random = new Random();
    String name = "Oval_1";
    TimeInterval duration;
    Position2D position;
    ColourRGB colour;
    int xRadius;
    int yRadius;
    AbstractShape ovalShape;
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
      xRadius = Math.abs(random.nextInt(1000) + 1);
      yRadius = Math.abs(random.nextInt(1000)) + 1;

      ovalShape = new Oval(name, duration, position, colour, xRadius, yRadius);

      shapeSVG = "<ellipse id=" + "\"" + name + "\" " + "cx=\"" + Math
          .round(position.getX()) + "\" " + "cy=\"" + Math
          .round(position.getY()) + "\" " + "rx=\"" + Math.round(xRadius) + "\" " + "ry=\"" + Math
          .round(yRadius) + "\" fill=\"rgb" + colour.toString() + "\" visibility=\"hidden\" > \n";
      shapeSVG += "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin=\""
          + (duration.getStart() * speed) + "ms\" dur=\"" + (duration.difference() * speed)
          + "ms\" />";

      assertEquals(ovalShape.getSVG(speed), shapeSVG);
    }
  }
}

