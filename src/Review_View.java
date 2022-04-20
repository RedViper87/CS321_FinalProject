import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
/**
 * Class used to represent the viewable panel for Reviews
 */
public class Review_View extends JPanel {

    JLabel reviewViewLabel = new JLabel("Leave a review!");
    JLabel chooseMovieLabel = new JLabel("Choose a movie: ");
    JLabel ratingLabel = new JLabel("Rating out of 10: ");
    JLabel commentsLabel = new JLabel("Comments: ");
    JButton addReview = new JButton("Add Review");
    JLabel reviewsLabel = new JLabel("Your User Reviews: ");
    JLabel userReview = new JLabel();
    JComboBox<Object> movieList;
    JComboBox<Object> ratingList = new JComboBox<>();
    JTextField commentField = new JTextField(20);

    /**
     * Constructs the Review view panel
     * @throws IOException used for try catch
     */
    Review_View() throws IOException {
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);
        JPanel titlePanel = new JPanel();
        reviewViewLabel.setFont(new Font(null, Font.BOLD, 20));
        titlePanel.add(reviewViewLabel);

        /* get movie data into arraylist*/
        Path path = Paths.get("SampleMovieFile.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        Movie_Model[] list;
        list = gson.fromJson(content,Movie_Model[].class);
        ArrayList<Movie_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        /* put the titles in the list */
        ArrayList<String> titles = new ArrayList<>();
        for(Movie_Model movies:arrayList){
            String Title = movies.getTitle();
            titles.add(Title);
        }
        Object[] listOfTitles = titles.toArray();

        movieList = new JComboBox<>(listOfTitles);

        /* add numbers to rating list*/
        for(int i = 1; i < 11; i++){
            ratingList.addItem(i);
        }

        JPanel review = new JPanel();
        BoxLayout layout = new BoxLayout(review, BoxLayout.Y_AXIS);
        review.setBorder(blackline1);
        review.setLayout(layout);
        review.add(reviewsLabel);
        review.add(userReview);

        JPanel choose = new JPanel();
        choose.add(chooseMovieLabel);
        choose.add(movieList);
        JPanel rating = new JPanel();
        rating.add(ratingLabel);
        rating.add(ratingList);
        JPanel comments = new JPanel();
        comments.add(commentsLabel);
        comments.add(commentField);

        JPanel makeReview= new JPanel();
        BoxLayout revLayout = new BoxLayout(makeReview, BoxLayout.Y_AXIS);
        makeReview.setLayout(revLayout);
        makeReview.add(choose);
        makeReview.add(rating);
        makeReview.add(comments);
        makeReview.add(addReview);

        JPanel twoPanel = new JPanel();
        BoxLayout Layout = new BoxLayout(twoPanel, BoxLayout.X_AXIS);
        twoPanel.setLayout(Layout);
        twoPanel.add(makeReview);
        twoPanel.add(review);
        JScrollPane scroll = new JScrollPane(twoPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(1250,300));
        scroll.getHorizontalScrollBar().setUnitIncrement(32);
        /* Main Outer Panel */
        JPanel outer = new JPanel();
        BoxLayout revLayout2 = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(revLayout2);
        outer.setPreferredSize(new Dimension(1250, 300));
        outer.setBorder(blackline2);
        //add stuff to outer panel
        outer.add(titlePanel);
        outer.add(scroll);
        this.setBackground(Color.darkGray);
        this.setVisible(false);
        this.add(outer);
    }

    /**
     * Alerts the Controller that button for Add Review has been pressed
     * @param listenerForAddReview the Action Listener to be added
     */
    void addReviewListener(ActionListener listenerForAddReview){
        addReview.addActionListener(listenerForAddReview);
    }

    /**
     * Erases the user review text
     */
    public void erase(){
        userReview.setText("");
    }

    /**
     * Updates the review text
     * @param s String passed in as value to set text
     */
    public void updateReview(String s){
        userReview.setText(userReview.getText()+s);
    }

    /**
     * Returns a string of movie lists
     * @return movieList
     */
    public String getMovie(){
        return Objects.requireNonNull(movieList.getSelectedItem()).toString();
    }

    /**
     * Returns numerical rating for a movie review
     * @return Integer 1-10
     */
    public Integer getRating(){
        return (Integer) ratingList.getSelectedItem();
    }

    /**
     * Returns comment entered for movie review
     * @return commentField
     */
    public String getComment(){
        return commentField.getText();
    }

    /**
     * Erases the comment field of a review
     */
    public void eraseComment(){
        commentField.setText("");
    }

}
