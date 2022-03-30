import java.util.ArrayList;

public class Search_Model extends Movie_Model{
    /* ATTRIBUTES */
    private String SearchParameter;
    private String UserInputSearch;
    private Movie_Library movieLibrary;
    private ArrayList<Movie_Model> listOfMovies;

    /* FUNCTIONS */
    public void setSearchParameter(String searchParameter) {
        SearchParameter = searchParameter;
    }
    public String getSearchParameter() {
        return SearchParameter;
    }

    public void setUserInputSearch(String userInputSearch) {
        UserInputSearch = userInputSearch;
    }
    public String getUserInputSearch() {
        return UserInputSearch;
    }

    public void SearchDatabase() { // pass in the string that is to be searched
        //some useful code goes here...probably
        // make the gson array of movies and search
    }
}
