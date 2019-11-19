

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Connect4Model extends Observable {
	
	public int turn = 1;
	public List<Circle> target;
	public int disk = 0;
	public int first; // represent the color of the first player
	public int second;
	private int[][] panel;
	private Connect4MoveMessage preMove = null;
	
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
		printP();
	}
	
	public void printP() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(panel[i][j]);
			}
			System.out.println();
		}
	}

	public void gameOver(){
		System.out.println("turn £º"+turn);
		if (turn == 43) {
			setChanged();
			notifyObservers(0); // 0 means end, 1 means yellow wins
		}
	}
	
	public void checkWhoWins(int turn) {
		int row = preMove.getRow();
		int col = preMove.getColumn();
		int hue = preMove.getColor();
		System.out.println(row+" "+col+" "+hue);
				
	}
	
	
	
	// list contains the ball places we access
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
			System.out.println("row:"+message.getRow()+" col:"+message.getColumn()+" color:"+message.getColor());
			preMove = message;
			success = true;
			turn++;
		}
		setChanged();
		notifyObservers(target);
		printP();
		return success;
	}



	
	
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
