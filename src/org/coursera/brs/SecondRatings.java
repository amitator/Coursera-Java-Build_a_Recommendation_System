package org.coursera.brs;

/**
 * Movie
 *
 * @author Igor Prus
 * @version Nov 10/16
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


























