import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class GUI {

    public GUI() {
        makeFrame();
    }

    private void makeFrame(){
        JFrame frame = new JFrame("Login Page");
        //frame.setUndecorated(true);
        JPanel contentpane = (JPanel) frame.getContentPane();
        contentpane.setBorder(new EmptyBorder(1,1,1,1));
        contentpane.setLayout(new BorderLayout(12,12));

        makeMenuBar(frame);

        //----- Border Panel -----
        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new GridLayout(2,1));

        JButton loginButton = new JButton("Login");
        //loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBackground(Color.gray);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(false);
        loginButton.addActionListener(e -> loginFrame(frame));
        borderPanel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        //signUpButton.setBorderPainted(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setBackground(Color.gray);
        signUpButton.setFocusPainted(false);
        signUpButton.setOpaque(false);
        signUpButton.addActionListener(e -> signUpFrame(frame));
        borderPanel.add(signUpButton);

        contentpane.add(borderPanel, BorderLayout.CENTER);

        frame.pack();

        frame.setSize(500,500);
        frame.setLocationRelativeTo(null); //Center frame
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private void quit() {
        System.exit(0);
    }

    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file;
        JMenuItem quit;

        file = new JMenu("File");
        menuBar.add(file);

        quit = new JMenuItem("Quit");
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        quit.addActionListener(e -> quit());
        file.add(quit);
    }

    private void loginFrame(JFrame frame)
    {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        JFrame loginFrame = new JFrame("Login Frame");
        JPanel contentpane = (JPanel) loginFrame.getContentPane();
        contentpane.setBorder(new EmptyBorder(1,1,1,1));
        contentpane.setLayout(new BorderLayout(12,12));

        makeMenuBar(loginFrame);

        //----- Border Panel -----
        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new GridLayout(2,1));

        JTextField username = new JTextField("Username");
        System.out.println(username);
        borderPanel.add(username);

        JPasswordField password = new JPasswordField(10);
        password.setActionCommand("OK");
        System.out.println(password);
        borderPanel.add(password);

        contentpane.add(borderPanel, BorderLayout.CENTER);

        loginFrame.pack();

        loginFrame.setSize(500,500);
        loginFrame.setLocationRelativeTo(null);
        //loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginFrame.setVisible(true);

        //new Login();
    }

    private void signUpFrame(JFrame frame)
    {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        JFrame signUpFrame = new JFrame("Sign Up Frame");
        JPanel contentpane = (JPanel) signUpFrame.getContentPane();
        contentpane.setBorder(new EmptyBorder(1,1,1,1));
        contentpane.setLayout(new BorderLayout(12,12));

        makeMenuBar(signUpFrame);

        //----- Border Panel -----
        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new GridLayout(2,1));


        contentpane.add(borderPanel, BorderLayout.CENTER);

        signUpFrame.pack();

        signUpFrame.setSize(500,500);
        signUpFrame.setLocationRelativeTo(null);
        //signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //signUpFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        signUpFrame.setVisible(true);
    }

}
