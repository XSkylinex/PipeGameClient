package view;

import java.util.ArrayList;
import java.util.Collection;

public class Tile {

    private char ch;
    private int row;
    private int column;

    public Tile(char ch, int row, int column) {
        this.ch = ch;
        this.row = row;
        this.column = column;
    }

    public Tile rotate()
    {
        switch (this.ch) {
            case '-': {
                this.ch = '|';
                break;
            }
            case '|': {
                this.ch = '-';
                break;
            }
            case '7': {
                this.ch = 'J';
                break;
            }
            case 'J': {
                this.ch = 'L';
                break;
            }
            case 'L': {
                this.ch = 'F';
                break;
            }
            case 'F': {
                this.ch = '7';
                break;
            }
            default:
            {
                break;
            }
        }
        return this;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isStart(){
        return getCh()=='s';
    }

    public boolean isGoal(){

        return getCh()=='g';
    }
    public boolean isBlank(){

        return getCh()==' ';
    }


    public Collection<Direction> getDirections(){
        Collection<Direction> directions=new ArrayList<Direction>();
        switch (this.ch) {
            case 's': {
                directions.add(Direction.up);
                directions.add(Direction.down);
                directions.add(Direction.right);
                directions.add(Direction.left);
                break;
            }
            case 'g': {
                directions.add(Direction.up);
                directions.add(Direction.down);
                directions.add(Direction.right);
                directions.add(Direction.left);
                break;
            }
            case '7': {
                directions.add(Direction.left);
                directions.add(Direction.down);
                break;
            }
            case 'J': {
                directions.add(Direction.left);
                directions.add(Direction.up);
                break;
            }
            case 'L': {
                directions.add(Direction.up);
                directions.add(Direction.right);
                break;
            }
            case 'F': {
                directions.add(Direction.down);
                directions.add(Direction.right);
                break;
            }
            case '-': {
                directions.add(Direction.left);
                directions.add(Direction.right);
                break;
            }
            case '|': {
                directions.add(Direction.up);
                directions.add(Direction.down);
                break;
            }
        }
        return directions;
    }

    public int countRotations(){
        if(this.ch == '|' || this.ch == '-'){
            return 2;
        }
        if(this.ch == 's' || this.ch == 'g' || this.ch ==' '){
            return 1;
        }
        return 4;
    }

    public Direction isTilesNeighbors(Tile tile){ //If the tiles ​​are neighbors
        //if(this.row==tile.row && this.column == tile.column){
        //    throw new Exception("Same tile");
        //}
        if(this.row==tile.row+1 && this.column == tile.column){
            return Direction.down;
        }
        if(this.row==tile.row && this.column == tile.column+1){
            return Direction.right;
        }
        if(this.row==tile.row-1 && this.column == tile.column){
            return Direction.up;
        }
        if(this.row==tile.row && this.column == tile.column-1){
            return Direction.left;
        }
        return null;
    }

    public boolean isTilesAreConnect(Tile tile){ // Checks if tiles are connected
        return this.getDirections().contains(this.isTilesNeighbors(tile))&&
                tile.getDirections().contains(tile.isTilesNeighbors(this));
    }

    protected Tile clone(){ // create new instance
        return new Tile(this.getCh(),row,column);
    }

    public Tile defaultTile(){ // set to default tile
        char hashch=getCh();
        switch (this.ch) {
            case '-': case '|': {
                hashch = '-';
                break;
            }
            case '7': case 'J': case 'L': case 'F': {
                hashch = 'J';
                break;
            }
            default:
            {
                break;
            }
        }
        return new Tile(hashch,row,column);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile))
            return false;
        if (obj == this)
            return true;
        return this.equals(((Tile) obj));
    }

    public boolean equals(Tile tile) {
        return this.getCh()==tile.getCh()&&
                this.getColumn()==tile.getColumn()&&
                this.getRow()==tile.getRow();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ch;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }


    @Override
    public String toString() {
        return ch+"";
    }



}
