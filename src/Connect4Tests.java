import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class Connect4Tests {
	
	@Test
	void testTurnAndDisk() {
		Connect4Model m = new Connect4Model();
		Connect4View v = new Connect4View();
		Connect4Controller c = new Connect4Controller(v, m);
		assertTrue(c.getTurn() == 1);
		assertTrue(c.getDisk(25, 44) == 0);
		assertTrue(c.getDisk(57, 44) == 1);
		assertTrue(c.getDisk(57 + 48 * 1, 44) == 2);
		assertTrue(c.getDisk(57 + 48 * 2, 44) == 3);
		assertTrue(c.getDisk(57 + 48 * 3, 44) == 4);
		assertTrue(c.getDisk(57 + 48 * 4, 44) == 5);
		assertTrue(c.getDisk(57 + 48 * 5, 44) == 6);
		
	}
	@Test
	void testMoveMessage() {
		Connect4MoveMessage m = new Connect4MoveMessage(3, 2, 1);
		assertTrue(m.getColor() == 1);
		assertTrue(m.getRow() == 3);
		assertTrue(m.getColumn() == 2);
	}
}