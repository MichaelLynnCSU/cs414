package a1;


import java.util.ArrayList;


abstract public class ChessPiece {

    public enum Color {WHITE, BLACK}

    protected ChessBoard board; // the board it belongs to, default null
    protected Color color; // the color of the piece
    protected int row; // the index of the horizontal rows 0..7
    protected int column; // the index of the vertical column 0..7


    public ChessPiece(ChessBoard board, Color color) {
        super();
       this.board = board;
       this.color = color;
    }

    public void setPosition(String position)
    {
        String alphabet = "abcdefgh";
        column =  alphabet.indexOf(position.charAt(0));
        row = Character.getNumericValue(position.charAt(1));
    //    System.out.println("Set Position " + position);
    }

    public String getPosition()
    {
        String alphabet = "abcdefgh";
       String col =  Character.toString(alphabet.charAt(column));
       row = row + 1;
       return col + row;
    }

    public Color getColor()
    {
        return color;
    }

    public String toString()
    {
        return "piece";
    }




        abstract public ArrayList<String> legalMoves() throws IllegalPositionException, IllegalMoveException;

}