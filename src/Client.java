import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Platform;


/**
 * client for connect4
 * 
 */
public class Client {

	@SuppressWarnings("unused")
	private boolean isHuman;
	private  Socket connection;
	@SuppressWarnings("unused")
	private  ObjectOutputStream output;
	private  ObjectInputStream input;

	/**
	 * constructor
	 *
	 * @param host     localhost
	 * @param port     the port
	 * @param isHuman  indicate if it is human play
	 */	
	public Client(String host, int port, boolean isHuman) throws Exception{
		this.isHuman = isHuman;
		Thread server = new Thread(){
			public void run() {
				try {
					connection = new Socket(host, port);
					output = new ObjectOutputStream(connection.getOutputStream());
					input = new ObjectInputStream(connection.getInputStream());
					Platform.runLater(() -> {
						System.out.println("connected!");
					});
					try {
						while(true) {
							@SuppressWarnings("unused")
							Connect4MoveMessage board = (Connect4MoveMessage) input.readObject();
							Platform.runLater(() -> {
								//get message / grid from controller
							});
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		server.start();
	}
}
