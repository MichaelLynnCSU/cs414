import static org.junit.Assert.*;
import org.junit.Test;

public class ChanceCardsTest {
	
	String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
	String[] tokens = {"Car", "Horse", "Cat", "Dog"};
	MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);
	ChanceCards cards = new ChanceCards(board);

	Player player = board.getPlayer(0);
	
	@Test
	public void testCardDrawn() {
		cards.cardDrawn(player, 1);
		assertTrue(player.getPosition() == 24);
	}
	
	@Test
	public void testCardDrawn1() {
		cards.cardDrawn(player, 5);
		assertTrue(player.getMoney() == 1600);
	}
	
	@Test
	public void testCardDrawn2() {
		cards.cardDrawn(player, 6);
		assertTrue(player.getMoney() == 1550);
	}
	
	@Test
	public void testCardDrawn3() {
		cards.cardDrawn(player, 10);
		assertTrue(player.getMoney() == 1485);
	}
	
	@Test
	public void testCardDrawn4() {
		cards.cardDrawn(player, 4);
		assertEquals(player.getPosition(), 5);
	}
	
	@Test
	public void testCardDrawn5() {
		cards.cardDrawn(player, 8);
		assertTrue(board.isJailed(0));
	}
	
	@Test
	public void testcardDrawn6() {
		cards.cardDrawn(player, 15);
		assertTrue(player.leaveJailChance);
	}
}
