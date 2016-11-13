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
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data\\ratedmovies.csv", "data\\ratings.csv");
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
     * This method for finding title of the movie with certain movieID
     *
     * @param movieID representing the ID of a movie
     * @return the title of the movie with that ID
     * */
    public String getTitle(String movieID){
        for (Movie movie: myMovies){
            if (movie.getID().equals(movieID)) {
                return movie.getTitle();
            }
        }
        return "Movie with movieID " + movieID + " was not found";
    }

    /**
     * This method for finding movieID of the movie with certain title
     *
     * @param title representing title of the movie
     * @return the ID of the movie with that title
     * */
    public String getID(String title){
        for (Movie movie: myMovies){
            if (movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }

}


























