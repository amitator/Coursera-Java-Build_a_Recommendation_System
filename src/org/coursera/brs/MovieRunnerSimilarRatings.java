package org.coursera.brs;

import java.util.ArrayList;
import java.util.Collections;

/**
 * MovieRunnerSimilarRatings
 *
 * @author Igor Prus
 * @version Nov 13/16
 *
 */

public class MovieRunnerSimilarRatings {
    private FourthRatings fourthRatings;

    public MovieRunnerSimilarRatings(){
        MovieDatabase.initialize("ratedmovies.csv");
        RaterDatabase.initialize("data\\ratings.csv");
        System.out.println("\nNumber of movies: " + MovieDatabase.size()
                + "\t" + "Number of ratings: " + RaterDatabase.size() + "\n");
    }

    /**
     * This method prints amount of movies and raitings loaded. Also prints average rating and
     * title for those movies that have at least a specified number of ratings
     */
    public void printAverageRatings(){
        System.out.println("===================================");
        System.out.println("\t\tprintAverageRatings()");
        System.out.println("===================================");
        //For all those movies that have at least a specified number of ratings
        int minNumRatings = 35;
        ArrayList<Rating> ratings = fourthRatings.getAverageRatings(minNumRatings);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings");
        System.out.println("Total number of movies " + ratings.size() + "\n");
//        for (Rating current: ratings){
//            System.out.println(current.getValue() + "\t" + MovieDatabase.getTitle(current.getItem()));
//        }
    }

    /**
     * This method calls getAverageRatingsByFilter() to get an ArrayList of type Rating
     * of all the movies that have a specified number of minimal ratings and the two criteria based
     * on year and genre. Print the number of movies found, and for each movie, print its rating, its year,
     * and its title on one line, and all its genres on the next line
     */
    public void printAverageRatingsByYearAfterAndGenre(){
        System.out.println("===================================");
        System.out.println("printAverageRatingsByYearAfterAndGenre() ");
        System.out.println("===================================");
        AllFilters allFilters = new AllFilters();
        //Initializing filters
        int minNumRatings = 8;
        int year = 1990;
        String genre = "Drama";
        YearAfterFilter yearAfterFilter = new YearAfterFilter(year);
        GenreFilter genreFilter = new GenreFilter(genre);
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);
        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(minNumRatings, allFilters);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings of "
                + genre + " genre, made on or after "
                + year);
        System.out.println("Total number of movies " + ratings.size() + "\n");
//        for (Rating current: ratings){
//            System.out.println(current.getValue() + "\t"
//                    + MovieDatabase.getYear(current.getItem()) + "\t"
//                    + MovieDatabase.getTitle(current.getItem()));
//            System.out.println("\t\t\t" + MovieDatabase.getGenres(current.getItem()));
//        }
    }
}
