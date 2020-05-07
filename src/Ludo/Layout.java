package Ludo;

import javax.swing.*;
import java.awt.*;

class Layout extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("LUDO GUI");
        frame.setSize(1500, 1000);
        frame.setVisible(true);
        frame.add(new Layout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    int x, y, width, height;

    public Layout() {
        x = 300;
        y = 100;
        width = 30;
        height = 30;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        g.setColor(Color.WHITE);
        g.fillRect(x, y, 15 * width, 15 * height);

        /*for (int i = 0; i < 6; i++) {
            g.setColor(Color.lightGray);
            g.fillRect(x + (i * width), y, width, height);
            g.fillRect(x, y + (i * height), width, height);
            g.fillRect(x + (i * width), y + (5 * height), width, height);
            g.fillRect(x + (5 * width), y + (i * height), width, height);
            g.setColor(Color.lightGray);
            g.fillRect(x + ((i + 9) * width), y, width, height);
            g.fillRect(x + (9 * width), y + (i * height), width, height);
            g.fillRect(x + ((i + 9) * width), y + (5 * height), width, height);
            g.fillRect(x + (14 * width), y + (i * height), width, height);
            g.setColor(Color.lightGray);
            g.fillRect(x + ((i + 9) * width), y + (9 * height), width, height);
            g.fillRect(x + (9 * width), y + ((i + 9) * height), width, height);
            g.fillRect(x + ((i + 9) * width), y + (14 * height), width, height);
            g.fillRect(x + (14 * width), y + ((i + 9) * height), width, height);
            g.setColor(Color.lightGray);
            g.fillRect(x + (i * width), y + (9 * height), width, height);
            g.fillRect(x, y + ((i + 9) * height), width, height);
            g.fillRect(x + (i * width), y + (14 * height), width, height);
            g.fillRect(x + (5 * width), y + ((i + 9) * height), width, height);
        }*/
        for (int i = 1; i < 5; i++) {
            g.setColor(Color.YELLOW);
            //g.fillArc(x+(i*width),y+(7*height),width-4,height-3,0,360);
            g.fillArc(x + (i * width), y + (7 * height), width, height, 0, 360);
            g.setColor(Color.GREEN);
            g.fillArc(x + ((8 + i) * width), y + (7 * height), width, height, 0, 360);
            g.setColor(Color.BLUE);
            g.fillArc(x + (7 * width), y + (i * height), width, height, 0, 360);
            g.setColor(Color.RED);
            g.fillArc(x + ((7) * width), y + ((8 + i) * height), width, height, 0, 360);
        }
       /* g.setColor(Color.YELLOW);
        g.fillArc(x + (1 * width), y + (6 * height), width, height, 0, 360);
        g.setColor(Color.GREEN);
        g.fillArc(x + ((13) * width), y + (8 * height), width, height, 0, 360);
        g.setColor(Color.BLUE);
        g.fillArc(x + (8 * width), y + (1 * height), width, height, 0, 360);
        g.setColor(Color.RED);
        g.fillArc(x + ((6) * width), y + ((13) * height), width, height, 0, 360);*/
        int temp1 = x + 45, temp2 = y + 45;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                g.setColor(Color.YELLOW);
                g.fillArc(temp1 + (2 * i * width), temp2 + (2 * j * height), width, height, 0, 360);
                g.setColor(Color.GREEN);
                g.fillArc(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 9 * height, width, height, 0, 360);
                g.setColor(Color.BLUE);
                g.fillArc(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 0 * height, width, height, 0, 360);
                g.setColor(Color.RED);
                g.fillArc(temp1 + (2 * i * width) + 0 * width, temp2 + (2 * j * height) + 9 * height, width, height, 0, 360);
                g.setColor(Color.BLACK);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                g.drawArc(x + ((i + 6) * width), y + (j * height), width, height, 0, 360);
                g.drawArc(x + ((j) * width), y + ((i + 6) * height), width, height, 0, 360);
                g.drawArc(x + ((i + 6) * width), y + ((j + 9) * height), width, height, 0, 360);
                g.drawArc(x + ((j + 9) * width), y + ((i + 6) * height), width, height, 0, 360);
            }
        }
       /* g.drawRect(x + ((1) * width), y + (1 * height), 4 * width, 4 * height);
        g.drawRect(x + ((10) * width), y + (1 * height), 4 * width, 4 * height);
        g.drawRect(x + ((1) * width), y + (10 * height), 4 * width, 4 * height);
        g.drawRect(x + ((10) * width), y + (10 * height), 4 * width, 4 * height);
        g.drawRect(x, y, 15 * width, 15 * height);*/
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                g.drawArc(temp1 + (2 * i * width), temp2 + (2 * j * height), width, height, 0, 360);
                g.drawArc(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 9 * height, width, height, 0, 360);
                g.drawArc(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 0 * height, width, height, 0, 360);
                g.drawArc(temp1 + (2 * i * width) + 0 * width, temp2 + (2 * j * height) + 9 * height, width, height, 0, 360);
            }
        }
//        g.drawPolygon(xpoints0, ypoints0, npoints);
//        g.drawPolygon(xpoints1, ypoints1, npoints1);
//        g.drawPolygon(xpoints2, ypoints2, npoints2);
//        g.drawPolygon(xpoints3, ypoints3, npoints3);
        g.drawOval(x + 5 + (6 * width), y + 5 + (2 * height), width - 10, height - 10);
        g.drawOval(x + 5 + (12 * width), y + 5 + (6 * height), width - 10, height - 10);
        g.drawOval(x + 5 + (8 * width), y + 5 + (12 * height), width - 10, height - 10);
        g.drawOval(x + 5 + (2 * width), y + 5 + (8 * height), width - 10, height - 10);
        g.setFont(new Font("serif", Font.BOLD, 40));
//        g.drawString("Player 1", 90, 35);
//        g.drawString("Player 2", 370, 35);
//        g.drawString("Player 4", 90, 540);
//        g.drawString("TOSS", 260, 540);
//        g.drawString("Instruction:", 550, 300);
//        g.drawString("1.Press enter to roll dice.", 550, 350);
//        g.drawString("2.Click on coin to move.", 550, 400);


    }
}
