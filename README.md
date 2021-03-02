# Golf Pro

## An easy-to-use application that will help you keep track of all your game statistics.

Having been an avid golfer for over 10 years, I have come to realize the importance of being able to visualize 
a change in my golf game. This application is for those golfers that spend hours at the driving range and want to see
the quantified benefits of their hard-work.

The **key** features of this application include:
- A golf course creator.
- A net strokes calculator for each golf-course created.
- A graphical representation of changes in the net-strokes per game.
- A course handicap calculator.

## User Stories:

- As a user, I want to be able to create a new golf course and add a list of golf holes to it.
- As a user, I want to be able to enter my scores for each of the holes created.
- As a user, I want to be able to view my performance on a course with respect to its par score.
- As a user, I want to be able to view the list of holes on a course(with par and distance values).
- As a user, I want to be able to be able to calculate my course handicap for a specific golf course.
- As a user, I want to be able to save my GolfCourse.
- As a user, I want to be able to load my GolfCourse from a file.

## Phase 4: Task 2:

- I have chosen to make a class in my model package robust. Specifically, I have chosen to make the **GolfCourse** class robust. The following methods play a role in this task:
  1) GolfCourse(The constructor for this class)
  2) GolfCourse.nineHoles()
  3) Gui.validGolfHole()
  4) Gui.addCourseHelper1()
  5) Gui.makeAddCourseFrame()
  
## Phase 4: Task 3:

- Reflecting upon the design presented in my UML diagram, I have observed that my code has a relatively low level of cohesion. Additionally, I have noticed that there are no abstract classes, that could have helped make my code more readable and organized. However, I have noticed that by using 'getter' and 'setter' methods I have been able to achieve a moderately low level of coupling.

- If I had more time to work on the project, I would do the following refactoring:
  1) I would firstly split up my GUI class into different classed for each menu option.
  2) I would use abstract classes to extract lines of code that are common in the instantiation of new swing objects.
  3) I would try to implement the design patters learnt in lectures.
  4) I would try to reduce the coupling between the GolfPro and Gui classes by perhaps making GolfPro an abstract class (because the implementation of most of the methods in Gui comes from the GolfPro class).# CPSC210project
