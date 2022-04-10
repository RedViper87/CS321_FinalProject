import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Recommendations_View extends JPanel{

    private JLabel recViewLabel = new JLabel("Recommendations View");

    Recommendations_View() {
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);
        recViewLabel.setFont(new Font(null, Font.BOLD, 20));

        /* Main Outer Panel */
        JPanel outer = new JPanel();
        outer.setPreferredSize(new Dimension(600, 200));
        outer.setBorder(blackline2);
        //add stuff to outer panel
        outer.add(recViewLabel);
        this.setVisible(false);
        this.add(outer);

        // refresh reccomendations button
    }
}
