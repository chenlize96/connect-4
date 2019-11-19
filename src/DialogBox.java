import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogBox extends Stage {
	DialogBox dialog = this;
	public DialogBox() {
		Label label1 = new Label("Create:");
		// create radiobuttons 
		RadioButton r1 = new RadioButton("Server"); 
		RadioButton r2 = new RadioButton("Client");
		r1.setSelected(true);
		ToggleGroup tg1 = new ToggleGroup(); 
		r1.setToggleGroup(tg1); 
		r2.setToggleGroup(tg1);
		HBox hbox1 = new HBox(label1, r1, r2);
		hbox1.setSpacing(20);

		Label label2 = new Label("Play as:");
		// create radiobuttons 
		RadioButton r3 = new RadioButton("Human"); 
		RadioButton r4 = new RadioButton("Computer");
		r3.setSelected(true);
		ToggleGroup tg2 = new ToggleGroup(); 
		r3.setToggleGroup(tg2); 
		r4.setToggleGroup(tg2);
		HBox hbox2 = new HBox(label2, r3, r4);
		hbox2.setSpacing(20);

		Label label3 = new Label("Server");
		TextField text1 = new TextField("localhost");
		Label label4 = new Label("Port");
		TextField text2 = new TextField("4000");
		HBox hbox3 = new HBox(label3, text1, label4, text2);
		hbox3.setSpacing(20);

		Button b1 = new Button("OK");
		b1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(r1.isSelected()) {
					try {
						Server sPlayer= new Server(
								Integer.parseInt(text2.getText()), r3.isSelected() );
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					try {
						Client cPlayer = new Client(text1.getText(),
								Integer.parseInt(text2.getText()), r3.isSelected() );
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				dialog.close();
			}

		});
		Button b2 = new Button("Cancel");
		b2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialog.close();
			}
		});
		HBox hbox4 = new HBox(b1, b2);
		hbox4.setSpacing(20);


		VBox vbox = new VBox(hbox1, hbox2, hbox3, hbox4);
		vbox.setSpacing(30);
		vbox.setPadding(new Insets(30));



		BorderPane p2 = new BorderPane();
		p2.setCenter(vbox);
		hbox1.setAlignment(Pos.CENTER_LEFT);
		this.setTitle("Network Setup");
		Scene scene = new Scene(p2, 500, 250); 
		this.setScene(scene);

	}
}
