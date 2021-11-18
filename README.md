# Welcome to our Shapes Animator, created in Java!

This project was completed by **Mitchell Lawson** and **Ian Yip** as a submission for CS 5004 - Object Oriented Design at Northeastern University in Spring 2021. This project applied the MVC software design pattern to create a program that could translate user inputs such as button presses and command line statements into an animation through text or a GUI component.
> This program can read files with the .txt extension that follow the animator's shape generation format. An example of this can be seen in `smalldemo.txt`.

# The Model

The model of our program is the portion that contains the real implementations of shape generation, animation details, data storage and additional classes.

### Interfaces and Abstract Classes
Our model included the following higher-level classes to establish more strict implementations and/or to abstract out common methods shared between inheriting classes.
- `AbstractAnimationEvent`
- `AbstractShape`
- `AnimatedMovieModel`

### Shapes
The classes and shapes that are supported and extend the AbstractShape class include:
- `Oval`
- `Rectangle`

Each of these classes has a specific `ShapeType` enumerator class value that represents each shape type. These shapes can be used to create objects in an animation. Because their superclass is the `AbstractShape` class some common methods do not need to be reimplmented in the future when additional shapes are added (polygons, triangles, etc.). These shapes contain the methods `toString()` and `getSVG()` that generates a string that can be used to represent the shape in either a valid text format or SVG format, respectively. These classes will each have different implementations of these methods because different shapes will have different representations. For example, a rectangle uses width and height to represent its dimensions but an oval will use an x radius and y radius to represent these similar dimensions.

### Animations
The classes and animations that are supported and extend the `AbstrastAnimationEvent` class include:
- `ColourChangeAnimation`
- `MoveAnimation`
- `ResizeAnimation`

Each of these classes has a specific `AnimationType` enumerator class value that represents each animation type. These animations can be used to create the animations associateed with shapes in the animation. Because their superclass is the `AbstractAnimation` class, some common methods do not need to be reimplemented in the future when additional animation may be added. These animation classes, similar to shapes, also contain a `toString()` and `getSVG` method that generates a string that can be used to represent the animation in either a valid text format or SVG format, respectively. These classes will each have different implementations of these methods because their content and function in an animation is unique.

### Shape States
In the visual (non-text) implementation of the view, shapes undergo many different operations like appearing, disappearing, moving, resizing and change in colour throughout the duration of an animation. This information had to be contained in a format that allowed for easy access by the view, without altering the original state of the model, so we created a class of shape states called `ShapeState` that allowed us to easily calculate each shape's current state at some point in time. A shape's insertion order/layer, current position, colour, size, etc. could then all be readily retrieved the controller and rendered by the view. We used the simple linear tweening formula to calculate the aforementioned values and parameters.

### Additional Classes
In order to favour composition over inheritance, we created a few different classes be that would be used throughout this program and implemented methods that would allow us to easily access their data:
- `ColourRGB`
- `Position2D`
- `TimeInterval`

These classes are used to represent the colour, position and time interval of shapes or animations. Their methods saved us from constantly reimplementing their methods and allowed for code reuse in various places. An example of this was reusing the `toString()` method in each of these classes in the `toString()` and `getSVG()` methods of the shape and animation classes.

### Implementations
We implemented the above classes and `AnimatedMovieModel` interface in `AnimatedMovieImplementation`, which contains the model's vital methods and collection shape/animation data. We used an `ArrayList` data structure to store shapes and animations, in a sorted order of start time in our model.

This class contains some of the following methods:
1. Shapes:
    - `addShape()` which adds a shape to the model.
    - `shapeExists()` which determine if a shape already exists in our list. It can used either a name or shape object as a parameter.
    - `getShape()` will return the shape object corresponding with a given shape name. When parsing any text files this is necessary because the names in the files are strings but may make references to the same shape object at different points in time.
    - `getShapeOrderList` will return the list of shapes in the model, in an order sorted by start (appearance) time.
2. Animations:
    - `addAnimation` will an animation in sorted order to the animation list. If the animation conflicts with another animation for a shape object, it will throw an exception.
    - `getAnimationOrderList` will return the list of animations in the model, in an order sorted by start time.
3. Strings:
    - `toString()` will return a text file representation of the shapes and animation events involved in this model.
    - `getSVG()` will return a playable SVG file representation of the shapes and animation events involved in this model for a given playback speed.
4. Shape States
    - `addShapeState()` will add a shape state object to the list of shapes for the model/animation.
    - `getShapeStateList()` will return the list of shape states in the model.
5. Other
    - `setBounds()` will set/change the canvas x bound, y bound, width and height from the default values of (400, 400, 400, 400) for an animation.
    - `getCanvasHeight()` will return the canvas height.
    - `getCanvasWidth()` will return the canvas width.


# The Controller
The controller takes input from the view and passes it to the model to get information back that can then be passed back to the view for display. It consists of the interface `AnimationControllerInterface` and its implementing class `AnimationController`. There is one method, `playAnimation()`, that will select the correct information to output from the model based on the view and associated view type that has been created. Depending on the view that has created and passed to the controller, one of three actions will occur:
1. Text from the model's `getSVG()` method (with playback speed) will be passed onto the SVG view.
2. Text from the model's `toString()` method will be passed onto the text view.
3. The controller will update the visual view's data with the shape states from the model, set the animation speed or time delay and set the canvas size (playable area of the animation).

Each of the view classes has a specific `AnimationType` enumerator class value that represents each view type.

# The View
The view is where our information is displayed to the user and where we will take inputs.

### Text
The text view outputs a string to the command line or file that represents the animation that is read from a text file. It consists of using the `TextView` class to print the specified file's animation information to a file specified by the user with a .txt file extension, otherwise it will just print it to the user's terminal. The `display()` method of this class will print the string that has been passed to it.

### SVG
The Scalable Vector Graphics (SVG) view outputs a .SVG file that can be played within a browser or media player using an animation's text file as input. It consists using the `SVGView` to write this information to the user's specified file name with the .SVG file extension. The `display()` method of this class will print the string that has been passed to it to the specified file.

### Visual
The visual view consists of many different aspects that are required to create a usable GUI that consists of a visual display, buttons and data created in the model.

Firstly, the text file that the user has selected to build an animation from is processed by the `AnimationBuilderImplementation` and the associated model is created with shape states because this view is dynamic. The controller then passes this information onto the visual view. Secondly, the `VisualView` class creates and sets up the canvas for the animation being displayed, and the components that the user can interact with such as buttons. Additionally, it determines what functions and purpose these buttons serve. Lastly, the `MoviePanel` class is responsible for displaying the shapes and other objects shown in the animation. It takes the states of the shapes displays them to the visual view window with their current attributes in time. The `MoviePanel`'s `repaint()` method will display the current data to the animation window. This method is invoked using a timer that will `repaint()` the entire animation for a given tick depending on the speed of the animation. For example, a default tick rate of 1 tick per second means that the animation will update the images by invoking the `repaint()` method once every second, whereas a rate of 10 ticks per second means that the animation will update the images by invoking the `repaint()` method 10 times per second. This logic was handled by using the `Timer()` class to set a delay depending on the speed given. A higher delay would result in slower updates from a slower speed and a low delay would result in faster updates from a faster speed.

Buttons, associated functions and effects are shown below:
1. Quit: Ends the animation and exits.
> System exit and the window closes.
2. Start: Plays the animation.
> Begins the timer.
3. Pause: Pauses the animation.
> Stops the timer and tick value.
4. Resume: Resumes the animation from a paused state.
> Starts the timer again at the same time it stopped and at the same tick in the animation.
5. Restart: Restarts the animation from the beginning.
> Resets the timer and tick value back to 0.
6. Enable Loop: Enables looping, the animation will restart after it has ended.
> At the end of the animation, the tick value will return back to 0 (beginning) and the timer will loop back to the beginning.
7. Disable Loop: Disables looping, the animation will not restart after it has ended.
> The animation will stop at the end.
8. Increase Speed: The animation speed will increase by a factor of 2.
> The animation's speed increases by a factor of 2, resulting in the timer's delay being reduced by a factor of 2.
9. Decrease Speed: The animation speed will decrease by a factor of 2.
> The animation's speed decreases by a factor of 2, resulting in the timer's delay being increased by a factor of 2.

# Utility Classes
This section consists of utility classes for converting a .txt file into a model's current state. The classes included in this section are:

- `AnimationBuilder` is the interface specifying supported methods and operations.
- `AnimationReader` is responsible for reading input from a document.
- `AnimationBuilderImplementation` is the implementation of building a model from a .txt document. This class sets the bounds of the canvas, create shapes and adds animation events in our model.

# Main

The main method is responsible for initializing our program. It creates the 3 components of the MVC design pattern, our model, view and controller. It is responsible for taking the user's input and determing which file to read, what view to set up and what speed to pass into the controller. Once a valid command has been passed, the program will create the selected view, it will then call the `AnimationBuilderReader` to parse the document and use the `AnimationBuilderImplementation` classes to build a model based on the file selected. The model and view will then be passed into the controller, and the following actions are dependent on the view selected and user arguments entered.

# Supported Commands and Instructions

Our main program supports a wide array of possible command line arguments of various forms and combinations.

1. Input file name. This is the text document that will contain all of the motions animations. It's preceding argument is "-in".
2. Output file name. This will be the name of the file that the view will output to. It's preceding argument is "-out".
3. View. This is the type of view that will display the animation. It's preceding argument is "-view".
4. Speed. The speed the animation will play at.  It's preceding argument is "-view".

Command line argument examples have the following form:
- -in "input file name" -view "view" -out "output file name" -speed "speed of the animation"
- -in "input file name" -view "view" -out "output file name"
- -in "input file name" -view "view"

The order in which the commands are given does no make a difference, but the -"argument" must always be before the actual argument.

The two arguments that are required are the View type and the input file name. If the output file name is not given, it will default to System.out and if the speed is not given, then the speed will default to 1 tick per second.

