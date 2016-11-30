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
//        MovieRunnerWithFilters runner = new MovieRunnerWithFilters();
//        runner.printAverageRatings();
//        runner.printAverageRatingsByYear();
//        runner.printAverageRatingsByGenre();
//        runner.printAverageRatingsByMinutes();
//        runner.printAverageRatingsByDirectors();
//        runner.printAverageRatingsByYearAfterAndGenre();
//        runner.printAverageRatingsByDirectorsAndMinutes();
        MovieRunnerSimilarRatings runner = new MovieRunnerSimilarRatings();
        runner.printAverageRatings();
        runner.printAverageRatingsByYearAfterAndGenre();
        runner.printSimilarRatings();
        runner.printSimilarRatingsByGenre();
        runner.printSimilarRatingsByDirector();
        runner.printSimilarRatingsByGenreAndMinutes();
        runner.printSimilarRatingsByYearAfterAndMinutes();
    }
}
