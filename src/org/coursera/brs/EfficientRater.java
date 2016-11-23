package org.coursera.brs;

/**
 * EfficientRater
 *
 * @author Igor Prus
 * @version Nov 09/16
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        for(Map.Entry<String, Rating> entry : myRatings.entrySet()){
            if (entry.getKey().equals(item)){
                return true;
            }
        }

        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(Map.Entry<String, Rating> entry : myRatings.entrySet()){
            if (entry.getKey().equals(item)){
                return entry.getValue().getValue();
            }
        }

        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Rating> entry : myRatings.entrySet()) {
            list.add(entry.getKey());
        }

        return list;
    }
}
