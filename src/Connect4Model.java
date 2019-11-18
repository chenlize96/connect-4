import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Connect4Model extends Observable {
	
	public int turn = 1;
	public List<Circle> target = new ArrayList<>();
	
	
	// list contains the ball places we access
	public void move(List<Circle> target) {
		Iterator<Circle> it = target.iterator();
		while (it.hasNext()) {
			if (it.next().getFill() != Color.WHITE) {
				it.remove();
			}
		}
		Paint setColor = Color.YELLOW;
		int i = 0;
		while (i < target.size()) {
			for (int j = 0; j < target.size(); j++) {
				if (j == i) {
					target.get(j).setFill(setColor);
				}else {
					target.get(j).setFill(Color.WHITE);
				}
			}
			System.out.println("1");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setChanged();
			notifyObservers(target);
			
			
			i++;
		}

	}
	
	
	
	
	
	public int getDisk(double x, double y) {
		int disk = 0;
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
