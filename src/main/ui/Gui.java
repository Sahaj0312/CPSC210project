package ui;

import exceptions.*;
import model.GolfHole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/////////////////////////////////////////////CITATIONS/////////////////////////////////////////////////////////////////

/*
1) https://stackoverflow.com/questions/8193801/how-to-set-specific-window-frame-size-in-java-swing#:~:text=setSize()%20and%20frame.,size%20of%20components%20in%20it.
---> Used to learn how to give a JFrame a specific size.
2) https://stackoverflow.com/questions/5911565/how-to-add-multiple-actionlisteners-for-multiple-buttons-in-java-swing
---> Used to learn how to add multiple action listeners to swing components.
3) https://stackoverflow.com/questions/15731246/align-text-and-icon-differently-in-jlabel
---> Used to learn how to align text and icon components in swing.
4) https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
---> Used to learn how to resize an image to appropriately fit a JLabel and other swing components.
5) https://stackoverflow.com/questions/17686827/how-to-place-a-big-heading-in-jlabel
---> Used to learn how to incorporate HTML into code and personalize headings etc.
6) https://stackoverflow.com/questions/1594423/setting-the-size-of-panels
---> Used to learn how to set the size of a JPanel
7) https://stackoverflow.com/questions/15694107/how-to-layout-multiple-panels-on-a-jframe-java
---> Used to learn how to layout multiple panels on a JFrame.
8) https://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
---> Used to learn how to set the size of a JButton.
9) https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/
---> Used to learn how to use the BoxLayout to make main-menu and properly arrange JButtons.
10) https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html#construct
---> Used to observe examples of how the BoxLayout is meant to be used.
11) https://stackoverflow.com/questions/10576111/how-to-add-a-list-of-jbuttons-to-a-jframe/10576141
---> Used to learn how to add a list of JButtons to a JFrame.
12) https://www.javatpoint.com/Displaying-image-in-swing
---> Used to learn how to add images to a canvas in swing.
13) https://www.youtube.com/watch?v=Kmgo00avvEw&t=2907s
---> Used as an introduction to the basics of swing.
14) http://www.java2s.com/Code/JavaAPI/javax.swing/JTextFieldaddFocusListenerFocusListenerl.htm
---> Used to learn how to add a FocusListener to a JTextField.
 */

/////////////////////////////////////////////--End of Citations---/////////////////////////////////////////////////////


//This class represents a user interface for the GolfPro application
public class Gui extends JFrame implements ActionListener {

    /////////////////////////////////////////////DECLARING FIELDS//////////////////////////////////////////////////////
    GolfPro golfPro = new GolfPro();
    JPanel mainP = new JPanel();
    JPanel boxPanel = new JPanel();
    BoxLayout boxlayout = new BoxLayout(boxPanel, BoxLayout.Y_AXIS);
    JLabel welcomeLabel = new JLabel();
    JPanel mainMenu = new JPanel();
    JPanel subPanelMenu = new JPanel();
    JLabel label1 = new JLabel();
    JButton viewGolfCourse;
    JButton addGolfCourse;
    JButton calculateCourseHandicap;
    JButton saveToFile;
    JButton loadFile;
    JButton quitApplication;
    JButton submit;
    JButton submit1 = new JButton("Submit");
    JButton submit2 = new JButton("Submit");
    JButton submit3 = new JButton("Submit");
    NewFrame addCourseFrame = new NewFrame("Add Course");
    NewFrame addHolesFrame = new NewFrame("Add Holes");
    NewFrame addScoresFrame = new NewFrame("Add Scores");
    NewFrame addHandicapFrame = new NewFrame("Calculate course handicap");
    JTextField textField = new JTextField();
    JTextField textField1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    JTextField textField5 = new JTextField();
    JTextField textField6 = new JTextField();
    JTextField textField7 = new JTextField();
    JTextField textField8 = new JTextField();
    JTextField tf;
    JLabel label = new JLabel("What is the hole number?");
    JLabel label2 = new JLabel("What is the distance to the hole?");
    JLabel label3 = new JLabel("What is par on this hole?");
    JLabel label4;
    JLabel label5 = new JLabel("What is your current handicap index?");
    JLabel label6 = new JLabel("What is the slope rating at your course?");
    JLabel label7 = new JLabel("What is the rating of your course?");
    JLabel label8 = new JLabel("What is par at this course?");
    List<GolfHole> holes = golfPro.course.holes;
    Boolean alreadyDone = false;
    public ArrayList<JTextField> textFields = new ArrayList<>();
    int count = 0;
    Boolean loaded = false;
    Boolean done = false;
    final ImageIcon joppImg = new ImageIcon(
            new ImageIcon("data/golfBall.png").getImage().getScaledInstance(
                    100, 125,Image.SCALE_DEFAULT));


    /////////////////////////////////////////////CONSTRUCTOR//////////////////////////////////////////////////////////

    //Creates a new instance of the user interface and initializes all the main components
    public Gui() throws FileNotFoundException, InvalidCourseParException, InvalidCourseNameException {
        super("GolfPro");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initButtons();

        setLayout(new BorderLayout());

        makeMainP();

        makeBoxPanel();

        ImageIcon logo = new ImageIcon("data/GolfPro.png");

        ImageIcon golfBallGif = new ImageIcon(
                new ImageIcon("data/ball.gif").getImage().getScaledInstance(
                        200, 200,Image.SCALE_DEFAULT));

        label1.setIcon(logo);

        label1.setHorizontalAlignment(SwingConstants.CENTER);

        makeSubPanel();

        makeWelcomeLabel(golfBallGif);


        mainMenu.add(subPanelMenu);

        mainMenu.setBackground(Color.lightGray);

        add(mainP);

        setSize(550,550);
        setResizable(false);
        setBackground(Color.green);
        setVisible(true);

        makeAddCourseFrame();
        makeAddHolesFrame();


    }

    /////////////////////////////////////////////VIEW COURSE/////////////////////////////////////////////////////////

    //EFFECTS: Prints out the entire GolfCourse object in a JOptionPane
    private void viewCourse() {
        JOptionPane.showMessageDialog(null,golfPro.viewGolfCourse(),
                "View Golf Course:",JOptionPane.PLAIN_MESSAGE, joppImg);
    }

    /////////////////////////////////////////////SAVE TO FILE/////////////////////////////////////////////////////////

    //EFFECTS: saves the GolfCourse as a Json file
    private void saveToFile() {
        golfPro.saveCourse();
        JOptionPane.showMessageDialog(null,"Successfully Saved Course!","Save Course",
                JOptionPane.PLAIN_MESSAGE,joppImg);
    }

    /////////////////////////////////////////////LOAD FROM FILE///////////////////////////////////////////////////////

    //MODIFIES: golfPro
    //EFFECTS: loads the GolfCourse from the GolfCourse.json file
    private void loadFromFile() {
        golfPro.loadCourse();
        loaded = true;
        JOptionPane.showMessageDialog(null,"Successfully Loaded Course!","Save Course",
                JOptionPane.PLAIN_MESSAGE,joppImg);
    }

    /////////////////////////////////////////////ADD HANDICAP/////////////////////////////////////////////////////////

    //Modifies AddHandicap NewFrame
    //EFFECTS: adds required swing components
    private void makeHandicapFrame() {
        addHandicapFrame.setTitle("Calculate Handicap");
        addHandicapFrame.setLayout(new FlowLayout());
        addHandicapFrame.setSize(300,330);
        textField5.setPreferredSize(new Dimension(250,40));
        textField6.setPreferredSize(new Dimension(250,40));
        textField7.setPreferredSize(new Dimension(250,40));
        textField8.setPreferredSize(new Dimension(250,40));
        addHandicapFrame.add(label5);
        addHandicapFrame.add(textField5);
        addHandicapFrame.add(label6);
        addHandicapFrame.add(textField6);
        addHandicapFrame.add(label7);
        addHandicapFrame.add(textField7);
        addHandicapFrame.add(label8);
        addHandicapFrame.add(textField8);
        addHandicapFrame.add(submit3);
        processHandicapInput();
    }

    //EFFECTS: Processes user input to calculate course handicap
    private void processHandicapInput() {
        submit3.addActionListener(
                e -> {
                    if (e.getSource() == submit3) {
                        JOptionPane.showMessageDialog(null,"Your course handicap is "
                                + (Integer.parseInt(textField5.getText()) * (Integer.parseInt(textField6.getText())
                                / 113) + (Integer.parseInt(textField7.getText())
                                - Integer.parseInt(textField8.getText()))), "Handicap", JOptionPane.PLAIN_MESSAGE,
                                joppImg);
                        if (JOptionPane.showConfirmDialog(null,
                                "Would you like to calculate another course handicap?",
                                "Course handicap",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, joppImg) == 0) {
                            clearHandicapTextFields();
                        } else {
                            addHandicapFrame.setVisible(false);
                            setVisible(true);
                        }
                    }
                }
        );
    }

    //MODIFIES: TextFields 5:8
    //EFFECTS: Clears the handicap textFields to accept new user input.
    private void clearHandicapTextFields() {
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");

    }

    /////////////////////////////////////////////ADD SCORES//////////////////////////////////////////////////////////

    //MODIFIES: addScoresFrame
    //EFFECTS: Shows the user the addScoresFrame if they wish to enter scores and process the scores inputted
    private void addScores() {
        if (JOptionPane.showConfirmDialog(null,
                "Do you want to add your scores in?",
                "Add scores?",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,joppImg) == 0) {
            addScoresFrame.setVisible(true);
            processing();
            makeAddScoresFrame();
        } else {
            setVisible(true);
        }
    }

    //MODIFIES: addScoresFrame
    //EFFECTS: Creates a label asking the user how many shots they took
    private void processing() {
        System.out.println(holes);
        for (GolfHole g : holes) {
            label4 = new JLabel("How many shots did you take on hole number " + g.getHoleNumber());
            addScoresFrame.add(label4);
            makeTextBox();
        }
        addActionListener();
        processInput();
        count = 0;
    }

    //MODIFIES: addScoresFrame
    //EFFECTS: Creates a new textField for every hole the user added to course and
    private void makeTextBox() {
        tf = new JTextField();
        tf.setPreferredSize(new Dimension(250,40));
        textFields.add(tf);
        addScoresFrame.add(tf);

    }

    //MODIFIES: Count
    //EFFECTS: Adds a focusListener to all the textFields and adds their input to the variable count
    private void addActionListener() {
        for (JTextField t : textFields) {
            t.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {}

                @Override
                public void focusLost(FocusEvent e) {
                    count = count + Integer.parseInt(t.getText());

                }
            });
        }
    }


    //EFFECTS: Adds an action listener to the submit2 button and processes user input accordingly
    private void processInput() {
        submit2.addActionListener(
                e -> {
                    if (e.getSource() == submit2) {
                        if (JOptionPane.showConfirmDialog(null,
                                "Do you want to edit your scores?",
                                "Edit Scores?",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,joppImg) == 0) {
                            count = 0;
                            clearAddScoresTextFields();
                            addScoresFrame.setVisible(true);
                        } else {
                            evaluateScores();
                            addScoresFrame.setVisible(false);
                            setVisible(true);
                        }
                    }
                }
        );
    }

    //EFFECTS: Creates a JOptionPane and gives the user feedback based on the scores they inputted
    private void evaluateScores() {
        if (count < golfPro.course.getPar()) {
            JOptionPane.showMessageDialog(null,
                    "Amazing! You were " + (golfPro.course.getPar() - count) + " under par.","Score",
                    JOptionPane.PLAIN_MESSAGE,joppImg);
        } else if (count == golfPro.course.getPar()) {
            JOptionPane.showMessageDialog(null,
                    "Good game, you averaged par on this course","Score", JOptionPane.PLAIN_MESSAGE,joppImg);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Better luck next time, you were " + (count - golfPro.course.getPar()) + " over par.",
                    "Score", JOptionPane.PLAIN_MESSAGE,joppImg);
        }
    }


    //MODIFIES: t
    //EFFECTS: Clears all the textFields previously created to get user input
    private void clearAddScoresTextFields() {
        for (JTextField t : textFields) {
            t.setText("");
        }
    }

    //MODIFIES: addScoresFrame
    //EFFECTS: adds required components to addScoresFrame
    private void makeAddScoresFrame() {
        addScoresFrame.setTitle("Add scores");
        submit2.setPreferredSize(new Dimension(70,30));
        addScoresFrame.add(submit2);
        addScoresFrame.setLayout(new FlowLayout());
        setSize();
    }

    //MODIFIES:addScoresFrame
    //EFFECTS: makes the frame dynamic by adjusting height of frame to perfectly fit an arbitrary number of holes added
    private void setSize() {
        if  (golfPro.course.getSize() == 1) {
            addScoresFrame.setSize(320,220);
        } else if (golfPro.course.getSize() == 2) {
            addScoresFrame.setSize(320,220);
        } else {
            addScoresFrame.setSize(320,golfPro.course.getSize() * 90);
        }
    }


    /////////////////////////////////////////////ADD HOLES//////////////////////////////////////////////////////////

    //EFFECTS: adds required swing components to the addHolesFrame
    private void makeAddHolesFrame() {
        if (!alreadyDone) {
            addHole();
            addActionListenerSubmit1();
            alreadyDone = true;
        }
    }

    //MODIFIES: addHolesFrame
    //EFFECTS: adds an action listener to the Submit1 button and uses the user input to create a new Hole and add it to
    //         a golfCourse(ListOfHoles:holes)
    private void addActionListenerSubmit1() {
        submit1.addActionListener(
                e -> {
                    if (e.getSource() == submit1) {
                        courseReset();
                        golfPro.addH(returnHn(), returnD(), returnP());
                        holes = golfPro.course.holes;
                        System.out.println(holes);
                        System.out.println("Successfully added Hole!");
                    if (JOptionPane.showConfirmDialog(null,
                            "Do you want to add another hole?",
                            "Add another hole?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,joppImg) == 0) {
                        clearTextField();
                        addHolesFrame.setVisible(true);
                        addHoleHelper1();
                    } else {
                        validGolfHole();
                        addHoleHelper2();
                    }
                    }
                }
        );
    }

    //MODIFIES: addHolesFrame
    //EFFECTS: Moves to adding scores only if exactly 9 holes are added to the course
    private void addHoleHelper2() {
        if (holes.size() == 9) {
            addScores();
            addHolesFrame.setVisible(false);
        }
    }

    //EFFECTS: Checks if the maximum of 9 holes has been added to the course
    private void addHoleHelper1() {
        try {
            golfPro.course.nineHoles(golfPro.course);
        } catch (NotEnoughHolesException ignored) {
            ;
        } catch (MaxHolesReachedException maxHolesReachedException) {
            JOptionPane.showMessageDialog(null,
                    "Each course can have only 9 holes!",
                    "9 holes required",
                    JOptionPane.PLAIN_MESSAGE);
            addScores();
            addHolesFrame.setVisible(false);
        }
    }

    //EFFECTS: Checks if the hole is valid and can be added to the list of holes.
    //         Catches NotEnoughHolesException and MaxHolesReachedException and displays user feedback in a JOptionPane
    private void validGolfHole() {
        try {
            golfPro.course.nineHoles(golfPro.course);
        } catch (NotEnoughHolesException notEnoughHolesException) {
            JOptionPane.showMessageDialog(null,
                    "Each course should have exactly 9 holes! \n add " + (9 - holes.size())
                            + " more holes to this course",
                    "9 holes required",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (MaxHolesReachedException maxHolesReachedException) {
            JOptionPane.showMessageDialog(null,
                    "Each course can have only 9 holes!",
                    "9 holes required",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    //MODIFIES: golfPro
    //EFFECTS:Resets the golfCourse and removes all the holes;
    private void courseReset() {
        if (loaded) {
            if (!done) {
                golfPro.course.holes.clear();
                done = true;
            }
        }
    }


    //MODIFIES: addHolesFrame
    //EFFECTS: adds required swing components to the addHolesFrame
    private void addHole() {
        textField2.setPreferredSize(new Dimension(250,40));
        textField3.setPreferredSize(new Dimension(250,40));
        textField4.setPreferredSize(new Dimension(250,40));
        addHolesFrame.setLayout(new FlowLayout());
        addHolesFrame.setSize(300,300);
        submit1.setPreferredSize(new Dimension(70,30));
        addHolesFrame.add(label);
        addHolesFrame.add(textField2);
        addHolesFrame.add(label2);
        addHolesFrame.add(textField3);
        addHolesFrame.add(label3);
        addHolesFrame.add(textField4);
        addHolesFrame.add(submit1);
    }


    /////////////////////////////////////////////ADD COURSE//////////////////////////////////////////////////////////

    //MODIFIES: addCourseFrame
    //EFFECTS: adds an action listener to the submit button and processes user input to create a GolfCourse
    private void makeAddCourseFrame() {
        helperForMaCf();
        submit.addActionListener(
                e -> {
                    if (e.getSource() == submit) {
                        try {
                            golfPro.addGolfCourse(returnNm(), returnPr());
                            System.out.println("Successfully added name!");
                            joppProcessing();
                            addCourseFrame.setVisible(false);
                        } catch (InvalidCourseException | NumberFormatException exception) {
                            System.out.println("Invalid course!");
                            JOptionPane.showMessageDialog(null,"Please enter a valid Golf Course",
                                    "Course Invalid!", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
        );
        addCourse();
    }

    //EFFECTS: creates a JOptionPane to ask the user if they want to add holes to the course and processes
    // input accordingly
    private void joppProcessing() {
        int i = JOptionPane.showConfirmDialog(null,
                "Do you want to add holes to this course?",
                "Add Holes?",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,joppImg);
        if (i == 0) {
            setVisible(false);
            makeAddHolesFrame();
            alreadyDone = false;

            addHolesFrame.setVisible(true);
        } else if (i == 1 || i == -1) {
            setVisible(true);
        }
    }

    //MODIFIES: addCourseFrame
    //EFFECTS: Adds required swing components to the addCourseFrame
    private void helperForMaCf() {
        textField.setPreferredSize(new Dimension(250,40));
        textField1.setPreferredSize(new Dimension(250,40));
        addCourseFrame.setLayout(new FlowLayout());
        addCourseFrame.setSize(300,200);
        submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(70,30));
        addCourseFrame.add(submit);
    }


    //MODIFIES: addCourseFrame
    //EFFECTS: Adds required swing components to the addCourseFrame
    private void addCourse() {
        addCourseFrame.add(new JLabel("What is the name of the golf course?"));
        addCourseFrame.add(textField);
        addCourseFrame.add(new JLabel("What is par at this course?"));
        addCourseFrame.add(textField1);
        addCourseFrame.add(submit);
    }

    /////////////////////////////////////////////MAIN MENU//////////////////////////////////////////////////////////

    //MODIFIES: this
    //EFFECTS: Creates a main-menu with buttons for the user to click
    private void initButtons() {
        viewGolfCourse = new JButton("View Golf Course");
        customizeButtons(viewGolfCourse);
        addGolfCourse = new JButton("Add Golf Course");
        customizeButtons(addGolfCourse);
        calculateCourseHandicap = new JButton("Calculate course handicap");
        customizeButtons(calculateCourseHandicap);
        saveToFile = new JButton("Save to file");
        customizeButtons(saveToFile);
        loadFile = new JButton("Load from file");
        customizeButtons(loadFile);
        quitApplication = new JButton("Quit Application");
        customizeButtons(quitApplication);
    }

    //MODIFIES: boxPanel, button
    //EFFECTS: customizes the parameter button and adds it to the boxPanel
    private void customizeButtons(JButton button) {
        button.setPreferredSize(new Dimension(70, 70));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        boxPanel.add(button);
    }

    /////////////////////////////////////////////HELPER FUNCTIONS//////////////////////////////////////////////////////

    private void clearTextField() {
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
    }

    private void makeMainP() {
        mainP.setLayout(new BorderLayout());
        mainP.add(mainMenu,BorderLayout.PAGE_START);
        mainP.add(boxPanel,BorderLayout.PAGE_END);
    }

    private void makeBoxPanel() {
        boxPanel.setLayout(boxlayout);
        boxPanel.setBorder(new EmptyBorder(150,100,-225,100));
        boxPanel.setBackground(Color.lightGray);
    }

    private void makeSubPanel() {
        subPanelMenu.setLayout(new BorderLayout());
        subPanelMenu.add(label1,BorderLayout.PAGE_START);
        subPanelMenu.add(welcomeLabel,BorderLayout.SOUTH);
        subPanelMenu.setBackground(Color.lightGray);
    }

    private void makeWelcomeLabel(ImageIcon golfBallGif) {
        welcomeLabel.setIconTextGap(10);
        welcomeLabel.setIcon(golfBallGif);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public int returnHn() {
        return Integer.parseInt(textField2.getText().trim());
    }

    public int returnD() {
        return Integer.parseInt(textField3.getText().trim());
    }

    public int returnP() {
        return Integer.parseInt(textField4.getText().trim());
    }


    public String returnNm() {
        return textField.getText();
    }

    public int returnPr() {
        return Integer.parseInt(textField1.getText().trim());
    }


    /////////////////////////////////////////////ACTION PERFORMED//////////////////////////////////////////////////////


    //EFFECTS: calls the method required to process users choice
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewGolfCourse) {
            viewCourse();
        } else if (e.getSource() == addGolfCourse) {
            setVisible(false);
            addCourseFrame.setVisible(true);
        } else if (e.getSource() == calculateCourseHandicap) {
            setVisible(false);
            makeHandicapFrame();
            addHandicapFrame.setVisible(true);
        } else if (e.getSource() == quitApplication) {
            JOptionPane.showMessageDialog(null,"Thank you for using GolfPro!","Quitting",
                    JOptionPane.PLAIN_MESSAGE,joppImg);
            System.exit(1);
        } else if (e.getSource() == saveToFile) {
            saveToFile();
        } else if (e.getSource() == loadFile) {
            loadFromFile();
        }

    }

    /////////////////////////////////////////////MAIN METHOD///////////////////////////////////////////////////

    //EFFECTS: Creates a new instance of a GUI and thus runs application
    public static void main(String[] args) throws FileNotFoundException, InvalidCourseParException,
            InvalidCourseNameException {
        new Gui();
    }


}
