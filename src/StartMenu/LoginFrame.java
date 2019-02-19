package StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class LoginFrame extends JPanel implements ActionListener {

    private static String LOGIN = "Login";
    private static String CANCEL = "Cancel";

    private JFrame controllingFrame;
    private JTextField textField;
    private JPasswordField passwordField;

    public LoginFrame(JFrame f) {
        // Using the default FlowLayout(Changing the layout another place to GridLayout).
        controllingFrame = f;

        // Create everything.
        textField = new JTextField(10);
        textField.addActionListener(this);
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(LOGIN);
        passwordField.addActionListener(this);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setLabelFor(textField);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setLabelFor(passwordField);

        JComponent buttonPane = createButtonPanel();

        // Lay out everything.
        JPanel usernamePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel passwordPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        usernamePane.add(usernameLabel);
        usernamePane.add(textField);
        passwordPane.add(passwordLabel);
        passwordPane.add(passwordField);

        add(usernamePane);
        add(passwordPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel(){
        JPanel buttonPane = new JPanel(new GridLayout(0,1));
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        loginButton.setActionCommand(LOGIN);
        loginButton.addActionListener(this);
        cancelButton.setActionCommand(CANCEL);
        cancelButton.addActionListener(this);

        buttonPane.add(loginButton);
        buttonPane.add(cancelButton);

        return buttonPane;
    }

    /**
     * Used for checking the users choice
     */
    public void actionPerformed(ActionEvent e){
        String choice = e.getActionCommand();

        if (LOGIN.equals(choice)) { // Process the password.
            char[] username = textField.getText().toCharArray();
            char[] password = passwordField.getPassword();
            if (isPasswordCorrect(password, username)) {
                JOptionPane.showMessageDialog(controllingFrame,"Correct");
            } else {
                JOptionPane.showMessageDialog(controllingFrame, "Invalid");
            }

            // Zero out the possible password, for security.
            Arrays.fill(password, '0');

            passwordField.selectAll();
        } else { // The user selected cancel.
            System.exit(0);
        }
    }

    /**
     * Checks the passed-in array against the correct password.
     */
    private static boolean isPasswordCorrect(char[] password, char[] username) {
        boolean isCorrect;

        readData();

        char[] name = { 'A', 'D', 'M', 'I', 'N' };
        char[] correctPassword = { 'P', 'A', 'S', 'S', 'W', 'O', 'R', 'D' };

        if (password.length != correctPassword.length && username.length != name.length) {
            isCorrect = false;
        } else if (Arrays.equals(password, correctPassword) && Arrays.equals(username, name)) {
            isCorrect = true;
        } else {
            isCorrect = false;
        }

        // Zero out the password.
        Arrays.fill(correctPassword, '0');

        return isCorrect;
    }

    private static void readData(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185105?"
                + "user=s185105&password=HzlMdPaCaRY0xr7mRHVhd")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM login");
            System.out.println("Got result set from database:");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2) + " | " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the GUI and show it.
     */
    private static void createAndShowGUI() {
        // Create and set up the frame.
        JFrame frame  = new JFrame("Login Page");

        // Create and set up the content pane.
        final LoginFrame newContentPane = new LoginFrame(frame);
        newContentPane.setOpaque(true);
        newContentPane.setLayout(new GridLayout(3,1));
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public LoginFrame(){
        createAndShowGUI();
    }
}
