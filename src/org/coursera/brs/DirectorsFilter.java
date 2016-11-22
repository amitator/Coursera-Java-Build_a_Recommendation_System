package org.coursera.brs;

/**
 * DirectorsFilter that implements Filter. The constructor have one parameter
 * named directors representing a list of directors separated by commas and its
 * satisfies method should return true if a movie has at least one of these directors
 * as one of its directors.
 *
 * @author Igor Prus
 * @version Nov 22/16
 *
 */

public class DirectorsFilter implements Filter {
    private String[] directors;

    public DirectorsFilter(String directorsList){
        directorsList = directorsList.replaceAll(",", "");
        directors = directorsList.split("\\s+");
    }

    public boolean satisfies(String id){
        String movieDirector = MovieDatabase.getDirector(id);
        for (String current: directors){
            if (current.equals(movieDirector)){
                return true;
            }
        }
        return false;
    }
}
