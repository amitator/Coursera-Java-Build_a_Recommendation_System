package org.coursera.brs;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Movie
 *
 * @author Igor Prus
 * @version Nov 10/16
 */

public class MovieRunnerAverage {
    /**
     * This method prints amount of movies and raitings loaded. Also prints average rating and
     * title for those movies that have at least a specified number of ratings
     */
    public void printAverageRatings(){
        System.out.println("\n===================================");
        System.out.println("\t\t\tprintAverageRatings()");
        System.out.println("===================================");
        SecondRatings secondRatings = new SecondRatings("data\\ratedmovies.csv", "data\\ratings.csv");
        System.out.println("\nNumber of movies: " + secondRatings.getMovieSize()
                + "\t" + "Number of ratings: " + secondRatings.getRaterSize() + "\n");
        //For all those movies that have at least a specified number of ratings
        int minNumRatings = 30;
        ArrayList<Rating> ratings = secondRatings.getAverageRatings(minNumRatings);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t" + secondRatings.getTitle(current.getItem()));
        }
    }

    /**
     * This method first creates a SecondRatings object, reading in data from the movie and
     * ratings data files. Then this method prints out the average ratings for a specific movie title
     */
    public void getAverageRatingOneMovie(){
        System.out.println("\n===================================");
        System.out.println("\t\t\tprintAverageRatings()");
        System.out.println("===================================");
        SecondRatings secondRatings = new SecondRatings("data\\ratedmovies.csv", "data\\ratings.csv");
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(1);
        //Movie title to find
        String title = "The Godfather";
        for (Rating rating: averageRatings){
            if (rating.getItem().equals(secondRatings.getID(title))){
                System.out.println(title + " has average rating " + rating.getValue());
            }
        }
    }
}













