public class User_Reviews {

    private String moviename;
    private String numericalrating;
    private String userinputreview;

    /* FUNCTIONS */
    public void setNumericalRating(String numericalRating) {
        numericalrating = numericalRating;
    }
    public String getNumericalRating() {
        return numericalrating;
    }

    public void setUserInputReview(String userInputReview) {
        userinputreview = userInputReview;
    }
    public String getUserInputReview() {
        return userinputreview;
    }

    public void setMovieName(String movieName){
        moviename = movieName;
    }
    public String getMovieName(){
        return moviename;
    }
}


