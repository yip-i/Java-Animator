import controller.AnimationController;
import model.AnimatedMovieModelImplementation;
import model.AbstractShape;
import model.Rectangle;
import model.Oval;
import model.ResizeAnimation;
import model.MoveAnimation;
import model.ColourChangeAnimation;
import model.TimeInterval;
import model.ColourRGB;
import model.Position2D;
import model.AbstractAnimationEvent;
import util.AnimationBuilder;
import util.AnimationReader;
import util.AnimatorBuilderImplementation;
import view.AnimatorViewInterface;
import view.SVGView;
import view.TextView;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.Closeable;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;



/**
 * Class for the Movie implementation test.
 */
public class AnimatedMovieModelImplementationTest {

  /**
   * Test adding to the movie model.
   * Ensures that the shape is in the movie model.
   */
  @Test public void testAddShape() {
    AbstractShape s = new Rectangle("R", new TimeInterval(10, 14), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);

    AbstractShape s2 = new Rectangle("R", new TimeInterval(10, 14), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);

    AbstractShape s3 = new Oval("O", new TimeInterval(20, 30), new Position2D(5, 7),
        new ColourRGB(24, 26, 200), 1, 2);

    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();

    movie.addShape(s);
    movie.addShape(s3);
    assertTrue(movie.shapeExists(s3));
    assertTrue(movie.shapeExists(s));
    assertFalse(movie.shapeExists(s2));
    assertFalse(s == s2);
    assertFalse(s.equals(s2));
  }

  /**
   * Test adding a move to the movie model.
   */
  @Test public void testAddMove() {
    AbstractShape s = new Rectangle("R", new TimeInterval(10, 100), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);

    AbstractShape s2 = new Rectangle("S", new TimeInterval(20, 150), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    movie.addShape(s);
    movie.addShape(s2);

    AbstractAnimationEvent move1 = new MoveAnimation(s, new TimeInterval(10, 15),
        new Position2D(1, 2), new Position2D(14, 15));
    AbstractAnimationEvent move2 = new MoveAnimation(s2, new TimeInterval(20, 40),
        new Position2D(1, 2), new Position2D(14, 15));
    movie.addAnimation(move2);

    movie.addAnimation(move1);
    String expected = s.toString() + s2.toString() + "\n" + move1.toString() + move2.toString();
    assertEquals(expected, movie.toString());
    System.out.println(movie.toString());
  }

  /**
   * Test for adding a colour change to the model.
   *
   */
  @Test public void testAddColourChange() {
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    AbstractShape o = new Oval("O", new TimeInterval(20, 50), new Position2D(5, 7),
        new ColourRGB(24, 26, 200), 1, 2);

    AbstractAnimationEvent e = new ColourChangeAnimation(o, new TimeInterval(25, 30),
        new ColourRGB(1, 1, 1), new ColourRGB(2, 2, 2));
    AbstractAnimationEvent e2 = new ColourChangeAnimation(o, new TimeInterval(31, 45),
        new ColourRGB(2, 2, 2), new ColourRGB(254, 255, 245));

    movie.addShape(o);
    movie.addAnimation(e);
    movie.addAnimation(e2);

    String expected = o.toString() + "\n" + e.toString() + e2.toString();
    assertEquals(expected, movie.toString());
  }

  /**
   * Test to add resize animation to the model.
   */
  @Test public void testAddResize() {
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    AbstractShape r = new Rectangle("R", new TimeInterval(20, 50), new Position2D(5, 7),
        new ColourRGB(24, 26, 200), 1, 2);
    AbstractAnimationEvent e = new ResizeAnimation(r, new TimeInterval(20, 25), 1, 2, 3, 5);
    AbstractAnimationEvent e2 = new ResizeAnimation(r, new TimeInterval(30, 40), 3, 5, 40, 50);
    movie.addShape(r);
    movie.addAnimation(e2);
    movie.addAnimation(e);

    String expected = r.toString() + "\n" + e.toString() + e2.toString();
    assertEquals(expected, movie.toString());
  }

  /**
   * Test to try to add an animation to the model without the shape being in the model.
   * This should throw an illegal argument exception.
   */
  @Test public void testAddEventFail() {
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    AbstractShape o = new Oval("O", new TimeInterval(20, 50), new Position2D(5, 7),
        new ColourRGB(24, 26, 200), 1, 2);
    AbstractShape s = new Rectangle("R", new TimeInterval(10, 100), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);

    AbstractAnimationEvent e = new ColourChangeAnimation(o, new TimeInterval(25, 30),
        new ColourRGB(1, 1, 1), new ColourRGB(2, 2, 2));
    AbstractAnimationEvent e2 = new ColourChangeAnimation(o, new TimeInterval(31, 45),
        new ColourRGB(2, 2, 2), new ColourRGB(254, 255, 245));
    movie.addShape(s);
    try {
      movie.addAnimation(e);
      fail();
    } catch (IllegalArgumentException myException) {
      assertEquals("Shape must be in model to add event.", myException.getMessage());
    }

  }

  /**
   * Test the SVG string of an event.
   */
  @Test public void testSVG() {
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    AbstractShape r = new Rectangle("R", new TimeInterval(20, 100), new Position2D(5, 7),
        new ColourRGB(24, 26, 200), 1, 2);

    AbstractShape r2 = new Rectangle("R2", new TimeInterval(30, 200), new Position2D(52, 40),
        new ColourRGB(240, 26, 50), 1, 2);
    AbstractAnimationEvent e = new ResizeAnimation(r, new TimeInterval(20, 25), 1, 2, 500, 500);
    AbstractAnimationEvent e2 = new ResizeAnimation(r, new TimeInterval(30, 40), 3, 5, 40, 50);
    AbstractAnimationEvent e3 = new ResizeAnimation(r2, new TimeInterval(30, 40), 3, 5, 400, 500);

    movie.addShape(r);
    movie.addShape(r2);
    movie.addAnimation(e2);
    AbstractAnimationEvent m = new MoveAnimation(r, new TimeInterval(50, 70), new Position2D(5, 7),
        new Position2D(100, 200));
    AbstractAnimationEvent m2 = new MoveAnimation(r2, new TimeInterval(50, 70),
        new Position2D(52, 40), new Position2D(300, 500));
    movie.addAnimation(m);
    movie.addAnimation(m2);

    AbstractAnimationEvent c = new ColourChangeAnimation(r, new TimeInterval(50, 70),
        new ColourRGB(24, 26, 200), new ColourRGB(50, 0, 0));
    AbstractAnimationEvent c2 = new ColourChangeAnimation(r2, new TimeInterval(50, 70),
        new ColourRGB(24, 26, 200), new ColourRGB(255, 0, 255));

    movie.addAnimation(c);
    movie.addAnimation(c2);
    movie.addAnimation(e);
    movie.addAnimation(e3);
    String expected = "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"500ms\" "
        + "attributeName=\"width\" from=\"1\" to=\"500.0\" fill=\"freeze\"/> \n"
         + "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"500ms\" "
        + "attributeName=\"height\" from=\"2\" to=\"500.0\" fill=\"freeze\"/> \n";
    assertEquals(expected, e.getSVG(100));

    System.out.println(movie.getSVG(100));
  }

  /**
   * Fuller Movie test.
   * Tests the animation builder, and the to string of the model.
   */
  @Test public void fullMovieTest() {
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    Readable myReader = null;

    AnimationBuilder builder = new AnimatorBuilderImplementation(movie);
    File filename = new File("/Users/ianyip/Documents/Coding/CS5004_code/Animator/smalldemo.txt");
    try {
      myReader = new FileReader(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    AnimationReader.parseFile(myReader, builder);

    AnimatorViewInterface view = null;
    Appendable fileWriter = null;
    try {
      fileWriter = new FileWriter("test.txt");
      view = new TextView(fileWriter);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    AnimationController controller = new AnimationController(view);

    controller.playAnimation(movie, 1);
    //System.out.println(movie.SVG(100));
    if (fileWriter instanceof FileWriter) {
      fileWriter = (FileWriter) fileWriter;
      try {
        ((Closeable) fileWriter).close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
    String expected = "Name:R\n" + "Type: Rectangle\n" + "Lower left corner: (200.0,200.0) "
        + "Width: 50.0, Height: 100.0\n"
        + "Colour:(255,0,0)\n" + "Appears at t=1\n" + "Disappears at t=100\n" + "Name:C\n"
        + "Type: Oval\n" + "Centre: (440.0,70.0) X radius: 120.0, Y Radius: 60.0\n" + ""
        + "Colour:(0,0,255)\n" + "Appears at t=6\n" + "Disappears at t=100\n" + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
        + "Shape C changes colour from (0,0,255) to (0,170,85) from t=50 to t=70\n"
        + "Shape C moves from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
        + "Height: 100.0 from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
        + "Shape C changes colour from (0,170,85) to (0,255,0) from t=70 to t=80\n";
    assertEquals(expected, movie.toString());
  }



  /**
   * Fuller Movie test.
   * Tests the values passed to controller compared to what is received by the view.
   * Tests the animation builder, controller and text view.
   */
  @Test public void fullMovieTest2() {
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    Readable myReader = null;

    AnimationBuilder builder = new AnimatorBuilderImplementation(movie);
    File filename = new File("/Users/ianyip/Documents/Coding/CS5004_code/Animator/smalldemo.txt");
    try {
      myReader = new FileReader(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    AnimationReader.parseFile(myReader, builder);


    Appendable fileWriter = new StringBuilder();
    AnimatorViewInterface view = new TextView(fileWriter);
    AnimationController controller = new AnimationController(view);

    controller.playAnimation(movie, 1);
    //System.out.println(movie.SVG(100));
    if (fileWriter instanceof FileWriter) {
      fileWriter = (FileWriter) fileWriter;
      try {
        ((Closeable) fileWriter).close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

    String expected = "Name:R\n" + "Type: Rectangle\n" + "Lower left corner: (200.0,200.0) "
        + "Width: 50.0, Height: 100.0\n"
        + "Colour:(255,0,0)\n" + "Appears at t=1\n" + "Disappears at t=100\n" + "Name:C\n"
        + "Type: Oval\n" + "Centre: (440.0,70.0) X radius: 120.0, Y Radius: 60.0\n" + ""
        + "Colour:(0,0,255)\n" + "Appears at t=6\n" + "Disappears at t=100\n" + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
        + "Shape C changes colour from (0,0,255) to (0,170,85) from t=50 to t=70\n"
        + "Shape C moves from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
        + "Height: 100.0 from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
        + "Shape C changes colour from (0,170,85) to (0,255,0) from t=70 to t=80\n";
    assertEquals(movie.toString(), fileWriter.toString());
    assertEquals(expected, fileWriter.toString());
  }

  /**
   * Fuller Movie test.
   * Tests the values passed to controller compared to what is received by the view.
   * Tests the animation builder, controller and svg view.
   */
  @Test public void fullMovieTest3SVG() {
    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    Readable myReader = null;

    AnimationBuilder builder = new AnimatorBuilderImplementation(movie);
    File filename = new File("/Users/ianyip/Documents/Coding/CS5004_code/Animator/smalldemo.txt");
    try {
      myReader = new FileReader(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    AnimationReader.parseFile(myReader, builder);


    Appendable fileWriter = new StringBuilder();
    AnimatorViewInterface view = new SVGView(fileWriter);
    AnimationController controller = new AnimationController(view);

    controller.playAnimation(movie, 1);
    //System.out.println(movie.SVG(100));
    if (fileWriter instanceof FileWriter) {
      fileWriter = (FileWriter) fileWriter;
      try {
        ((Closeable) fileWriter).close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

    assertEquals(movie.getSVG(1), fileWriter.toString());
  }


}
