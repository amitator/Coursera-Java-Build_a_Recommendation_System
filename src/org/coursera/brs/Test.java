package org.coursera.brs;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
//        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.testLoadMovies();
//        firstRatings.testLoadRaters();
//        MovieRunnerAverage runner = new MovieRunnerAverage();
//        runner.printAverageRatings();
        SecondRatings secondRatings = new SecondRatings();
        ArrayList<Rating> temp = new ArrayList<>();
        temp = secondRatings.getAverageRatings(40);
        for (Rating rating: temp){
            System.out.println(rating);
        }
        System.out.println("\nSize: " + temp.size()+ "\n");
    }
}
