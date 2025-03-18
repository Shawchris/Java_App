package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main application class that serves as the entry point and window manager
 */
public class MainApplication {
    
    private static final String APP_TITLE = "My Java Application";
    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 600;
    

    private JFrame mainFrame;
    
    private JMenuBar menuBar;
    
    
    private boolean isDarkMode = false;
    
    /**
     * Application constructor
     */
    public MainApplication() {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }
    }
    
    /**
     * Initialize and display the main application window
     */
    public void initialize() {
        
        mainFrame = new JFrame(APP_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(APP_WIDTH, APP_HEIGHT);
        mainFrame.setLocationRelativeTo(null);  

       
        createMenuBar();
        mainFrame.setJMenuBar(menuBar);
        
    
        JPanel contentPanel = new JPanel(new CardLayout());
        mainFrame.add(contentPanel, BorderLayout.CENTER);
        
        
        contentPanel.add(createWelcomePanel(), "WELCOME");
        
        
        mainFrame.add(createStatusBar(), BorderLayout.SOUTH);
        
   
        mainFrame.setVisible(true);
    }
    
    /**
     * Create the application menu bar
     */
    private void createMenuBar() {
        menuBar = new JMenuBar();
        
      
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem newItem = new JMenuItem("New", KeyEvent.VK_N);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newItem.addActionListener(e -> openNewWindow());
        
        JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        
        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic(KeyEvent.VK_T);
        
        JMenuItem settingsItem = new JMenuItem("Settings", KeyEvent.VK_S);
        settingsItem.addActionListener(e -> openSettingsWindow());
        
        JCheckBoxMenuItem darkModeItem = new JCheckBoxMenuItem("Dark Mode");
        darkModeItem.addActionListener(e -> toggleDarkMode());
        
        toolsMenu.add(settingsItem);
        toolsMenu.add(darkModeItem);
        
       
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutItem.addActionListener(e -> showAboutDialog());
        
        helpMenu.add(aboutItem);
        
      
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
    }
    
    /**
     * Create the welcome panel
     */
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Welcome title
        JLabel titleLabel = new JLabel("OHHHH Clicky Clicky");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Center panel with buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Create module buttons
        JButton[] moduleButtons = new JButton[]{
            createModuleButton("Text Editor", "Open a simple text editor", e -> openTextEditor()),
            createModuleButton("Calculator", "Open a calculator", e -> openCalculator()),
            createModuleButton("File Browser", "Browse files on your system", e -> openFileBrowser()),
            createModuleButton("Settings", "Configure application settings", e -> openSettingsWindow())
        };
        
        // Add buttons to center panel
        for (JButton button : moduleButtons) {
            centerPanel.add(button, gbc);
        }
        
        panel.add(centerPanel, BorderLayout.CENTER);
        
        // Footer
        JLabel footerLabel = new JLabel("Things may work and also may not");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(footerLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Create a styled module button
     */
    private JButton createModuleButton(String title, String description, ActionListener action) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        button.setPreferredSize(new Dimension(300, 80));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        button.add(titleLabel, BorderLayout.NORTH);
        button.add(descLabel, BorderLayout.CENTER);
        
        button.addActionListener(action);
        return button;
    }
    
    /**
     * Create a status bar for the application
     */
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        
        JLabel statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        statusBar.add(statusLabel, BorderLayout.WEST);
        
        return statusBar;
    }
    
    /**
     * Open text editor window
     */
    private void openTextEditor() {
        TextEditorWindow editor = new TextEditorWindow();
        editor.setVisible(true);
    }
    
    /**
     * Open calculator window
     */
    private void openCalculator() {
        CalculatorWindow calculator = new CalculatorWindow();
        calculator.setVisible(true);
    }
    
    /**
     * Open file browser window
     */
    private void openFileBrowser() {
        FileBrowserWindow browser = new FileBrowserWindow();
        browser.setVisible(true);
    }
    
    /**
     * Open settings window
     */
    private void openSettingsWindow() {
        SettingsWindow settings = new SettingsWindow(isDarkMode);
        settings.setVisible(true);
    }
    
    /**
     * Open a new main application window
     */
    private void openNewWindow() {
        MainApplication newApp = new MainApplication();
        newApp.initialize();
    }
    
    /**
     * Toggle dark mode
     */
    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        // In a real app, you would implement the dark mode UI changes here
        JOptionPane.showMessageDialog(mainFrame, 
            "Dark mode " + (isDarkMode ? "enabled" : "disabled") + 
            "\n(Full implementation would change the UI theme)",
            "Theme Changed", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Show about dialog
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(mainFrame,
            "My Java Application v1.0\n" +
            "Copyright © 2025 Your Name\n\n" +
            "A learning project for Java application development.",
            "About My Java Application",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Application entry point
     */
    public static void main(String[] args) {
        // Ensure UI updates run on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.initialize();
        });
    }
}




public class MainApplication {
    
}
