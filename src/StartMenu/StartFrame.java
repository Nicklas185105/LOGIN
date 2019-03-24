package StartMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class StartFrame extends JPanel implements ActionListener {

    private static String SIGNIN = "signIn";
    private static String LOGIN = "login";

    //Menu Bar
    private static String QUIT = "Quit";

    private JFrame controllingFrame;

    public StartFrame() {
        MakeFrame();
    }

    private void MakeFrame(){
        controllingFrame = new JFrame("Start Page");
        controllingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel)controllingFrame.getContentPane();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(12,12));

        makeMenuBar(controllingFrame);

        JPanel borderPanel = new JPanel(new FlowLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2,1));

        JButton signInButton = new JButton("Sign In");
        signInButton.setActionCommand(SIGNIN);
        signInButton.addActionListener(this);
        signInButton.setContentAreaFilled(false);

        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand(LOGIN);
        loginButton.addActionListener(this);
        loginButton.setContentAreaFilled(false);

        buttonPanel.add(signInButton);
        buttonPanel.add(loginButton);

        borderPanel.add(buttonPanel);

        contentPane.add(borderPanel);

        controllingFrame.pack();
        controllingFrame.setVisible(true);
        controllingFrame.setLocationRelativeTo(null);
    }

    private void makeMenuBar(JFrame frame) {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file;
        JMenuItem quit;

        file = new JMenu("File");
        menuBar.add(file);

        quit = new JMenuItem("Quit");
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        quit.setActionCommand(QUIT);
        quit.addActionListener(this);
        file.add(quit);
    }

    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();

        if (choice.equals(SIGNIN)) {
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new SignUpFrame();
        } else if (choice.equals(LOGIN)){
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new LoginFrame();
        } else if (choice.equals(QUIT)) {
            System.exit(0);
        }
    }
}
