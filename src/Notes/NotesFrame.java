package Notes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesFrame extends JPanel implements ActionListener {

    JFrame controllingFrame;

    public NotesFrame() { makeFrame(); }

    public void makeFrame() {
        //TODO: Få NotesFrame til at virke.
        /*
        controllingFrame = new JFrame("Notes Page");
        controllingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextArea ta = new JTextArea(200,200);
        JPanel p1 = new JPanel();
        p1.add(ta);
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(50,50,200,200);
        tp.add("Main", p1);
        tp.add("visit", p2);
        tp.add("help", p3);
        controllingFrame.add(p1);
        controllingFrame.setSize(400,400);
        controllingFrame.setLayout(new FlowLayout());
        controllingFrame.setVisible(true);
        controllingFrame.setLocationRelativeTo(null);
        */

        controllingFrame = new JFrame("Notes Page");
        controllingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel)controllingFrame.getContentPane();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(12,12));

        JPanel borderPanel = new JPanel(new FlowLayout());

        JPanel testPanel = new JPanel(new GridLayout(1,2));

        JTabbedPane testPane = new JTabbedPane();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setAutoscrolls(true);
        scrollPane.setPreferredSize(new Dimension(1000,600));

        JTextArea text = new JTextArea(10,20);

        scrollPane.setViewportView(text);

        testPane.add("File1" ,scrollPane);

        testPanel.add(testPane);
        // VIRKER IKKE -.-
        JButton testButton = new JButton("Save");
        testButton.setActionCommand("save");
        testButton.addActionListener(this);
        testPanel.add(testButton);
        //------------------------------------------------

        borderPanel.add(testPane);

        contentPane.add(borderPanel);

        controllingFrame.pack();
        //controllingFrame.setSize(1200,800);
        controllingFrame.setVisible(true);
        controllingFrame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
