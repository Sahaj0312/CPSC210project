package model;


import org.json.JSONObject;
import persistence.Writable;

//This class represents a golf hole having a hole number, distance, and a par score.
public class GolfHole implements Writable {
    int holeNumber;
    int distance;
    int par;

    /*
    //Constructing a new instance of the GolfHole object
    REQUIRES: The hole number and par score have to be greater than 0
              The distance inputted has to be in yards
    EFFECTS: parameters inputted are assigned as attributes to a new golf hole object
     */
    public GolfHole(int holeNumber, int par, int distance) {
        this.holeNumber =  holeNumber;
        this.distance = distance;
        this.par = par;
    }


    public int getHoleNumber() {
        return holeNumber;
    }

    public int getDistance() {
        return distance;
    }

    public int getPar() {
        return par;
    }

    @Override
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("holeNumber", holeNumber);
        json.put("par", par);
        json.put("distance", distance);
        return json;
    }

}
