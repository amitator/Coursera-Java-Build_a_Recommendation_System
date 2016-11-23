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
    private ThirdRatings thirdRatings;

    public MovieRunnerWithFilters(){
        MovieDatabase.initialize("ratedmovies_short.csv");
        thirdRatings = new ThirdRatings("data\\ratings_short.csv");
        System.out.println("\nNumber of movies: " + MovieDatabase.size()
                + "\t" + "Number of ratings: " + thirdRatings.getRaterSize() + "\n");
    }
    /**
     * This method prints amount of movies and raitings loaded. Also prints average rating and
     * title for those movies that have at least a specified number of ratings
     */
    public void printAverageRatings(){
        System.out.println("===================================");
        System.out.println("\t\t\tprintAverageRatings()");
        System.out.println("===================================");
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
     * print its rating, its year, and its title
     */
    public void printAverageRatingsByYear(){
        System.out.println("===================================");
        System.out.println("\tprintAverageRatingsByYear()");
        System.out.println("===================================");
        //For all those movies that have at least a specified number of ratings
        int minNumRatings = 1;
        int yearAfter = 2000;
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

    /**
     * This method prints all the movies that have a specified number of minimal ratings and specified genre.
     * Print the number of movies found, and for each movie found, print its rating, its year, and its title
     */
    public void printAverageRatingsByGenre(){
        System.out.println("===================================");
        System.out.println("\tprintAverageRatingsByGenre()");
        System.out.println("===================================");
        int minNumRatings = 1;
        String genre = "Crime";
        GenreFilter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minNumRatings, genreFilter);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings and genre "
                + genre);
        System.out.println("Total number of movies " + ratings.size() + "\n");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t"
                    + MovieDatabase.getTitle(current.getItem()));
        }
    }

    /**
     * This method prints all the movies that have a specified number of minimal ratings and duration in range of
     * min and max time. Print the number of movies found, and for each movie print its rating, its running time,
     * and its title on one line.
     */
    public void printAverageRatingsByMinutes(){
        System.out.println("===================================");
        System.out.println("\tprintAverageRatingsByMinutes()");
        System.out.println("===================================");
        int minNumRatings = 1;
        int minMin = 110;
        int maxMin = 170;
        MinutesFilter minutesFilter = new MinutesFilter(minMin, maxMin);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minNumRatings, minutesFilter);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings and min length of "
                + minMin + " and max length of " + maxMin);
        System.out.println("Total number of movies " + ratings.size() + "\n");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t"
                    + + MovieDatabase.getMinutes(current.getItem()) + "\t"
                    + MovieDatabase.getTitle(current.getItem()));
        }
    }

    /**
     * This method prints all the movies that have a specified number of minimal ratings and at least one
     * of the directors provided. Print the number of movies found, and for each movie print its rating
     * and its title on one line, and all its directors on the next line.
     */
    public void printAverageRatingsByDirectors(){
        System.out.println("===================================");
        System.out.println("\tprintAverageRatingsByDirectors()");
        System.out.println("===================================");
        int minNumRatings = 1;
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minNumRatings, directorsFilter);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings following directors: "
                + directors);
        System.out.println("Total number of movies " + ratings.size() + "\n");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t"
                    + MovieDatabase.getTitle(current.getItem()));
            System.out.println("\t\t\t" + MovieDatabase.getDirector(current.getItem()));
        }
    }

    /**
     * This method calls getAverageRatingsByFilter() to get an ArrayList of type Rating
     * of all the movies that have a specified number of minimal ratings and the two criteria based
     * on year and genre. Print the number of movies found, and for each movie, print its rating, its year,
     * and its title on one line, and all its genres on the next line
     */
    public void printAverageRatingsByYearAfterAndGenre(){
        System.out.println("===================================");
        System.out.println("printAverageRatingsByYearAfterAndGenre()");
        System.out.println("===================================");
        AllFilters allFilters = new AllFilters();
        //Initializing filters
        int minNumRatings = 1;
        int year = 1980;
        String genre = "Romance";
        YearAfterFilter yearAfterFilter = new YearAfterFilter(year);
        GenreFilter genreFilter = new GenreFilter(genre);
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minNumRatings, allFilters);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings of "
                + genre + " genre, made on or after "
                + year);
        System.out.println("Total number of movies " + ratings.size() + "\n");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t"
                    + MovieDatabase.getYear(current.getItem()) + "\t"
                    + MovieDatabase.getTitle(current.getItem()));
            System.out.println("\t\t\t" + MovieDatabase.getGenres(current.getItem()));
        }
    }

    /**
     * This method should call getAverageRatingsByFilter to get an ArrayList of type Rating of all the
     * movies that have a specified number of minimal ratings and the two criteria based on minutes and
     * directors. Print the number of movies found, and for each movie, print its rating, its time length,
     * and its title on one line, and all its directors on the next line.
     */
    public void printAverageRatingsByDirectorsAndMinutes(){
        System.out.println("===================================");
        System.out.println("printAverageRatingsByDirectorsAndMinutes()");
        System.out.println("===================================");
        AllFilters allFilters = new AllFilters();
        //Initializing filters
        int minNumRatings = 1;
        int minMin = 30;
        int maxMin = 170;
        String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        MinutesFilter minutesFilter = new MinutesFilter(minMin, maxMin);
        allFilters.addFilter(directorsFilter);
        allFilters.addFilter(minutesFilter);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minNumRatings, allFilters);
        Collections.sort(ratings);
        System.out.println("Movies that have at least " + minNumRatings + " ratings and min length of "
                + minMin + " and max length of "
                + maxMin + " and following director(s): "
                + directors);
        System.out.println("Total number of movies " + ratings.size() + "\n");
        for (Rating current: ratings){
            System.out.println(current.getValue() + "\t"
                    + "Time: " + MovieDatabase.getMinutes(current.getItem()) + "\t"
                    + MovieDatabase.getTitle(current.getItem()));
            System.out.println("\t\t\t" + MovieDatabase.getDirector(current.getItem()));
        }
    }
}



























