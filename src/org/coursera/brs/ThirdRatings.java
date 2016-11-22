package org.coursera.brs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ThirdRatings
 *
 * @author Igor Prus
 * @version Nov 13/16
 *
 *
 */

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        this("data\\ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsFile);
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
        for (EfficientRater rater: myRaters){
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

    /**
     * This method finding the average rating for every movie that has been rated
     * by at least minimalRaters raters. Store each such rating in a Rating object in
     * which the movie ID and the average rating are used in creating the Rating object
     *
     * @param minimalRaters representing minimal number of rating for the certain movie.
     * @return an ArrayList of all the Rating objects for movies that have at least
     * the minimal number of raters supplying a rating.
     * */
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> result= new ArrayList<>();
        ArrayList<String> movieIdRated = new ArrayList<>();
        //Adding all ratings made by each rater to movieIdRated
        for (EfficientRater current : myRaters) {
            movieIdRated.addAll(current.getItemsRated());
        }
        //To store frequency of how many those movies in movieIdRated
        Map<String, Integer> moviesFrequencyMap = new HashMap<>();
        for (String movie: movieIdRated){
            moviesFrequencyMap.put(movie, Collections.frequency(movieIdRated, movie));
        }
        //minimalRatersMovie list contains movieIDs with number of raters >= minimalRaters
        ArrayList<String> minimalRatersMoviesList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: moviesFrequencyMap.entrySet()){
            if (entry.getValue() >= minimalRaters){
                minimalRatersMoviesList.add(entry.getKey());
            }
        }
        //Creating new Rating object from movieID and getAverageByID method
        //and storing them in result
        for (String movie: minimalRatersMoviesList){
            result.add(new Rating(movie, getAverageByID(movie, minimalRaters)));
        }
        return result;
    }

    /**
     * This method should create and return an ArrayList of type Rating of all
     * the movies that have at least minimalRaters ratings and satisfies the filter criteria.
     * */
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<Rating> avrRating = new ArrayList<>();
        avrRating = getAverageRatings(minimalRaters);
        for (Rating rating : avrRating) {
            if (filterCriteria.satisfies(rating.getItem())){
                result.add(rating);
            }
        }

        return result;
    }

}
