package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns input as a Json object
    //CITATION: This logic behind using this interface is from the sample:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    JSONObject toJson();
}
