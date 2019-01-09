import static org.junit.Assert.*;
import org.junit.Test;

public class CommunityChestCardsTest {

	String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
	String[] tokens = {"Car", "Horse", "Cat", "Dog"};
	MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);
	CommunityChestCards cards = new CommunityChestCards(board);

	Player player = board.getPlayer(0);

	@Test
	public void testCardDrawn0() {
		cards.cardDrawn(player, 0);
		assertEquals(player.getMoney(), 1700);
		assertEquals(0, player.getPosition());
	}

	@Test
	public void testCardDrawn1() {
		cards.cardDrawn(player, 1);
		assertEquals(player.getMoney(), 1700);
	}
	
	@Test
	public void testCardDrawn2() {
		cards.cardDrawn(player, 2);
		assertEquals(player.getMoney(), 1450);
	}

	//@Test
	public void testCardDrawn3() {
		cards.cardDrawn(player, 3);
		assertEquals(12, player.getPosition());
	}

	@Test
	public void testCardDrawn4() {
		cards.cardDrawn(player, 4);
		assertEquals(player.getMoney(), 1600);
	}
	
	@Test
	public void testCardDrawn5() {
		cards.cardDrawn(player, 15);
		assertTrue(player.leaveJailCC);
	}
}
