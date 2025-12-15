package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class FlightInfo extends JFrame {

    BufferedImage bgImage;

    public FlightInfo() {
        setTitle("Flight Information");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocation(400, 200);

        // Load background image
        try {
            bgImage = ImageIO.read(getClass().getResource("/airlinemanagementsystem/icons/flightImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Background image not found.");
        }

        // Custom JPanel with faded background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    // Fade the background image only (opacity 0.4f)
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f)); // Background opacity
                    g2d.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // Title
        JLabel title = new JLabel("Available Flights");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.BLACK);  // Title text color remains solid
        title.setBounds(280, 10, 300, 30);
        backgroundPanel.add(title);

        // Table
        JTable table = new JTable();
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(24);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(30, 60, 720, 380);
        backgroundPanel.add(jsp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}
