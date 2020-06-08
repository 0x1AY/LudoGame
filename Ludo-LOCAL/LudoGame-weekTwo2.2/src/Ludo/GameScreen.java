package Ludo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.lang.Integer.parseInt;


public class GameScreen extends JFrame implements ActionListener {
    JMenuBar menuBar;
    public JMenuItem newGame;
    private JMenuItem instructions;
    public JMenuItem highScore;
    public JMenuItem multiplayerGame;
    public JMenuItem exit;
    JFrame jframe;
    private static String username;
    private static int wins;
    private static int totalPlayers = 2;

    private static boolean trueValue;
    private GameMoves gm;



    public static String getUsername() {
        return username;
    }

    public GameScreen() {
        jframe = new JFrame();
        jframe.setBounds(0, 0, 1000, 600);
        jframe.setResizable(false);
        gm = new GameMoves();
        gm.setFocusable(true);
        //event listeners
        gm.addKeyListener(gm);
        jframe.add(gm);

        jframe.setTitle("LUDO");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);
        menu.add(newGame);

        instructions = new JMenuItem("Instructions");
        instructions.addActionListener(this);
        menu.add(instructions);

        highScore = new JMenuItem("High-score");
        highScore.addActionListener(this);
        menu.add(highScore);

        multiplayerGame = new JMenuItem("Multi-player");
        multiplayerGame.addActionListener(this);
        menu.add(multiplayerGame);

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        menu.add(exit);


        jframe.setJMenuBar(menuBar);
        jframe.setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            String s = (String) JOptionPane.showInputDialog("Enter the number of players", "");
            totalPlayers = parseInt(s);
            gm.reset(totalPlayers);
            gm.removeAll();
            gm.repaint();
            gm.revalidate();

        } else if (e.getSource() == instructions) {
            JOptionPane.showMessageDialog(null, "1. Press Enter to roll die. \n2. Playing piece moves automatically.");
        } else if (e.getSource() == highScore) {
            //If username exists, check no of wins
            if (trueValue) {
                System.out.println("[*] Checking high score...");
                if ((username != null) && (username.length() > 0)) {
                    DB_Access DB_AccessObject = new DB_Access();
                    wins = DB_AccessObject.checkWins(username);
                    JOptionPane.showMessageDialog(null, username + " has won " + wins);
                }

            } else {
                String s = (String) JOptionPane.showInputDialog("Enter your Username", "");
                System.out.println("[*] Checking for username in database...");
                //If a string was returned, say so.
                if ((s != null) && (s.length() > 0)) {
                    DB_Access DB_AccessObject = new DB_Access();
                    trueValue = DB_AccessObject.verifyPlayer(s);
                    username = s;
                    // if user name exists, display hs else start again
                    if (trueValue) {
                        //An alert to show user exists
                        JOptionPane.showMessageDialog(null, "1. User found! \n2. Check high score again.");
                        System.out.println(s + trueValue);
                    } else {
                        JOptionPane.showMessageDialog(null, "1. User does not exist! \n2. Check user name and try again.");
                    }
                }

            }


        } else if (e.getSource() == multiplayerGame) {
            if (trueValue) {

                ////Start Multi-player
            } else {

                String s = (String) JOptionPane.showInputDialog("Enter your Username", "");
                System.out.println("[*] Checking for username in database...");
                //If a string was returned, say so.
                if ((s != null) && (s.length() > 0)) {
                    DB_Access DB_AccessObject = new DB_Access();
                    trueValue = DB_AccessObject.verifyPlayer(s);
                    username = s;
                    //should be an alart
                    JOptionPane.showMessageDialog(null, "Hi, " + username);
                    //System.out.println(s + trueValue);
                }
            }
        } else if (e.getSource() == exit) {
            System.exit(0);
        }


    }



}