package org.coursera.brs;

/**
 * Movie
 *
 * @author Igor Prus
 * @version Nov 10/16
 */

public class MovieRunnerAverage {
    public void printAverageRatings(){
        System.out.println("\n===================================");
        System.out.println("\t\t\tprintAverageRatings()");
        System.out.println("===================================");
        SecondRatings secondRatings = new SecondRatings("data\\ratedmovies.csv", "data\\ratings.csv");
        System.out.println("\nNumber of movies: " + secondRatings.getMovieSize()
                + "\t" + "Number of ratings: " + secondRatings.getRaterSize());
    }
}
