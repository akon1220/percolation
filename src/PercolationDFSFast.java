public class PercolationDFSFast extends PercolationDFS {
    public PercolationDFSFast(int n) {
        super(n);
    }
    
    @Override
    protected void updateOnOpen(int row, int col) {
        if (!inBounds(row, col)) {
            return;
        }

        boolean full = false;
        if (row == 0 ) {
            full = true;
        }
        if (inBounds(row-1, col) && isFull(row-1, col)) {
            full = true;
        }
        if (inBounds(row+1, col) && isFull(row+1, col)) {
            full = true;
        }
        if (inBounds(row, col-1) && isFull(row, col-1)) {
            full = true;
        }
        if (inBounds(row, col+1) && isFull(row, col+1)){
            full = true;
        } 
        if (full == true) {
            search(row, col);
        } 
    }
}
