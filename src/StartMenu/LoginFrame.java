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

    public static String finalUsername = "";

    public LoginFrame(JFrame f) {
        // Using the default FlowLayout(Changing the layout another place to GridLayout).
        controllingFrame = f;

        // Create everything.
        textField = new JTextField(10);
        textField.setActionCommand(LOGIN);
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

        if (LOGIN.equals(choice)) { // Process the username and password.
            char[] username = textField.getText().toCharArray();
            char[] password = passwordField.getPassword();
            if (isPasswordCorrect(password, username)) {
                // Login
                controllingFrame.setVisible(false);
                controllingFrame.dispose();
                new ChoiceMenu();
            } else {
                JOptionPane.showMessageDialog(controllingFrame, "Invalid, try again.");
            }

            // Zero out the possible password, for security.
            Arrays.fill(password, '0');

            passwordField.selectAll();
        } else { // The user selected cancel.
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new StartFrame();
        }
    }

    /**
     * Checks the passed-in array against the correct password.
     */
    private static boolean isPasswordCorrect(char[] password, char[] username) {
        boolean isCorrect = false;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185105?"
                + "user=s185105&password=HzlMdPaCaRY0xr7mRHVhd")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username, password FROM login");

            while (resultSet.next()) {
                if (Arrays.equals(username, resultSet.getString(1).toCharArray())
                        && Arrays.equals(password, resultSet.getString(2).toCharArray()))
                {
                    isCorrect = true;
                    for (int i = 0; i < username.length; i++) {
                        finalUsername += username[i];
                    }
                    break;
                } else {
                    isCorrect = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isCorrect;
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

        // Display the frame.
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public LoginFrame(){
        createAndShowGUI();
    }
}
