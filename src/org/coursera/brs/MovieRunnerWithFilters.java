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
        System.out.println("Total number of movies " + ratings.size());
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t" + MovieDatabase.getTitle(current.getItem()));
        }
    }
}
