package a1;


import java.util.ArrayList;

public class Queen extends ChessPiece{

    public Queen(ChessBoard board, Color color) {
        super(board, color);
     //   System.out.println("Queen Constructor");
    }

    public ArrayList<String> legalMoves() throws IllegalPositionException, IllegalMoveException {

        ArrayList<String> ok = new ArrayList<String>();

//        ChessPiece rook = new Rook(board,color);
//        ChessPiece bishop = new Bishop(board,color);
//
//
//        rook.row = this.row;
//        rook.column = this.column;
//        bishop.row = this.row;
//        bishop.column = this.column;
//
//
//        ok.addAll(rook.legalMoves());
//        ok.addAll(bishop.legalMoves());
//        
     

        return ok;
    }

    public String toString()
    {
        if(color.equals(Color.WHITE))
            return "\u2655";
        else
            return  "\u265B";
    }
}