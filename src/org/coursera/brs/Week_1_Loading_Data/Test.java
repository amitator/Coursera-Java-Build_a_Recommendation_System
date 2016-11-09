package org.coursera.brs.Week_1_Loading_Data;

import java.util.ArrayList;


public class Test {
    public static void main(String[] args) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> test = fr.loadMovies("data\\ratedmovies_short.csv");
    }
}
