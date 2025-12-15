package airlinemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JPanel sidePanel, mainPanel;
    String userRole;

    public Home(String userRole) {
        this.userRole = userRole;
        setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Load original image
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/Airfront.jpg"));
        Image originalImage = originalIcon.getImage();

        // Maintain aspect ratio
        double imgWidth = originalIcon.getIconWidth();
        double imgHeight = originalIcon.getIconHeight();
        double scaleFactor = Math.min(screenWidth / imgWidth, screenHeight / imgHeight);

        int newWidth = (int) (imgWidth * scaleFactor);
        int newHeight = (int) (imgHeight * scaleFactor);

       Image scaledImage = originalImage.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);


        // Background Image Panel
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setLayout(null);
        add(background);

        // Overlay Welcome Text
	JLabel welcomeLabel = new JLabel("Welcome to Air India", 	SwingConstants.CENTER);
	welcomeLabel.setBounds(screenWidth / 2 - 300, 50, 600, 60);
	welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 42));
	welcomeLabel.setForeground(new Color(0, 0, 128)); // Navy blue
	welcomeLabel.setOpaque(false);
	background.add(welcomeLabel);


        // Side Panel
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(0, 51, 102));
        sidePanel.setBounds(0, 0, 250, screenHeight);
        sidePanel.setLayout(null);
        background.add(sidePanel);

        JLabel logo = new JLabel("✈️ AIR INDIA");
        logo.setBounds(30, 30, 200, 40);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Poppins", Font.BOLD, 24));
        sidePanel.add(logo);

        // Navigation Buttons
        String[] menuItems = {"Dashboard", "Flights", "Book Flight", "Passengers", "Journey Details", "Cancel Ticket", "Boarding Pass"};
        int y = 120;
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setBounds(20, y, 200, 40);
            button.setFocusPainted(false);
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(0, 76, 153));
            button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setBorderPainted(false);
            button.addActionListener(this);
            button.setActionCommand(item);
            sidePanel.add(button);
            y += 60;
        }

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setBounds(250, 0, screenWidth - 250, screenHeight);
        mainPanel.setLayout(null);
        background.add(mainPanel);

     int rightMargin = 40;
int buttonSpacing = 20;
int buttonWidth = 120;
int buttonHeight = 40;

// Calculate width relative to mainPanel, not full screen
int mainPanelWidth = screenWidth - 250;

// Login Button
JButton loginButton = new JButton("Login");
loginButton.setBounds(mainPanelWidth - (2 * buttonWidth + buttonSpacing + rightMargin), 30, buttonWidth, buttonHeight);
loginButton.setFocusPainted(false);
loginButton.setFont(new Font("Poppins", Font.BOLD, 16));
loginButton.setBackground(new Color(0, 153, 76));
loginButton.setForeground(Color.WHITE);
loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
loginButton.addActionListener(this);
loginButton.setActionCommand("Login");
mainPanel.add(loginButton);

// Signup Button
JButton signupButton = new JButton("Signup");
signupButton.setBounds(mainPanelWidth - (buttonWidth + rightMargin), 30, buttonWidth, buttonHeight);
signupButton.setFocusPainted(false);
signupButton.setFont(new Font("Poppins", Font.BOLD, 16));
signupButton.setBackground(new Color(51, 153, 255));
signupButton.setForeground(Color.WHITE);
signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
signupButton.addActionListener(this);
signupButton.setActionCommand("Signup");
mainPanel.add(signupButton);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        switch (command) {
            case "Login":
                JOptionPane.showMessageDialog(null, "Redirecting to Login Page...");
                new Login();
                break;
            case "Signup":
                JOptionPane.showMessageDialog(null, "Redirecting to Signup Page...");
                new Signup();
                break;
            case "Dashboard":
                JOptionPane.showMessageDialog(null, "You are already at Dashboard.");
                break;
            case "Flights":
                new FlightInfo();
                break;
            case "Book Flight":
                new BookFlight();
                break;
            case "Passengers":
                new AddCustomer();
                break;
            case "Journey Details":
                new journeyDetails();
                break;
            case "Cancel Ticket":
                new Cancel();
                break;
            case "Boarding Pass":
                new BoardingPass();
                break;
        }
    }

    public static void main(String[] args) {
        new Home("admin");
    }
}
