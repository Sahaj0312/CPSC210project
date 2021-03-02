package persistence;

import exceptions.InvalidCourseNameException;
import exceptions.InvalidCourseParException;
import model.GolfCourse;
import model.GolfHole;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//this class represents a reader that outputs the GolfCourse stored in Json format in the destination file
public class Reader {
    private String source;

    //EFFECTS: constructs a reader object to read from the source file
    public Reader(String source) {
        this.source = source;
    }

    //EFFECTS: Makes the destination file a String and returns it
    //CITATION: This method was made in accordance with the sample provided from TA Eric Newton:
    //https://github.com/e-newton/CPSC210-Project-Demo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    //EFFECTS: reads the GolfCourse and returns it. Throws IOException if error in data reading
    //CITATION: This method was made in accordance with the sample provided from TA Eric Newton:
    //https://github.com/e-newton/CPSC210-Project-Demo
    public GolfCourse read() throws IOException, InvalidCourseParException, InvalidCourseNameException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGolfCourse(jsonObject);
    }


    //EFFECTS: construes a GolfCourse from the stored Json and then returns it
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private GolfCourse parseGolfCourse(JSONObject jsonObject) throws InvalidCourseParException,
            InvalidCourseNameException {
        String name = jsonObject.getString("name");
        int par = jsonObject.getInt("par");
        GolfCourse gc = new GolfCourse(name, par);
        addHoles(gc, jsonObject);
        return gc;
    }

    //MODIFIES: gc
    //EFFECTS: construes the GolfHoles from the Json object and adds it to the course
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addHoles(GolfCourse gc, JSONObject jsonObject) {
        JSONArray holesArray = jsonObject.getJSONArray("holes");
        for (Object h : holesArray) {
            JSONObject nextHole = (JSONObject) h;
            addHole(gc, nextHole);
        }
    }


    //MODIFIES: gc
    //EFFECTS: construes a single GolfHole from the Json object and adds it to the list of holes.
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addHole(GolfCourse gc, JSONObject jsonObject) {
        int holeNumber = jsonObject.getInt("holeNumber");
        int par = jsonObject.getInt("par");
        int distance = jsonObject.getInt("distance");
        GolfHole hole = new GolfHole(holeNumber, par, distance);
        gc.addHole(hole);
    }


}