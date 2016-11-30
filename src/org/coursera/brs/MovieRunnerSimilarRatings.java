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
        RaterDatabase.initialize("ratings.csv");
        System.out.println("\nNumber of movies: " + MovieDatabase.size()
                + "\t" + "Number of ratings: " + RaterDatabase.size() + "\n");
        fourthRatings = new FourthRatings();
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
        System.out.println("\n===================================");
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

    public void printSimilarRatings(){
        System.out.println("\n===================================");
        System.out.println("\t\tprintSimilarRatings()");
        System.out.println("===================================");
        String raterID = "71";
        int topRaters = 20;
        int minimalRaters = 5;
        FourthRatings fourthrating = new FourthRatings();
        ArrayList<Rating> ratings = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        System.out.println("Total movies recomended: " + ratings.size() + "\n");
        for (Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
        }
    }

    public void printSimilarRatingsByGenre () {
        System.out.println("\n===================================");
        System.out.println("\t\tprintSimilarRatingsByGenre()");
        System.out.println("===================================");
        String raterID = "964";
        int topRaters = 20;
        int minimalRaters = 5;
        AllFilters allfilters = new AllFilters();
        String type = "Mystery";
        GenreFilter genrefiler = new GenreFilter(type);
        allfilters.addFilter(genrefiler);
        FourthRatings fourthrating = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size() + "\n");
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
        }
    }

    public void printSimilarRatingsByDirector() {
        System.out.println("\n===================================");
        System.out.println("\tprintSimilarRatingsByDirector()");
        System.out.println("===================================");
        String raterID = "120";
        int topRaters = 10;
        int minimalRaters = 2;
        AllFilters allfilters = new AllFilters();
        String name = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorsFilter directorFiler = new DirectorsFilter(name);
        allfilters.addFilter(directorFiler);
        FourthRatings fourthrating = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size() + "\n");
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        System.out.println("\n===================================");
        System.out.println("printSimilarRatingsByGenreAndMinutes()");
        System.out.println("===================================");
        String raterID = "168";
        int topRaters = 10;
        int minimalRaters = 3;
        AllFilters allfilters = new AllFilters();
        String type = "Drama";
        GenreFilter genrefiler = new GenreFilter(type);
        allfilters.addFilter(genrefiler);
        int min = 80;
        int max = 160;
        MinutesFilter minutesfiler = new MinutesFilter(min, max);
        allfilters.addFilter(minutesfiler);
        FourthRatings fourthrating = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size() + "\n");
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        System.out.println("\n===================================");
        System.out.println("printSimilarRatingsByYearAfterAndMinutes()");
        System.out.println("===================================");
        String raterID = "314";
        int topRaters = 10;
        int minimalRaters = 5;
        AllFilters allfilters = new AllFilters();
        int year = 1975;
        YearAfterFilter yearAfterFilter = new YearAfterFilter(year);
        allfilters.addFilter(yearAfterFilter);
        int min = 70;
        int max = 200;
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        allfilters.addFilter(minutesFilter);
        FourthRatings fourthRatings = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthRatings.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthRatings.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size() + "\n");
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
        }
    }

}
