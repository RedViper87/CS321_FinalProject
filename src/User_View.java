import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * A class that creates a JPanel view of the user login page
 */
public class User_View extends JPanel{

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(10);
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(10);
    JButton checkUser = new JButton("Returning User");
    JButton newUser = new JButton("Create New User");
    JLabel welcomeLabel = new JLabel("Welcome to My Movie Library!");

    /**
     * Constructs an User_View oject that will show the login page
     */
    User_View(){
        Border blackline = BorderFactory.createLineBorder(Color.black, 2);
        JPanel welcomePanel = new JPanel();
        JPanel loginPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel outer = new JPanel();
        BoxLayout layout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(layout);
        welcomeLabel.setFont(new Font(null, Font.BOLD, 20));
        welcomePanel.add(welcomeLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        JCheckBox show_password = new JCheckBox("Show Password");
        show_password.addActionListener(PWListener -> {
            JCheckBox box = (JCheckBox) PWListener.getSource();
            passwordField.setEchoChar(box.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
        });
        loginPanel.add(show_password, BorderLayout.SOUTH);
        buttonPanel.add(checkUser);
        buttonPanel.add(newUser);

        outer.add(welcomePanel);
        outer.add(loginPanel);
        outer.add(buttonPanel);
        outer.setBorder(blackline);

        this.setBackground(Color.darkGray);
        this.add(outer);
        this.setVisible(true);
    }

    /**
     * Erases the usernameField textbox
     */
    public void eraseUsername(){
        usernameField.setText("");
    }

    /**
     * Erases the passwordField textbox
     */
    public void erasePassword(){
        passwordField.setText("");
    }

    /**
     * returns the usernameField of the username a user has typed in
     * @return usernameField the username of the user that is trying to login
     */
    public String getUsername(){
        return usernameField.getText();
    }

    /**
     * returns the password Field of the username a user has typed in
     * @return passwordField the password of the user that is trying to login
     */
    public String getPassword(){
        return passwordField.getText();
    }

    /**
     * returns the password Field of the username a user has typed in
     * @return passwordField the password of the user that is trying to login
     */
    public void hidePassword(){passwordField.setEchoChar('*');}

    /**
     * Adds an actionlistener to the checkuser button to be used in the controller
     * @param listenerForCheckUser the actionlistener for the checkuser button
     */
    void checkUserListener(ActionListener listenerForCheckUser){
        checkUser.addActionListener(listenerForCheckUser);
    }

    /**
     * Adds an actionlistener to the newUser button to be used in the controller
     * @param listenerForNewUser the actionlistener for the newUser button
     */
    void newUserListener(ActionListener listenerForNewUser){
        newUser.addActionListener(listenerForNewUser);
    }

    /**
     * displays a success message if the account was created
     */
    void displaySuccess(){
        JOptionPane.showMessageDialog(this, "Account successfully created! Please login.");
    }

    /**
     * displays a success message if the account was created
     * @param msg the message to display when a user action completes
     */
    void displayMessage(String msg){JOptionPane.showMessageDialog(this, msg); }

    /**
     * displays an error message if the account could not be created
     * @param error the error that a user was unable to create an account
     */

    void displayError(String error){
        JOptionPane.showMessageDialog(this,error);
    }
}
