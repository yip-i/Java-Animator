package util;

import model.AnimatedMovieModelImplementation;
import model.ShapeType;
import model.ShapeState;
import model.Rectangle;
import model.AbstractShape;
import model.Oval;
import model.Position2D;
import model.TimeInterval;
import model.ColourRGB;
import model.ResizeAnimation;
import model.ColourChangeAnimation;
import model.MoveAnimation;

import java.util.HashMap;
import java.util.Map;

/**
 * Animation builder implementation.
 */
public class AnimatorBuilderImplementation implements AnimationBuilder {
  private AnimatedMovieModelImplementation model;
  private Map<String, ShapeType> shapeLookup;

  /**
   * Animation builder constructor.
   *
   * @param model Model that we are adding shapes and events to.
   */
  public AnimatorBuilderImplementation(AnimatedMovieModelImplementation model) {
    this.model = model;
    this.shapeLookup = new HashMap<String, ShapeType>();
  }

  @Override
  public Object build() {
    return null;
  }

  @Override
  public AnimationBuilder setBounds(int x, int y, int width, int height) {
    model.setBounds(x, y, width, height);
    // Why does this return anything?
    return null;
  }

  @Override
  public AnimationBuilder declareShape(String name, String type) {
    ShapeType shapeType;

    if (type.equals("rectangle")) {
      shapeType = ShapeType.RECTANGLE;
    } else if (type.equals("ellipse")) {
      shapeType = ShapeType.OVAL;
    } else {
      throw new IllegalArgumentException("This shape is not supported.");
    }

    shapeLookup.put(name, shapeType);
    // Why does this return anything?
    return null;
  }

  @Override
  public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    AbstractShape current;
    ShapeType shapeType = shapeLookup.get(name);

    if (!model.shapeExists(name)) {
      if (shapeType == ShapeType.RECTANGLE) {
        current = new Rectangle(name, new TimeInterval(t1, t2), new Position2D(x1, y1),
            new ColourRGB(r1, g1, b2), w1, h1);

      } else {
        current = new Oval(name, new TimeInterval(t1, t2), new Position2D(x1, y1),
            new ColourRGB(r1, g1, b2), w1, h1);

      }
      model.addShape(current);
    }
    if (shapeType == ShapeType.RECTANGLE) {

      model.addShapeState(
          new ShapeState(ShapeType.RECTANGLE, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2,
              r2, g2, b2));
    } else {

      model.addShapeState(
          new ShapeState(ShapeType.OVAL, t1, x1, y1, w1, h1, r1, g1,
              b1, t2, x2, y2, w2, h2, r2, g2, b2));
    }

    AbstractShape shapePlaceholder = model.getShape(name);
    if (shapePlaceholder == null) {
      throw new IllegalArgumentException("Shape with that name does not exist.");
    }

    if (shapePlaceholder.getDuration().getEnd() < t2) {
      shapePlaceholder.getDuration().setEnd(t2);
    }

    if (r1 != r2 || g1 != g2 || b1 != b2) {
      model.addAnimation(new ColourChangeAnimation(shapePlaceholder, new TimeInterval(t1, t2),
          new ColourRGB(r1, g1, b1), new ColourRGB(r2, g2, b2)));
    }
    if (w1 != w2 || h1 != h2) {
      if (shapePlaceholder.getShapeType() == ShapeType.RECTANGLE) {
        model.addAnimation(
            new ResizeAnimation(shapePlaceholder, new TimeInterval(t1, t2), w1, h1, w2, h2));
      } else {
        model.addAnimation(
            new ResizeAnimation(shapePlaceholder, new TimeInterval(t1, t2), w1, h1, w2,
                h2));
      }
    }
    if (x1 != x2 || y1 != y2) {
      model.addAnimation(
          new MoveAnimation(shapePlaceholder, new TimeInterval(t1, t2), new Position2D(x1, y1),
              new Position2D(x2, y2)));
    }

    return null;
  }
}
