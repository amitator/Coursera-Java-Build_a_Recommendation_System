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
    private String[] requiredDirectors;

    public DirectorsFilter(String directorsList){
        requiredDirectors = directorsList.split(",");
    }

    @Override
    public boolean satisfies(String id){
        String[] movieDirectors = MovieDatabase.getDirector(id).split(",");

        for (String currRequireq: requiredDirectors){
            for (String currMovieDirs: movieDirectors) {
                if (currMovieDirs.equals(currRequireq)) {
                    return true;
                }
            }
        }
        return false;
    }
}
