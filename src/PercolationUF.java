import java.util.*;

public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size){
        int n = size;
        myGrid = new boolean[n][n]; 
    
        for (boolean[] row: myGrid) {
            Arrays.fill(row, false);
        }

        myOpenCount = 0;

        finder.initialize(n*n+2);
        myFinder = finder;

        VTOP = n * n;
        VBOTTOM = n * n + 1;

    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
        int cell = row*myGrid.length + col;
        return myFinder.connected(cell, VTOP);
    }

    @Override 
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    @Override 
    public void open(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        if (! inBounds(row,col)) return;

        if (myGrid[row][col]) return;

        myGrid[row][col] = true;

        myOpenCount+=1;

        int cell = row*myGrid.length + col;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        if (row == 0) {
            myFinder.union(VTOP, cell);
        }

        if (row == myGrid.length - 1) {
            myFinder.union(VBOTTOM, cell);
        }

        for (int i = 0; i < dx.length; i++) {
            int ROW = row + dx[i];
            int COL = col + dy[i]; 
            int value = ROW*myGrid.length + COL;
            if (inBounds(ROW, COL) && isOpen(ROW, COL)) {
                myFinder.union(cell, value);
            }
        }
    }


    private boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
}
