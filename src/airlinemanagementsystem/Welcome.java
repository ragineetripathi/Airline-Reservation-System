package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Welcome extends JFrame implements ActionListener {
    JButton loginButton, signupButton;
    BufferedImage backgroundImage;

    public Welcome() {
        // Load background image
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/airlinemanagementsystem/icons/welcome_bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Panel with faded background
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f)); // fade level
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    g2d.dispose();
                }
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        JLabel welcomeLabel = new JLabel("Welcome to Air India");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 36));
        welcomeLabel.setForeground(new Color(0xBA0C2F));
        welcomeLabel.setBounds(200, 60, 400, 40);
        backgroundPanel.add(welcomeLabel);

        loginButton = createStyledButton("Login");
        loginButton.setBounds(230, 150, 100, 40);
        backgroundPanel.add(loginButton);

        signupButton = createStyledButton("Signup");
        signupButton.setBounds(370, 150, 100, 40);
        backgroundPanel.add(signupButton);

        setTitle("Air India - Welcome");
        setSize(700, 400);
        setLocationRelativeTo(null); // center
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(0xBA0C2F));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            dispose();
            new Login();
        } else if (ae.getSource() == signupButton) {
            dispose();
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Welcome();
    }
}
