import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Review_View extends JPanel {

    private JLabel reviewViewLabel = new JLabel("Review View");
    private JLabel chooseMovieLabel = new JLabel("Choose a movie: ");
    private JLabel ratingLabel = new JLabel("Rating out of 10: ");
    private JLabel commentsLabel = new JLabel("Comments: ");
    private JButton addReview = new JButton("Add Review");
    private JLabel reviewsLabel = new JLabel("Your User Reviews: ");
    private JLabel userReview = new JLabel();
    private JComboBox movieList;
    private JComboBox ratingList = new JComboBox();
    private JTextField commentField = new JTextField(20);


    Review_View() throws IOException {
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);
        reviewViewLabel.setFont(new Font(null, Font.BOLD, 20));

        /* get movie data into arraylist*/
        Path path = Paths.get("SampleMovieFile.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        Movie_Model[] list;
        list = gson.fromJson(content,Movie_Model[].class);
        ArrayList<Movie_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        /* put the titles in the list */
        ArrayList<String> titles = new ArrayList<>();
        for(Movie_Model movies:arrayList){
            String Title = movies.getTitle();
            String Year = movies.getYear();
            titles.add(Title+","+Year);
        }
        Object[] listOfTitles = titles.toArray();

        movieList = new JComboBox(listOfTitles);

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
        outer.add(reviewViewLabel);
        outer.add(scroll);
        this.setVisible(false);
        this.add(outer);
    }

    /* alert controller that this button is pressed */
    void addReviewListener(ActionListener listenerForAddReview){
        addReview.addActionListener(listenerForAddReview);
    }
    public void erase(){
        userReview.setText("");
    }
    public void updateReview(String s){
        userReview.setText(userReview.getText()+s);
    }
    public String getMovie(){
        return movieList.getSelectedItem().toString();
    }
    public Integer getRating(){
        return (Integer) ratingList.getSelectedItem();
    }
    public String getComment(){
        return commentField.getText();
    }
    /* erase comment */
    public void eraseComment(){
        commentField.setText("");
    }

}
