import static org.junit.Assert.*;
import org.junit.Test;

public class ControllerTest extends BaseTest {

    Controller controller = new Controller("");

    @Test
    public void testRoll() {
        controller.roll();
        assertFalse(controller.hasRolled());
    }
}
