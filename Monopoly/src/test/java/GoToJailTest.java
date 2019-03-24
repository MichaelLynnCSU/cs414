import static org.junit.Assert.*;
import org.junit.Test;

public class GoToJailTest extends BaseTest {

    String[] playerNames = {"Bill", "Ted"};
    String[] playerTokens = {"", ""};
    Controller controller = null;
    MonopolyBoard board = new MonopolyBoard(2, playerNames, playerTokens, controller);
    GoToJail goToJail = new GoToJail("thing");

    @Test
    public void constructorTest() {
        assertEquals("thing", goToJail.name);
    }

    @Test
    public void doAction() {
        goToJail.doAction(board.getPlayer(0), board, controller);
        assertEquals(10, board.getPlayer(0).getPosition());
        assertTrue(board.getJail().isJailed[board.getPlayer(0).getTurn()]);
    }
}
