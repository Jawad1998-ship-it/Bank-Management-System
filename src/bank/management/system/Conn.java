package bank.management.system;

import java.sql.*;


public class Conn {

    Connection connection;
    Statement statement;
    public Conn()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root", "");
            statement = connection.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main (String[] args)
    {

    }

}
