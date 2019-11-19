import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Platform;

/**
 * server for connect4
 * 
 */
public class Server {

	@SuppressWarnings("unused")
	private boolean isHuman;
	private Socket clientConnection;
	@SuppressWarnings("unused")
	private ObjectOutputStream output;
	private ObjectInputStream input;

	@SuppressWarnings("resource")
	
	/**
	 * constructor
	 *
	 * @param port     the port
	 * @param isHuman  indicate if it is human play
	 */	
	public Server(int port, boolean isHuman) throws Exception{
		this.isHuman = isHuman;
		ServerSocket serverSocket = new ServerSocket(port);		
		System.out.println("Waiting ...");
		Thread socketAccepter = new Thread() {
			public void run() {
				try {
					clientConnection = serverSocket.accept();
					output = new ObjectOutputStream(clientConnection.getOutputStream());
					input = new ObjectInputStream(clientConnection.getInputStream());
					Platform.runLater(() -> {
						System.out.println("connected1!");
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
		socketAccepter.start();			
	}

}
