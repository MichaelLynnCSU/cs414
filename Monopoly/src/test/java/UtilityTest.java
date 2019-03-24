import org.junit.Test;
import static org.junit.Assert.*;

public class UtilityTest extends BaseTest{

	String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
	String[] tokens = {"Car", "Horse", "Cat", "Dog"};
	MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);	
	Utility waterWorks = new Utility("Water Works", 150);

	@Test
	public void testConstructor() {
		assertEquals(150, waterWorks.value);
		assertFalse(waterWorks.mortgaged);
		assertNull(waterWorks.owner);
		assertEquals("Water Works", waterWorks.name);
	}
	
	@Test
	public void testBuyProperty() {
		Player player = board.getPlayer(0);
		assertEquals(player.getProperties().size(), 0);
		board.setPosition(player,  28,  true);
		assertEquals(player.getProperties().size(), 1);
	}

	@Test
	public void calculateRent() {
		board.setCurrentRoll(7);
		assertEquals(28, waterWorks.calculateRent(board));
	}
}
