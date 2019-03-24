
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest extends BaseTest {
	
	Player player = new Player("Billy", "Car", 0);
	Player player2 = new Player("Bob", "Apple", 1);
	Player player1 = new Player("Steve", "Car", 2);
	
	@Test
	public void testName() {
		assertTrue(player.getName().equals("Billy"));
	}
	
	@Test
	public void testToken() {
		assertTrue(player.getToken().equals("Car"));
	}
	
	@Test
	public void testMoney() {
		assertTrue(player.getMoney() == 1500);
	}
	
	@Test
	public void testAddMoney() {
		player.addMoney(200);
		assertTrue(player.getMoney() == 1700);
	}
	
	@Test
	public void testSubtractMoney() {
		player.subtractMoney(200);
		assertTrue(player.getMoney() == 1300);
	}
	
	@Test
	public void testEquals() {
		assertTrue(player.equals(player1));
		assertFalse(player.equals(player2));
		Player none = null;
		assertFalse(player2.equals(null));
		//assertFalse(none.equals(player2));
	}
	
	@Test
	public void testAddProperty() {
		Property Tennessee = new Property("Tennessee Avenue", 180, 14, Property.Color.ORANGE);
		Property NewYork = new Property("New York Avenue", 200, 16, Property.Color.ORANGE);
		player.addProperty(Tennessee);
		player.addProperty(NewYork);
		ArrayList<BuyableProperty> props = player.getProperties();
		assertTrue(props.contains(NewYork));
		assertTrue(props.contains(Tennessee));
		assertEquals(props.size(), 2);
	}
	
	@Test
	public void testGetMortgageable() {
		Property Tennessee = new Property("Tennessee Avenue", 180, 14, Property.Color.ORANGE);
		Property NewYork = new Property("New York Avenue", 200, 16, Property.Color.ORANGE);
		player.addProperty(Tennessee);
		player.addProperty(NewYork);
		assertEquals(player.getMortgageable().size(), 2);
	}
	
	@Test
	public void testGetMortgaged() {
		Property Tennessee = new Property("Tennessee Avenue", 180, 14, Property.Color.ORANGE);
		Property NewYork = new Property("New York Avenue", 200, 16, Property.Color.ORANGE);
		NewYork.buyProperty(player);
		Tennessee.buyProperty(player);
		//player.addProperty(Tennessee);
		//player.addProperty(NewYork);
		assertEquals(player.getUnmortgageable().size(), 0);
		Tennessee.mortgageProperty();
		NewYork.mortgageProperty();
		assertEquals(player.getUnmortgageable().size(), 2);
	}
}
