
import static org.junit.Assert.*;
import org.junit.Test;

public class MonopolyBoardTest extends BaseTest {

	String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
	String[] tokens = {"Car", "Horse", "Cat", "Dog"};

	MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);
	Player player = board.getPlayer(0);
	
	@Test
	public void testConstructor() {
		for (int i=0; i<4; i++) {
			Player player = board.getPlayer(i);
			assertEquals(names[i], player.getName());
			assertEquals(tokens[i], player.getToken());
			assertEquals(1500, player.getMoney());
		}

		assertEquals(4, board.getNumPlayers());
		assertEquals(40, board.getBoard().length);
	}

	@Test
	public void move() {
		board.move(player, 4);
		assertEquals(4, player.getPosition());
	}

	@Test
	public void setPosition() {
		assertEquals(0, player.getPosition());
		board.setPosition(player, 20, true);
		assertEquals(20, player.getPosition());

		int moneyBefore = player.getMoney();
		board.setPosition(player, 19, true);
		assertEquals(19, player.getPosition());
		assertEquals(moneyBefore, player.getMoney());

		board.setPosition(player, 18, false);
		assertEquals(18, player.getPosition());
		assertEquals(1320, player.getMoney());

		board.setPosition(player, 17, true);
		assertEquals(17, player.getPosition());
		assertEquals(1520, player.getMoney());

		Player player = board.getPlayer(1);
		board.setPosition(player, 37, true);
		assertEquals(37, player.getPosition());
	}

	@Test
	public void movePassGo(){
		player.setPosition(37);
		board.move(player, 6);
		assertEquals(3, player.getPosition());
		assertEquals(1640, player.getMoney());
	}

	@Test
	public void passGo(){
		board.passGo(player);
		assertEquals(1700, player.getMoney());
	}

	@Test
	public void getPlayer() {
		assertEquals(player, board.getPlayer(0));
	}

	@Test
	public void getNumPlayers() {
		assertEquals(4, board.getNumPlayers());
	}

	@Test
	public void getCurrentRoll() {
		assertEquals(0, board.getCurrentRoll());
	}

	@Test
	public void setCurrentRoll() {
		board.setCurrentRoll(2);
		assertEquals(2, board.getCurrentRoll());
	}

	@Test
	public void getBoard() {
		Square[] squares = board.getBoard();
		assertEquals(40, squares.length);
		Property baltic = new Property("Baltic Avenue", 60, 2, Property.Color.DARK_PURPLE);
		assertEquals(baltic.getName(), squares[3].getName());
	}

	@Test
	public void getOwner() {
		assertNull(board.getOwner(0));
	}

	@Test
	public void calculateRent() {
		assertEquals(16, board.calculateRent(19));
	}

	@Test
	public void isJailed() {
		assertFalse(board.isJailed(0));
		board.getJail().sentToJail(0);
		assertTrue(board.isJailed(0));
	}

	@Test
	public void rolledDoubles() {
		assertFalse(board.rolledDoubles(0));
		board.getJail().numDoubles[0] = 2;
		assertTrue(board.rolledDoubles(0));
	}

	@Test
	public void noDoubles() {
		board.getJail().numDoubles[0] = 2;
		board.noDoubles(0);
		assertEquals(0, board.getJail().numDoubles[0]);
	}

	@Test
	public void leftJail() {
		board.getJail().isJailed[0] = true;
		board.getJail().turnsInJail[0] = 2;
		board.getJail().numDoubles[0] = 2;
		board.leftJail(0);
		assertFalse(board.getJail().isJailed[0]);
		assertEquals(0, board.getJail().turnsInJail[0]);
		assertEquals(0, board.getJail().numDoubles[0]);
	}

	@Test
	public void noDoublesJailed() {
		int moneyBefore = board.getPlayer(0).getMoney();
		board.sentToJail(0);
		board.noDoublesJailed(0);
		assertTrue(board.isJailed(0));
		assertEquals(moneyBefore, board.getPlayer(0).getMoney());
		assertEquals(1, board.getJail().turnsInJail[0]);
		board.noDoublesJailed(0);
		assertTrue(board.isJailed(0));
		assertEquals(moneyBefore, board.getPlayer(0).getMoney());
		assertEquals(2, board.getJail().turnsInJail[0]);
		board.noDoublesJailed(0);
		assertFalse(board.isJailed(0));
		assertEquals(moneyBefore-50, board.getPlayer(0).getMoney());
		assertEquals(0, board.getJail().turnsInJail[0]);
	}

	@Test
	public void jailedPayFine() {
		board.sentToJail(1);
		int moneyBefore = board.getPlayer(1).getMoney();
		board.jailedPayFine(1);
		assertEquals(moneyBefore-50, board.getPlayer(1).getMoney());
		assertFalse(board.isJailed(1));
	}

	@Test
	public void sentToJail() {
		int moneyBefore = board.getPlayer(1).getMoney();
		board.sentToJail(1);
		assertEquals(10, board.getPlayer(1).getPosition());
		assertEquals(moneyBefore, board.getPlayer(1).getMoney());
		assertTrue(board.isJailed(1));
		assertEquals(0, board.getJail().numDoubles[1]);
		assertEquals(0, board.getJail().turnsInJail[1]);
	}
	
	@Test
	public void sentToJailPlayer() {
		Player player = board.getPlayer(0);
		board.sentToJail(player);
		assertTrue(board.isJailed(0));
	}

	@Test
	public void getJail() {
		assertEquals(0, board.getJail().numDoubles[0]);
	}
	
	@Test
	public void leaveJailCC() {
		player.leaveJailCC = true;
		board.leaveJailCC(player);
		assertFalse(player.leaveJailCC);
	}
	
	@Test
	public void leaveJailChance() {
		player.leaveJailChance = true;
		board.leaveJailChance(player);
		assertFalse(player.leaveJailChance);
	}
}
