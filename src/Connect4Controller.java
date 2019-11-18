import java.util.ArrayList;
import java.util.List;

import javafx.animation.PathTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Connect4Controller {

	public Connect4View view;
	public Connect4Model model;

	public Connect4Controller(Connect4View view, Connect4Model model) {
		this.view = view;
		this.model = model;
	}
	
	
	@SuppressWarnings("static-access")
	public void move(int disk, GridPane grid) {
		int gridSize = grid.getChildren().size();
		List<Circle> target = new ArrayList<>(); 
		for (int i = 0; i < gridSize; i++) {
			Circle temp = (Circle) grid.getChildren().get(i);
			if (grid.getColumnIndex(temp) == disk) {
				target.add(temp);
			}
		}
		
		for (Circle n : target) {
			System.out.println(n.getCenterX() + " "+n.getCenterY());
		}
		model.move(target);
		//Circle start = target.get(target.size() - 1);
		//Circle end = target.get(0);
		
		
		// check
	}
	
	
	public int getDisk(double x, double y) {
	    return model.getDisk(x, y);
	}
	
	
	
	
}
