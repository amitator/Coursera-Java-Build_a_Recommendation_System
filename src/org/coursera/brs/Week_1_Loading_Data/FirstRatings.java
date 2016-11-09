package org.coursera.brs.Week_1_Loading_Data;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;


/**
 * Rating
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

}
