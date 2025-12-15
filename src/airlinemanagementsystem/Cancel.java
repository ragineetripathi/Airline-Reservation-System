
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class Cancel extends JFrame implements ActionListener {
    
    JTextField tfpnr;
    JLabel tfname,tfaadhar,tfnationality, lblcancellation,cancellationno,lblfcode,lbldateoftravel,labelfname, labelfcode; 
    JButton Cancel, fetchButton;
    Choice source, destination;
    JDateChooser dcdate;
    
    public Cancel(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        Random random = new Random();
        
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        //heading.setForeground(Color.BLUE);
        add(heading); 
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 130, 200, 200);
        add(image);
        
        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar); 
        
        tfpnr= new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);
        
        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname); 
        
        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
       
        
        lblcancellation = new JLabel("Cancellation No.");
        lblcancellation.setBounds(60, 180, 150, 25);
        lblcancellation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcancellation); 
        
        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);
        
        lblcancellation = new JLabel("Flight Code");
        lblcancellation.setBounds(60, 230, 150, 25);
        lblcancellation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcancellation); 
        
        lblfcode = new JLabel("" + random.nextInt(1000000));
        lblfcode.setBounds(220, 230, 150, 25);
        add(lblfcode);
        
        
        JLabel lblgender = new JLabel("Date");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN , 16));
        add(lblgender);
        
        lbldateoftravel = new JLabel();
        lbldateoftravel.setBounds(220, 280, 150, 25);
        add(lbldateoftravel);
        
       
        Cancel = new JButton("Cancel");
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        Cancel.setBounds(220, 330, 120,25);
        Cancel.addActionListener(this);
        add(Cancel);
             
       
        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
       
        if(ae.getSource() == fetchButton){
        
            String pnr = tfpnr.getText();
       
            try{
                 Conn conn = new Conn();
                 String query = "select * from reservation where PNR = '"+pnr+"'";

                 ResultSet rs = conn.s.executeQuery(query);

                 if(rs.next()){
                     tfname.setText(rs.getString("name"));
                     lblfcode.setText(rs.getString("flightcode"));
                     lbldateoftravel.setText(rs.getString("ddate"));
                     
                 }
                 else{
                      JOptionPane.showMessageDialog(null, "Please enter correct aadhar");
                 }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == Cancel){
        
            String name = tfname.getText();
            String pnr = tfpnr.getText();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String date = lbldateoftravel.getText();
            
            try{
                 Conn conn = new Conn();
                 String query = "insert into cancel values('"+pnr+"','"+name+"','"+cancelno+"', '"+fcode+"','"+date+"')";

                 conn.s.executeUpdate(query);
                 conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
                
                 JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                 setVisible(false);
                 
            }catch (Exception e){
                e.printStackTrace();
            }
        } else{
            Random random = new Random();
            
            String aadhar = tfaadhar.getText();
            String pnr = tfpnr.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            
            try{
                 Conn conn = new Conn();
                 String query = "insert into reservation values ('PNR -"+random.nextInt(1000000)+"','TIC-"+random.nextInt(10000)+"', '"+aadhar+"', '"+name+"','"+nationality+"','"+flightname+"','"+flightcode+"','"+src+"','"+des+"','"+ddate+"')";
                  
                 conn.s.executeUpdate(query);
                 conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
                
                 JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                 setVisible(false);
            
            }catch (Exception e){
                e.printStackTrace();
            } 
        }
    }
    public static void main(String[] args){
         new Cancel();
     }
}



