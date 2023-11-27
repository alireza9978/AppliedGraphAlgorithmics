import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Between extends GraphWtAL {

    public float[] btwn;

    public Between(int size) {
        super(size);
        this.btwn = new float[size];
    }

    public void betFind() {
        int[] d;
        int[] p;
        float[] b;

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
//          adding all betweenness scores
            for (int j = 0; j < this.n; j++) {
                this.btwn[j] += b[j];
            }
        }

        int nodes = (2 * n) - 1;
        int totalPath = (n * n) - (3 * n) + 2;
        for (int j = 0; j < this.n; j++) {
            this.btwn[j] -= nodes;
            this.btwn[j] /= totalPath;
        }

    }

    private float bpost(int u, float[] b, int[] p) {
        if (b[u] == 0) {
            b[u] = 1;
            for (GNode list = AdjList[u]; list != null; list = list.next) {
                int v = list.nbr;
                if (list.mark == 1) {
                    b[u] = b[u] + bpost(v, b, p);
                }
            }
        }
        return b[u] / p[u];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(i).append(" (").append(mark[i]).append(") : ");
            for (GNode list = AdjList[i]; list != null; list = list.next) {
                result.append("\t").append(list.nbr).append(" (").append(list.weight).append(") ");
            }
            result.append("\n");
        }
        result.append("Between{" + "btwn=").append(Arrays.toString(btwn)).append('}');
        return result.toString();
    }
}
