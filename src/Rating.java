/**
 * Rating class for each of the ratings in the SampleMovieFile.json
 */
public class Rating {
    //attributes
    private final String Source;
    private final String Value;

    /**
     * Contructs a Rating object that holds the information of a Rating of a movie
     * @param source the source (critic website) of the review of a movie
     * @param value the numeric value that the critic made of a movie
     */
    public Rating(String source, String value) {
        Source = source;
        Value = value;
    }

    /**
     * Return the source variable
     * @return the Source from the movie information
     */
    public String getSource() {
        return Source;
    }

    /**
     * Return the Value variable
     * @return the Value from the movie information
     */
    public String getValue() {
        return Value;
    }
}
