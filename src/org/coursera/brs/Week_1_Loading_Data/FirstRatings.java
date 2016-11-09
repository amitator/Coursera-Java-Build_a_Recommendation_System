package org.coursera.brs.Week_1_Loading_Data;

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
        movies = loadMovies("data\\ratedmovies_short.csv");
        System.out.println("\n===================================");
        System.out.println("\t\t\ttestLoadMovies()");
        System.out.println("===================================");
        System.out.println("Number of movies: " + movies.size());
        int genreCounter = 0;
        int minutesCounter = 0;
        String genre = "Comedy";
        int movieLength = 150;
        Map<String, Integer> directorsMap = new HashMap<>();

        for (Movie movie: movies){
            System.out.println(movie);
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
        System.out.println("\nMaximum number of movies (" + directorsMap.size() +
                ") having following directors:");
        int maxValue = Collections.max(directorsMap.values());
        for (Map.Entry<String, Integer> entry: directorsMap.entrySet()) {
            //If director's movie counter == MAX, then print him
            if (entry.getValue() == maxValue){
                System.out.println(entry.getKey());
            }
        }
    }

    public ArrayList<Rater> loadRaters(String fileName){
        ArrayList<Rater> result = new ArrayList<>();
        FileResource resource = new FileResource(fileName);
        CSVParser parser = resource.getCSVParser();
        for (CSVRecord record : parser){
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            Double rating = Double.parseDouble(record.get("rating"));
            Rater rater = new Rater(raterID);
            if (!result.contains(rater)) {
                rater.addRating(movieID, rating);
                result.add(rater);
            } else {
                rater.addRating(movieID, rating);
            }
        }
        return result;
    }
}


















