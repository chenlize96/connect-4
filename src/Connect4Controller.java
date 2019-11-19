import java.util.ArrayList;
import java.util.List;


import javafx.scene.shape.Circle;


public class Connect4Controller {

	public Connect4View view;
	public Connect4Model model;
	public int turn = 1;
	
	public Connect4Controller(Connect4View view, Connect4Model model) {
		this.view = view;
		this.model = model;
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
		for (Circle n : target) {
			System.out.println(n.getCenterX() + " "+n.getCenterY());
		}
		if (model.move(target)) {
			turn++;
			checkWinner();
			checkEnd();
		}
		System.out.println("newturn:" + turn);
	}
	
	public void computerTurn() {
		
		
	}
	
	
	public void checkWinner() {
		model.checkWhoWins(turn);
	}
	
	public void checkEnd() {
		model.gameOver();
	}
	
	
	public int getDisk(double x, double y) {
	    return model.getDisk(x, y);
	}
	
	
	
	
}
