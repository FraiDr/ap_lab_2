import java.util.*;



class GFG
{
    static WorkWithFile w = new WorkWithFile();
    static int[] arr;

    static {
        try {
            arr = w.readFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int ROW = arr[4];
    static int COL = arr[5];


    static class queueNode
    {
        int x;

        int y;// The coordinates of a cell
        int dist; // cell's distance of from the source

        public queueNode(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    // check whether given cell (row, col)
// is a valid cell or not.
    static boolean isValid(int row, int col)
    {
        // return true if row number and
        // column number is in range
        return (row >= 0) && (row < ROW) &&
                (col >= 0) && (col < COL);
    }

    // These arrays are used to get row and column
// numbers of 4 neighbours of a given cell
    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    // function to find the shortest path between
// a given source cell to a destination cell.
    static int BFS(int[][] mat, int xStart, int yStart, int xDest, int yDest)
    {
        if (mat[xStart][yStart] != 1 ||
                mat[xDest][yDest] != 1)
            return -1;

        boolean [][]visited = new boolean[ROW][COL];

        // Mark the source cell as visited
        visited[xStart][yStart] = true;

        // Create a queue for BFS
        Queue<queueNode> q = new LinkedList<>();

        // Distance of source cell is 0
        queueNode s = new queueNode(xStart,yStart, 0);
        q.add(s); // Enqueue source cell

        // Do a BFS starting from source cell
        while (!q.isEmpty())
        {
            queueNode curr = q.peek();
            if (curr.x == xDest && curr.y == yDest)
                return curr.dist;

            // Otherwise dequeue the front cell
            // in the queue and enqueue
            // its adjacent cells
            q.remove();

            for (int i = 0; i < 4; i++)
            {
                int row = curr.x + rowNum[i];
                int col = curr.y + colNum[i];

                // if adjacent cell is valid, has path
                // and not visited yet, enqueue it.
                if (isValid(row, col) &&
                        mat[row][col] == 1 &&
                        !visited[row][col])
                {
                    // mark cell as visited and enqueue it
                    visited[row][col] = true;
                    queueNode Adjcell = new queueNode
                            (row,col,curr.dist + 1 );
                    q.add(Adjcell);
                }
            }
        }

        // Return -1 if destination cannot be reached
        return -1;
    }

    public static int[][] doMatrix(int col, int row)
    {
        Random random=new Random();
        int[][] matrix=new int[row][col];
        for (int i=0; i<row; i++){
            for (int j=0;j<col; j++)
            {
                if(i==0 && j == 0)
                    matrix[i][j]=1;
                else
                    matrix[i][j]= random.nextInt(2);
            }
        }
        return matrix;
    }

    // Driver Code
    public static void main(String[] args) throws Exception {

        int[] arr = w.readFile();
        int[][] matrix = doMatrix(ROW,COL);
        int shortest_distance=BFS(matrix,arr[0],arr[1],arr[2],arr[3]);
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n"));
        System.out.println(shortest_distance);
        w.addFile(shortest_distance);

    }
}