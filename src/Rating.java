//separate struct for Ratings, composed of 2 strings each
public class Rating {
    //attributes
    private String Source;
    private String Value;

    //functions
    public void setSource(String source) {
        Source = source;
    }
    public String getSource() {
        return Source;
    }

    public void setValue(String value) {
        Value = value;
    }
    public String getValue() {
        return Value;
    }
}
