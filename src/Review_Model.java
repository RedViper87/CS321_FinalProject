public class Review_Model {
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
}
