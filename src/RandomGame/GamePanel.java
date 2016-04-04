package RandomGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	public ArrayList<GameElement> elements = new ArrayList<GameElement>();
	public GameElement player;
	public static int numBoxes = 128;

	/**
	 * GamePanel Constructor. adds elements to the list of elements.
	 */
	public GamePanel() {
		Random rand = new Random();

		player = new GameElement((MainFrame.main.height / 2 - 31),
				MainFrame.main.width / 2 - 7, 50, 50, (float) 2, Color.WHITE){
			
			@Override
			public void move() {
				// if moving right
				if (dx > 0) {
					if (destX <= (int) x) {
						dx = 0;
					}
				}
				// if moving left
				else {
					if (destX >= (int) x) {
						dx = 0;
					}
				}
				// if moving down
				if (dy > 0) {
					if (destY <= (int) y) {
						dy = 0;
					}
				}
				// if moving up
				else {
					if (destY >= (int) y) {
						dy = 0;
					}
				}
				x = x + dx;
				y = y + dy;
			}
			
			public void paint(Graphics2D g2d) {
				g2d.setColor(color);
				g2d.fillOval((int) x, (int) y, width, height);
			}
		};

		int x, y, eleWidth = 50;
		float s;
		GameElement temp;
		for (int i = 0; i < 2; i++) {
			x = rand.nextInt(MainFrame.main.width  - eleWidth) + eleWidth / 2;
			y = rand.nextInt(MainFrame.main.height - eleWidth) + eleWidth / 2;
			s = (float) (rand.nextDouble() + 1);
			temp = new GameElement(x, y, eleWidth, eleWidth, s, Color.red);
			elements.add(temp);
		}
	}

	/**
	 * Calculates movements of all elements on the GamePanel and updates the
	 * screen.
	 */
	public void run() {
		move();
		this.repaint();
	}

	/**
	 * Process all moves needed on the GamePanel
	 */
	private void move() {
		player.move();
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).move();
		}
	}

	/**
	 * Draw the image for everything on the GamePanel. Overwrites the previous
	 * image.
	 */
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		// initialize needed variables to save space
		int red, green, blue, x, y;
		// determine the width and height of each box in the background
		double boxHeight = (MainFrame.main.height * 1.0) / numBoxes;
		double boxWidth = (MainFrame.main.width * 1.0) / numBoxes;

		// draw the background as a gradient
		for (int row = 0; row < numBoxes; row++) {
			for (int col = 0; col < numBoxes; col++) {
				try {
					// for every box on the screen, determine the color and draw
					// a rectangle
					red = (int) ((256 * col * boxWidth) / MainFrame.main.width);
					green = (int) ((256 * col * boxWidth) / MainFrame.main.width);
					blue = 0;
					x = (int) (col * boxWidth);
					y = (int) (row * boxHeight);
					g2d.setColor(new Color(red, green, blue));
					g2d.fillRect(x, y, (int) Math.ceil(boxWidth),
							(int) Math.ceil(boxHeight));
				} catch (Exception e) {
					break;
				}
			}
		}
		// for every element on the GamePanel, paint them
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).paint(g2d);
		}
		player.paint(g2d);
	}
}// end GamePanel class
