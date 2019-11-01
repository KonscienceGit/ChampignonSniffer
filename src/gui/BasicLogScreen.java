package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collections;
import java.util.Vector;

import static resources.Constants.*;

public class BasicLogScreen extends JFrame {
    private JPanel mainPannel;

    private JTable eventTable;
    private JPanel tableLine;
    private JPanel highlightEventLine;
    private JLabel highlightEventLabel;
    private Vector<Vector<String>> dataVector;
    private int maxEventNumber = 3;
    private int maxCharLength = 100;
    private int width = maxCharLength*10;
    private int height = maxEventNumber*33;



    public BasicLogScreen() {
        //allow it to be dragged by clicking anywhere
        addListeners();

        //Make it transparent
        setUndecorated(true);
        setBackground(invisibleColor);
        setLocationRelativeTo(null); //center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//end the process on clicking the red cross
        setAlwaysOnTop(true); //this can hide the prompt to end the platform, beware

        mainPannel = new JPanel();
        mainPannel.setLayout(new BoxLayout(mainPannel, BoxLayout.PAGE_AXIS)); //Vertical Box layout for the whole window
        setContentPane(mainPannel);
        mainPannel.setBackground(invisibleColor);

        setSize(width, height);
        setLocation(4,0);
        //Event table
        tableLine = new JPanel();
        tableLine.setLayout(new BoxLayout(tableLine,BoxLayout.LINE_AXIS));
        dataVector = new Vector<>();
        eventTable = new JTable(dataVector, new Vector<>(Collections.singletonList("Event")));
        eventTable.setEnabled(false);
        eventTable.setForeground(textEliteTrColor);
        eventTable.setGridColor(invisibleColor);
        eventTable.setBackground(invisibleColor);
        eventTable.setRowHeight(22);
        eventTable.setFont(new Font("Dosis", Font.PLAIN, 18));
        tableLine.add(eventTable);
        tableLine.setBackground(blackEliteTrColor);

        //Event highlight
        highlightEventLine = new JPanel();
        highlightEventLine.setLayout(new FlowLayout(FlowLayout.LEFT));
        highlightEventLine.setBackground(blackEliteTrColor);
        highlightEventLabel = new JLabel("Event log");
        highlightEventLabel.setForeground(textEliteTrColor);
        highlightEventLabel.setOpaque(true);// if false (by default) it doesn't display its background
        highlightEventLabel.setBackground(backScreenEliteTrColor);
        highlightEventLabel.setFont(new Font("Dosis", Font.PLAIN, 22));
        highlightEventLine.add(highlightEventLabel);

        mainPannel.add(tableLine);
        mainPannel.add(highlightEventLine);

        setVisible(true);
        refreshGUI();
    }

    //Magic that allow to drag the window
    private void addListeners() {
        final Point offset = new Point();
        addMouseListener(new MouseAdapter() {@Override public void mousePressed(final MouseEvent e) {offset.setLocation(e.getPoint());}});
        addMouseMotionListener(new MouseMotionAdapter() {@Override public void mouseDragged(final MouseEvent e) {setLocation(e.getXOnScreen()-offset.x, e.getYOnScreen()-offset.y);}});
    }

    public void addEvent(String event){
        String temp = highlightEventLabel.getText();
        highlightEventLabel.setText(event);
        Vector<String> vec = new Vector<>();
        vec.add(temp);
        dataVector.add(vec);
        if (dataVector.size() > maxEventNumber){
            dataVector.remove(0);
        }
        refreshGUI();
    }

    private void refreshGUI() {
        this.invalidate();
        this.revalidate();
        this.repaint();
        eventTable.invalidate();
        eventTable.revalidate();
        eventTable.repaint();
    }
}