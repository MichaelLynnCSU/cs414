import static org.junit.Assert.*;
import org.junit.Test;

public class JailTest extends BaseTest {

    String[] playerNames = {"Bill", "Ted"};
    String[] playerTokens = {"", ""};
    Controller controller = null;
    MonopolyBoard board = new MonopolyBoard(2, playerNames, playerTokens, controller);
    Jail jail = new Jail(board, 2);

    @Test
    public void testConstructor() {
        for(int i=0; i<jail.isJailed.length; i++) {
            assertTrue(!jail.isJailed[i]);
        }
        for(int i=0; i<jail.numDoubles.length; i++) {
            assertEquals( 0, jail.numDoubles[i]);
        }
        for(int i=0; i<jail.turnsInJail.length; i++) {
            assertEquals( 0, jail.turnsInJail[i]);
        }
        jail.board = board;
    }

    @Test
    public void getJailed() {
       jail.getJailed(0);
       assertTrue(!jail.isJailed[0]);
       jail.getJailed(1);
       assertTrue(!jail.isJailed[1]);

        for(int i=0; i<jail.isJailed.length; i++) {
            jail.isJailed[i] = false;
        }
    }

    @Test
    public void getNumDoubles() {
        assertEquals(0, jail.getnumDoubles(0));
        jail.numDoubles[0] = 2;
        assertEquals(2, jail.getnumDoubles(0));
    }

    @Test
    public void getTurns() {
        assertEquals(0, jail.getTurns(0));
        jail.turnsInJail[0] = 2;
        assertEquals(2, jail.getTurns(0));
    }

    @Test
    public void rolledDoubles() {
        jail.rolledDoubles(0);
        assertEquals(1, jail.numDoubles[0]);
        jail.rolledDoubles(0);
        assertEquals(2, jail.numDoubles[0]);
        jail.rolledDoubles(0);
        assertEquals(0, jail.numDoubles[0]);
        assertTrue(jail.isJailed[0]);
        assertEquals(0, jail.numDoubles[0]);
        assertEquals(0, jail.turnsInJail[0]);
    }

    @Test
    public void sentToJail() {
        int moneyBefore = board.getPlayer(1).getMoney();
        jail.sentToJail(1);
        assertEquals(10, board.getPlayer(1).getPosition());
        assertEquals(moneyBefore, board.getPlayer(1).getMoney());
        assertTrue(jail.isJailed[1]);
        assertEquals(0, jail.numDoubles[1]);
        assertEquals(0, jail.turnsInJail[1]);
    }

    @Test
    public void rolledDoublesJailed() {
        jail.sentToJail(0);
        assertTrue(jail.isJailed[0]);
        assertEquals(0, jail.numDoubles[0]);
        assertEquals(0, jail.turnsInJail[0]);

        jail.turnsInJail[0] = 1;
        jail.rolledDoublesJailed(0);
        assertFalse(jail.isJailed[0]);
        assertEquals(1, jail.numDoubles[0]);
        assertEquals(0, jail.turnsInJail[0]);
    }

    @Test
    public void payFine() {
        int moneyBefore = board.getPlayer(1).getMoney();
        jail.isJailed[1] = true;
        jail.numDoubles[1] = 1;
        jail.turnsInJail[1] = 2;
        jail.payFine(1);
        assertFalse(jail.isJailed[1]);
        assertEquals(0, jail.numDoubles[1]);
        assertEquals(0, jail.turnsInJail[1]);

        assertEquals(moneyBefore-50, board.getPlayer(1).getMoney());
    }

    @Test
    public void leftJail() {
        jail.isJailed[0] = true;
        jail.numDoubles[0] = 1;
        jail.turnsInJail[0] = 2;
        jail.leftJail(0);
        assertFalse(jail.isJailed[0]);
        assertEquals(0, jail.numDoubles[0]);
        assertEquals(0, jail.turnsInJail[0]);
    }

    @Test
    public void noDoubles() {
        jail.numDoubles[1] = 2;
        jail.noDoubles(1);
        assertEquals(0, jail.numDoubles[1]);
    }

    @Test
    public void noDoublesJailed() {
        jail.isJailed[0] = true;
        int moneyBefore = board.getPlayer(0).getMoney();
        jail.noDoublesJailed(0);
        assertEquals(1, jail.turnsInJail[0]);
        assertTrue(jail.isJailed[0]);
        assertEquals(0, jail.numDoubles[0]);
        assertEquals(moneyBefore, board.getPlayer(0).getMoney());

        jail.noDoublesJailed(0);
        assertEquals(2, jail.turnsInJail[0]);
        assertTrue(jail.isJailed[0]);
        assertEquals(0, jail.numDoubles[0]);
        assertEquals(moneyBefore, board.getPlayer(0).getMoney());

        jail.noDoublesJailed(0);
        assertEquals(0, jail.turnsInJail[0]);
        assertFalse(jail.isJailed[0]);
        assertEquals(0, jail.numDoubles[0]);
        assertEquals(moneyBefore-50, board.getPlayer(0).getMoney());
    }
}
