package RandomGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class GameElement {

	public float x;
	public float y;
	public float dx;
	public float dy;
	public int width;
	public int height;
	public int destX;
	public int destY;
	public float speed;
	public boolean atDestX;
	public boolean atDestY;
	int deltaX =0, deltaY =0, dist=0;
	
	public Color color;

	/**
	 * GameElement Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param speed
	 */
	public GameElement(int x, int y, int width, int height, float speed, Color color) {
		this.x = x;
		this.y = y;
		this.dx = 0;
		this.dy = 0;
		this.width = width;
		this.height = height;
		this.destX = x;
		this.destY = y;
		this.speed = speed;
		this.color = color;
		this.atDestX = true;
		this.atDestY = true;
	}

	/**
	 * Calculates the movements of this GameElement and changes its location.
	 */
	public void move() {
		Random r = new Random();
		//if the element is at its destination, set a new destination
		if(atDestX){
			destX = r.nextInt(MainFrame.main.width);
			deltaX = (int) (destX - x);
			atDestX = false;
		}
		if(atDestY){
			destY = r.nextInt(MainFrame.main.height);
			deltaY = (int) (destY - y);
			atDestY = false;
		}
		dist = (int) (Math.sqrt(deltaX * deltaX + deltaY * deltaY));
		// set the speed of the element relative to
		dx = (float) (speed * (deltaX * 1.0) / dist);
		dy = (float) (speed * (deltaY * 1.0) / dist);
		
		// if moving right
		if (dx > 0) {
			if (destX <= (int) x) {
				dx = 0;
				atDestX = true;
			}
		}
		// if moving left
		else {
			if (destX >= (int) x) {
				dx = 0;
				atDestX = true;
			}
		}
		// if moving down
		if (dy > 0) {
			if (destY <= (int) y) {
				dy = 0;
				atDestY = true;
			}
		}
		// if moving up
		else {
			if (destY >= (int) y) {
				dy = 0;
				atDestY = true;
			}
		}
		x = x + dx;
		y = y + dy;
	}

	public void paint(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect((int) x, (int) y, width, height);
	}
}//end GameElement
