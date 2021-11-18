
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
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Class testing the animation events.
 */
public class AnimationEventTest {

  /**
   * Static test to get the toString of the Move event.
   */
  @Test
  public void testMoveToString() {
    AbstractShape s = new Rectangle("R", new TimeInterval(10, 100), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);
    AbstractAnimationEvent e = new MoveAnimation(s, new TimeInterval(10, 15), new Position2D(1, 2),
        new Position2D(14, 15));
    String expected = "Shape R moves from (1.0,2.0) to (14.0,15.0) from t=10 to t=15\n";
    assertEquals(expected, e.toString());
  }

  /**
   * Testing the Change colour animation's toString method.
   */
  @Test
  public void testChangeColourToString() {
    AbstractShape o = new Oval("O", new TimeInterval(20, 30), new Position2D(5, 7),
        new ColourRGB(24, 26, 200), 1, 2);

    AbstractAnimationEvent e = new ColourChangeAnimation(o, new TimeInterval(25, 30),
        new ColourRGB(1, 1, 1), new ColourRGB(2, 2, 2));

    String expected = "Shape O changes colour from (1,1,1) to (2,2,2) from t=25 to t=30\n";
    assertEquals(expected, e.toString());
  }

  @Test
  public void testResizeToString() {
    AbstractShape o = new Oval("O", new TimeInterval(0, 30), new Position2D(5, 7),
        new ColourRGB(24, 26, 200), 1, 2);

    AbstractAnimationEvent e = new ResizeAnimation(o,
        new TimeInterval(0, 25), 1, 2, 3, 5);
    String expected = "Shape O scales from X radius: 1.0, Y radius: 2.0 to X radius: "
        + "3.0, Y radius: 5.0 from t=0 to t=25\n";
    e.toString();
    assertEquals(expected, e.toString());

    AbstractShape s = new Rectangle("R", new TimeInterval(10, 100), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);
    AbstractAnimationEvent e2 = new ResizeAnimation(s, new TimeInterval(14, 25), 1, 2,
        3, 5);
    String otherExpected = "Shape R scales from Width: 1.0, Height: 2.0 to Width: 3.0, "
        + "Height: 5.0 from t=14 to t=25\n";

    assertEquals(otherExpected, e2.toString());
  }

  /**
   * Test the conflict method of the abstract animation event.
   */
  @Test
  public void testConflict() {
    AbstractShape s = new Rectangle("R", new TimeInterval(10, 100), new Position2D(13, 14),
        new ColourRGB(1, 1, 1), 1, 2);
    AbstractAnimationEvent e = new MoveAnimation(s, new TimeInterval(10, 15), new Position2D(1, 2),
        new Position2D(14, 15));
    AbstractAnimationEvent e2 = new MoveAnimation(s, new TimeInterval(10, 15), new Position2D(1, 2),
        new Position2D(14, 15));
    AbstractAnimationEvent e3 = new MoveAnimation(s, new TimeInterval(11, 14), new Position2D(1, 2),
        new Position2D(14, 15));
    AbstractAnimationEvent e4 = new MoveAnimation(s, new TimeInterval(11, 25), new Position2D(1, 2),
        new Position2D(14, 15));
    AbstractAnimationEvent e5 = new MoveAnimation(s, new TimeInterval(15, 25), new Position2D(1, 2),
        new Position2D(14, 15));
    AbstractAnimationEvent e6 = new ColourChangeAnimation(s, new TimeInterval(15, 25),
        new ColourRGB(1, 1, 1), new ColourRGB(1, 1, 1));

    assertTrue(e.conflictWith(e2));
    assertTrue(e.conflictWith(e3));
    assertTrue(e.conflictWith(e4));

    assertFalse(e.conflictWith(e6));
    // How should event that starts the same time another ends be handled?
    assertFalse(e.conflictWith(e5));

  }


}
