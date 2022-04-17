/**
 * A class for a user's review that they make about a movie
 */
public class User_Reviews {

    private String moviename;
    private String numericalrating;
    private String userinputreview;

    /**
     * Sets the current user's numerical rating of a movie
     * @param numericalRating the score out of 10 a user gives a movie
     */
    public void setNumericalRating(String numericalRating) {
        numericalrating = numericalRating;
    }

    /**
     * Gets the numerical rating of a movie
     * @return numericalrating the score out of 10 a user gives a movie
     */
    public String getNumericalRating() {
        return numericalrating;
    }

    /**
     * Sets the userInputReview of a movie
     * @param userInputReview the comment a user makes about a movie
     */
    public void setUserInputReview(String userInputReview) {
        userinputreview = userInputReview;
    }

    /**
     * Gets the userInputReview of a movie
     * @return userinputreview the comment a user makes about a movie
     */
    public String getUserInputReview() {
        return userinputreview;
    }

    /**
     * Sets the name of a movie the user picks to make a review of
     * @param movieName the name of a movie the user writes a review about
     */
    public void setMovieName(String movieName){
        moviename = movieName;
    }

    /**
     * Gets the name of a movie the user picks to make a review of
     * @return moviename the name of a movie the user writes a review about
     */
    public String getMovieName(){
        return moviename;
    }
}


