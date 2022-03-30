import javax.swing.*;
import java.awt.*;

public class Watchlist_View extends JPanel {

    private JLabel watchlistNameLabel = new JLabel("Watchlist name");

    Watchlist_View() {
        JPanel namePanel = new JPanel();
        JPanel outer = new JPanel();
        BoxLayout layout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(layout);
        watchlistNameLabel.setFont(new Font(null, Font.BOLD, 20));
        namePanel.add(watchlistNameLabel);

        outer.add(namePanel);

        this.add(outer);
        this.setVisible(true);
    }

}
