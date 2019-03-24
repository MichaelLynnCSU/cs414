import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest extends BaseTest{
	
	Die die = new Die("");

	@Test
	public void testConstructor() {
		assertFalse(die.firstNum >0 && die.firstNum <=6);
		assertFalse(die.secondNum > 0 && die.secondNum <= 6);
	}
	
	@Test
	public void testRoll(){
		die.roll();
		assertTrue(die.firstNum >0 && die.firstNum <=6);
		assertTrue(die.secondNum > 0 && die.secondNum <= 6);
	}
	
	@Test
	public void testGetFirstNum(){
		int sum = die.firstNum + die.secondNum;
		assertTrue(sum - die.secondNum == die.getFirstNum());
		
	}
	
	@Test
	public void testGetSecondNum(){
		int sum = die.firstNum + die.secondNum;
		assertTrue(sum - die.firstNum == die.getSecondNum());
	}
	
	@Test
	public void testGetSum(){
		int sum = die.firstNum + die.secondNum;
		assertFalse(die.getSum() <= 12 && die.getSum() >=1);
		assertTrue(die.getSum() == sum);
		
	}

	@Test
	public void isDoubles() {
		Die d = new Die("-d");
		assertTrue(d.isDoubles());

	}
}
