package Notes;

import StartMenu.LoginFrame;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NotesFrame extends JPanel implements ActionListener {
    
    private final JTabbedPane tabbedPane;
    private final JTextArea textArea;

    private JButton jButton;
    private JButton jButton1;

    private JMenuItem top;
    private JMenuItem left;
    private JMenuItem bottom;
    private JMenuItem right;

    private final String SAVE = "Save";
    private final String LOAD = "Load Test";

    private static JFrame frame;

    /**
     * TabbedPaneDemo constructor.
     */
    public NotesFrame() {
        frame = new JFrame("Demo");
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        frame.setJMenuBar(makeMenuBar());

        // Create tab position controls.
        JPanel tabControls = new JPanel();
        tabControls.add(jButton = new JButton(SAVE));
        tabControls.add(jButton1 = new JButton(LOAD));
        jButton.addActionListener(this);
        jButton1.addActionListener(this);
        contentPane.add(tabControls, BorderLayout.SOUTH);

        // Create tab.
        tabbedPane = new JTabbedPane();
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        String name = "File 1";
        textArea = new JTextArea(5,5);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setAutoscrolls(true);
        tabbedPane.add(name, scroll);

        name = "File 2";
        scroll = new JScrollPane(new JTextArea(5,5));
        tabbedPane.add(name, scroll);

        name = "File 3";
        scroll = new JScrollPane(new JTextArea(5,5));
        tabbedPane.add(name, scroll);

        name = "File 4";
        scroll = new JScrollPane(new JTextArea(5,5));
        tabbedPane.add(name, scroll);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(frame);
        frame.setPreferredSize(new Dimension(800,600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SAVE) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185105?"
                    + "user=s185105&password=HzlMdPaCaRY0xr7mRHVhd")) {

                PreparedStatement statement = connection.prepareStatement("INSERT INTO notes(username, notes) VALUES(?, ?)");
                statement.setString(1, LoginFrame.finalUsername);
                statement.setString(2, textArea.getText());
                statement.execute();

                //System.out.println(textArea.getText());

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == LOAD) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185105?"
                    + "user=s185105&password=HzlMdPaCaRY0xr7mRHVhd")) {

                ResultSet resultSet = connection.createStatement().executeQuery("SELECT username,notes FROM notes");

                if (resultSet.next() && resultSet.getString(1).equals(LoginFrame.finalUsername)) {
                    textArea.setText(resultSet.getString(2));
                    System.out.println("WIN");
                } else { System.out.println("FAIL"); }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == top) { tabbedPane.setTabPlacement(JTabbedPane.TOP); }
        else if (e.getSource() == left) { tabbedPane.setTabPlacement(JTabbedPane.LEFT); }
        else if (e.getSource() == bottom) { tabbedPane.setTabPlacement(JTabbedPane.BOTTOM); }
        else if (e.getSource() == right) { tabbedPane.setTabPlacement(JTabbedPane.RIGHT); }
    }

    private JMenuBar makeMenuBar() {
        //final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuBar = new JMenuBar();

        JMenu layout;
        JMenu tabs;

        layout = new JMenu("Layout");
        menuBar.add(layout);
        tabs = new JMenu("Tabs");
        layout.add(tabs);

        top = new JMenuItem("Top");
        top.addActionListener(this);
        tabs.add(top);
        left = new JMenuItem("Left");
        left.addActionListener(this);
        tabs.add(left);
        bottom = new JMenuItem("Bottom");
        bottom.addActionListener(this);
        tabs.add(bottom);
        right = new JMenuItem("Right");
        right.addActionListener(this);
        tabs.add(right);

        return menuBar;
    }
}
