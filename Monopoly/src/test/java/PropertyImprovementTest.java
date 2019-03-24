import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyImprovementTest extends BaseTest {

    Property Vermont = new Property("Vermont Avenue", 100, 6, Property.Color.LIGHT_BLUE);
    Property Oriental = new Property("Oriental Avenue", 100, 6, Property.Color.LIGHT_BLUE);
    Property Conn = new Property("Connecticut Avenue", 120, 8, Property.Color.LIGHT_BLUE);

    String[] names = {"Jesse", "Meilin", "Mike", "Ken"};
    String[] tokens = {"Car", "Horse", "Cat", "Dog"};
    MonopolyBoard board = new MonopolyBoard(4, names, tokens, null);

    PropertyImprovement upgrade = new PropertyImprovement(Vermont);

    @Test
    public void buyImprovement() {
        upgrade.buyImprovement(board.getPlayer(1));
        assertEquals(1450, board.getPlayer(1).getMoney());

        upgrade.buyImprovement(board.getPlayer(1));
        assertEquals(1400, board.getPlayer(1).getMoney());

        upgrade.buyImprovement(board.getPlayer(1));
        assertEquals(1350, board.getPlayer(1).getMoney());

        upgrade.buyImprovement(board.getPlayer(1));
        assertEquals(1300, board.getPlayer(1).getMoney());

        upgrade.buyImprovement(board.getPlayer(1));
        assertEquals(1250, board.getPlayer(1).getMoney());
    }
}
