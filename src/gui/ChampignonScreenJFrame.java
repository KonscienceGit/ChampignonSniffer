package gui;

import controller.Controller;
import gui.swingComponents.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import static tools.Constants.*;

public final class ChampignonScreenJFrame extends JFrame implements ActionListener {
    private Controller _controller;
    private static final int _maxStarClassMapSize = 10;


    //////////////////////
    //General Super Panel:
    private ElitePanel mainPanel = new ElitePanel();
    private EliteTabbedPanel tabsPanel = new EliteTabbedPanel();
    private ElitePanel gShipInfoLine = new ElitePanel();
    private ElitePanel gStarsInfo = new ElitePanel();
    private ElitePanel gSystemInfoLine = new ElitePanel();
    private ElitePanel miningPanel = new ElitePanel();
    private ElitePanel marketPanel = new ElitePanel();
    //Labels
    private EliteLabel gShipModelLabel = new EliteLabel();
    private EliteLabel gShipNameLabel = new EliteLabel();
    private EliteLabel gFuelLabel = new EliteLabel("Fuel: ");
    private EliteLabel gMoneyLabel =  new EliteLabel();
    private EliteLabel gMainStarClassTextLabel = new EliteLabel("Main Star Class: ");
    private EliteLabel gMainStarClassLabel = new EliteLabel("?");
    private EliteLabel gStarClassMapTextLabel = new EliteLabel("System Stars: ");
    private static EliteLabel[] gStarClassMapLabel = new EliteLabel[_maxStarClassMapSize];
    private EliteLabel gSystemLabel = new EliteLabel("System: ?");
    private EliteLabel gSystemPopulationLabel = new EliteLabel("Population: ?");
    private EliteLabel gSystemSecurityLabel = new EliteLabel();
    private EliteLabel gSystemAllegianceLabel = new EliteLabel();

    //Discovery Tab
    private ElitePanel discoveryPanel = new ElitePanel();
    private EliteTabbedPanel dTabbedLeftPanel = new EliteTabbedPanel();
    private EliteScrollPane dSubTabBodiesScrollPane = new EliteScrollPane();
    private EliteTable dSubTabBodiesTable = new EliteTable();
    private EliteScrollPane dSubTabCodexScrollPane = new EliteScrollPane();
    private EliteTable dSubTabCodexTable = new EliteTable();
    private ElitePanel dSubRightPanel = new ElitePanel();

    //Combat Tab
    private ElitePanel combatPanel = new ElitePanel();
    private ElitePanel cGameModeLine = new ElitePanel();
    private EliteLabel cGameModeLabel =  new EliteLabel();

    //Settings Tab
    private ElitePanel settingsPanel = new ElitePanel();
    private EliteButton sReloadBtn = new EliteButton("Reload latest Journal.log");
    private EliteButton sExitBtn = new EliteButton("Exit program");

    public ChampignonScreenJFrame(Controller controller) {
        _controller = controller;

        //Windows properties
        addListeners();
        setLocationRelativeTo(null); //center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//end the process on clicking the red cross
        setSize(1024, 720);
        setVisible(true);
        setTitle(PROGRAM_NAME);
        setFont(ELITE_FONT_PLAIN_28);
        setForeground(TEXT_ELITE_COLOR);
        setBackground(BACK_SCREEN_ELITE_COLOR);

        //Panels
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)); //Vertical Box layout for the whole window
        setContentPane(mainPanel);

        //generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.PAGE_AXIS));
        mainPanel.add(gShipInfoLine);
        mainPanel.add(new EliteSeparator(SwingConstants.HORIZONTAL));
        mainPanel.add(gStarsInfo);
        mainPanel.add(new EliteSeparator(SwingConstants.HORIZONTAL));
        mainPanel.add(gSystemInfoLine);

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
        for (int i = 0; i < _maxStarClassMapSize; i++ ) {
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
        mainPanel.add(tabsPanel);
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

        //Combat Panel
        combatPanel.setLayout(new BoxLayout(combatPanel,BoxLayout.PAGE_AXIS));
        combatPanel.add(cGameModeLine);
        cGameModeLine.setLayout(new BoxLayout(cGameModeLine,BoxLayout.LINE_AXIS));
        cGameModeLine.add(cGameModeLabel);

        //Settings Panel
        settingsPanel.setLayout(new BoxLayout(settingsPanel,BoxLayout.PAGE_AXIS));
        settingsPanel.add(sReloadBtn);
        sReloadBtn.addActionListener(this);
        sReloadBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        settingsPanel.add(sExitBtn);
        sExitBtn.addActionListener(this);

        refreshGUI();
    }//end Constructor

    //Setters
    //Game Session, ship status
    public void setgShipModelLabel(String text) {
        gShipModelLabel.setText(text);
    }
    public void setgShipNameLabel(String text) {
        gShipNameLabel.setText(text);
    }
    public void setgMoneyLabel(long value) {
        gMoneyLabel.setText("Credits: "+value);
    }
    public void setgFuelLabel(float value,float capacity) {
        gFuelLabel.setText("Fuel: "+DECIMAL_FORMAT.format(value)+" / "+DECIMAL_FORMAT.format(capacity)+"t");
    }
    public void setgMainStarClassLabel(String text) {
        gMainStarClassLabel.setText(text);
        if (text != null && HYDROGEN_STAR_CLASS.contains(text)){
            gMainStarClassLabel.setForeground(STAR_CLASS_COLOR_MAP.get(text));
        }else{
            gMainStarClassLabel.setForeground(TEXT_ELITE_COLOR);
        }
    }
    public void setcGameModeLabel(String text) {
        this.cGameModeLabel.setText("Game Mode: "+text);
    }

    //Star System update
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

    public void updateStarClasses(Vector<String> starClassVec){
        //Cleaning the GUI
        for (int i = 0; i < _maxStarClassMapSize; i++){
            gStarClassMapLabel[i].setText("");
        }
        int vecSize = starClassVec.size();
        if (vecSize > _maxStarClassMapSize){vecSize = _maxStarClassMapSize;}
        for (int i = 0; i < vecSize; i++){
            String classMap = starClassVec.get(i);
            if (classMap != null){
                if (i == vecSize-1){
                    gStarClassMapLabel[i].setText(classMap);
                }else{
                    gStarClassMapLabel[i].setText(classMap+", ");
                }
                Color starColor = STAR_CLASS_COLOR_MAP.get(classMap);
                if(starColor != null){
                    gStarClassMapLabel[i].setForeground(starColor);
                }else{
                    gStarClassMapLabel[i].setForeground(TEXT_ELITE_COLOR);
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
        //Click on button Reload
        if (e.getSource() == sReloadBtn) {
            _controller.setLoadLatestLog(true);
        } else if(e.getSource() == sExitBtn){
            _controller.setExitProgram();
        }
    }
}