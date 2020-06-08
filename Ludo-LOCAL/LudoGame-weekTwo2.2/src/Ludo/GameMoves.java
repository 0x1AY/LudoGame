package Ludo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMoves extends JPanel implements KeyListener, ActionListener {


    //private static final long serialVersionUID = 1L;
    Layout la;
    Build_Player p;
    Timer time;
    int delay = 10;
    int current_player, dice;
    //create a variable to keep track of the piece at play
    int current_piece;
    int playerPiece1, playerPiece2, playerPiece3, playerPiece4;
    ////////////////////
    private static int totalPlayers = 2;

    int x;
    int y;
    int currentMoves = 0;
    //flag is set to 1 after enter key is pressed to decide whether or not to allow the piece to move
    //roll checks value of dice roll up to 6
    //kill is
    int flag = 0, roll, kill = 0;

    public GameMoves() {
        setFocusTraversalKeysEnabled(false);
        requestFocus();
        //
        reset(totalPlayers);
    }

    @Override
    public void paint(Graphics g) {
        la.draw((Graphics2D) g);
        p.draw((Graphics2D) g);

        la.currentPlayerIndicator((Graphics2D) g, current_player);

        //Coin variable counts how many pieces per player is at home.
        //If All pieces are at home, do:
        if (p.pl[current_player].coin == 4) {
            g.setColor(Color.WHITE);
            g.fillRect(590, 100, 380, 130);
            if (current_player == 0) {
                g.setColor(Color.YELLOW);
            } else if (current_player == 1) {
                g.setColor(Color.BLUE);
            } else if (current_player == 2) {
                g.setColor(Color.GREEN);
            } else if (current_player == 3) {
                g.setColor(Color.RED);
            }
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Player " + (current_player + 1) + " wins.", 600, 150);
            g.drawString("Congratulations.", 600, 200);
            //JOptionPane.showMessageDialog(null,"Player" + (current_player + 1) + " wins. \nCongratulations.");

            //DB_Access DB_AccessObject = new DB_Access();
            //GameScreen GSO = new GameScreen();
            //DB_AccessObject.updateWins(GSO.username);
            ////
            reset(totalPlayers);
//

        } else if (dice != 0) {
            //Player name and toss value background
            {
                g.setColor(Color.WHITE);
                g.fillRect(590, 100, 380, 130);
            }

            //Setting text color according to current player
            if (current_player == 0) {
                g.setColor(Color.YELLOW);
            } else if (current_player == 1) {
                g.setColor(Color.BLUE);
            } else if (current_player == 2) {
                g.setColor(Color.GREEN);
            } else if (current_player == 3) {
                g.setColor(Color.RED);
            }
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Player " + (current_player + 1), 600, 150);
            g.drawString("Number on dice is " + dice, 600, 200);
        }
        //Changes to the next player after piece has been moved
        //change to support just 2 players for now , add more later
        if (flag == 0 && dice != 0 && dice != 6 && kill == 0) {
            //For a game between n players, %n
            current_player = (current_player + 1) % totalPlayers;


        }
        ///kill variable
        kill = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_ENTER && flag == 0) {
            roll = 0;
            dice = diceToss();


            //CHECK IF YOUR TURN
            repaint();
            for (int i = 0; i < 4; i++) {
                if (p.pl[current_player].pa[i].current != -1 && p.pl[current_player].pa[i].current != 47 && (p.pl[current_player].pa[i].current + dice) <= 47) {

                    //////current_piece = i;
                    flag = 1;
                    break;
                }
            }
            //check if any pieces are yet to move and if there's a 6 set flag to 1 to allow it move
            if (flag == 0 && dice == 6) {
                for (int i = 0; i < 4; i++) {
                    if (p.pl[current_player].pa[i].current == -1) {
                        //////current_piece = i;
                        flag = 1;
                        break;
                    }
                }
            }
        }


        //movement

        if (flag == 1) {

            //System.out.println("Dice: "+ dice + " Initial Positions:" +x +" " + y);
            x = x - 80;
            y = y - 50;
            x = x / 30;
            y = y / 30;
            int value = -1;
            //Printing out coordinates in terminal, remove from final work
            //System.out.println(" Adjusted Positions:" +x  + y);
            /////////////////////////


            if (current_player == 0) {
                current_piece = playerPiece1;

            } else if (current_player == 1) {
                current_piece = playerPiece2;
            } else if (current_player == 2) {
                current_piece = playerPiece3;
            } else if (current_piece == 3) {
                current_piece = playerPiece4;
            }
            //////////////////
            if (dice == 6 && p.pl[current_player].pa[current_piece].current == -1) {
                ///removed all for loops to check which piece
                //removed since no need to check if player x is same as piece x
                p.pl[current_player].pa[current_piece].current = 0;
                flag = 0;
            } else {

                value = p.pl[current_player].pa[current_piece].current;
                ///////
                //System.out.println("Value is " + value);
                //////////
                if (value != -1 && value + dice < 48) {
                    p.pl[current_player].pa[current_piece].current += dice;
                    ////CHECKS IF PIECE HAS COMPLETELY TRAVERSED PATH
                    if (p.pl[current_player].pa[current_piece].current == 47) {
                        p.pl[current_player].coin++;
                        current_piece++;

                    }
                    //////////////////
                }
            }
            //repaint();

            flag = 0;


            {
                if (current_player == 0) {
                    playerPiece1 = current_piece;

                } else if (current_player == 1) {
                    playerPiece2 = current_piece;
                } else if (current_player == 2) {
                    playerPiece3 = current_piece;
                } else if (current_piece == 3) {
                    playerPiece4 = current_piece;
                } else {
                    System.out.println("Error in player numbers. Only 4 supported");
                }
            }

        }
    }


    public void reset(int totalPlayers) {
        this.totalPlayers = totalPlayers;
        current_player = 0;
        current_piece = 0;
        playerPiece1 = 0;
        playerPiece2 = 0;
        playerPiece3 = 0;
        playerPiece4 = 0;
        la = new Layout(80, 50);
        p = new Build_Player(la.height, la.width);
        dice = 0;
        flag = 0;
        roll = 0;
        kill = 0;
    }

    public int diceToss() {
        dice = 1 + (int) (Math.random() * 6);
        return dice;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }


}


