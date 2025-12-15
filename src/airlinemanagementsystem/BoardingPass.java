package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BoardingPass extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;
    JButton fetchButton;
    BufferedImage watermarkImage;

    public BoardingPass() {
        // Load watermark image
        try {
            watermarkImage = ImageIO.read(getClass().getResource("/airlinemanagementsystem/icons/airindia_watermark.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create custom panel with watermark
        JPanel contentPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (watermarkImage != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.06f)); // faded watermark
                    g2d.drawImage(watermarkImage, 100, 50, getWidth() - 200, getHeight() - 100, this);
                    g2d.dispose();
                }
            }
        };
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        setContentPane(contentPanel);

        // Title
        JLabel heading = new JLabel("✈ AIR INDIA");
        heading.setBounds(380, 10, 300, 35);
        heading.setFont(new Font("Serif", Font.BOLD, 32));
        heading.setForeground(new Color(0xBA0C2F));
        contentPanel.add(heading);

        // Subheading
        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(380, 50, 300, 30);
        subheading.setFont(new Font("SansSerif", Font.BOLD, 22));
        subheading.setForeground(Color.DARK_GRAY);
        contentPanel.add(subheading);

        // PNR
        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60, 110, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPanel.add(lblaadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(180, 110, 150, 25);
        contentPanel.add(tfpnr);

        fetchButton = new JButton("Fetch Details");
        fetchButton.setBackground(new Color(0xBA0C2F));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(350, 110, 150, 25);
        fetchButton.addActionListener(this);
        contentPanel.add(fetchButton);

        // Passenger Details
        tfname = createLabel(contentPanel, "Name", 60, 160);
        tfnationality = createLabel(contentPanel, "Nationality", 60, 200);
        lblsrc = createLabel(contentPanel, "From", 60, 240);
        lbldest = createLabel(contentPanel, "To", 400, 240);
        labelfname = createLabel(contentPanel, "Flight Name", 60, 280);
        labelfcode = createLabel(contentPanel, "Flight Code", 400, 280);
        labeldate = createLabel(contentPanel, "Date of Travel", 60, 320);

        // Window settings
        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }

    // Helper method to add field labels
    private JLabel createLabel(JPanel panel, String title, int x, int y) {
        JLabel titleLabel = new JLabel(title + ":");
        titleLabel.setBounds(x, y, 120, 25);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(titleLabel);

        JLabel valueLabel = new JLabel();
        valueLabel.setBounds(x + 130, y, 200, 25);
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(valueLabel);

        return valueLabel;
    }

    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM reservation WHERE PNR = '" + pnr + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                lblsrc.setText(rs.getString("src"));
                lbldest.setText(rs.getString("des"));
                labelfname.setText(rs.getString("flightname"));
                labelfcode.setText(rs.getString("flightcode"));
                labeldate.setText(rs.getString("ddate"));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid PNR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}
