import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class User_View extends JFrame{

    private JLabel usernameLabel = new JLabel("Username:");
    private JTextField usernameField = new JTextField(10);
    private JLabel passwordLabel = new JLabel("Password:");
    private JTextField passwordField = new JTextField(10);
    private JButton checkUser = new JButton("Returning User");
    private JButton newUser = new JButton("Create New User");

    User_View(){
        JPanel loginPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel outer = new JPanel(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        buttonPanel.add(checkUser);
        buttonPanel.add(newUser);
        outer.add(loginPanel, BorderLayout.NORTH);
        outer.add(buttonPanel, BorderLayout.CENTER);

        this.add(outer);
    }

    /* get the username from what is entered in the box*/
    public String getUsername(){
        return usernameField.getText();
    }

    /* get the password from what is entered in the box*/
    public String getPassword(){
        return passwordField.getText();
    }

    /* alert controller that this button is pressed */
    void checkUserListener(ActionListener listenerForCheckUser){
        checkUser.addActionListener(listenerForCheckUser);
    }

    /* alert controller that this button is pressed */
    void newUserListener(ActionListener listenerForNewUser){
        newUser.addActionListener(listenerForNewUser);
    }

    /* error message if fields are empty*/
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }


}
