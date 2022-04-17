import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class User_View extends JPanel{

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(10);
    JLabel passwordLabel = new JLabel("Password:");
    JTextField passwordField = new JTextField(10);
    JButton checkUser = new JButton("Returning User");
    JButton newUser = new JButton("Create New User");
    JLabel welcomeLabel = new JLabel("Welcome to My Movie Library!");

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
        buttonPanel.add(checkUser);
        buttonPanel.add(newUser);

        outer.add(welcomePanel);
        outer.add(loginPanel);
        outer.add(buttonPanel);
        outer.setBorder(blackline);
        this.add(outer);
        this.setVisible(true);
    }

    public void eraseUsername(){
        usernameField.setText("");
    }

    public void erasePassword(){
        passwordField.setText("");
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

    /* display success */
    void displaySuccess(){
        JOptionPane.showMessageDialog(this, "Account successfully created! Please login.");
    }
    /* display error */
    void displayError(String error){
        JOptionPane.showMessageDialog(this,error);
    }
}
