import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Search_View extends JPanel{

    JLabel searchViewLabel = new JLabel("Search View");
    JLabel searchLabel = new JLabel("Search: ");
    JTextField searchField = new JTextField(25);
    JButton movieButton;
    JCheckBox cb1 = new JCheckBox("Title");
    JCheckBox cb2 = new JCheckBox("Actor");
    JCheckBox cb3 = new JCheckBox("Genre");
    JCheckBox cb4 = new JCheckBox("Year");
    JButton searchButton = new JButton("Search");
    JLabel title = new JLabel("Title:");
    JLabel year = new JLabel("Year:");
    JLabel rated = new JLabel("Rated:");
    JLabel released = new JLabel("Released:");
    JLabel runtime = new JLabel("Runtime:");
    JLabel genre = new JLabel("Genre:");
    JLabel director = new JLabel("Director:");
    JLabel writer = new JLabel("Writer:");
    JLabel actors = new JLabel("Actors:");
    JLabel plot = new JLabel("Plot:");
    JLabel language = new JLabel("Language:");
    JLabel country = new JLabel("Country:");
    JLabel awards = new JLabel("Awards:");
    JLabel ratings = new JLabel("Ratings:");
    JLabel metascore = new JLabel("Metascore:");
    JLabel imdbrating = new JLabel("imdbRating:");
    JLabel imdbvotes = new JLabel("imdbVotes:");
    JLabel imdbid = new JLabel("imbdID:");
    JLabel type = new JLabel("Type:");
    JLabel dvd = new JLabel("DVD:");
    JLabel boxoffice = new JLabel("BoxOffice:");
    JLabel production = new JLabel("Production:");
    JLabel website = new JLabel("Website:");
    JLabel response = new JLabel("Response:");
    JPanel resultsPanel = new JPanel();

    Search_View(){
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);

        // Create panels
        JPanel viewPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel checkBoxPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel detailsPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel outer = new JPanel();

        // configure view panel
        searchViewLabel.setFont(new Font(null, Font.BOLD, 20));
        viewPanel.add(searchViewLabel);

        // configure text panel (i.e. search field)
        textPanel.add(searchLabel);
        textPanel.add(searchField);

        // configure checkbox panel
        checkBoxPanel.add(cb1);
        checkBoxPanel.add(cb2);
        checkBoxPanel.add(cb3);
        checkBoxPanel.add(cb4);

        // configure button panel
        buttonPanel.add(searchButton);

        // configure search panel
        searchPanel.setPreferredSize(new Dimension(350, 200));
        BoxLayout searchPanelLayout = new BoxLayout(searchPanel, BoxLayout.Y_AXIS);
        searchPanel.setLayout(searchPanelLayout);
        searchPanel.add(textPanel);
        searchPanel.add(checkBoxPanel);
        searchPanel.add(buttonPanel);

        // details panel
        JLabel movieDetailsLabel = new JLabel("Movie Details: ");
        BoxLayout detailsLayout = new BoxLayout(detailsPanel, BoxLayout.Y_AXIS);
        detailsPanel.setLayout(detailsLayout);
        BoxLayout attributes = new BoxLayout(detailsPanel, BoxLayout.Y_AXIS);
        detailsPanel.setLayout(attributes);
        detailsPanel.setBorder(blackline2);//Create a border to go around panel
        detailsPanel.add(movieDetailsLabel);
        detailsPanel.add(title);
        detailsPanel.add(year);
        detailsPanel.add(rated);
        detailsPanel.add(released);
        detailsPanel.add(runtime);
        detailsPanel.add(genre);
        detailsPanel.add(director);
        detailsPanel.add(writer);
        detailsPanel.add(actors);
        detailsPanel.add(plot);
        detailsPanel.add(language);
        detailsPanel.add(country);
        detailsPanel.add(awards);
        detailsPanel.add(ratings);
        detailsPanel.add(imdbrating);
        detailsPanel.add(imdbvotes);
        detailsPanel.add(imdbid);
        detailsPanel.add(type);
        detailsPanel.add(dvd);
        detailsPanel.add(boxoffice);
        detailsPanel.add(production);
        detailsPanel.add(website);
        detailsPanel.add(response);

        JScrollPane scroll2 = new JScrollPane(detailsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setPreferredSize(new Dimension(600,400));
        scroll2.getVerticalScrollBar().setUnitIncrement(32);

        BoxLayout moviesPanelLayout = new BoxLayout(resultsPanel, BoxLayout.Y_AXIS);
        resultsPanel.setLayout(moviesPanelLayout);
        JScrollPane scroll = new JScrollPane(resultsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(210,400));
        scroll.getVerticalScrollBar().setUnitIncrement(32);
        scroll.setBorder(blackline2);

        // configure main panel
        BoxLayout mainPanelLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.add(searchPanel);
        mainPanel.add(scroll2);

        /* Main Outer Panel */
        outer.setPreferredSize(new Dimension(1250, 350));
        BoxLayout outerLayout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(outerLayout);
        outer.setBorder(blackline2);

        //add stuff to outer panel
        outer.add(viewPanel);
        outer.add(mainPanel);

        JPanel format = new JPanel();
        BoxLayout outerLayout2 = new BoxLayout(format, BoxLayout.X_AXIS);
        format.setLayout(outerLayout2);

        format.add(outer);
        format.add(scroll);

        this.setVisible(false);
        this.add(format);
    }

    /* alert controller that this button is pressed */
    void searchListener(ActionListener listenerForSearch){
        searchButton.addActionListener(listenerForSearch);
    }

    /* get the status of the check boxes */
    public boolean getcb1(){
        return cb1.isSelected();
    }
    public boolean getcb2(){
        return cb2.isSelected();
    }
    public boolean getcb3(){
        return cb3.isSelected();
    }
    public boolean getcb4(){
        return cb4.isSelected();
    }

    public void displaySearchMovies(ArrayList<Movie_Model> movies) throws IOException {
        resultsPanel.removeAll();
        for(Movie_Model movie: movies){
            String Icon = movie.getPoster();
            String Title = movie.getTitle();
            String Year = movie.getYear();
            String Rated = movie.getRated();
            String Released = movie.getReleased();
            String Runtime = movie.getRuntime();
            String Genre = movie.getGenre();
            String Director = movie.getDirector();
            String Writer = movie.getWriter();
            String Actors = movie.getActors();
            String Plot = movie.getPlot();
            String Language = movie.getLanguage();
            String Country = movie.getCountry();
            String Awards = movie.getAwards();
            String Metascore = movie.getMetascore();
            String Imdbrating = movie.getImdbRating();
            String Imdbvotes = movie.getImdbVotes();
            String Imdbid = movie.getImdbID();
            String Type = movie.getType();
            String Dvd = movie.getDVD();
            String Boxoffice = movie.getBoxOffice();
            String Production = movie.getProduction();
            String Website = movie.getWebsite();
            String Response = movie.getResponse();
            String s;
            String v;
            String Ratings;
            ArrayList <String> rate = new ArrayList<>();
            for(Rating r: movie.getRatings()){
                s = r.getSource();
                v = r.getValue();
                rate.add("{Source: "+s+", Value: "+v+"} ");
            }
            StringBuilder sb = new StringBuilder();
            for(String string: rate){
                sb.append(string);
            }
            Ratings = sb.toString();

            if(Icon.equals("N/A")){
                movieButton = new JButton(Title);
            }else{
                ImageIcon imageIcon = new ImageIcon(new URL(Icon));
                Image image = imageIcon.getImage();
                Image newImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImage);
                movieButton = new JButton(imageIcon);
            }
            resultsPanel.add(movieButton);
            movieButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            movieButton.addActionListener(event ->
            {
                title.setText("Title: "+ Title);
                year.setText("Year: "+ Year);
                rated.setText("Rated: " + Rated);
                released.setText("Released: " + Released);
                runtime.setText("Runtime: " + Runtime);
                genre.setText("Genre: " + Genre);
                director.setText("Director: " + Director);
                writer.setText("Writer: " + Writer);
                actors.setText("Actors: " + Actors);
                plot.setText("Plot: " + Plot);
                language.setText("Language: " + Language);
                country.setText("Country: " + Country);
                awards.setText("Awards: " + Awards);
                ratings.setText("Ratings: " + Ratings);
                metascore.setText("Metascore: " + Metascore);
                imdbrating.setText("imdbRating: " + Imdbrating);
                imdbvotes.setText("imbdVotes: " + Imdbvotes);
                imdbid.setText("imdbID: " + Imdbid);
                type.setText("Type: " + Type);
                dvd.setText("DVD: " + Dvd);
                boxoffice.setText("BoxOffice: " + Boxoffice);
                production.setText("Production: " + Production);
                website.setText("Website: " + Website);
                response.setText("Response: " + Response);
            });
        }
    }
    public String getSearch(){
        return searchField.getText();
    }

    /* display error */
    void displayError(){
        JOptionPane.showMessageDialog(this, "No results from search.");
    }
}

