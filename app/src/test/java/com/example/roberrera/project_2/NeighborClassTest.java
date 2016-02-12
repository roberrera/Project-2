package com.example.roberrera.project_2;

import org.junit.Test;

import Classes.Neighborhood;

import static org.junit.Assert.*;

public class NeighborClassTest {

    Neighborhood neighborhood = new Neighborhood(1, "Starbucks", "Sample Desc", "123 Coffee St", 0);

    @Test
    public void getDrawableImageTest() {

        neighborhood.getDrawableValue("Starbucks");

        int expectedResult = R.drawable.starbucks;
        int actualValue = Neighborhood.getDrawableValue("Starbucks");

        assertEquals(expectedResult, actualValue);
    }
}