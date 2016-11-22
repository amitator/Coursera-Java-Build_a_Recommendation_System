package org.coursera.brs;

import java.util.ArrayList;
import java.util.Collections;

/**
 * AllFilters
 *
 * @author Igor Prus
 * @version Nov 13/16
 *
 *
 */

public class MovieRunnerWithFilters {
    /**
     * This method prints amount of movies and raitings loaded. Also prints average rating and
     * title for those movies that have at least a specified number of ratings
     */
    public void printAverageRatings(){
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("\n===================================");
        System.out.println("\t\t\tprintAverageRatings()");
        System.out.println("===================================");
        ThirdRatings thirdRatings = new ThirdRatings("data\\ratings_short.csv");
        System.out.println("\nNumber of movies: " + MovieDatabase.size()
                + "\t" + "Number of ratings: " + thirdRatings.getRaterSize() + "\n");
        //For all those movies that have at least a specified number of ratings
        int minNumRatings = 1;
        ArrayList<Rating> ratings = thirdRatings.getAverageRatings(minNumRatings);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings");
        System.out.println("Total number of movies " + ratings.size() + "\n");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t" + MovieDatabase.getTitle(current.getItem()));
        }
    }

    /**
     * This method prints all the movies that have a specified number of minimal ratings and came out in
     * a specified year or later. Print the number of movies found, and for each movie found,
     * print its rating, its year, and its title*/
    public void printAverageRatingsByYear(){
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("\n===================================");
        System.out.println("\t\t\tprintAverageRatingsByYear()");
        System.out.println("===================================");
        ThirdRatings thirdRatings = new ThirdRatings("data\\ratings_short.csv");
        System.out.println("\nNumber of movies: " + MovieDatabase.size()
                + "\t" + "Number of ratings: " + thirdRatings.getRaterSize() + "\n");
        //For all those movies that have at least a specified number of ratings
        int minNumRatings = 1;
        int yearAfter = 1990;
        YearAfterFilter filter = new YearAfterFilter(yearAfter);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minNumRatings, filter);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings and came out after "
                            + yearAfter);
        System.out.println("Total number of movies " + ratings.size() + "\n");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t"
                                + MovieDatabase.getYear(current.getItem()) + "\t"
                                + MovieDatabase.getTitle(current.getItem()));
        }
    }
}
