package model;


import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

import java.util.ArrayList;
//This class tests methods in the GolfCourse class

class GolfCourseTest {
     GolfCourse testCourse;
     private String name = "Els";
     private int par = 72;
     GolfHole testHole = new GolfHole(1, 4, 300);
     GolfHole testHole1 = new GolfHole(2, 5, 500);
     GolfHole testHole2 = new GolfHole(3, 3, 190);
     GolfHole testHole3 = new GolfHole(4, 4, 450);
     List<GolfHole> testHoles = new ArrayList<GolfHole>();


     @BeforeEach
     public void setup() throws InvalidCourseParException, InvalidCourseNameException {
         testCourse = new GolfCourse(name, par);
         testCourse.holes = new ArrayList<GolfHole>();
     }

     @Test
    public void testConstructorExceptionNotExpected() {
         assertEquals(testCourse.getName(), "Els");
         assertEquals(testCourse.getPar(), 72);
         try {
             GolfCourse golfCourse = new GolfCourse("St.Andrews",72);
         } catch (InvalidCourseException courseException) {
             fail("Exception should not have been thrown!");
         }
     }

     @Test
     public void testConstructorExceptionExpectedInvalidNameAndPar() {
         try {
             GolfCourse golfCourse = new GolfCourse("", 0);
             fail("Should not have reached this line");
         } catch (InvalidCourseException courseException) {
             //This is the expected outcome
         }
     }

     @Test
    public void testConstructorExceptionExpectedParIsNegative() {
         try {
             GolfCourse gct = new GolfCourse("Hello", -1);
         } catch (InvalidCourseNameException invalidCourseNameException) {
             fail("Should not have caught this exception!");
         } catch (InvalidCourseParException invalidCourseParException) {
             //This is the expected outcome
         }
     }

     @Test
    public void testAddHoleOneTime() {
         testCourse.addHole(testHole);
         assertTrue(testCourse.holes.contains(testHole));
     }

     @Test
    public void testAddHoleMultipleTimes() {
         testHoles.add(testHole);
         testHoles.add(testHole1);
         testHoles.add(testHole2);
         testHoles.add(testHole3);

         for (GolfHole h : testHoles) {
             testCourse.addHole(h);
         }

         assertTrue(testCourse.holes.contains(testHole));
         assertTrue(testCourse.holes.contains(testHole1));
         assertTrue(testCourse.holes.contains(testHole2));
         assertTrue(testCourse.holes.contains(testHole3));
     }

     @Test
    public void testMakeStringNoHoles() {
         assertEquals(testCourse.makeString(), "The Els Golf Course has the following holes:");
     }

     @Test
    public void testMakeStringMultipleHoles() {
         testCourse.addHole(testHole);
         testCourse.addHole(testHole1);
         testCourse.addHole(testHole2);
         testCourse.addHole(testHole3);

         assertEquals(testCourse.makeString(), "The Els Golf Course has the following holes:" +
                 "\nHole 1: Distance- 300 Par- 4" +
                 "\nHole 2: Distance- 500 Par- 5" +
                 "\nHole 3: Distance- 190 Par- 3" +
                 "\nHole 4: Distance- 450 Par- 4"
         );
     }

     @Test
    public void testAddNameNoExceptionExpected()  {
         try {
             testCourse.addName("Arabian Ranches");
             assertEquals(testCourse.name,"Arabian Ranches");
         } catch (InvalidCourseNameException e) {
             fail("Exception should not have been thrown!");
         }

     }

    @Test
    public void testAddNameExceptionExpected()  {
        try {
            testCourse.addName("");
            fail("Should not have reached this line!");
        } catch (InvalidCourseNameException e) {
            //This is the expected outcome
        }

    }

     @Test
    public void testAddParNoExceptionExpected() {
         try {
             testCourse.addPar(75);
             assertEquals(testCourse.par, 75);
         } catch (InvalidCourseParException e) {
             fail("Exception should not have been thrown!");
         }
     }

    @Test
    public void testAddParExceptionExpected() {
        try {
            testCourse.addPar(0);
            fail("Should not have reached this line!");
        } catch (InvalidCourseParException e) {
            //This is the expected outcome
        }
    }

     @Test
    public void testNineHolesMaxHolesReachedExpected() {
         int count = 0;
         while (count < 9) {
             testCourse.addHole(testHole1);
             count ++;
         }
         System.out.println(testCourse.holes.size());
         try {
             testCourse.nineHoles(testCourse);
         } catch (NotEnoughHolesException enoughHolesException) {
             fail("This exception should not have been thrown");
         } catch (MaxHolesReachedException maxHolesReachedException) {
             //This is the expected outcome
         }
     }


     @Test
    public void testNineHolesExceptionNotExpected() throws InvalidCourseParException, InvalidCourseNameException {
         GolfCourse gc = new GolfCourse("Hello",1);
         gc.addHole(testHole1);
         System.out.println(gc.holes.size());
         try {
             gc.nineHoles(gc);
         } catch (MaxHolesReachedException e) {
                  fail("Shouldn't have thrown exception!"); //Exception should not have been thrown
         } catch (NotEnoughHolesException enoughHolesException) {
             //This is the expected outcome
         }
     }


     }
