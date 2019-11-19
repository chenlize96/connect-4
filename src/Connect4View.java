
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * view for connect4
 * 
 */
public class Connect4View extends Application implements Observer{

	public GridPane grid;

	/**
	 * it is the stage
	 *
	 * @param stage    Stage
	 * @throws exception
	 */	
	@Override
	public void start(Stage stage) throws Exception {
		Connect4Model m = new Connect4Model();
		m.addObserver(this);
		Connect4Controller c = new Connect4Controller(this, m);
		Menu menu = new Menu("File"); 
		MenuItem item = new MenuItem("New Game"); 
		item.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DialogBox box = new DialogBox();
				box.initModality(Modality.APPLICATION_MODAL);
				box.showAndWait();
			}
		});
		menu.getItems().add(item);
		MenuBar mb = new MenuBar(); mb.setMinHeight(25); 
		mb.getMenus().add(menu); 
		grid = new GridPane(); int i, j; double circleY = 28 + 25;
		for (i = 0; i < 6; i++) {
			double circleX = 28;
			for (j = 0; j < 7; j++) {
				Circle circle = new Circle(20, Color.WHITE);
				circle.setCenterX(circleX);
				circle.setCenterY(circleY);
				grid.add(circle, j, i);
				circleX += 48;
			}
			circleY += 48;
		}
		grid.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		grid.setVgap(8); grid.setHgap(8);
		grid.setPadding(new Insets(8));
		grid.setOnMouseClicked(e -> {
			double x = e.getSceneX(); double y = e.getSceneY();
			int disk = c.getDisk(x, y);
			c.humanTurn(disk);
		});
		BorderPane p = new BorderPane();
		p.setCenter(grid); p.setTop(mb);
		Scene scene = new Scene(p, 344, 296 + 25); 
		stage.setScene(scene); stage.setTitle("Connect 4");
		stage.show();
	}


	/**
	 * update the view
	 *
	 * @param o    observable
	 * @param arg  the argument passed in
	 */	
	@Override
	public void update(Observable o, Object arg) {	
		if (arg instanceof List<?>) {
			@SuppressWarnings("unchecked")
			List<Circle> target = (List<Circle>) arg;
			if (target.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Column full, pick somewhere else!\n");
				alert.showAndWait();
				return;
			}
			int gridSize = grid.getChildren().size();
			for (Circle c : target) {
				Paint pre = c.getFill();
				double x = c.getCenterX(); double y = c.getCenterY();
				for (int i = 0; i < gridSize; i++) {
					Circle temp = (Circle) grid.getChildren().get(i);
					if (temp.getCenterX() == x && temp.getCenterY() == y) {
						temp.setFill(pre);
						break;
					}
				}
			}

		}
		if (arg instanceof Integer) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			int command = (int) arg;
			if (command == 0) {
				alert.setContentText("It is a draw!\n");
			}else if (command != 0) {
				alert.setContentText("You won!\n");
				//}else if (command == 1) {
				//	alert.setContentText("You lost!\n");
			}
			alert.showAndWait();

		}



	}
}
