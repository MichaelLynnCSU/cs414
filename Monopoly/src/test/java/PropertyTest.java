import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyTest extends BaseTest{

	Property Vermont = new Property("Vermont Avenue", 100, 6, Property.Color.LIGHT_BLUE);
	Property Oriental = new Property("Oriental Avenue", 100, 6, Property.Color.LIGHT_BLUE);
	Property Conn = new Property("Connecticut Avenue", 120, 8, Property.Color.LIGHT_BLUE);

	String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
	String[] tokens = {"Car", "Horse", "Cat", "Dog"};
	MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);
	
	@Test
	public void testConstructor() {
		assertTrue(Vermont.color == Property.Color.LIGHT_BLUE);
		assertTrue(Vermont.value == 100);
		assertTrue(Vermont.rent == 6);
		assertTrue(Vermont.name.equals("Vermont Avenue"));

		assertEquals(10, Conn.upgradeRents[2][0]);
		assertEquals(180, Conn.upgradeRents[12][1]);
		assertEquals(800, Conn.upgradeRents[22][2]);
		assertEquals(1300, Conn.upgradeRents[35][3]);
	}
	
	@Test
	public void testDoAction() {
		Player player = board.getPlayer(0);
		Vermont.doAction(player, board, null);
		assertTrue(Vermont.owner.equals(player));
		assertTrue(player.getMoney() == 1400);
	}
	
	@Test
	public void testCalculateRent1() {
		Player player = board.getPlayer(0);
		Player player2 = board.getPlayer(1);
		board.move(player, 6);
		board.move(player2, 6);
		
		assertEquals(player2.getMoney(), 1494);
	}
	
	@Test
	public void testCalculateRent2() {
		Player player = board.getPlayer(0);
		Player player2 = board.getPlayer(1);
		board.move(player, 6);
		board.move(player, 2);
		board.move(player, 1);
		board.move(player2, 6);
		assertEquals(player2.getMoney(), 1488);
	}
	
	@Test
	public void testMortgage() {
		Player player = board.getPlayer(0);
		board.move(player, 6);
		Property prop = (Property)player.getMortgageable().get(0);
		prop.mortgageProperty();
		assertEquals(player.getMoney(), 1450);
	}
	
	@Test
	public void testUnmortgage() {
		Player player = board.getPlayer(0);
		board.move(player, 6);
		Property prop = (Property)player.getMortgageable().get(0);
		prop.mortgageProperty();
		prop.unmortgageProperty();
		assertEquals(player.getMoney(), 1390);
	}

	@Test
	public void upgrade() {
		Conn.upgrade();
		assertEquals(40, Conn.calculateRent(board));
		Conn.upgrade();
		assertEquals(100, Conn.calculateRent(board));
		Conn.upgrade();
		assertEquals(300, Conn.calculateRent(board));
		Conn.upgrade();
		assertEquals(450, Conn.calculateRent(board));
		Conn.upgrade();
		assertEquals(600, Conn.calculateRent(board));
		Conn.upgrade();

		Vermont.upgrade();
		assertEquals(30, Vermont.calculateRent(board));
		Vermont.upgrade();
		assertEquals(90, Vermont.calculateRent(board));
		Vermont.upgrade();
		assertEquals(270, Vermont.calculateRent(board));
		Vermont.upgrade();
		assertEquals(400, Vermont.calculateRent(board));
		Vermont.upgrade();
		assertEquals(550, Vermont.calculateRent(board));
	}
}
