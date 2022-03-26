public class Search_Model {
    /* ATTRIBUTES */
    private String SearchParameter;
    private String UserInputSearch;
    private Movie_Library movieLibrary;

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

    public void SearchDatabase() {
        //some useful code goes here...probably
    }
}
