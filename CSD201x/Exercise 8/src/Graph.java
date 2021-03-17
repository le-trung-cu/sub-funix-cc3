import java.util.ArrayList;

public class Graph {
    int[][] a;
    int n;

    Graph() {
        a = null;
        n = 0;
    }

    void clear() {
        a = null;
        n = 0;
    }

    void setData(int[][] b) {
        n = b.length;
        a = new int[n][n];

        int i, j;

        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                a[i][j] = b[i][j];

    }

    void dispAdj() {
        int i, j;

        System.out.println("\nThe adjacency matrix:");

        for (i = 0; i < n; i++) {
            System.out.println();

            for (j = 0; j < n; j++)
                System.out.printf("%5d", a[i][j]);

        }
        System.out.print(System.lineSeparator());
    }

    void visit(int i) {
        System.out.print(i + " ");
    }

    // Hàm duyệt theo BFS bắt đầu tại đỉnh k bằng cách sử dụng MyQueue, mỗi khi lấy
    // 1 đỉnh ra khỏi queue thì hiển thị luôn (duyệt) đỉnh đó ra màn hình
    void breadth(int k) {
        System.out.println();
        System.out.println("các đỉnh liên thông với đỉnh B: ");
        MyQueue q = new MyQueue();
        boolean[] visited = new boolean[this.n];

        q.enqueue(k);
        visited[k] = true;

        while (!q.isEmpty()) {
            int current = q.dequeue();
            if(current != k)
                System.out.print(current + ", ");
            ArrayList<Integer> neighbors = getNeighbors(current);
            for (int next : neighbors) {
                if(!visited[next]){
                    visited[next] = true;
                    q.enqueue(next);
                }
            }
        }
        System.out.print(System.lineSeparator());
    }

    // Hàm kiểm tra tính liên thông của đồ thị
    boolean isConnected() {
        MyQueue q = new MyQueue();
        boolean[] visited = new boolean[this.n];
        int countVisited = 0;
        int start = 0;
        q.enqueue(start);
        countVisited = 1;
        visited[start] = true;
        
        while(!q.isEmpty()){
            int current = q.dequeue();
            ArrayList<Integer> neighbors = getNeighbors(current);

            for (Integer next : neighbors) {
                if(!visited[next]){
                    q.enqueue(next);
                    visited[next] = true;
                    countVisited++;
                }
            }
        }

        return countVisited == n;
    }

    // Hàm tính bậc của đỉnh k
    int degree(int k) {
        return getNeighbors(k).size();
    }

    ArrayList<Integer> getNeighbors(int k){
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < this.n; i++) {
            if(a[k][i] == 1){
                neighbors.add(i);
            }
        }
        return neighbors;
    }

}
