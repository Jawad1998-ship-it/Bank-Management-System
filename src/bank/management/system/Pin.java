package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Pin extends JFrame implements ActionListener {

    JButton b1, b2;
    JPasswordField p1, p2;
    String pin;

    Pin(String pin)
    {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("Change Your PIN");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430,180,400,35);
        l3.add(label1);


        JLabel label2 = new JLabel("New PIN: ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430,220,150,35);
        l3.add(label2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(65,125,128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(600,220,180,25);
        p1.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(p1);

        JLabel label3 = new JLabel("Re-Enter New PIN: ");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("System", Font.BOLD, 16));
        label3.setBounds(430,250,400,35);
        l3.add(label3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(65,125,128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(600,255,180,25);
        p2.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(p2);

        b1 = new JButton("Change");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setSize(1550,1080);
        setLayout(null);
        setLocation(0,0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
            char[] pin1 = p1.getPassword();
            char[] pin2 = p2.getPassword();
            if(!Arrays.equals(pin1, pin2))
            {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            if(e.getSource() == b1)
            {
                if(p1.getPassword() == null || p1.getPassword().length == 0)
                {
                    JOptionPane.showMessageDialog(null, "Enter new PIN");
                    return;
                }
                if(p2.getPassword() == null || p2.getPassword().length == 0)
                {
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                    return;
                }

                Conn con = new Conn();

                String q1 = "UPDATE bank SET pin = '"+pin1+"' WHERE pin = '"+pin+"'";
                String q2 = "UPDATE login SET pin = '"+pin1+"' WHERE pin = '"+pin+"'";
                String q3 = "UPDATE signup3 SET pin = '"+pin1+"' WHERE pin = '"+pin+"'";

                con.statement.executeUpdate(q1);
                con.statement.executeUpdate(q2);
                con.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "Successfully changed PIN");
                setVisible(false);
                new main_Class(pin);

            }

            else if (e.getSource() == b2)
            {
                setVisible(false);
                new main_Class(pin);
            }
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }

    public static void main (String[] args)
    {
        new Pin("");
    }

}
