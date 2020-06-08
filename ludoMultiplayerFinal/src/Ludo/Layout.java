package Ludo;

import java.awt.*;

public class Layout {

    int x, y, width, height;
    int pieceWidth, pieceHeight;
    int pathWidth, pathHeight;


    public Layout(int xi, int yi) {
        x = xi;
        y = yi;
        width = 30;
        height = 30;
        pieceWidth = width - 8;
        pieceHeight = height - 8;
        pathWidth = width - 4;
        pathHeight = height - 4;


    }


    public void draw(Graphics2D g) {
        //Setting background - Replace with image
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1000, 600);

        g.setColor(Color.WHITE);
        //DIVIDING LAYOUT INTO 15 PARTS
        g.fillRect(x, y, 15 * width, 15 * height);

        for (int i = 1; i < 5; i++) {
            g.setColor(Color.YELLOW);
            g.fillArc(x + 30 + (i * width), y + (7 * height), pathWidth, pathHeight, 0, 360);
            g.setColor(Color.GREEN);
            g.fillArc(x + ((8 + i) * width), y + (7 * height), pathWidth, pathHeight, 0, 360);
            g.setColor(Color.BLUE);
            g.fillArc(x + (7 * width), y + 30 + (i * height), pathWidth, pathHeight, 0, 360);
            g.setColor(Color.RED);
            g.fillArc(x + ((7) * width), y + ((8 + i) * height), pathWidth, pathHeight, 0, 360);
        }

        int temp1 = x + 45, temp2 = y + 45;
///////////////////////

///////////////////////////////////
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                g.setColor(Color.BLACK);
                //g.setColor(Color.YELLOW);
                g.drawArc(temp1 + (2 * i * width), temp2 + (2 * j * height), width, height, 0, 360);
                //g.setColor(Color.GREEN);
                g.drawArc(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 9 * height, width, height, 0, 360);
                //g.setColor(Color.BLUE);
                g.drawArc(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 0 * height, width, height, 0, 360);
                // g.setColor(Color.RED);
                g.drawArc(temp1 + (2 * i * width) + 0 * width, temp2 + (2 * j * height) + 9 * height, width, height, 0, 360);

            }
        }

        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                g.drawArc(x + ((i + 6) * width), y + 30 + (j * height), pathWidth, pathHeight, 0, 360);
                g.drawArc(x + 30 + ((j) * width), y + ((i + 6) * height), pathWidth, pathHeight, 0, 360);
                g.drawArc(x + ((i + 6) * width), y + ((j + 9) * height), pathWidth, pathHeight, 0, 360);
                g.drawArc(x + ((j + 9) * width), y + ((i + 6) * height), pathWidth, pathHeight, 0, 360);
            }
        }


//         g.setFont(new Font("serif", Font.BOLD, 40));
//        g.drawString("Player 1", 90, 35);
//          g.drawString("Player 2", 370, 35);
//           g.drawString("Player 4", 90, 540);
//          g.drawString("Player 3", 370, 540);
//         g.drawString("Instruction:", 550, 300);
//         g.drawString("1.Press enter to roll dice.", 550, 350);
//         g.drawString("2.Click on coin to move.", 550, 400);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("Press Enter!", 220, 35);

    }

    public void currentPlayerIndicator(Graphics2D g, int current_player) {

        if (current_player == 0) {
            //Yellow
            g.setColor(Color.yellow);
            g.drawArc(x + (20), y + (20), (4 * width) + 20, (4 * height) + 20, 0, 360);
        } else if (current_player == 1) {
            //blue
            g.setColor(Color.blue);
            g.drawArc(10 + (12 * width), y + (20), (4 * width) + 20, (4 * height) + 20, 0, 360);
        } else if (current_player == 2) {
            //Green
            g.setColor(Color.green);
            g.drawArc(10 + (12 * width), 15 * height - (y + 60), (4 * width) + 20, (4 * height) + 20, 0, 360);
        } else if (current_player == 3) {
            //Red
            g.setColor(Color.red);
            g.drawArc((x + 20), 15 * height - (y + 60), (4 * width) + 20, (4 * height) + 20, 0, 360);
        } else {
            System.out.println("Error, number of cureent players exceed 4");
        }
    }


}

