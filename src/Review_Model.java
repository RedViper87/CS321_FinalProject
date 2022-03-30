public class Review_Model extends Movie_Model{
    /* ATTRIBUTES */
    private Integer NumericalRating;
    private String UserInputReview;

    /* FUNCTIONS */
    public void setNumericalRating(Integer numericalRating) {
        NumericalRating = numericalRating;
    }
    public Integer getNumericalRating() {
        return NumericalRating;
    }

    public void setUserInputReview(String userInputReview) {
        UserInputReview = userInputReview;
    }
    public String getUserInputReview() {
        return UserInputReview;
    }

    public void addUserReview(){
        // when dropdown button for movie is clicked and button to enter review is clicked
        // add review to movie in json
    }
}
