package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField tfusername;
    JPasswordField tfpassword;
    JButton submit, reset, close;

    public Login() {
        setTitle("Login - Air India");
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background Image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/login.jpg"));
        Image bgImage = bgIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImage));
        background.setBounds(0, 0, 800, 500);
        add(background);

        // Login Panel (Card Style)
        // Login Panel (Centered & Larger)
int panelWidth = 360;
int panelHeight = 360;
int x = (800 - panelWidth) / 2;
int y = (500 - panelHeight) / 2;

JPanel loginPanel = new JPanel();
loginPanel.setLayout(null);
loginPanel.setBounds(x, y, panelWidth, panelHeight);
loginPanel.setBackground(new Color(255, 255, 255, 230));
loginPanel.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(0, 102, 204), 1),
    BorderFactory.createEmptyBorder(20, 20, 20, 20)
));
background.add(loginPanel);

JLabel title = new JLabel("Login");
title.setFont(new Font("Segoe UI", Font.BOLD, 28));
title.setForeground(new Color(0, 51, 102));
title.setBounds((panelWidth - 100) / 2, 10, 100, 40);
loginPanel.add(title);

JLabel lblusername = new JLabel("Username:");
lblusername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
lblusername.setBounds(30, 70, 100, 25);
loginPanel.add(lblusername);

tfusername = new JTextField();
tfusername.setBounds(130, 70, 180, 25);
tfusername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
loginPanel.add(tfusername);

JLabel lblpassword = new JLabel("Password:");
lblpassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
lblpassword.setBounds(30, 110, 100, 25);
loginPanel.add(lblpassword);

tfpassword = new JPasswordField();
tfpassword.setBounds(130, 110, 180, 25);
tfpassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
loginPanel.add(tfpassword);

submit = createButton("Login", new Color(34, 139, 34));
submit.setBounds(30, 180, 120, 35);
loginPanel.add(submit);
submit.addActionListener(this);

reset = createButton("Reset", new Color(255, 140, 0));
reset.setBounds(170, 180, 120, 35);
loginPanel.add(reset);
reset.addActionListener(this);

close = createButton("Close", new Color(178, 34, 34));
close.setBounds((panelWidth - 140) / 2, 240, 140, 35);
loginPanel.add(close);
close.addActionListener(this);


        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = String.valueOf(tfpassword.getPassword());

            try {
                Conn c = new Conn();
                String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    String role = "user";
                    if (username.equalsIgnoreCase("admin")) {
                        role = "admin";
                    }
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    dispose();
                    new Home(role);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        } else if (ae.getSource() == close) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
