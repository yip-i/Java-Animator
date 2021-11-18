import controller.AnimationController;
import view.AnimatorViewInterface;
import view.SVGView;
import view.TextView;
import view.VisualView;
import model.AnimatedMovieModelImplementation;
import util.AnimationBuilder;
import util.AnimationReader;
import util.AnimatorBuilderImplementation;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Closeable;

/**
 * Main class of our program.
 */
public class Main {

  /**
   * Constructor of the main of our program.
   *
   * @param args Arguments that we are passing to main.
   */
  public static void main(String[] args) {
    String input = "";
    String output = "";
    String view = "";
    int speed = 1;

    int iterator = 0;
    int length = args.length;
    if (length % 2 != 0) {
      throw new IllegalArgumentException("Must pass even number of arguments");
    }

    while (iterator < length) {
      if (args[iterator].equals("-in")) {
        input = args[iterator + 1];
      } else if (args[iterator].equals("-view")) {
        view = args[iterator + 1];
      } else if (args[iterator].equals("-out")) {
        output = args[iterator + 1];
      } else if (args[iterator].equals("-speed")) {
        speed = Integer.parseInt(args[iterator + 1]);
      } else {
        throw new IllegalArgumentException("Input not valid");
      }
      iterator += 2;
    }
    File filename;
    Readable myReader = null;
    //Might need to give file the directory
    try {
      if (input.contains(".txt")) {
        filename = new File(input);
        if (!filename.canRead()) {
          throw new IOException();
        } else {
          myReader = new FileReader(filename);

        }
      } else {
        throw new IllegalArgumentException("File must be a .txt file");
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    AnimatorViewInterface animatorView;
    Appendable outputMethod = System.out;

    if (output.length() > 0) {
      if (!output.contains(".txt") && !output.contains(".svg")) {
        throw new IllegalArgumentException("Output must be a file type.");
      }
      try {
        outputMethod = new FileWriter(output);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      outputMethod = System.out;
    }

    if (view.equals("text")) {
      animatorView = new TextView(outputMethod);
    } else if (view.equals("svg")) {
      animatorView = new SVGView(outputMethod);
    } else if (view.equals("visual")) {
      animatorView = new VisualView();
    } else {
      throw new IllegalArgumentException();
    }

    AnimatedMovieModelImplementation movie = new AnimatedMovieModelImplementation();
    AnimationBuilder builder = new AnimatorBuilderImplementation(movie);
    AnimationReader.parseFile(myReader, builder);


    AnimationController controller = new AnimationController(animatorView);
    controller.playAnimation(movie, speed);


    if (outputMethod instanceof FileWriter) {
      outputMethod = (FileWriter) outputMethod;
      try {
        ((Closeable) outputMethod).close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

  }
}
