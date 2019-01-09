package a1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class QueenTest {
	
	private static ChessBoard b1;
	
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		    
		    b1 = new ChessBoard();
			b1.placePiece(new Queen(b1,ChessPiece.Color.WHITE),"a0"); // offset applied in method
	     
				
	}

	@Test
	public void testLegalMoves() throws IllegalPositionException, IllegalMoveException {
		  ArrayList<String> ok = new ArrayList<String>(b1.getPiece("a1").legalMoves());
		  assertEquals(0,ok.size());
		  
		  
	}


}
