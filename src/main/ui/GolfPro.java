package ui;

import exceptions.InvalidCourseNameException;
import exceptions.InvalidCourseParException;
import model.GolfCourse;
import model.GolfHole;
import persistence.Reader;
import persistence.Writer;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

//GolfPro Application
public class GolfPro {
    GolfCourse course;
    private Scanner sc;
    private Writer writer;
    private Reader reader;
    //The final fields below were borrowed from stackoverflow to change the color of printed text
    // Additionally, this is also where I learnt how to use the System.exit command
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";


    //Effects: Runs the GolfPro application
    public GolfPro() throws FileNotFoundException, InvalidCourseParException, InvalidCourseNameException {
        sc = new Scanner(System.in);
        course = new GolfCourse("-",1);
        writer = new Writer("./data/GolfCourse.json");
        reader = new Reader("./data/GolfCourse.json");
       // runGolfPro();
    }

    //MODIFIES: this
    //REQUIRES: the number inputted has to be in the range[1,6]
    //EFFECTS: processes input given by the user
    private void runGolfPro() throws InvalidCourseParException, InvalidCourseNameException {
        System.out.println(ANSI_YELLOW + "Welcome to GolfPro" + ANSI_RESET);
        boolean stop = false;
        showOptions();
        while (!stop) {
            int runCommand = sc.nextInt();
            if (runCommand == 6) {
                exitGolfPro();
            } else if (runCommand == 1) {
                viewGolfCourse();
                stop = true;
            } else if (runCommand == 2) {
                addGolfCourse("",1);
                stop = true;
            } else if (runCommand == 3) {
                calculateCourseHandicap();
                stop = true;
            } else if (runCommand == 4) {
                saveCourse();
            } else if (runCommand == 5) {
                loadCourse();
            }
        }
    }


    //EFFECTS: exits the application when called
    public void exitGolfPro() {
        System.out.println(ANSI_YELLOW + "Thank you for using GolfPro!" + ANSI_RESET);
        System.exit(1);
    }

    //EFFECTS: Prints out the main-menu for the application
    private void showOptions() {
        System.out.println("1) View Golf Course");
        System.out.println("2) Add a Golf Course");
        System.out.println("3) Calculate a course handicap");
        System.out.println("4) Save to file");
        System.out.println("5) Load from file");
        System.out.println("6) Quit application");
    }

    //EFFECTS: Prints out the entire GolfCourse object
    public String viewGolfCourse() {
        if ((course.getName().equals("-")) && (course.getPar() == 1)) {
            return ("Please add or load a golf course first");
            //runGolfPro();
        } else {
            return (course.makeString());
            //returnToMainMenuOrNo();
        }
    }

    //REQUIRES: Either 9 holes or 18 holes be added to the course
    //MODIFIES: this
    //EFFECTS: Creates a new instance of a golf course
    public void addGolfCourse(String nm, int pr) throws InvalidCourseNameException, InvalidCourseParException {
//        System.out.print("What is the name of the Golf Course? ");
//        addName();
        course.addName(nm);
//        System.out.println("\nWhat is par at this course?");
//        //int pr = sc.nextInt();
        course.addPar(pr);
//        addHolesOrNot();
//        addScoresOrNot();
    }

    //MODIFIES: this
    //EFFECTS: adds a new GolfHole object to the list of holes attributed to the GolfCourse
    public void addH(int hn, int dn, int pr) {
        //System.out.println("What is the hole number?");
        //int hn = sc.nextInt();
        //System.out.println("What is the distance to the hole?");
        //int dn = sc.nextInt();
        //System.out.println("What is par at this hole?");
        //int pr = sc.nextInt();
        this.course.addHole(new GolfHole(hn,dn,pr));
       // saveCourse();
        //System.out.println(ANSI_YELLOW + "The hole was successfully added!" + ANSI_RESET);
        //addAnotherHoleOrNo();
    }

    //MODIFIES: this
    //EFFECTS: calculates handicap for a specific golf course.
    private void calculateCourseHandicap() throws InvalidCourseParException, InvalidCourseNameException {
        System.out.println("What is your current handicap index?");
        int hi = sc.nextInt();
        System.out.println("What is the slope rating at your course?");
        int sr = sc.nextInt();
        System.out.println("What is the rating of your course?");
        int cr = sc.nextInt();
        System.out.println("What is par at this course?");
        int pr = sc.nextInt();
        System.out.println("Your handicap for the course is " + (hi * (sr / 113) + (cr - pr)));
        System.out.println("Would you like to return to the main menu?" + "\n1) Yes" + "\n2) No, quit application");
        int qon = sc.nextInt();
        if (qon == 1) {
            runGolfPro();
        } else if (qon == 2) {
            System.out.println(ANSI_YELLOW + "Thank you for using ProGolf!" + ANSI_YELLOW);
            System.exit(1);
        }
    }

    //REQUIRES: number of shots taken on a hole has to be >= 0
    //MODIFIES: this
    //EFFECTS: Adds the total shots taken on each hole created.
    private void addScores() throws InvalidCourseParException, InvalidCourseNameException {
        int count = 0;
        int ch = 0;
        for (GolfHole h : course.holes) {
            System.out.println("How many shots did you take at " + "Hole number " + h.getHoleNumber());
            int rp = sc.nextInt();
            count = count + rp;
            ch = ch + 1;
        }
        System.out.println("You score was " + count);
        if (count > course.getPar()) {
            System.out.println("You played " + ch + " holes today and were " + (count - course.getPar()) + " over par");
        } else if (count == course.getPar()) {
            System.out.println("Great game! You played " + ch + " holes today and averaged par on every hole.");
        } else if (count < course.getPar()) {
            System.out.println("\nAmazing! You played " + ch + " holes today and were "
                    + (course.getPar() - count) + " under par");
        }
        returnToMainMenuOrNo();
    }

    //EFFECTS: Calls method addH if user wants to add holes to the course.
    //         If not, returns to the main menu
    private void addHolesOrNot() throws InvalidCourseParException, InvalidCourseNameException {
        System.out.println("Would you like to add holes to this course?" + "\n1) Yes" + "\n2) No");
        int rp = sc.nextInt();
        if (rp == 1) {
            System.out.println("");
            //addH();
        } else if (rp == 2) {
            runGolfPro();
        } else {
            System.out.println(ANSI_RED + "Please enter a valid numeric response" + ANSI_RESET);
            addHolesOrNot();
        }

    }

    //EFFECTS: Calls method addScores if user wants to add scores.
    //         If not, returns to the main menu
    private void addScoresOrNot() throws InvalidCourseParException, InvalidCourseNameException {
        System.out.println("Would you like to add your scores in ?" + "\n1) Yes" + "\n2) No");
        int rp1 = sc.nextInt();
        if (rp1 == 1) {
            addScores();
            System.exit(1);
        } else if (rp1 == 2) {
            runGolfPro();
        } else {
            System.out.println(ANSI_RED + "Please enter a valid response" + ANSI_RESET);
            addScoresOrNot();
        }
    }

    //EFFECTS: Calls method addH if user wants to add another hole
    private void addAnotherHoleOrNo() throws InvalidCourseParException, InvalidCourseNameException {
        System.out.println("Would you like to add another hole?" + "\n1) Yes" + "\n2) No");
        int rp = sc.nextInt();
        if (rp == 1) {
            //addH();
            System.out.println("");
        } else if (rp == 2) {
            addScoresOrNot();
        } else {
            System.out.println(ANSI_RED + "Please enter a valid numeric response" + ANSI_RESET);
            addAnotherHoleOrNo();
        }
    }

    //EFFECTS: Returns user to the main-menu if if they choose to
    private void returnToMainMenuOrNo() throws InvalidCourseParException, InvalidCourseNameException {
        System.out.println("Would you like to return to the main menu?" + "\n1) Yes" + "\n2) No, quit");
        int rm = sc.nextInt();
        if (rm == 1) {
            runGolfPro();
        } else if (rm == 2) {
            System.out.println(ANSI_YELLOW + "Thank you for using GolfPro!" + ANSI_RESET);
            System.exit(1);
        } else {
            System.out.println(ANSI_RED + "Please enter a valid numeric response" + ANSI_RESET);
            returnToMainMenuOrNo();
        }
    }

    //EFFECTS: Accepts name of course from user
    private String addName() {
        return sc.nextLine();
    }

    //EFFECTS: saves the GolfCourse as a Json file
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void saveCourse() {
        try {
            writer.open();
            writer.write(course);
            writer.close();
            System.out.println(ANSI_GREEN + "Successfully saved " + course.getName() + ANSI_RESET);
           // runGolfPro();
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED + "Sorry, unable to write to file:" + ANSI_RESET);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the GolfCourse from the GolfCourse.json file
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void loadCourse() {
        try {
            course = reader.read();
            System.out.println(ANSI_GREEN + "Successfully Loaded " + course.getName() + ANSI_RESET);
            //runGolfPro();
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Unable to read from file" + ANSI_RESET);
        } catch (InvalidCourseNameException e) {
            System.out.println("Invalid course name");
        } catch (InvalidCourseParException e) {
            System.out.println("Invalid course par value");
        }
    }


}