import org.junit.Test;

public class BottomPanelTest extends BaseTest {

    Controller controller = new Controller("");
    Window window = new Window(controller);
    BottomPanel bottomPanel = new BottomPanel(window);

    @Test
    public void testConstructor() {
        bottomPanel = new BottomPanel(window);
    }
}
