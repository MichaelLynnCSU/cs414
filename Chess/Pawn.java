package a1;


import java.util.ArrayList;

public class Pawn extends ChessPiece{

    int moves = 0;

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
      //  System.out.println("Pawn Constructor");
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
        }

        return false;

    }


    private  ArrayList<String> moveUPx1() throws IllegalPositionException, IllegalMoveException {
        ArrayList<String> ok = new ArrayList<String>();

        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;
        System.out.println("2 ");
        if(color.equals(Color.WHITE)) {
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));
            moveUp++;
            if (collison(moveSides, moveUp)) {
            	 System.out.println("3 ");
                // do nothing
            } else {
            	 System.out.println("4 ");
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);
                System.out.println(move);
                ok.add(move);
            }
            }
        }
        else
        {
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));
            moveUp--;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
            if (collison(moveSides, moveUp)) {
                // do nothing
            } else {
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);

                ok.add(move);
            }
            }
        }
        moves++;
        return ok;
    }

    private  ArrayList<String> moveUPx2() throws IllegalPositionException, IllegalMoveException {

        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;

        if(color.equals(Color.WHITE)) {
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));
            moveUp++;
            moveUp++;

            if (collison(moveSides, moveUp)) {
                // do nothing
            } else {
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);
                System.out.println(move);
                ok.add(move);
            }
            }
        }
        else
        {
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));
            moveUp--;
            moveUp--;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
            if (collison(moveSides, moveUp)) {
                // do nothing
            } else {
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);
 
                ok.add(move);
            }
            }
        }
        moves++;
        return ok;
    }



    private  ArrayList<String> moveUPright() throws IllegalPositionException, IllegalMoveException {

        ArrayList<String> ok = new ArrayList<String>();

        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;
        if(color.equals(Color.WHITE)) {

            moveUp++;
            moveSides++;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));

            System.out.println("new position " + moveSides + moveUp);
            if (collison(moveSides, moveUp)) {
                // do nothing
            } else {
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);
                System.out.println(move);
                ok.add(move);
            }
            }
        }
        else
        {
            moveSides++;
            moveUp--;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));


            if (collison(moveSides, moveUp)) {
                // do nothing
            } else {
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);
   
                ok.add(move);
            }
        }
        }
        moves++;
        return ok;
    }

    private  ArrayList<String> moveUPleft() throws IllegalPositionException, IllegalMoveException {

        ArrayList<String> ok = new ArrayList<String>();

        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;
        if(color.equals(Color.WHITE)) {
            System.out.println("here 1");
            moveUp++;
            moveSides--;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
         
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));

            System.out.println("new position " + moveSides + moveUp);

            if (collison(moveSides, moveUp)) {
                // do nothing
            } else {
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);
                System.out.println(move);
                ok.add(move);
            }
            }
        }
        else
        {
            moveSides--;
            moveUp--;
            if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) 
            {
                System.out.println("bounds dont add this");
            }
            else
            {
            moveSides = alphabet.indexOf(alphabet.charAt(moveSides));


            if (collison(moveSides, moveUp)) {
                // do nothing
            } else {
                String move =  Character.toString(alphabet.charAt(moveSides));
                move += Integer.toString(moveUp);
    
                ok.add(move);
            }
        }
        }
        
        
        moves++;
        return ok;
    }


    public ArrayList<String> legalMoves() throws IllegalPositionException, IllegalMoveException {
        System.out.println(" color " + color);
        System.out.println("current position " + column + row);
        ArrayList<String> ok = new ArrayList<String>();

        if(moves == 0)
        {
        	   System.out.println("1 ");
           
            ok.addAll(moveUPx1());
            ok.addAll(moveUPx2());
            ok.addAll(moveUPright());
            ok.addAll(moveUPleft());
  	     

        }
    else
        {
            ok.addAll(moveUPx1());
            ok.addAll(moveUPright());
            ok.addAll(moveUPleft());
        }
        
        for(int i = 0; i < ok.size(); i++)
        {     
        String col =  ok.get(i).substring(0,1); 
        int row = Integer.parseInt(ok.get(i).substring(1,2));
        row++;
        System.out.println(col + row);               
        String offset = col;
        col += Integer.toString(row);     
        ok.set(i,col);
	    	}
        

        return ok;
    }

    public String toString()
    {
        if(color.equals(Color.WHITE))
            return  "\u2659";
        else
            return  "\u265F";
    }

}