package org.coursera.brs;

/**
 * GenreFilter that implements Filter. The constructor should have one parameter
 * named genre representing one genre, and the satisfies method should return true
 * if a movie has this genre
 *
 * @author Igor Prus
 * @version Nov 22/16
 *
 */

public class GenreFilter implements Filter{
    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        String[] genreArray = MovieDatabase.getGenres(id).split("\\s+");
        for (String current: genreArray){
            current = current.replaceAll(",","");
            if (current.equals(genre)){
                return true;
            }
        }
        return false;
    }

}
