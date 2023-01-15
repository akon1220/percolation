import java.util.*;

public class PercolationBFS extends PercolationDFSFast{
    public PercolationBFS(int n) {
        super(n);
    }
    
    @Override
    protected void search(int row, int col) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        if (myGrid[row][col] == FULL) return;
        if (!isOpen(row,col)) return;

        Queue<int[]> qp = new LinkedList<>();
        myGrid[row][col] = FULL;
        
        qp.add(new int[]{row, col});

        while (qp.size() != 0) {
            int[] p = qp.remove();
            for (int i = 0; i < dx.length; i++){
                row = p[0] + dx[i];
                col = p[1] + dy[i];
                if (inBounds(row, col) && isOpen(row,col) && !isFull(row, col)) {
                    qp.add(new int[]{row, col});
                    myGrid[row][col] = FULL;
                }
            }
        }
    }
}
