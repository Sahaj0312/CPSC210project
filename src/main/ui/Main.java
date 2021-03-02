package ui;
//This is the main class that calls GolfPro and starts the application

import exceptions.InvalidCourseNameException;
import exceptions.InvalidCourseParException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new GolfPro();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: File not found");
        } catch (InvalidCourseNameException e) {
            System.out.println("Invalid course name!");
        } catch (InvalidCourseParException e) {
            System.out.println("Invalid course par value!");
        }
    }
}
