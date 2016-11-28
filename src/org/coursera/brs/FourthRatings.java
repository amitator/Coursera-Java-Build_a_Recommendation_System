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
        ArrayList<Rater> myRaters = new ArrayList<>();
        myRaters = RaterDatabase.getRaters();
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
        ArrayList<Rater> myRaters = new ArrayList<>();
        myRaters = RaterDatabase.getRaters();
        for (Rater rater: myRaters){
            movieIdRated.addAll(rater.getItemsRated());
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
     *
     * @param minimalRaters representing minimal number of rating for the certain movie.
     * @param filterCriteria representing filter to use
     * @return  an ArrayList of all the Rating objects for movies that have at least
     * the minimal number of raters supplying a rating and meet Filter criteria.
     */
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

    /**
     *  This method translates a rating from the scale 0 to 10 to the scale -5 to 5
     *
     *  @param me
     *  @param rater
     *  @return the dot product of the ratings of movies that they both rated
     * */
    private double dotProduct(Rater me, Rater rater){
        double dotproduct = 0;
        ArrayList<String> movieItemList = me.getItemsRated();
        for (String id : movieItemList) {
            if (rater.hasRating(id)) {
                dotproduct += (me.getRating(id) - 5.) * (rater.getRating(id) - 5.);
            }
        }
        return dotproduct;
    }

    /**
     * this method computes a similarity rating for each rater in the RaterDatabase
     * (except the rater with the ID given by the parameter) to see how similar they are to
     * the Rater whose ID is the parameter to getSimilarities.
     *
     * @param id
     * @return an ArrayList of type Rating sorted by ratings from highest to lowest rating
     * with the highest rating first and only including those raters who have a positive similarity
     * rating since those with negative values are not similar in any way.
     * */
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> result = new ArrayList<Rating>();
        for (Rater rater : RaterDatabase.getRaters()) {
            if (!rater.getID().equals(id)) {
                double dotproduct = dotProduct(RaterDatabase.getRater(id), rater);
                if (dotproduct > 0) {
                    Rating rating = new Rating(rater.getID(), dotproduct);
                    result.add(rating);
                }
            }
        }
        Collections.sort(result, Collections.<Rating>reverseOrder());
        return result;
    }

    /**
     * This method returns an ArrayList of type Rating, of movies and their weighted average ratings
     * using only the top numSimilarRaters with positive ratings and including only those movies that
     * have at least minimalRaters ratings from those most similar raters (not just minimalRaters
     * ratings overall).
     */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<Rating> topRaters = new ArrayList<>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        for (int i = 0; i < numSimilarRaters; i++) {
            topRaters.add(similarRaters.get(i));
        }
        System.out.println("Top Raters size: " + topRaters.size());
        //Movie weight
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            double ratersCount = 0;
            double score = 0;
            for (Rating rater : topRaters) {
                Rater topRater = RaterDatabase.getRater(rater.getItem());
                if (topRater.hasRating(movieID)) {
                    ratersCount += 1;
                    score += rater.getValue() * topRater.getRating(movieID);
                }
            }
            if (ratersCount >= minimalRaters) {
                double weightedAverage = score / ratersCount;
                Rating rating = new Rating(movieID, weightedAverage);
                result.add(rating);
            }
        }
        Collections.sort(result, Collections.<Rating>reverseOrder());
        return result;
    }

    /**
     * This method is similar to the getSimilarRatings method but has one additional Filter parameter
     * named filterCriteria and uses that filter to access and rate only those movies that match the
     * filter criteria.
     */
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                                       Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<Rating> topRaters = new ArrayList<>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        for (int i = 0; i < numSimilarRaters; i++) {
            topRaters.add(similarRaters.get(i));
        }
        System.out.println("Top Raters size: " + topRaters.size());
        //Movie weight
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            double ratersCount = 0;
            double score = 0;
            for (Rating rater : topRaters) {
                Rater topRater = RaterDatabase.getRater(rater.getItem());
                if (topRater.hasRating(movieID)) {
                    ratersCount += 1;
                    score += rater.getValue() * topRater.getRating(movieID);
                }
            }
            if (ratersCount >= minimalRaters) {
                double weightedAverage = score / ratersCount;
                Rating rating = new Rating(movieID, weightedAverage);
                result.add(rating);
            }
        }
        Collections.sort(result, Collections.<Rating>reverseOrder());
        return result;
    }
}
