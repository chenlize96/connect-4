import java.io.Serializable;

/**
 * moveMessage for connect4 which is used in client and server
 */	
public class Connect4MoveMessage implements Serializable {
	public static int YELLOW = 1;
	public static int RED = 2;
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private int color;

	
	/**
	 * constructor
	 *
	 * @param row     the target row
	 * @param col     the target col
	 * @param color     the target color
	 */	
	public Connect4MoveMessage(int row, int col, int color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	/**
	 * get the row
	 *
	 * @return the row
	 */	
	public int getRow() {
		return row;
	}

	/**
	 * get the col
	 *
	 * @return the col
	 */	
	public int getColumn() {
		return col;
	}

	/**
	 * get the color
	 *
	 * @return the color
	 */	
	public int getColor() {
		return color;
	}

}