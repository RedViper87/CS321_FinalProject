import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
public class Search_View extends JPanel{

    private JLabel searchViewLabel = new JLabel("Search View");

    Search_View(){
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);
        searchViewLabel.setFont(new Font(null, Font.BOLD, 20));

        /* Main Outer Panel */
        JPanel outer = new JPanel();
        outer.setPreferredSize(new Dimension(900, 105));
        outer.setBorder(blackline2);
        //add stuff to outer panel
        outer.add(searchViewLabel);

        this.add(outer);
        this.setVisible(true);
    }
}
