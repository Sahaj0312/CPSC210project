package model;


import exceptions.InvalidCourseNameException;
import exceptions.InvalidCourseParException;
import exceptions.MaxHolesReachedException;
import exceptions.NotEnoughHolesException;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//This class represents a golf course having a name, par score and list of golf holes.
public class GolfCourse implements Writable {
    String name;
    int par;
    public List<GolfHole> holes;
    /*
    //Creating a new instance of the GolfCourse object
    EFFECTS:The parameters inputted are assigned as attributes of a new golf course object
     */

    public GolfCourse(String name, int par) throws InvalidCourseNameException, InvalidCourseParException {
        if (name.isEmpty()) {
            throw new InvalidCourseNameException();
        }
        if (par <= 0) {
            throw new InvalidCourseParException();
        }
        this.name = name;
        this.par = par;
        this.holes = new ArrayList<>();
    }

    //EFFECTS: throws the appropriate exception depending on the number of holes added to the course
    public void nineHoles(GolfCourse golfCourse) throws NotEnoughHolesException, MaxHolesReachedException {
        if (golfCourse.holes.size() == 9) {
            throw new MaxHolesReachedException();
        } else {
            throw new NotEnoughHolesException();
        }
    }

    //MODIFIES: GolfCourse.holes (this)
    //EFFECTS: Adds the inputted golf hole to a list
    //         of golf holes in the golf course object.
    public void addHole(GolfHole hole) {
        holes.add(hole);
    }

    public String getName() {
        return name;
    }

    public int getPar() {
        return par;
    }

    public List<GolfHole> getHoles() {
        return holes;
    }


    //MODIFIES: GolfCourse.name (this)
    //EFFECTS: Assigns the inputted name as the name of the golf course
    public void addName(String s) throws InvalidCourseNameException {
        if (s.equals("")) {
            throw new InvalidCourseNameException();
        }
        this.name = s;
    }

    //MODIFIES: GolfCourse.par (this)
    //EFFECTS: Assigns the inputted integer as the par score on the course
    public void addPar(int i) throws InvalidCourseParException {
        if (i == 0) {
            throw new InvalidCourseParException();
        }
        this.par = i;
    }

    public int getSize() {
        return holes.size();
    }


    //Converts the data held by GolfCourse into a String
    //EFFECTS: Concatenates different fields held by the object into one String
    public String makeString() {
        String st = "The " + name + " Golf Course has the following holes:";
        for (GolfHole h: holes) {
            st = st.concat("\n" + "Hole " + (h.holeNumber)
                    + ": " + "Distance- " + (h.distance) + " " + "Par- " + (h.par));
        }
        return st;
    }

    @Override
    //CITATION: The template for this method  was borrowed from the sample provided at:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("par", par);
        json.put("holes", holesToJson());
        return json;
    }

    //EFFECTS: returns the holes on this course as a Json array
    private JSONArray holesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GolfHole h : holes) {
            jsonArray.put(h.toJson());
        }
        return jsonArray;
    }

}
