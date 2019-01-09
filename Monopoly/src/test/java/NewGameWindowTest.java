import static org.junit.Assert.*;
import org.junit.Test;

public class NewGameWindowTest extends BaseTest {

    Controller controller = new Controller("");
    NewGameWindow newGameWindow = new NewGameWindow(controller);

    @Test
    public void testConstructor() {
        newGameWindow = new NewGameWindow(controller);
        assertEquals(controller, newGameWindow.controller);
        assertEquals(20, newGameWindow.player1.getColumns());
    }

    @Test
    public void testGetNames() {
        String[] names = {"1", "2"};
        //assertEquals(names, newGameWindow.getNames());
    }
}
