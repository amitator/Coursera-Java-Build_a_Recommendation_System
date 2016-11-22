package org.coursera.brs;

public class Test {
    public static void main(String[] args) {
//        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.testLoadMovies();
//        firstRatings.testLoadRaters();
//        MovieRunnerAverage runner = new MovieRunnerAverage();
//        runner.printAverageRatings();
//        runner.getAverageRatingOneMovie();


//        SecondRatings secondRatings = new SecondRatings();
//        ArrayList<Rating> temp = new ArrayList<>();
//        temp = secondRatings.getAverageRatings(40);
//        for (Rating rating: temp){
//            System.out.println(rating);
//        }
//        System.out.println("\nSize: " + temp.size()+ "\n");
//        System.out.println(secondRatings.getTitle("04467sqaq55"));
        MovieRunnerWithFilters runner = new MovieRunnerWithFilters();
        runner.printAverageRatingsByYear();
    }
}
