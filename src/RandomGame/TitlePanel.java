package RandomGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private int numRows = 4;
	private int numCols = 3;
	public JButton newGame;
	public JButton loadGame;

	/**
	 * TitlePanel Constructor. Creates a panel that has 3 buttons and a title.
	 * 
	 * @param title
	 */
	public TitlePanel(String title) {
		Color[] colors = { Color.DARK_GRAY, Color.gray, Color.LIGHT_GRAY };
		int row, col;
		JPanel tempPanel;

		// set the layout for the panel
		this.setLayout(new GridLayout(numRows, numCols));
		// for every row and column on the title screen
		for (row = 0; row < numRows; row++) {
			for (col = 0; col < numCols; col++) {
				// create a new panel to fill up the TitlePanel
				tempPanel = new JPanel();
				// set the background color to see each panel
				tempPanel.setBackground(colors[(row + col) % colors.length]);
				// for a specific panel, create and add a label with the title.
				if (row == 1 && col == 1) {
					tempPanel.setLayout(new GridBagLayout());
					JLabel label = new JLabel(title);
					label.setFont(new Font("Serif", Font.PLAIN, 40));
					tempPanel.add(label);
				}
				// for the second row
				if (row == 2) {
					// set a layout to center everything, and add a different
					// button to each column
					tempPanel.setLayout(new GridBagLayout());
					if (col == 0) {
						newGame = new JButton("New Game");
						newGame.addActionListener(MainFrame.main);
						tempPanel.add(newGame);
					}
					if (col == 1) {
						loadGame = new JButton("Load Game");
						loadGame.addActionListener(MainFrame.main);
						tempPanel.add(loadGame);
					}
					if (col == 2) {

					}
				}
				// add the created panel
				this.add(tempPanel);
			}
		}
	}

	/**
	 * Runs the title screen. Constantly loops while the title screen is still
	 * shown.
	 */
	public void run() {
		// while the title screen is still shown, just loop and wait.
		while (MainFrame.main.titleShown) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {

			}
			revalidate();
		}
	}

}// end TitlePanel class
