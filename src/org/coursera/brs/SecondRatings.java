package org.coursera.brs;

/**
 * SecondRatings
 *
 * @author Igor Prus
 * @version Nov 10/16
 *
 *
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmovies.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    /**
     * This method returns a double representing the average movie rating for this ID
     * if there are at least minimalRaters ratings. If there are not minimalRaters
     * ratings, then it returns 0.0.
     *
     * @param movieID  representing a movie ID.
     * @param minimalRaters representing minimal number of rating for the certain movie.
     * @return average of ratings if there is minimal Rates rated movie
     * or 0.0 it there is not.
     */
    public double getAverageByID(String movieID, int minimalRaters){
        int ratersCounter = 0;
        double totalRatings = 0.0;
        for (Rater rater: myRaters){
            //If rater has rating for movieID movie then ++counter and sum rating to rating counter
            if (rater.hasRating(movieID)){
                ratersCounter++;
                totalRatings+=rater.getRating(movieID);
            }
        }
        if (ratersCounter >= minimalRaters){
            return totalRatings / (double) ratersCounter;
        }
        return 0.0;
    }


}


























