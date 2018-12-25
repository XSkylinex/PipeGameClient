package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

class Index{
    int row;
    int col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return row == index.row &&
                col == index.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Index{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

public class Maze implements Searchable<Index> {
    private Tile[][] d2arryMaze;
    private int row;
    private int col;

    public Maze() {
        this(new Tile[0][0]);
    }

    public Maze(Tile[][] d2arryMaze) {
        this(d2arryMaze,d2arryMaze.length,(d2arryMaze.length==0)?0:d2arryMaze[0].length);
    }

    private Maze(Tile[][] d2arryMaze ,int row, int col) {
        this.d2arryMaze = d2arryMaze;
        this.row = row;
        this.col = col;
    }

    public Maze(ArrayList<String> arrayListMaze) {
        String[][] strings=new String[arrayListMaze.size()][];
        for (int i = 0; i < arrayListMaze.size(); i++) {
            strings[i]=arrayListMaze.get(i).split(",");
        }
        this.d2arryMaze = new Tile[strings.length][(strings.length==0)?0:strings[0].length];
        for (int i = 0; i < this.d2arryMaze.length; i++) {
            for (int j = 0; j < this.d2arryMaze[i].length; j++) {
                d2arryMaze[i][j] = new Tile(strings[i][j].charAt(0),i,j);
            }
        }
        this.row = this.d2arryMaze.length;
        this.col = (d2arryMaze.length==0)?0:d2arryMaze[0].length;

    }

    public Tile[][] getD2arryMaze() {
        return d2arryMaze;
    }

    public void setD2arryMaze(Tile[][] d2arryMaze) {
        this.d2arryMaze = d2arryMaze;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        for (Tile[] tiles : d2arryMaze) {
            stringBuilder.append(Arrays.toString(tiles)).append("\n");
        }
        return stringBuilder.toString();
    }


    public Tile getStart() throws Exception {
        for (Tile[] tiles : d2arryMaze) {
            for (Tile tile : tiles) {
                if(tile.isStart())
                    return tile;
            }
        }
        throw new Exception("no start");
    }

    public Tile getEnd() throws Exception {
        for (Tile[] tiles : d2arryMaze) {
            for (Tile tile : tiles) {
                if(tile.isGoal())
                    return tile;
            }
        }
        throw new Exception("No end");
    }


    @Override
    public State<Index> getInitialState() {
        return null;
    }

    @Override
    public Collection<State<Index>> getAllPossibleStates(State<Index> s) {
        return null;
    }

    @Override
    public Boolean IsGoalState(State<Index> s) {
        return null;
    }
}
