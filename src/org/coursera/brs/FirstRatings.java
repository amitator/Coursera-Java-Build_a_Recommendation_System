package org.coursera.brs;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.*;


/**
 * FirstRatings
 *
 * @author Igor Prus
 * @version Nov 09/16
 */

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> result = new ArrayList<>();
        FileResource resource = new FileResource(fileName);
        CSVParser parser = resource.getCSVParser();
        for (CSVRecord record : parser){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
            result.add(movie);
        }
        return result;
    }

    public void testLoadMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        movies = loadMovies("data\\ratedmovies.csv");
        System.out.println("\n===================================");
        System.out.println("\t\t\ttestLoadMovies()");
        System.out.println("===================================");
        System.out.println("Number of movies: " + movies.size() + "\n");
        int genreCounter = 0;
        int minutesCounter = 0;
        String genre = "Comedy";
        int movieLength = 150;
        Map<String, Integer> directorsMap = new HashMap<>();

        for (Movie movie: movies){
//            System.out.println(movie);
            if (movie.getGenres().contains(genre)){
                genreCounter++;
            }
            if(movie.getMinutes() > movieLength){
                minutesCounter++;
            }
            System.out.println("Director " + movie.getDirector());
            //Some movies might have more than 1 director
            String[] directors = movie.getDirector().split(",");
            for (String dir: directors) {
                if (!directorsMap.containsKey(dir)){
                    //Adding first record (Dir Name, 1 - movie counter)
                    directorsMap.put(dir, 1);
                } else {
                    //Get current counter and increase it
                    int counter = directorsMap.get(dir);
                    directorsMap.put(dir, ++counter);
                }
            }
        }
        System.out.println("\nThere are " + genreCounter +
                " movies in " + genre);
        System.out.println("\nThere are " + minutesCounter +
                " movies longer then " + movieLength + " minutes");
        int maxValue = Collections.max(directorsMap.values());
        System.out.println("\nMaximum number of movies (" + maxValue
                +") having following directors:");
        for (Map.Entry<String, Integer> entry: directorsMap.entrySet()) {
            //If director's movie counter == MAX, then print him
            if (entry.getValue() == maxValue){
                System.out.println(entry.getKey());
            }
        }
    }

    public ArrayList<PlainRater> loadRaters(String fileName){
        ArrayList<PlainRater> result = new ArrayList<>();
        FileResource resource = new FileResource(fileName);
        CSVParser parser = resource.getCSVParser();
        for (CSVRecord record : parser){
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            Double rating = Double.parseDouble(record.get("rating"));
            PlainRater rater = new PlainRater(raterID);
            //Flag turns TRUE if rater was edited and rating was added to that rater
            boolean flag = false;
            if (result.size() != 0){
                //running over all result
                for (PlainRater current: result){
                    //if rater in result then add him more rating and change the flag
                    if (current.getID().equals(raterID)){
                        current.addRating(movieID, rating);
                        flag = true;
                    }
                }
                //if rater not yet in result then add him
                if (!flag){
                    rater.addRating(movieID, rating);
                    result.add(rater);
                }
            } else {
                //for the first record in rating
                rater.addRating(movieID, rating);
                result.add(rater);
            }
        }
        return result;
    }

    public void testLoadRaters(){
        ArrayList<PlainRater> raters = new ArrayList<>();
        raters = loadRaters("data\\ratings.csv");
        System.out.println("\n===================================");
        System.out.println("\t\t\ttestLoadRaters()");
        System.out.println("===================================");
        System.out.println("Number of raters: " + raters.size() + "\n");
        String neededRaterID = "193";
        String movieID = "1798709";
        int maxNumRatings = 0;
        int numRatingForParticularMovieCounter = 0;
        ArrayList<String> itemsRated = new ArrayList<>();
        for (PlainRater current : raters){
            if (neededRaterID.equals(current.getID())) {
                //Getting movieIDs
                ArrayList<String> ratedMoviesID = current.getItemsRated();
                System.out.println("Rater ID: " + current.getID()
                        + "\t\t" + "Number of ratings: " + current.numRatings());
                //Printing all movieIDs and ratings of current rater
//                for (String movie : ratedMoviesID) {
//                    System.out.println("\t\t\t\t" + "Movie ID: " + movie
//                            + "\t" + "Rating: " + current.getRating(movie));
//                }
            }
            if (current.numRatings() > maxNumRatings){
                maxNumRatings = current.numRatings();
            }
            if (current.hasRating(movieID)){
                numRatingForParticularMovieCounter++;
            }
            //How many different movies have been rated by all these raters
            itemsRated.addAll(current.getItemsRated());
        }
        //Creating list of raters with max number ratings
        ArrayList<PlainRater> list = new ArrayList<>();
        for (PlainRater current: raters){
            if (current.numRatings() == maxNumRatings){
                list.add(current);

            }
        }
        System.out.println("\nMax number of ratings: " + maxNumRatings
                        + "\nRaters with max:");
        //Printing previous list
        for (PlainRater curr: list){
            System.out.println("RaterID: " + curr.getID());
        }
        //Number of ratings for a particular movie
        System.out.println("\nMovie: " + movieID + " has " + numRatingForParticularMovieCounter
                        + " ratings");
        Set<String> uniqueRatedMovies = new HashSet<>(itemsRated);
        System.out.println("\nThere are " + uniqueRatedMovies.size() + " unique movies rated");
    }
}


















