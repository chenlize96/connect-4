

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * model for connect4
 * 
 */
public class Connect4Model extends Observable {

	private int turn = 1;
	private int disk = 0;
	private int first; // represent the color of the first player
	private int second;
	private int[][] panel;
	private Connect4MoveMessage preMove = null;

	/**
	 * constructor
	 *
	 */	
	public Connect4Model() {
		// Generate random integers in range 1 to 2 
		Random rand = new Random(); 
		this.first = rand.nextInt(2) + 1; // red = 2 yellow = 1
		this.second = 3 - first;
		this.panel = new int[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				panel[i][j] = 0;
			}
		}
	}

	/*public void printP() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(panel[i][j]);
			}
			System.out.println();
		}
	}*/

	/**
	 * check if the game is a draw
	 *
	 */	
	public void gameOver(){
		if (turn == 43) {
			setChanged();
			notifyObservers(0); // 0 means end
		}
	}

	/**
	 * Get the best disk the computer plays
	 *
	 * @return the target disk
	 */	
	public int getBestColumns() {
		// Generate random integers in range 0 to 6 
		Random rand = new Random();
		disk = 0;
		if (turn == 1) {
			disk = rand.nextInt(7);
		}
		if (turn != 1) { // then we have preMove
			List<Integer> empty = new ArrayList<>(); // get usable disks 
			for (int i = 0; i < panel[0].length; i++) {
				if (panel[0][i] == 0) { // if it is not full
					empty.add(i);
				}
			}
			disk = nextStepSelfWin(empty);	
		}	
		return disk;
	}

	/**
	 * it is aimed to win next step
	 * if impossible, then prevent rival winning
	 *
	 * @param use    the usable disks
	 * @return the target disk
	 */	
	private int nextStepSelfWin(List<Integer> use) {
		int disk = 0; boolean changed = false;
		int myColor = 3 - preMove.getColor(); //get self color
		for (int i = 0; i < use.size(); i++) {
			disk = use.get(i);
			int row;
			for (row = panel.length - 1; row >= 0; row--) {
				changed = false;
				if (panel[row][disk] == 0) {
					panel[row][disk] = myColor;
					changed = true;
					break;
				}
			}
			if (checkSurrounding()) {
				panel[row][disk] = 0;
				return disk;
			}
			if (changed) {
				panel[row][disk] = 0;
			}
		}
		// if there is no one leading to victory, then call preventRivalToWin
		disk = preventRivalToWin(use);
		return disk;
	}

	/**
	 * it is aimed to prevent rival to win
	 *
	 * @param use    the usable disks
	 * @return the target disk
	 */
	private int preventRivalToWin(List<Integer> use) {
		int disk = 0; boolean changed = false;
		int rival = preMove.getColor(); //get rival color
		for (int i = 0; i < use.size(); i++) {
			disk = use.get(i);
			int row; 
			for (row = panel.length - 1; row >= 0; row--) {
				changed = false;
				if (panel[row][disk] == 0) {
					panel[row][disk] = rival;
					changed = true;
					break;
				}
			}
			if (checkSurrounding()) {
				panel[row][disk] = 0;
				return disk;
			}
			if (changed) {
				panel[row][disk] = 0;
			}
		}
		// if rival does not have connect 3, block its connect 2 in rows
		for (int j = panel.length - 1; j >= 0; j--) {
			for (int col = 0; col < panel[j].length - 3; col++) {
				if (panel[j][col] == 0 && panel[j][col + 1] == rival 
						&& panel[j][col + 2] == rival
						&& panel[j][col + 3] == 0) {
					if (use.contains(col)) {
						return col;
					}else if (use.contains(col + 3)) {
						return col + 3;
					}
				}
			}
		}
		Random rand = new Random();
		int p = rand.nextInt(use.size());
		return use.get(p);
	}


	/**
	 * it is aimed to check if someone wins
	 *
	 * @param turn    the current turn
	 */
	public void checkWhoWins(int turn) {
		int hue = preMove.getColor();
		if (checkSurrounding()){
			setChanged();         // 2 means red wins
			notifyObservers(hue); // 1 means yellow wins
		}

	}

	/**
	 * it is aimed to check if there is a connect4 on the grid
	 *
	 * @return return true if there exists, otherwise false
	 */
	private boolean checkSurrounding() {
		return checkRows() || checkColumns() || checkRestDiagonal() ||
				checkMainDiagonal();
	}

	/**
	 * it is aimed to check connect4 in rows
	 *
	 * @return return true if there exists, otherwise false
	 */
	private boolean checkRows() {
		for (int row = 0; row < panel.length; row++) {
			for (int col = 0; col < panel[row].length - 3; col++) {
				int hue = panel[row][col];
				if (hue != 0 && hue == panel[row][col + 1] && 
						hue == panel[row][col + 2] && 
						hue == panel[row][col + 3]) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * it is aimed to check connect4 in main diagonal
	 *
	 * @return return true if there exists, otherwise false
	 */
	private boolean checkMainDiagonal() {
		for (int row = 0; row < panel.length - 3; row++) {
			for (int col = 0; col < panel[row].length - 3; col++) {
				int hue = panel[row][col];
				if (hue != 0 && hue == panel[row + 1][col + 1] && 
						hue == panel[row + 2][col + 2] && 
						hue == panel[row + 3][col + 3]) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * it is aimed to check connect4 in rest diagonal
	 *
	 * @return return true if there exists, otherwise false
	 */
	private boolean checkRestDiagonal() {
		for (int row = 0; row < panel.length - 3; row++) {
			for (int col = 3; col < panel[row].length; col++) {
				int hue = panel[row][col];
				if (hue != 0 && hue == panel[row + 1][col - 1] && 
						hue == panel[row + 2][col - 2] && 
						hue == panel[row + 3][col - 3]) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * it is aimed to check connect4 in columns
	 *
	 * @return return true if there exists, otherwise false
	 */
	private boolean checkColumns(){
		for (int row = 0; row < panel.length - 3; row++) {
			for (int col = 0; col < panel[row].length; col++) {
				int hue = panel[row][col];
				if (hue != 0 && hue == panel[row + 1][col] && 
						hue == panel[row + 2][col] && 
						hue == panel[row + 3][col]) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * it is aimed to check connect4 in rows
	 *
	 * @param target    list contains the ball places we access
	 * @return return true if the move is valid, otherwise false
	 */
	public boolean move(List<Circle> target) {
		boolean success = false; // check if the move is valid
		Iterator<Circle> it = target.iterator();
		while (it.hasNext()) {
			if (it.next().getFill() != Color.WHITE) {
				it.remove();
			}
		}
		Paint setColor = Color.WHITE;
		int hue = 1;
		// start with turn = 1
		if (turn % 2 == 1 && first == 1 || turn % 2 == 0 && second == 1) { 
			// start with turn = 1
			setColor = Color.YELLOW;
		}else if (turn % 2 == 1 && first == 2 || turn % 2 == 0 && second == 2) { 
			setColor = Color.RED;
			hue = 2;
		}
		if (!target.isEmpty()) {
			target.get(target.size() - 1).setFill(setColor);
			Connect4MoveMessage message = new Connect4MoveMessage(target.size() - 1, disk, hue);
			panel[target.size() - 1][disk] = hue;
			preMove = message;
			success = true;
			turn++;
		}
		setChanged();
		notifyObservers(target);
		return success;
	}


	/**
	 * it is aimed to get disk from the x, y
	 *
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return the target disk
	 */
	public int getDisk(double x, double y) {
		disk = 0;
		if (x <= 52) {
		}else if (x <= 52 + 48 * 1) {
			disk = 1;
		}else if (x <= 52 + 48 * 2) {
			disk = 2;
		}else if (x <= 52 + 48 * 3) {
			disk = 3;
		}else if (x <= 52 + 48 * 4) {
			disk = 4;
		}else if (x <= 52 + 48 * 5) {
			disk = 5;
		}else {
			disk = 6;
		}
		return disk;
	}

}
