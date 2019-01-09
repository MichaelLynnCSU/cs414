package a1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class BishopTest {

	private static ChessBoard b1;
	
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		    
		    b1 = new ChessBoard();
			b1.placePiece(new Bishop(b1,ChessPiece.Color.WHITE),"a0"); // offset applyed in method
	     
				
	}
	
	@Test
	public void testLegalMoves() throws Exception, IllegalPositionException {
		
		ArrayList<String> places = new ArrayList<>(Arrays.asList("b2", "c3", "d4")); // this returns based of offset utility
		
	     ArrayList<String> ok = new ArrayList<String>(b1.getPiece("a1").legalMoves());
	     
	    for(int i = 0; i < ok.size(); i++)
	    {
	    	System.out.print(ok.get(i) + " " );
	    }
	     assertTrue(ok.containsAll(places));
	     			
	}
	
	@Test
	public void collision() throws Exception, IllegalPositionException {
		
		b1.placePiece(new Bishop(b1,ChessPiece.Color.WHITE),"d0"); // offset applyed in method
		
		b1.placePiece(new Bishop(b1,ChessPiece.Color.WHITE),"g3"); // offset applyed in method
		
	b1.placePiece(new Bishop(b1,ChessPiece.Color.BLACK),"b2"); // offset applyed in method
	
		ArrayList<String> places = new ArrayList<>(Arrays.asList("e2", "f3", "c2", "b3")); // this returns based of offset utility
		
	     ArrayList<String> ok = new ArrayList<String>(b1.getPiece("d1").legalMoves());
	     
	    for(int i = 0; i < ok.size(); i++)
	    {
	    	System.out.print(ok.get(i) + " " );
	    }
	     assertTrue(ok.containsAll(places));
	     			
	}

}
