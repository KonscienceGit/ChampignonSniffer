package gui;

import gui.swingComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static resources.Constants.*;

public final class ChampignonScreen extends JFrame implements ActionListener {
    //Boolean states for buttons
    private boolean needToReload = false;
    public void setNeedToReload(boolean needToReload) {
        this.needToReload = needToReload;
    }
    public boolean isNeedToReload() {
        return needToReload;
    }

    //max Size of starclassmap
    private static final int maxStarClassMapSize = 10;

    //Panels
    private ElitePanel miningPanel = new ElitePanel();
    private ElitePanel marketPanel = new ElitePanel();

    //////////////////////
    //General Super Panel:
    private ElitePanel mainPannel = new ElitePanel();
    private EliteTabbedPanel tabsPanel = new EliteTabbedPanel();
    private ElitePanel gShipInfoLine = new ElitePanel();
    private ElitePanel gStarsInfo = new ElitePanel();
    private ElitePanel gSystemInfoLine = new ElitePanel();
    //Labels
    private EliteLabel gShipModelLabel = new EliteLabel();
    private EliteLabel gShipNameLabel = new EliteLabel();
    private EliteLabel gFuelLabel = new EliteLabel("Fuel: ");
    private EliteLabel gMoneyLabel =  new EliteLabel();
    private EliteLabel gMainStarClassTextLabel = new EliteLabel("Main Star Class: ");
    private EliteLabel gMainStarClassLabel = new EliteLabel("?");
    private EliteLabel gStarClassMapTextLabel = new EliteLabel("System Stars: ");
    private static EliteLabel[] gStarClassMapLabel = new EliteLabel[maxStarClassMapSize];
    private EliteLabel gSystemLabel = new EliteLabel("System: ?");
    private EliteLabel gSystemPopulationLabel = new EliteLabel("Population: ?");
    private EliteLabel gSystemSecurityLabel = new EliteLabel();
    private EliteLabel gSystemAllegianceLabel = new EliteLabel();
    //Setters
    public void setgShipModelLabel(String text) {
        gShipModelLabel.setText(text);
    }
    public void setgShipNameLabel(String text) {
        gShipNameLabel.setText(text);
    }
    public void setgMoneyLabel(int value) {
        gMoneyLabel.setText("Credits: "+value);
    }
    public void setgFuelLabel(float value,float capacity) {
        gFuelLabel.setText("Fuel: "+df.format(value)+" / "+df.format(capacity)+"t");
    }
    public void setgMainStarClassLabel(String text) {
        gMainStarClassLabel.setText(text);
        if (text != null && hydrogenStarClass.contains(text)){
            gMainStarClassLabel.setForeground(starClassColorMap.get(text));
        }else{
            gMainStarClassLabel.setForeground(textEliteColor);
        }
    }
    public void setgSystemLabel(String text) {
        gSystemLabel.setText("System: "+text);
    }
    public void setgSystemPopulationLabel(int value) {
        gSystemPopulationLabel.setText("Population: "+value);
    }
    public void setgSystemSecurityLabel(String text) {
        if(text.equals("")){
            gSystemSecurityLabel.setText("");
        }else{
            gSystemSecurityLabel.setText("Secutity: "+text);
        }
    }
    public void setgSystemAllegianceLabel(String text) {
        if(text.equals("")){
            gSystemAllegianceLabel.setText("");
        }else{
            gSystemAllegianceLabel.setText("Allegiance: "+text);
        }
    }

    //////////////////
    //Discovery Tab
    private ElitePanel discoveryPanel = new ElitePanel();
    private EliteTabbedPanel dTabbedLeftPanel = new EliteTabbedPanel();
    private EliteScrollPane dSubTabBodiesScrollPane = new EliteScrollPane();
    private EliteTable dSubTabBodiesTable = new EliteTable();
    private EliteScrollPane dSubTabCodexScrollPane = new EliteScrollPane();
    private EliteTable dSubTabCodexTable = new EliteTable();
    private ElitePanel dSubRightPanel = new ElitePanel();

    //////////////////
    //Combat Tab
    private ElitePanel combatPanel = new ElitePanel();
    private ElitePanel cGameModeLine = new ElitePanel();
    //Labels
    private EliteLabel cGameModeLabel =  new EliteLabel();
    //Setters
    public void setcGameModeLabel(String text) {
        this.cGameModeLabel.setText("Game Mode: "+text);
    }

    /////////////////
    //Settings Tab
    private ElitePanel settingsPanel = new ElitePanel();
    private ElitePanel sReloadLine = new ElitePanel();
    //Buttons
    private EliteButton sReloadBtn = new EliteButton("Reload latest Journal.log");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constructor / SETUP /////////////////////////////////////////////////////////////////////////////////////////////

    public ChampignonScreen() {
        //gStarClassMapLabel[0] = new EliteLabel();
        ////////////////////
        //Windows properties
        addListeners();//allow it to be draggable by clicking anywhere
        setLocationRelativeTo(null); //center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//end the process on clicking the red cross
        setSize(1024, 720);
        setVisible(true);
        setTitle("Champignon Sniffer 3000");
        setFont(eliteFontPlain28);
        setForeground(textEliteColor);
        setBackground(backScreenEliteColor);

        ////////////////////////////////////////////
        //Panels
        //General panel
        mainPannel.setLayout(new BoxLayout(mainPannel, BoxLayout.PAGE_AXIS)); //Vertical Box layout for the whole window
        setContentPane(mainPannel);

        //generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.PAGE_AXIS));
        mainPannel.add(gShipInfoLine);
        mainPannel.add(new EliteSeparator(SwingConstants.HORIZONTAL));
        mainPannel.add(gStarsInfo);
        mainPannel.add(new EliteSeparator(SwingConstants.HORIZONTAL));
        mainPannel.add(gSystemInfoLine);

        //Lines filling
        gShipInfoLine.setLayout(new BoxLayout(gShipInfoLine,BoxLayout.LINE_AXIS));
        gShipInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gShipInfoLine.add(gShipModelLabel);
        gShipInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gShipInfoLine.add(gShipNameLabel);
        gShipInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gShipInfoLine.add(new EliteSeparator(SwingConstants.VERTICAL));
        gShipInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gShipInfoLine.add(gFuelLabel);
        gShipInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gShipInfoLine.add(new EliteSeparator(SwingConstants.VERTICAL));
        gShipInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gShipInfoLine.add(gMoneyLabel);
        gShipInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gShipInfoLine.setMaximumSize(new Dimension(4096,EliteLabel.getFontSize()+5));

        gStarsInfo.setLayout(new BoxLayout(gStarsInfo,BoxLayout.LINE_AXIS));
        gStarsInfo.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gStarsInfo.add(gMainStarClassTextLabel);
        gStarsInfo.add(gMainStarClassLabel);
        gStarsInfo.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gStarsInfo.add(new EliteSeparator(SwingConstants.VERTICAL));
        gStarsInfo.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gStarsInfo.add(gStarClassMapTextLabel);
        for (int i = 0; i <maxStarClassMapSize; i++ ) {
            gStarClassMapLabel[i] = new EliteLabel();
            gStarsInfo.add(gStarClassMapLabel[i]);
        }
        gStarsInfo.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gStarsInfo.setMaximumSize(new Dimension(4096,EliteLabel.getFontSize()+5));

        gSystemInfoLine.setLayout(new BoxLayout(gSystemInfoLine,BoxLayout.LINE_AXIS));
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.add(gSystemLabel);
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.add(new EliteSeparator(SwingConstants.VERTICAL));
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.add(gSystemPopulationLabel);
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.add(new EliteSeparator(SwingConstants.VERTICAL));
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.add(gSystemSecurityLabel);
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.add(new EliteSeparator(SwingConstants.VERTICAL));
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.add(gSystemAllegianceLabel);
        gSystemInfoLine.add(new EliteSpace(SwingConstants.HORIZONTAL));
        gSystemInfoLine.setMaximumSize(new Dimension(4096,EliteLabel.getFontSize()+5));

        //Tab Panel
        mainPannel.add(tabsPanel);
        ImageIcon icon = null; //createImageIcon("images/middle.gif");
        tabsPanel.addTab("Discovery", icon, discoveryPanel,"Info on current system and important celestial objects");
        //tabsPanel.setMnemonicAt(0, KeyEvent.VK_1); //to add keyboard shortcuts to access the tabs
        tabsPanel.addTab("Mining", icon, miningPanel,"Info on prospector scans and asteroid belt detector");
        //tabsPanel.setMnemonicAt(1, KeyEvent.VK_2);
        tabsPanel.addTab("Market", icon, marketPanel,"Info on last visited Market");
        //tabsPanel.setMnemonicAt(2, KeyEvent.VK_3);
        tabsPanel.addTab("Combat", icon, combatPanel,"Info on scans and aggressors");
        //tabsPanel.setMnemonicAt(3, KeyEvent.VK_4);
        tabsPanel.addTab("Settings", icon, settingsPanel,"Change parameters of the Champignon Sniffer 3000");
        //tabsPanel.setMnemonicAt(4, KeyEvent.VK_5);

        //////
        //Discovery Panel
        discoveryPanel.setLayout(new BoxLayout(discoveryPanel,BoxLayout.LINE_AXIS));
        discoveryPanel.add(dTabbedLeftPanel);
        discoveryPanel.add(dSubRightPanel);
        dTabbedLeftPanel.addTab("Bodies & Signals", icon, dSubTabBodiesScrollPane,"List of astronomical bodies and signals detected in the system");
        //tabsPanel.setMnemonicAt(0, KeyEvent.VK_1);
        dTabbedLeftPanel.addTab("Local Codex entries", icon, dSubTabCodexScrollPane,"List of Codex entries discovered in the system");
        //tabsPanel.setMnemonicAt(1, KeyEvent.VK_2);
        dSubTabBodiesScrollPane.add(dSubTabBodiesTable);
        dSubTabCodexScrollPane.add(dSubTabCodexTable);
        //.getImage(getClass().getResource("/package1/package2/dump.jpg")));

        ///////
        //Combat Panel
        combatPanel.setLayout(new BoxLayout(combatPanel,BoxLayout.PAGE_AXIS));
        combatPanel.add(cGameModeLine);
        //Lines filling
        cGameModeLine.setLayout(new BoxLayout(cGameModeLine,BoxLayout.LINE_AXIS));
        cGameModeLine.add(cGameModeLabel);

        ///////
        //Settigns Panel
        settingsPanel.setLayout(new BoxLayout(settingsPanel,BoxLayout.PAGE_AXIS));
        settingsPanel.add(sReloadLine);
        //Lines filling
        sReloadLine.setLayout(new BoxLayout(sReloadLine,BoxLayout.LINE_AXIS));
        sReloadLine.add(sReloadBtn);
        sReloadBtn.addActionListener(this);

        refreshGUI();
    }//end Constructor

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateStarClasses(Vector<String> starClassVec){
        //Cleaning the GUI
        for (int i = 0; i < maxStarClassMapSize; i++){
            gStarClassMapLabel[i].setText("");
        }
        int vecSize = starClassVec.size();
        if (vecSize > maxStarClassMapSize ){vecSize = maxStarClassMapSize;}
        for (int i = 0; i < vecSize; i++){
            String classMap = starClassVec.get(i);
            if (classMap != null){
                if (i == vecSize-1){
                    gStarClassMapLabel[i].setText(classMap);
                }else{
                    gStarClassMapLabel[i].setText(classMap+", ");
                }
                Color starColor = starClassColorMap.get(classMap);
                if(starColor != null){
                    gStarClassMapLabel[i].setForeground(starColor);
                }else{
                    gStarClassMapLabel[i].setForeground(textEliteColor);
                }
            }
        }
    }

    private void addListeners() {
        //Magic that allow to drag the window
        final Point offset = new Point();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                offset.setLocation(e.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent e) {
                setLocation(e.getXOnScreen()-offset.x, e.getYOnScreen()-offset.y);
            }
        });
    }

    private void refreshGUI() {
        this.invalidate();
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /////////////////////////////////////
        //Click on Bouton Reload
        if (e.getSource() == sReloadBtn) {
            needToReload = true;
        }
    }
}