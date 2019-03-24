import static org.junit.Assert.*;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class BoardPanelTest extends BaseTest {

    BoardPanel boardPanel = new BoardPanel();

    @Test
    public void testConstructor() {
        assertNotNull(boardPanel.images[0]);
        assertNotNull(boardPanel.images[1]);
        assertNotNull(boardPanel.images[2]);
        assertNotNull(boardPanel.images[3]);
    }

    @Test
    public void testAddPlayers() {
        boardPanel.addPlayers(1);
        assertEquals(1, boardPanel.players);
        assertNotNull(boardPanel.tokens);
        assertEquals(1, boardPanel.tokens.length);
        assertNotNull(boardPanel.startingPoints);
    }

    @Test
    public void testMove() {
        boardPanel.addPlayers(1);
        boardPanel.move(1, 0, 0, false);
        assertFalse(boardPanel.left);
        assertFalse(boardPanel.up);
        assertFalse(boardPanel.right);

        boardPanel.addPlayers(2);
        boardPanel.move(30, 0, 1, false);
        assertEquals(boardPanel.startingPoints[0][1], boardPanel.coordinates[0][1]);
        assertEquals(boardPanel.startingPoints[1][0] - 410, boardPanel.coordinates[1][0]);

        boardPanel.addPlayers(1);
        boardPanel.move(1, 10, 0, false);
        assertFalse(boardPanel.left);
        assertFalse(boardPanel.up);
        assertFalse(boardPanel.right);

        boardPanel.addPlayers(1);
        boardPanel.move(20, 0, 0, false);
        assertEquals(boardPanel.moveUp(20, 0), 20);
    }

    @Test
    public void testMoveRight() {
        boardPanel.addPlayers(1);
        assertEquals(1, boardPanel.moveRight(1, 0));
    }

    @Test
    public void testMoveDown() {
        boardPanel.addPlayers(1);
        assertEquals(1, boardPanel.moveDown(1, 0));
    }
}
