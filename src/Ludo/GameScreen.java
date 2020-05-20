package Ludo;

import javax.swing.*;

public class GameScreen {
	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		jframe.setBounds(10, 10, 1000, 600);
		jframe.setResizable(false);
		GameMoves gm = new GameMoves();
		gm.setFocusable(true);
		//event listeners
		gm.addKeyListener(gm);
		gm.addMouseListener(gm);
		jframe.add(gm);
		jframe.setVisible(true);
		jframe.setTitle("LUDO");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Menu");
		JMenuItem newMenuItem = new JMenuItem("New Game");
		JMenuItem highScore = new JMenuItem("High Score");
		JMenuItem instructions = new JMenuItem("Instructions");

		//add items to the menu item
		fileMenu.add(newMenuItem);
		fileMenu.add(highScore);
		fileMenu.add(instructions);
		//add the file menu to the menu bar
		menuBar.add(fileMenu);
		//add menu bar to the frame
		jframe.setJMenuBar(menuBar);
		jframe.add(menuBar);



	}
}
