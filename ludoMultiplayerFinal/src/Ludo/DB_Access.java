package Ludo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_Access {

    private static String username;
    int trueV = 0;
    private static int wins;

    //FUNCTION TO RETRIEVE USER NAME FROM DB WHEN LOGGING IN
    public boolean verifyPlayer(String username) {
        DB_Connection obj_DB_Connection = new DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        //retrieve wins from player1
        try {
            String query = "select username from PLAYERS ";
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    System.out.println("Username- " + username + "exists");
                    trueV = 1;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        if (trueV == 1) {
            return true;
        } else {
            return false;
        }
    }

    //FUNCTION TO RETRIEVE WINS FROM DB

    public int checkWins(String username) {

        DB_Connection obj_DB_Connection = new DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select * from PLAYERS where username = '" + username + "'";
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("PLAYER " + rs.getString("username") + " has " + rs.getInt("wins") + " wins");
                wins = rs.getInt("wins");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return wins;

    }

//FUNCTION TO UPDATE DB HIGH SCORE
public void updateWins(String username){

    DB_Connection obj_DB_Connection=new DB_Connection();
    Connection connection=obj_DB_Connection.get_connection();
    PreparedStatement ps=null;
    try {
        String query="UPDATE Ludo.PLAYERS SET wins = wins + 1 where username = '" + username +"'";
        ps=connection.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        System.out.println("Wins Updated");
    } catch (Exception e) {
        System.out.println(e);
    }

}

}
