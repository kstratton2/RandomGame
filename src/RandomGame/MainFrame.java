package RandomGame;

import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable , ActionListener{

	private static final long serialVersionUID = 1L;

	// create the game panel and the frame for the game

	public int height = 128*5, width = 128*5;
	
	public static MainFrame main = new MainFrame();
	public static TitlePanel title;
	public static GamePanel game;
	
	public KL kl = new KL();
	public ML ml = new ML();
	public MML mml = new MML();
	
	public boolean titleShown = true;
	public boolean gameShown = false;
	public boolean pauseShown = false;

	public static void main(String args[]) {
		// constantly run the game

		startTitle();
		while (true) {
			main.run();
			try {
				Thread.sleep(10); // add small delay to counter visual shutter
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Constructor for the MainFrame of the game. Sets visibility, size, and
	 * close operation. Adds a panel, KeyListener, and MouseListener
	 */
	public MainFrame() {
		super("RandomGame");
		this.setVisible(true);
		this.setSize(width + 8, height + 31);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(kl);
		this.addMouseListener(ml);
		this.addMouseMotionListener(mml);
	}

	/**
	 * Handles each loop of execution in the game.
	 * 
	 */
	public void run() {
		height = main.getHeight();
		width = main.getWidth();
		if(titleShown){
			title.run();
		}else if(gameShown){
			game.run();
		}else if(pauseShown){
			
		}
	}


	public void actionPerformed(ActionEvent e) {
		if(title.newGame == e.getSource()){
			System.out.println("new Game");
			titleShown = false;
			gameShown = true;
			removeTitle();
			startGame();
		}
	}
	
	public static void startTitle(){
		title = new TitlePanel("RandomGame");
		main.add(title);
	}
	
	public void removeTitle(){
		main.remove(title);
		revalidate();
	}
	
	public void startGame(){
		game = new GamePanel();
		main.add(game);
		game.revalidate();	
	}
	
	public void removeGame(){
		
	}
}//end MainFrame class

// ///////////////////Key Listener Class////////////////////////////
class KL extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		System.out.println(e);
	}

	public void keyReleased(KeyEvent e) {
		
	}
}

// ///////////////END Key Listener Class////////////////////////////

// /////////////////Mouse Listener Class////////////////////////////
class ML extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		if (MainFrame.main.titleShown) {
			
		} else if (MainFrame.main.gameShown) {
			// determine the destination of the block, shifted by the window
			// boarders and
			GameElement ele;
			int deltaX, deltaY, dist;
			ele = MainFrame.game.player;
			ele.destX = (int) (e.getX() - ele.width / 2 - 8);
			ele.destY = (int) (e.getY() - ele.height / 2 - 31);
			// determine how far the element needs to move
			deltaX = (int) (ele.destX - ele.x);
			deltaY = (int) (ele.destY - ele.y);
			dist = (int) (Math.sqrt(deltaX * deltaX + deltaY * deltaY));
			// set the speed of the element relative to
			ele.dx = (float) (ele.speed * (deltaX * 1.0) / dist);
			ele.dy = (float) (ele.speed * (deltaY * 1.0) / dist);
		} else if (MainFrame.main.pauseShown) {

		}
	}
}// /////////////END Mouse Listener Class////////////////////////////

// ////////////Mouse Motion Listener Class////////////////////////////
class MML extends MouseMotionAdapter {
	public void mouseDragged(MouseEvent e) {
		MainFrame.main.ml.mousePressed(e);
	}
}// ////////End Mouse Motion Listener Class////////////////////////////

