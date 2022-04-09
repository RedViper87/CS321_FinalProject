import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
public class Search_View extends JPanel{

    private JLabel searchViewLabel = new JLabel("Search View");
    private JLabel searchLabel = new JLabel("Search: ");
    private JLabel resultsLabel = new JLabel("Results:");
    private JLabel detailsLabel = new JLabel("Movie Details");
    private JTextField searchField = new JTextField(25);
    private JCheckBox cb1 = new JCheckBox("Title");
    private JCheckBox cb2 = new JCheckBox("Actor");
    private JCheckBox cb3 = new JCheckBox("Genre");
    private JCheckBox cb4 = new JCheckBox("Year");
    private JButton searchButton = new JButton("Search");



    Search_View(){
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);

        // Create panels
        JPanel viewPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel checkBoxPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel resultsPanel = new JPanel();
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
        BoxLayout checkBoxLayout = new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS);
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
        searchPanel.setBorder(blackline1);
        searchPanel.add(textPanel);
        searchPanel.add(checkBoxPanel);
        searchPanel.add(buttonPanel);

        // configure results panel
        resultsPanel.setPreferredSize(new Dimension(700, 100));
        BoxLayout resultsPanelLayout = new BoxLayout(resultsPanel, BoxLayout.Y_AXIS);
        resultsPanel.setLayout(resultsPanelLayout);
        resultsPanel.setBorder(blackline1);
        resultsPanel.add(resultsLabel);

        // configure details panel
        detailsPanel.setPreferredSize(new Dimension(350, 200));
        BoxLayout detailsPanelLayout = new BoxLayout(detailsPanel, BoxLayout.Y_AXIS);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanel.setBorder(blackline1);
        detailsPanel.add(detailsLabel);

        // configure main panel
        BoxLayout mainPanelLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.add(searchPanel);
        mainPanel.add(detailsPanel);

        /* Main Outer Panel */
        outer.setPreferredSize(new Dimension(700, 350));
        BoxLayout outerLayout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(outerLayout);
        outer.setBorder(blackline2);
        //add stuff to outer panel
        outer.add(viewPanel);
        outer.add(mainPanel);
        outer.add(resultsPanel);
        this.setVisible(false);

        this.add(outer);
    }
}

