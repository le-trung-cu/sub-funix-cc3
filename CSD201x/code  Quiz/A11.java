/**
 * A11
 */
public class A11 {
    int[][] g = new int[][] { { 2, 3, 4 }, { 1, 3, 4, 6 }, { 1, 2, 4, 5 }, { 1, 2, 3, 7 }, { 3, 6, 7, 8, 12 },
            { 2, 5, 7, 12 }, { 4, 5, 6, 8 }, { 5, 7, 12 }, { 10, 11, 13 }, { 9, 11, 12, 13 }, { 9, 10, 13 },
            { 5, 6, 8, 10 }, { 9, 10, 11 } };
    int n = 13;
    int id = 0;
    int outEdgeCount = 0;
    int[] low = new int[n];
    int[] ids = new int[n];
    boolean[] visited = new boolean[n];
    boolean[] isArt = new boolean[n];

    public boolean[] findArtPoints() {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                outEdgeCount = 0; // Reset edge count
                dfs(i, i, -1);
                isArt[i] = (outEdgeCount > 1);
            }
        }
        return isArt;
    }

    public void dfs(int root, int at, int parent) {
        if (parent == root) {
            outEdgeCount++;
        }
        visited[at] = true;
        id = id + 1;
        low[at] = id;
        ids[at] = id;

        for (int to : g[at]) {
            to = to - 1;
            if (to == parent) {
                continue;
            }
            if (!visited[to]) {
                dfs(root, to, at);
                low[at] = Math.min(low[at], low[to]);

                if (ids[at] <= low[to]) {
                    isArt[at] = true;
                    System.out.println("is Art ");
                }
            } else {
                low[at] = Math.min(low[at], ids[to]);
            }
        }
    }

    public static void main(String[] args) {
        A11 a = new A11();
        a.findArtPoints();
    }
}