package a1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class PawnTest {

	private static ChessBoard b1;
	private static String fromPos = "a1";
	
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		    
		    b1 = new ChessBoard();
			b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"a0"); // offset applyed in method
	     
				
	}
	
	@Test
	public void testLegalMoves() throws Exception, IllegalPositionException {
		
		ArrayList<String> places = new ArrayList<>(Arrays.asList("a2", "a3", "b2")); // this returns based of offset utility
		
	     ArrayList<String> ok = new ArrayList<String>(b1.getPiece(fromPos).legalMoves());
	     
	     for(int i = 0; i < ok.size(); i++)
		    {
		    	System.out.println("pawn test" + ok.get(i) );
		    }
	     
	     assertTrue(ok.containsAll(places));
	     			
	}
	


}
