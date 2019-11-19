import java.util.ArrayList;
import java.util.List;


import javafx.scene.shape.Circle;

/**
 * controller for connect4
 * 
 */
public class Connect4Controller {

	private Connect4View view;
	private Connect4Model model;
	private int turn = 1;
	private boolean isComputer;

	public Connect4Controller(Connect4View view, Connect4Model model) {
		this.view = view;
		this.model = model;
		this.isComputer = true; 
	}



	/**
	 * it represents humanturn
	 *
	 * @param col    the disk we put the ball in
	 */	
	@SuppressWarnings("static-access")
	public void humanTurn(int col) {
		int gridSize = view.grid.getChildren().size();
		List<Circle> target = new ArrayList<>(); 
		for (int i = 0; i < gridSize; i++) {
			Circle temp = (Circle) view.grid.getChildren().get(i);
			if (view.grid.getColumnIndex(temp) == col) {
				target.add(temp);
			}
		}
		if (model.move(target)) {
			turn++;
			checkWinner();
			checkEnd();
		}
		if (isComputer) {
			computerTurn();
		}
	}

	/**
	 * it represents computerturn
	 *
	 */	
	@SuppressWarnings("static-access")
	public void computerTurn() {
		int col = model.getBestColumns();
		int gridSize = view.grid.getChildren().size();
		List<Circle> target = new ArrayList<>(); 
		for (int i = 0; i < gridSize; i++) {
			Circle temp = (Circle) view.grid.getChildren().get(i);
			if (view.grid.getColumnIndex(temp) == col) {
				target.add(temp);
			}
		}
		if (model.move(target)) {
			turn++;
			checkWinner();
			checkEnd();
		}
	}

	/**
	 * it checks who is the winner
	 *
	 */	
	public void checkWinner() {
		model.checkWhoWins(turn);
	}

	/**
	 * it checks if the game is over
	 */	
	public void checkEnd() {
		model.gameOver();
	}

	/**
	 * it gets the current turn
	 *
	 * @return the current turn
	 */	
	public int getTurn() {
		return turn;
	}

	/**
	 * it gets the disk by x, y
	 *
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return the target disk
	 */	
	public int getDisk(double x, double y) {
		return model.getDisk(x, y);
	}




}
