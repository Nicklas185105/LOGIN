package StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class SignUpFrame extends JPanel implements ActionListener {

    private static String SIGNUP = "Sign Up";

    //TODO: Den tjekker ikke efter navn eller password mere når man opretter en bruger.

    private JFrame controllingFrame;
    private JTextField textField;
    private JPasswordField passwordField;

    private SignUpFrame(JFrame f) {
        // Using the default FlowLayout(Changing the layout another place to GridLayout).
        controllingFrame = f;

        // Create everything
        textField = new JTextField(10);
        textField.setActionCommand(SIGNUP);
        textField.addActionListener(this);
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(SIGNUP);
        passwordField.addActionListener(this);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setLabelFor(textField);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setLabelFor(passwordLabel);

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

    private JComponent createButtonPanel() {
        String CANCEL = "Cancel";
        
        JPanel buttonPane = new JPanel(new GridLayout(0,1));
        JButton signUpButton = new JButton("Sign Up");
        JButton cancelButton = new JButton("Cancel");

        signUpButton.setActionCommand(SIGNUP);
        signUpButton.addActionListener(this);
        cancelButton.setActionCommand(CANCEL);
        cancelButton.addActionListener(this);

        buttonPane.add(signUpButton);
        buttonPane.add(cancelButton);

        return buttonPane;
    }

    /**
     * Used for checking the users choice
     */
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();

        if (SIGNUP.equals(choice)) { // Process the username and password.
            char[] username = textField.getText().toCharArray();
            char[] passwordA = passwordField.getPassword();
            String password = "";

            /*for (char pass : passwordA) {
                password += pass;
            }*/
            
            for (int i = 0; i < passwordA.length; i++) {
                password += passwordA[i];
            }

            //char[] password = passwordField.getPassword();
            if (isUsernameCorrect(username, password)) {
                // Sign Up.
                JOptionPane.showMessageDialog(controllingFrame,"Successfully signed up\n" + "You will now get redirected to the Login Page");
                controllingFrame.setVisible(false);
                controllingFrame.dispose();
                new LoginFrame();
            } else {
                JOptionPane.showMessageDialog(controllingFrame,"Invalid, try again");
            }

            passwordField.selectAll();
        } else {
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new StartFrame();
        }
    }

    /**
     * Checks the passed-in string against other username's.
     */
    private static boolean isUsernameCorrect(char[] username, String password) {
        boolean isCorrect = false;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185105?"
                + "user=s185105&password=HzlMdPaCaRY0xr7mRHVhd")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM login");

            while (resultSet.next()) {
                if (Arrays.equals(username, resultSet.getString(1).toCharArray())) {
                    isCorrect = false;
                    break;

                } else {
                    PreparedStatement statement1 = connection.prepareStatement("INSERT INTO login(username, password) VALUES(?, ?)");
                    String username1 = "";
                    for (int i = 0; i < username.length; i++) {
                        username1 += username[i];
                    }
                    statement1.setString(1, username1);
                    statement1.setString(2, password);
                    statement1.execute();
                    isCorrect = true;
                    break;
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
    private void createAndShowGUI() {
        // Create and set up the frame.
        JFrame frame = new JFrame("Sign Up Frame");

        // Create and set up the content pane.
        final SignUpFrame newContentPane = new SignUpFrame(frame);
        newContentPane.setOpaque(true);
        newContentPane.setLayout(new GridLayout(3,1));
        frame.setContentPane(newContentPane);

        // Display the frame.
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public SignUpFrame() {
        createAndShowGUI();
    }
}
