package persistence;

import exceptions.InvalidCourseNameException;
import exceptions.InvalidCourseParException;
import model.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//this class tests the functions of the Json Writer
public class WriterTest {
    public Writer writer;

    public void makeWriter(String s) {
        writer = new Writer(s);
    }

    @Test
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testWriterWhenThereIsNoFile() throws InvalidCourseParException, InvalidCourseNameException {
        try {
            GolfCourse gc = new GolfCourse("Hello",1);
            makeWriter("./dfrgr/\011Inval1d:N.");
            writer.open();
            fail("IOException was expected to have been thrown");
        } catch (IOException e) {
            //this is the expected outcome
        }
    }


    @Test
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testWriterGolfCourseAsExpected() throws InvalidCourseParException, InvalidCourseNameException {
        try {
            GolfCourse gc = new GolfCourse("St. Andrews", 72);
            gc.addHole(new GolfHole(2,3,150));
            gc.addHole(new GolfHole(3,4,450));
            makeWriter("./data/testWriterCourseAsExpected.json");
            writer.open();
            writer.write(gc);
            writer.close();

            Reader reader = new Reader("./data/testWriterCourseAsExpected.json");
            gc = reader.read();
            assertEquals("St. Andrews", gc.getName());
            List<GolfHole> holes = gc.getHoles();
            assertEquals(2, gc.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testWriterEmptyGolfCourse() throws InvalidCourseParException, InvalidCourseNameException {
        try {
            GolfCourse gc = new GolfCourse("-", 1);
            makeWriter("./data/testWriterEmptyGolfCourse.json");
            writer.open();
            writer.write(gc);
            writer.close();

            Reader reader = new Reader("./data/testWriterEmptyGolfCourse.json");
            gc = reader.read();
            assertEquals("-", gc.getName());
            assertEquals(0, gc.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}

