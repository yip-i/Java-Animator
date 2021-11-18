package view;

import model.ShapeState;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents the visual view of our animator.
 * It has has all of the buttons needed for our animator and contains a movie panel.
 */
public class VisualView extends JFrame implements AnimatorViewInterface, ActionListener {

  private ArrayList<ShapeState> shapeStates;
  private boolean loop;
  int speed;
  int delay;

  private MoviePanel moviePanel;
  private Timer timer;
  private int tick;

  /**
   * Constructor for our visual view.
   */
  public VisualView() {
    super("Movie");
    this.setBackground(Color.WHITE);
    // Do we have a minimum view size?
    this.setSize(750, 750);
    this.shapeStates = new ArrayList<ShapeState>();

    this.loop = false;
    this.speed = 1;
    this.delay = 1000 / speed;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JPanel buttonPane;
    JButton quitButton;
    JButton startButton;
    JButton pauseButton;
    JButton resumeButton;
    JButton restartButton;
    JButton enableLoopButton;
    JButton disableLoopButton;
    JButton increaseSpeedButton;
    JButton decreaseSpeedButton;


    buttonPane = new JPanel(true);
    buttonPane.setBackground(Color.WHITE);
    // Should we make the button Pane relative to the view size?
    buttonPane.setSize(200, 250);
    this.setLocation(0, 0);
    buttonPane.setLayout(new FlowLayout());
    this.add(buttonPane);

    quitButton = new JButton("Quit");
    quitButton.setName("Quit");
    quitButton.addActionListener(this);
    buttonPane.add(quitButton);

    startButton = new JButton("Start");
    startButton.setName("Start");
    startButton.addActionListener(this);
    buttonPane.add(startButton);

    pauseButton = new JButton("Pause");
    pauseButton.setName("Pause");
    pauseButton.addActionListener(this);
    buttonPane.add(pauseButton);

    resumeButton = new JButton("Resume");
    resumeButton.setName("Resume");
    resumeButton.addActionListener(this);
    buttonPane.add(resumeButton);

    restartButton = new JButton("Restart");
    restartButton.setName("Restart");
    restartButton.addActionListener(this);
    buttonPane.add(restartButton);

    enableLoopButton = new JButton("Enable Loop");
    enableLoopButton.setName("Enable Loop");
    enableLoopButton.addActionListener(this);
    buttonPane.add(enableLoopButton);

    disableLoopButton = new JButton("Disable Loop");
    disableLoopButton.setName("Disable Loop");
    disableLoopButton.addActionListener(this);
    buttonPane.add(disableLoopButton);

    increaseSpeedButton = new JButton("Increase Speed");
    increaseSpeedButton.setName("Increase Speed");
    increaseSpeedButton.addActionListener(this);
    buttonPane.add(increaseSpeedButton);

    decreaseSpeedButton = new JButton("Decrease Speed");
    decreaseSpeedButton.setName("Decrease Speed");
    decreaseSpeedButton.addActionListener(this);
    buttonPane.add(decreaseSpeedButton);


    moviePanel = new MoviePanel();
    this.add(moviePanel);
    this.moviePanel = moviePanel;

    this.timer = new Timer(this.speed, this);
    this.tick = 0;
    this.setVisible(true);
    moviePanel.repaint();
  }

  /**
   * Passes the array list of shapeStates to this and the movie panel.
   * @param shapeStates List of shape states that we are passing into this
   *                     and the movie panel.
   */
  public void updateData(ArrayList<ShapeState> shapeStates) {
    this.shapeStates = shapeStates;
    this.moviePanel.retrieveShapeStates(shapeStates);
    this.timer.start();

  }

  @Override
  public void setDelay(int speed) {
    this.speed = speed;
    this.delay = 1000 / speed;
    this.timer.setDelay(this.delay);
  }

  @Override public void setCanvasSize(int width, int height) {
    if (height < 250) {
      this.setSize(width + 250, 250);
    }
    this.setSize(width + 250, height);
  }

  @Override
  public void display(String animationSequence) {
    throw new UnsupportedOperationException("This view does not accept an string to display.");
  }

  @Override
  public void display() {
    moviePanel.repaint();
    this.repaint();
  }

  @Override
  public ViewType getViewType() {
    return ViewType.VISUAL;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JButton) {
      Component b = (Component) e.getSource();
      if (b.getName().equals("Quit")) {
        System.exit(0);
      } else if (b.getName().equals("Start")) {
        timer.start();
      } else if (b.getName().equals("Pause")) {
        timer.stop();
      } else if (b.getName().equals("Resume")) {
        timer.start();
      } else if (b.getName().equals("Restart")) {
        timer.restart();
        tick = 0;
      } else if (b.getName().equals("Enable Loop")) {
        this.loop = true;
      } else if (b.getName().equals("Disable Loop")) {
        this.loop = false;
      } else if (b.getName().equals("Increase Speed")) {
        setDelay(this.speed * 2);
      } else if (b.getName().equals("Decrease Speed")) {
        setDelay(this.speed / 2);
      }
    } else if (e.getSource() instanceof Timer) {

      moviePanel.updateTime(tick);
      //display();
      this.repaint();
      if (tick > moviePanel.findMaxTick() && loop) {
        tick = 0;
      } else if (tick > moviePanel.findMaxTick() && loop) {
        timer.stop();
      } else {
        tick++;
      }
    }
  }
}
