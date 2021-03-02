package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//This class tests methods in the GolfHole class

public class GolfHoleTest {
    GolfHole testHole;
    int holeNumber = 1;
    int distance = 300;
    int par = 4;

    @BeforeEach
    public void setup() {
        testHole = new GolfHole(holeNumber,par,distance);
    }

    @Test
    public void testConstructor() {
        assertEquals(testHole.getHoleNumber(), holeNumber );
        assertEquals(testHole.getDistance(), distance);
        assertEquals(testHole.getPar(), par);
    }


}
