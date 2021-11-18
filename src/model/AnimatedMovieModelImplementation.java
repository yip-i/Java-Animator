package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class builds the model for an animated movie and implements the
 * AnimatedMovieModelInterface.
 */
public class AnimatedMovieModelImplementation implements AnimatedMovieModelInterface {
  private String name;
  private ArrayList<AbstractShape> shapesInOrder;
  private ArrayList<AbstractAnimationEvent> animationsInOrder;
  private ArrayList<ShapeState> shapeStateArrayList;
  private int xBound;
  private int yBound;
  private int width;
  private int height;

  /**
   * Constructor for the Animated Movie Model implementation that takes no parameters.
   */
  public AnimatedMovieModelImplementation() {
    this("");
  }

  /**
   * Main constructor for the animated movie model implementation. Takes name of the movie as
   *          a parameter.
   * @param name Name of the movie.
   */
  public AnimatedMovieModelImplementation(String name) {
    this.name = name;
    this.shapesInOrder = new ArrayList<AbstractShape>();
    this.animationsInOrder = new ArrayList<AbstractAnimationEvent>();
    this.shapeStateArrayList = new ArrayList<ShapeState>();
    this.xBound = 400;
    this.yBound = 400;
    this.width = 400;
    this.height = 400;
  }

  @Override
  public void addShape(AbstractShape shape) throws IllegalArgumentException {
    shapesInOrder.add(shape);

  }

  /**
   * Method to see if the shape is stored in our shapes in order list.
   * @param shape Shape that we are looking for.
   * @return True if shape is in the list, false otherwise.
   */
  public boolean shapeExists(AbstractShape shape) {
    return shapesInOrder.contains(shape);

  }

  /**
   * Method to check if a shape with this name exists in our shapes in order list.
   * @param shapeName Shape name that we are checking.
   * @return True if the shape with this name is in our list, false otherwise.
   */
  public boolean shapeExists(String shapeName) {
    for (int i = 0; i < shapesInOrder.size(); i++) {
      if (shapesInOrder.get(i).getName().equals(shapeName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the abstract shape with this shape name.
   * @param shapeName The name of the shape that we are looking for.
   * @return Abstract shape with the name we are looking for.
   */
  public AbstractShape getShape(String shapeName) {
    for (int i = 0; i < shapesInOrder.size(); i++) {
      if (shapesInOrder.get(i).getName().equals(shapeName)) {
        return shapesInOrder.get(i);
      }
    }
    return null;
  }

  @Override
  public void addAnimation(AbstractAnimationEvent event) {
    for (int i = 0; i < animationsInOrder.size(); i++) {
      if (event.conflictWith(animationsInOrder.get(i))) {
        throw new IllegalArgumentException("Animation conflicts with animation in list");
      }
    }
    if (shapeExists(event.getShape())) {
      animationsInOrder.add(event);
      Collections.sort(animationsInOrder);
      return;
    }
    throw new IllegalArgumentException("Shape must be in model to add event.");
  }

  @Override public void addShapeState(ShapeState shapeState) {
    shapeStateArrayList.add(shapeState);
  }

  @Override public ArrayList<ShapeState> getShapeStateList() {
    return shapeStateArrayList;
  }

  @Override public ArrayList<AbstractShape> getShapeOrderList() {
    return shapesInOrder;
  }

  @Override public ArrayList<AbstractAnimationEvent> getAnimationOrderList() {
    return animationsInOrder;
  }

  @Override public void setBounds(int xBound, int yBound, int width, int height) {
    this.xBound = xBound;
    this.yBound = yBound;
    this.width = width;
    this.height = height;
  }

  @Override public String toString() {
    ArrayList<AbstractShape> cloneShapes = (ArrayList<AbstractShape>) shapesInOrder.clone();
    Collections.sort(cloneShapes);
    String output = "";
    for (int i = 0; i < cloneShapes.size(); i++) {
      output += cloneShapes.get(i).toString();
    }
    output += "\n";
    for (int i = 0; i < animationsInOrder.size(); i++) {
      output += animationsInOrder.get(i).toString();
      //output += "\n";
    }

    return output;
  }

  @Override public String getSVG(int speed) {
    String output = "";
    output += "<svg width=\"" + width + "\" height=\"" + height + "\" version=\"1.1\"\n" + "\txmlns=\"http://www.w3.org/2000/svg\">\n\n";

    for (int i = 0; i < shapesInOrder.size(); i++) {
      output += shapesInOrder.get(i).getSVG(speed);

      for (int x = 0; x < animationsInOrder.size(); x++) {
        if (animationsInOrder.get(x).getShape().equals(shapesInOrder.get(i))) {
          output += animationsInOrder.get(x).getSVG(speed);
        }
        output += "\n";

      }
      if (shapesInOrder.get(i).getShapeType() == ShapeType.RECTANGLE) {
        output += "</rect>\n\n";
      }
      if (shapesInOrder.get(i).getShapeType() == ShapeType.OVAL) {
        output += "</ellipse>\n\n";
      }

    }

    output += "</svg>";
    return output;
  }

  @Override public int getCanvasWidth() {
    return this.width;
  }

  @Override public int getCanvasHeight() {
    return this.height;
  }

  /**
   * Gets the name of the movie.
   * @return Name of the movie.
   */
  public String getName() {
    return this.name;
  }

}
