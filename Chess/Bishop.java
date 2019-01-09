package a1;


import java.util.ArrayList;

public class Bishop extends ChessPiece{

    public Bishop(ChessBoard board, Color color) {
        super(board, color);
     //   System.out.println("Bishop Constructor");
    }

    private Boolean collison(int x2, int y2) throws IllegalPositionException, IllegalMoveException {

        String alphabet = "abcdefgh";
        String col =  Character.toString(alphabet.charAt(x2));
        String Pos = col+(y2+1);


        if(board.getPiece(Pos) != null) {
            ChessPiece piece = board.getPiece(Pos);
            if (piece.color.equals(color)) {
                System.out.println("collision with " + piece + color + piece.color);
                return true;
            }
            if (!piece.color.equals(color)) {
                System.out.println("collision capture " + piece + color + piece.color);
                return true;
            }
        }

        return false;

    }

    private  ArrayList<String> moveUPright() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move up");
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;

        for (int j = column, i = row; j < 7 && i < 7; j++, i++)
        {
            moveUp++;
            moveSides++;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) {
                System.out.println("bounds dont add this");
            }
            else {
                int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                String movesides2 = Character.toString(alphabet.charAt(temp));
                movesides2 += Integer.toString(moveUp);


                if (collison(moveSides, moveUp)) {
                 	
                	
                	 
                    String col =  Character.toString(alphabet.charAt(moveSides));
                    String Pos = col+(moveUp+1);
                    ChessPiece piece = board.getPiece(Pos);
                	 if (!piece.color.equals(color)) {   
      
                	       ok.add(movesides2);
                	       break;
                	 }
                	 else{
                		 for(int k = 0; k < ok.size(); k++)
             		    {
             		    	System.out.print(ok.get(k) + " " );
             		    }
                		   System.out.println("same color dont add " + movesides2);
                	       ok.add(movesides2);
                	         ok.remove(ok.size() - 1);
                		 break;
                	 }
                } else {
                    ok.add(movesides2);
                }
            }
        }

        return ok;
    }


    private  ArrayList<String> moveDownright() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move up");
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;

        for (int j = column, i = row; j < 7 && i > 0; j++, i--)
        {
            moveUp--;
            moveSides++;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) {
                System.out.println("bounds dont add this");
            }
            else {
                int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                String movesides2 = Character.toString(alphabet.charAt(temp));
                movesides2 += Integer.toString(moveUp);


                if (collison(moveSides, moveUp)) {
                 	
                	
                	 
                    String col =  Character.toString(alphabet.charAt(moveSides));
                    String Pos = col+(moveUp+1);
                    ChessPiece piece = board.getPiece(Pos);
                	 if (!piece.color.equals(color)) {     
        
                	       ok.add(movesides2);
                	       break;
                	 }
                	 else{
                		 for(int k = 0; k < ok.size(); k++)
             		    {
             		    	System.out.print(ok.get(k) + " " );
             		    }
                		   System.out.println("same color dont add " + movesides2);
                	       ok.add(movesides2);
                	         ok.remove(ok.size() - 1);
                		 break;
                	 }
                } else {
                    ok.add(movesides2);
                }
            }
        }

        return ok;
    }

    private  ArrayList<String> moveUPleft() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move up");
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;

        for (int j = column, i = row; j > 0 && i < 7; j--, i++)
        {
            moveUp++;
            moveSides--;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) {
                System.out.println("bounds dont add this");
            }
            else {
                int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                String movesides2 = Character.toString(alphabet.charAt(temp));
                movesides2 += Integer.toString(moveUp);


                if (collison(moveSides, moveUp)) {
                 	
                	
        		  	System.out.print("hrtr" );
                    String col =  Character.toString(alphabet.charAt(moveSides));
                    String Pos = col+(moveUp+1);
                    ChessPiece piece = board.getPiece(Pos);
                	 if (!piece.color.equals(color)) { 

                	       ok.add(movesides2);
                	       break;
                	 }
                	 else{
                		 for(int k = 0; k < ok.size(); k++)
             		    {
             		    	System.out.print(ok.get(k) + " " );
             		    }
                		   System.out.println("same color dont add " + movesides2);
                	       ok.add(movesides2);
                	         ok.remove(ok.size() - 1);
                		 break;
                	 }
                } else {
                    ok.add(movesides2);
                }
            }
        }

        return ok;
    }

    private  ArrayList<String> moveDownleft() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move up");
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;

        for (int j = column, i = row; j > 0 && i > 0; j--, i--)
        {
            moveUp--;
            moveSides--;
            if (moveUp < 0 || moveUp >= 8 || moveSides < 0 || moveSides >= 8) {
                System.out.println("bounds dont add this");
            }
            else {
                int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                String movesides2 = Character.toString(alphabet.charAt(temp));
                movesides2 += Integer.toString(moveUp);


                if (collison(moveSides, moveUp)) {
                 	
                	
                	 
                    String col =  Character.toString(alphabet.charAt(moveSides));
                    String Pos = col+(moveUp+1);
                    ChessPiece piece = board.getPiece(Pos);
                	 if (!piece.color.equals(color)) {       

                	       ok.add(movesides2);
                	       break;
                	 }
                	 else{
                		 for(int k = 0; k < ok.size(); k++)
             		    {
             		    	System.out.print(ok.get(k) + " " );
             		    }
                		   System.out.println("same color dont add " + movesides2);
                	       ok.add(movesides2);
                	         ok.remove(ok.size() - 1);
                		 break;
                	 }
                } else {
                    ok.add(movesides2);
                }
            }
        }

        return ok;
    }

    public ArrayList<String> legalMoves() throws IllegalPositionException, IllegalMoveException {

        ArrayList<String> ok = new ArrayList<String>();
        ok.addAll(moveUPright());
        ok.addAll(moveDownright());
        ok.addAll(moveUPleft());
        ok.addAll(moveDownleft());
        
        for(int i = 0; i < ok.size(); i++)
        {     
        String col =  ok.get(i).substring(0,1); 
        int row = Integer.parseInt(ok.get(i).substring(1,2));
        row++;             
        String offset = col;
        col += Integer.toString(row);     
        ok.set(i,col);
	    	}
        
        
        return ok;
    }

    public String toString()
    {
        if(color.equals(Color.WHITE))
            return  "\u2657";
        else
            return  "\u265D";
    }

}