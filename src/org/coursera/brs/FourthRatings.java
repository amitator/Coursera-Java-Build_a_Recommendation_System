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
 */

public class FourthRatings {

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
