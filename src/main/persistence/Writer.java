package persistence;

import org.json.JSONObject;
import model.GolfCourse;
import java.io.*;

//this class represents a writer that creates a JSON representation of a GolfCourse
public class Writer {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs a writer to write to the destination file
    public Writer(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens the writer and throws the FileNotFoundException if file cannot be opened
    //CITATION: This method was made in accordance with the sample provided from TA Eric Newton at:
    //https://github.com/e-newton/CPSC210-Project-Demo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes a JSON representation of a GolfCourse to the destination file
    //CITATION: This method was made in accordance with the sample provided from TA Eric Newton at:
    //https://github.com/e-newton/CPSC210-Project-Demo
    public void write(GolfCourse gf) {
        JSONObject json = gf.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: shuts the writer down
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes the string to the destination file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
