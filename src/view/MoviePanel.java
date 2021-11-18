package view;

import model.ShapeType;
import model.ShapeState;
import model.Rectangle;
import model.Oval;


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class where the movie will be animated.
 */
public class MoviePanel extends JPanel {

  ArrayList<ShapeState> shapeStateArrayList;
  int currentTick;

  /**
   * Contructor for the movie class.
   */
  public MoviePanel() {
    super(true);
    this.setBackground(Color.WHITE);
    //this.setSize();
    this.setSize(getPreferredSize());
    this.currentTick = 0;
    this.setLocation(0, 0);
    this.shapeStateArrayList = new ArrayList<ShapeState>();
  }
  //Java Swing timer.

  public void updateTime(int tick) {
    this.currentTick = tick;
    this.repaint();
  }

  public void retrieveShapeStates(ArrayList<ShapeState> shapeStates) {
    this.shapeStateArrayList = shapeStates;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Rectangle tempRectangle;
    Oval tempOval;


    for (int i = 0; i < shapeStateArrayList.size(); i++) {
      if (shapeStateArrayList.get(i).isShapeDrawn(currentTick)) {
        if (shapeStateArrayList.get(i).getShapeType() == ShapeType.RECTANGLE) {
          tempRectangle = (Rectangle) shapeStateArrayList.get(i).tweenShape(currentTick);
          g.setColor(new Color(tempRectangle.startColour().getRed(),
                  tempRectangle.startColour().getGreen(),
                  tempRectangle.startColour().getBlue()));
          g.fillRect(tempRectangle.getStartPosition().getX() + 200,
                  tempRectangle.getStartPosition().getY(),
                  tempRectangle.getWidth(),
                  tempRectangle.getHeight());
        } else if (shapeStateArrayList.get(i).getShapeType() == ShapeType.OVAL) {
          tempOval = (Oval) shapeStateArrayList.get(i).tweenShape(currentTick);
          g.setColor(new Color(tempOval.startColour().getRed(),
                  tempOval.startColour().getGreen(),
                  tempOval.startColour().getBlue()));
          g.fillOval(tempOval.getStartPosition().getX() + 200,
                  tempOval.getStartPosition().getY(),
                  tempOval.getXRadius(),
                  tempOval.getYRadius());
        }
      }
    }

  }

  /**
   * Gets the max tick of the movie.
   * @return Returns the max tick of the movie.
   */
  public int findMaxTick() {
    int max = 0;
    for (int i = 0; i < shapeStateArrayList.size(); i++) {
      if (shapeStateArrayList.get(i).getDuration().getEnd() > max) {
        max = shapeStateArrayList.get(i).getDuration().getEnd();
      }
    }
    return max;
  }


}
