import java.util.LinkedList;
import java.util.Queue;

public class EBetween extends GraphWtAL {

    public int m;
    public int remainedEdgesCount;
    private int edgeIndex;
    public float[] btwn;

    public EBetween(int n, int m) {
        super(n);
        this.m = m;
        this.remainedEdgesCount = m;
        this.edgeIndex = 0;
        this.btwn = new float[m];
    }

    public void addEdge(int x, int y) {
        super.addEdge(x, y, this.edgeIndex);
        this.edgeIndex++;
    }

    public void betFind() {
//      defining variables that are needed for next part
        int[] d;
        int[] p;
        float[] b;
        this.btwn = new float[m];

        for (int i = 0; i < this.n; i++) {
//          creating variables and resetting edges for each starting node
            d = new int[this.n];
            p = new int[this.n];
            edgeReset(-1);

//          Using BFS to find all shortest path
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < this.n; j++) {
                d[j] = Integer.MAX_VALUE;
                p[j] = 1;
            }
            d[i] = 0;
            queue.add(i);
            while (!queue.isEmpty()) {
                int u = queue.remove();
                for (GNode list = AdjList[u]; list != null; list = list.next) {
                    int v = list.nbr;
                    if (d[v] == Integer.MAX_VALUE) {
                        d[v] = d[u] + 1;
                        list.mark = 1;
                        queue.add(v);
                    } else if (d[v] == d[u] + 1) {
                        p[v] = p[v] + 1;
                        list.mark = 1;
                    }
                }
            }
//          Counting path
            b = new float[this.n];
            bpost(i, b, p);
        }

//      final part of calculation to make numbers comparable
        int totalPath = (n * n) - n;
        for (int j = 0; j < this.remainedEdgesCount; j++) {
            this.btwn[j] /= totalPath;
        }

    }

    private float bpost(int u, float[] b, int[] p) {
        if (b[u] == 0) {
            b[u] = 1;
            for (GNode list = AdjList[u]; list != null; list = list.next) {
                int v = list.nbr;
                if (list.mark == 1) {
                    float bp = bpost(v, b, p);
                    b[u] = b[u] + bp;
                    btwn[list.weight] += bp;
                }
            }
        }
        return b[u] / p[u];
    }

    public void cluster(float limit) {
        float maxEdgeBet;
        int maxEdgeIndex = -1;
        this.remainedEdgesCount = m;
        do {
            betFind();
            maxEdgeBet = Float.MIN_VALUE;
            for (int i = 0; i < btwn.length; i++) {
                if (maxEdgeBet < btwn[i]){
                    maxEdgeBet = btwn[i];
                    maxEdgeIndex = i;
                }
            }

            // I want to find the edge with max and remove it from the graph
            for (int i = 0; i < n; i++) {
                for (GNode list = AdjList[i]; list != null; list = list.next) {
                    if (list.weight == maxEdgeIndex) {
                        // the code is not going to run multiple time, so I don't need to track the removed edges
                        this.delEdge(i, list.nbr);
                    }
                }
            }



        } while (limit < maxEdgeBet);

//      Using BFS to find all components
        int component = 1;
        Queue<Integer> queue = new LinkedList<>();
        reset(-1);
        for (int i = 0; i < n; i++) {
            if (mark[i] == -1){
                queue.add(i);
                mark[i] = component;
                while (!queue.isEmpty()) {
                    int u = queue.remove();
                    for (GNode list = AdjList[u]; list != null; list = list.next) {
                        if (mark[list.nbr] == -1){
                            mark[list.nbr] = component;
                            queue.add(list.nbr);
                        }
                    }
                }
                component += 1;
            }
        }


    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            result.append(String.format("%.2f", btwn[i])).append("\t");
        }
        return super.toString() + result;

    }
}
