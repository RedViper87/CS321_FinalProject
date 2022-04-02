import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class Review_View extends JPanel {

    private JLabel reviewViewLabel = new JLabel("Review View");

    Review_View() {
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);
        reviewViewLabel.setFont(new Font(null, Font.BOLD, 20));

        /* Main Outer Panel */
        JPanel outer = new JPanel();
        outer.setPreferredSize(new Dimension(450, 150));
        outer.setBorder(blackline2);
        //add stuff to outer panel
        outer.add(reviewViewLabel);

        this.add(outer);
        this.setVisible(true);
    }
}
