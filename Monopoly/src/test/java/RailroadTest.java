import org.junit.Test;
import static org.junit.Assert.*;

public class RailroadTest extends BaseTest{

	Railroad reading = new Railroad("Reading Railroad", 200); //Reading Railroad
	
	String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
	String[] tokens = {"Car", "Horse", "Cat", "Dog"};
	MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);
	
	
	@Test
	public void testConstructor() {
		assertTrue(reading.getName().equals("Reading Railroad"));
		assertEquals(reading.value, 200);
	}
	
	@Test
	public void testDoAction() {
		Player player = board.getPlayer(0);
		board.move(player, 5);
		assertTrue(board.getOwner(5).equals(player));
	}

	@Test
	public void testCalculateRent() {
		Player player = board.getPlayer(0);
		Player player2 = board.getPlayer(1);
		board.move(player,  5);
		board.move(player, 10);
		board.move(player2, 5);
		assertEquals(player2.getMoney(), 1450);
	}
	
	@Test
	public void testCalculateRent2() {
		Player player = board.getPlayer(0);
		Player player2 = board.getPlayer(1);
		board.move(player,  5);
		board.move(player,  10);
		board.move(player,  10);
		board.move(player,  10);
		board.move(player2, 5);		
		assertEquals(player2.getMoney(), 1300);
	}	
	
	@Test
	public void testMortgage() {
		Player player = board.getPlayer(0);
		board.move(player, 5);
		Railroad rail = (Railroad) player.getProperties().get(0);
		rail.mortgageProperty();
		assertEquals(player.getMoney(), 1400);
	}
	

}
