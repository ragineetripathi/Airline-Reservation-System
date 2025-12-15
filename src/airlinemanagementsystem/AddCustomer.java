package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone, tfaadhar, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;
    Image backgroundImage;

    public AddCustomer() {
        setTitle("Add Passenger Details");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocation(300, 100);

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/airlinemanagementsystem/icons/f3.jpg")); // adjust path
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Custom panel with background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f)); // faded effect
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    g2d.dispose();
                }
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // Transparent panel for form
        JPanel formPanel = new JPanel();
        formPanel.setBounds(120, 50, 650, 460);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(255, 255, 255, 200)); // semi-transparent white
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));
        backgroundPanel.add(formPanel);

        JLabel heading = new JLabel("Add Passenger Details");
        heading.setBounds(170, 10, 400, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(25, 118, 210));
        formPanel.add(heading);

        String[] labels = {"Name:", "Nationality:", "Aadhar Number:", "Address:", "Gender:", "Phone No:"};
        int y = 70;
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        Color textColor = Color.DARK_GRAY;

        JLabel[] jlabels = new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            jlabels[i] = new JLabel(labels[i]);
            jlabels[i].setBounds(50, y, 150, 25);
            jlabels[i].setFont(labelFont);
            jlabels[i].setForeground(textColor);
            formPanel.add(jlabels[i]);
            y += 40;
        }

        tfname = new JTextField(); tfname.setBounds(220, 70, 200, 25); formPanel.add(tfname);
        tfnationality = new JTextField(); tfnationality.setBounds(220, 110, 200, 25); formPanel.add(tfnationality);
        tfaadhar = new JTextField(); tfaadhar.setBounds(220, 150, 200, 25); formPanel.add(tfaadhar);
        tfaddress = new JTextField(); tfaddress.setBounds(220, 190, 200, 25); formPanel.add(tfaddress);
        tfphone = new JTextField(); tfphone.setBounds(220, 270, 200, 25); formPanel.add(tfphone);

        rbmale = new JRadioButton("Male");
        rbfemale = new JRadioButton("Female");
        rbmale.setBounds(220, 230, 70, 25);
        rbfemale.setBounds(300, 230, 100, 25);
        rbmale.setBackground(new Color(255, 255, 255, 200));
        rbfemale.setBackground(new Color(255, 255, 255, 200));
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbmale);
        genderGroup.add(rbfemale);
        formPanel.add(rbmale);
        formPanel.add(rbfemale);

        JButton save = new JButton("Save");
        save.setBackground(new Color(25, 118, 210));
        save.setForeground(Color.WHITE);
        save.setFont(new Font("Segoe UI", Font.BOLD, 16));
        save.setFocusPainted(false);
        save.setBorder(BorderFactory.createEmptyBorder());
        save.setBounds(220, 330, 120, 35);
        save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        save.addActionListener(this);
        formPanel.add(save);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String aadhar = tfaadhar.getText();
        String gender = null;
        if (rbmale.isSelected()) gender = "Male";
        else if (rbfemale.isSelected()) gender = "Female";

        try {
            Conn conn = new Conn();
            String query = "insert into passenger values('" + name + "','" + nationality + "','" + phone + "','" + address + "','" + aadhar + "','" + gender + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Passenger Details Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
