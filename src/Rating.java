//separate struct for Ratings, composed of 2 strings each
public class Rating {
    //attributes
    private final String Source;
    private final String Value;

    public Rating(String source, String value) {
        Source = source;
        Value = value;
    }

    //functions
    public String getSource() {
        return Source;
    }

    public String getValue() {
        return Value;
    }
}
