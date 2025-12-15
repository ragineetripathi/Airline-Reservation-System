package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    JTextField tfUsername;
    JPasswordField tfPassword;
    JButton signupBtn, backBtn;

    public Signup() {
    
    setTitle("Signup - Air India");
    setSize(500, 400);
    setLocationRelativeTo(null); // center on screen
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Set gradient background
    GradientPanel background = new GradientPanel();
    background.setLayout(null);
    setContentPane(background);

    // Card-style panel
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBounds(70, 50, 350, 280);
    panel.setBackground(Color.WHITE);
    panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0, 102, 204), 1),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));
    background.add(panel);

    // Heading
    JLabel heading = new JLabel("Create New Account");
    heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
    heading.setForeground(new Color(0, 51, 102));
    heading.setBounds(60, 10, 250, 30);
    panel.add(heading);

    // Username
    JLabel lblUsername = new JLabel("Username:");
    lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblUsername.setBounds(30, 70, 100, 25);
    panel.add(lblUsername);

    tfUsername = new JTextField();
    tfUsername.setBounds(130, 70, 180, 25);
    tfUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    panel.add(tfUsername);

    // Password
    JLabel lblPassword = new JLabel("Password:");
    lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblPassword.setBounds(30, 110, 100, 25);
    panel.add(lblPassword);

    tfPassword = new JPasswordField();
    tfPassword.setBounds(130, 110, 180, 25);
    tfPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    panel.add(tfPassword);

    // Signup Button
    signupBtn = new JButton("Signup");
    signupBtn.setBounds(40, 180, 120, 35);
    signupBtn.setBackground(new Color(0, 153, 76));
    signupBtn.setForeground(Color.WHITE);
    signupBtn.setFocusPainted(false);
    signupBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
    signupBtn.addActionListener(this);
    panel.add(signupBtn);

    // Back Button (Black)
    backBtn = new JButton("Back");
    backBtn.setBounds(180, 180, 120, 35);
    backBtn.setBackground(Color.BLACK);
    backBtn.setForeground(Color.WHITE);
    backBtn.setFocusPainted(false);
    backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
    backBtn.addActionListener(this);
    panel.add(backBtn);

    setVisible(true);
}
   

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signupBtn) {
           // String name = tfName.getText();
            String username = tfUsername.getText();
            String password = new String(tfPassword.getPassword());

            if (  username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
                return;
            }

            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password must be at least 10 characters and include an uppercase letter, a digit, and a special character.");
                return;
            }

            try {
                Conn c = new Conn();
                PreparedStatement ps = c.c.prepareStatement("INSERT INTO login ( username, password, role) VALUES ( ?, ?, ?)");
             
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, "user");
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Signup Successful! You can now login.");
                dispose();
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Signup Failed. Username may already exist.");
            }
        } else if (ae.getSource() == backBtn) {
            dispose();
            new Welcome();
        }
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 10) return false;

        boolean hasUpper = false, hasDigit = false, hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasUpper && hasDigit && hasSpecial;
    }
   
    public static void main(String[] args) {
        new Signup();
    }
}
class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        Color color1 = new Color(0, 153, 255);
        Color color2 = new Color(204, 229, 255);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
