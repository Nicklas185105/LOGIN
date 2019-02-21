package StartMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JPanel implements ActionListener {

    private JFrame controllingFrame;

    public StartFrame() {
        MakeFrame();
    }

    private void MakeFrame(){
        controllingFrame = new JFrame("Start Page");
        controllingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel contentpane = (JPanel)controllingFrame.getContentPane();
        contentpane.setBorder(new EmptyBorder(5,5,5,5));
        contentpane.setLayout(new BorderLayout(12,12));

        JPanel borderPanel = new JPanel(new FlowLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2,1));

        JButton signInButton = new JButton("Sign In");
        signInButton.setActionCommand("signIn");
        signInButton.addActionListener(this);
        signInButton.setContentAreaFilled(false);

        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand("login");
        loginButton.addActionListener(this);
        loginButton.setContentAreaFilled(false);

        buttonPanel.add(signInButton);
        buttonPanel.add(loginButton);

        borderPanel.add(buttonPanel);

        contentpane.add(borderPanel);

        controllingFrame.pack();
        controllingFrame.setVisible(true);
        controllingFrame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();

        if (choice.equals("signIgn")) {
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new SignUpFrame();
        } else {
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new LoginFrame();
        }
    }
}
