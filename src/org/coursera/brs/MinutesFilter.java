package org.coursera.brs;

/**
 * MinutesFilter that implements Filter.  Its satisfies method should return true
 * if a movie’s running time is at least min minutes and no more than max minutes.
 *
 * @author Igor Prus
 * @version Nov 22/16
 *
 */

public class MinutesFilter implements Filter {
    private int minTime, maxTime;

    public MinutesFilter(int min, int max){
        this.minTime = min;
        this.maxTime = max;
    }

    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getMinutes(id) >= minTime
                && MovieDatabase.getMinutes(id) <= maxTime;
    }

}
