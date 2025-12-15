package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfaadhar;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, flight, fetchButton;
    Choice source, destination;
    JDateChooser dcdate;

    public BookFlight() {
        setTitle("Book Flight");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        setSize(1100, 700);
        setLocation(200, 50);

        // Load background image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/f2.jpg"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1100, 700, Image.SCALE_SMOOTH);

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Heading with Light Glow
        JLabel heading = new JLabel("Book Flight") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setFont(getFont());
                g2.setColor(new Color(135, 206, 250, 180)); // Light Blue Glow
                for (int i = 0; i < 4; i++) { // draw multiple times for glow effect
                    g2.drawString(getText(), 2 - i, getHeight() - 8 + i);
                }
                g2.setColor(getForeground()); // Main Text
                g2.drawString(getText(), 0, getHeight() - 6);
                g2.dispose();
            }
        };
        heading.setBounds(420, 20, 400, 50);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 36));
        heading.setForeground(Color.WHITE);
        contentPane.add(heading);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(40, 80, 500, 520);
        formPanel.setBackground(new Color(255, 255, 255, 200)); // semi-transparent white
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        contentPane.add(formPanel);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        int y = 20;

        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(20, y, 150, 25);
        lblaadhar.setFont(labelFont);
        formPanel.add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(170, y, 150, 25);
        formPanel.add(tfaadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBounds(330, y, 120, 25);
        fetchButton.addActionListener(this);
        styleButton(fetchButton);
        formPanel.add(fetchButton);

        y += 40;
        formPanel.add(tfname = createLabel("Name", y));
        y += 40;
        formPanel.add(tfaddress = createLabel("Address", y));
        y += 40;
        formPanel.add(tfnationality = createLabel("Nationality", y));
        y += 40;
        formPanel.add(labelgender = createLabel("Gender", y));
        y += 40;

        source = new Choice();
        source.setBounds(170, y, 150, 25);
        formPanel.add(createStaticLabel("Source", y));
        formPanel.add(source);

        y += 40;
        destination = new Choice();
        destination.setBounds(170, y, 150, 25);
        formPanel.add(createStaticLabel("Destination", y));
        formPanel.add(destination);

        flight = new JButton("Fetch Flights");
        flight.setBounds(330, y, 120, 25);
        styleButton(flight);
        flight.addActionListener(this);
        formPanel.add(flight);

        y += 40;
        formPanel.add(labelfname = createLabel("Flight Name", y));
        y += 40;
        formPanel.add(labelfcode = createLabel("Flight Code", y));
        y += 40;

        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(20, y, 150, 25);
        lbldate.setFont(labelFont);
        formPanel.add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(170, y, 150, 25);
        formPanel.add(dcdate);

        y += 50;
        bookflight = new JButton("Book Flight");
        bookflight.setBounds(170, y, 150, 30);
        styleMainButton(bookflight);
        bookflight.addActionListener(this);
        formPanel.add(bookflight);

        // Load source and destination choices
        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    private JLabel createLabel(String field, int y) {
        JLabel lbl = new JLabel();
        lbl.setBounds(170, y, 200, 25);
        JLabel title = new JLabel(field);
        title.setBounds(20, y, 150, 25);
        title.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        ((JPanel) getContentPane().getComponent(1)).add(title);
        return lbl;
    }

    private JLabel createStaticLabel(String text, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(20, y, 150, 25);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        return lbl;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 153, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 102, 204));
            }
        });
    }

    private void styleMainButton(JButton btn) {
        btn.setBackground(new Color(34, 139, 34)); // Green color
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(50, 205, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(34, 139, 34));
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String aadhar = tfaadhar.getText();
            try {
                Conn conn = new Conn();
                String query = "select * from passenger where aadhar = '" + aadhar + "'";
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct Aadhar");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            try {
                Conn conn = new Conn();
                String query = "select * from flight where source = '" + src + "' and destination = '" + dest + "'";
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bookflight) {
            Random random = new Random();
            String aadhar = tfaadhar.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            try {
                Conn conn = new Conn();
                String query = "insert into reservation values ('PNR-" + random.nextInt(1000000) + "','TIC-" + random.nextInt(10000) + "', '" + aadhar + "', '" + name + "','" + nationality + "','" + flightname + "','" + flightcode + "','" + src + "','" + des + "','" + ddate + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
