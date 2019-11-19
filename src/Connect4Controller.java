import java.util.ArrayList;
import java.util.List;


import javafx.scene.shape.Circle;


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
	
	
	public void checkWinner() {
		model.checkWhoWins(turn);
	}
	
	public void checkEnd() {
		model.gameOver();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public int getDisk(double x, double y) {
	    return model.getDisk(x, y);
	}
	
	
	
	
}
