package StartMenu;

import Notes.NotesFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceMenu extends JPanel implements ActionListener {

    private static String NOTES = "Notes";
    private static String LOGOUT = "Log Out";

    private JFrame controllingFrame;

    public ChoiceMenu() { MakeFrame(); }

    private void MakeFrame() {
        controllingFrame = new JFrame("Choice Menu");
        controllingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel)controllingFrame.getContentPane();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(12,12));

        JPanel borderPanel = new JPanel(new FlowLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2,1));

        JButton notesButton = new JButton(NOTES);
        notesButton.setActionCommand(NOTES);
        notesButton.addActionListener(this);
        notesButton.setContentAreaFilled(false);

        JButton logOutButton = new JButton(LOGOUT);
        logOutButton.setActionCommand(LOGOUT);
        logOutButton.addActionListener(this);
        logOutButton.setContentAreaFilled(false);

        buttonPanel.add(notesButton);
        buttonPanel.add(logOutButton);

        borderPanel.add(buttonPanel);

        contentPane.add(borderPanel);

        controllingFrame.pack();
        controllingFrame.setVisible(true);
        controllingFrame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e){
        String choice = e.getActionCommand();

        if (choice.equals(NOTES)) {
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new NotesFrame();
        } else if (choice.equals(LOGOUT)) {
            controllingFrame.setVisible(false);
            controllingFrame.dispose();
            new StartFrame();
        }
    }

}