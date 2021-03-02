package persistence;

import exceptions.InvalidCourseNameException;
import exceptions.InvalidCourseParException;
import model.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//this class tests the functions of the Json Reader
public class ReaderTest {
    public Reader reader;


     public void makeReader(String s) {
         reader = new Reader(s);
     }


    @Test
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testReaderWhenThereIsNoFile() throws InvalidCourseParException, InvalidCourseNameException {
        makeReader("./data/doesNotExist.json");
        try {
            GolfCourse gc = reader.read();
            fail("Should have thrown IOException");
        } catch(IOException e) {
            //this is the expected outcome
        }
    }

    @Test
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testReaderWhenGolfCourseIsAsExpected() throws InvalidCourseParException, InvalidCourseNameException {
         makeReader("./data/GolfCourseIsAsExpected.json");
         try {
             GolfCourse gc = reader.read();
             List<GolfHole> golfHoles = gc.getHoles();
             assertEquals("St. Andrews",gc.getName());
             assertEquals(4, gc.getSize());
             assertEquals(72, gc.getPar());
         } catch (IOException e) {
             fail("Exception should not have been caught");
         }
    }

    @Test
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testReaderWhenGolfCourseIsEmpty() throws InvalidCourseParException, InvalidCourseNameException {
         makeReader("./data/EmptyGolfCourse.json");
         try {
             GolfCourse gc = reader.read();
             List<GolfHole> golfHoles = gc.getHoles();
             assertEquals("-", gc.getName());
             assertEquals(0, gc.getSize());
             assertEquals(1, gc.getPar());
         } catch (IOException e) {
             fail("Exception should not have been caught");
         }
    }


}
