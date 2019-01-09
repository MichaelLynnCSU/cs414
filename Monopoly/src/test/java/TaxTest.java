import org.junit.Test;
import static org.junit.Assert.*;

public class TaxTest extends BaseTest{

	String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
	String[] tokens = {"Car", "Horse", "Cat", "Dog"};
	//Controller c = new Controller();
	MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);
	
	@Test
	public void testLuxuryTax() {
		
		Player player = board.getPlayer(0);
		board.setPosition(player, 38, true);
		assertEquals(player.getMoney(), 1425);
	}
	
	@Test
	public void testIncomeTax() {
		Player player = board.getPlayer(0);
		board.setPosition(player, 4, true);
		assertEquals(player.getMoney(), 1300);
	}
}
