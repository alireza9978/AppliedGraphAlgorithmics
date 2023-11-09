import java.util.ArrayList;

public class Projects {

    private int[] profits;
    private ArrayList<Integer>[] prereq;
    private int n;
    private int maxCapacity = 1;

    public Projects(int n) {
        this.n = n;
        this.profits = new int[n];
        this.prereq = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            this.prereq[i] = new ArrayList<>();
        }
    }

    public void addProfit(int index, int profit) {
        profits[index] = profit;
        if (profit > 0) {
            maxCapacity += profit;
        }
    }

    public void addPrereq(int u, int v) {
        this.prereq[u].add(v);
    }

    public int[] projSelect() {
//      building the main graph
        DGraphWtAL graph = new DGraphWtAL(n + 2);
//      adding edges
        for (int u = 0; u < n; u++) {
            for (int index = 0; index < prereq[u].size(); index++) {
                int v = prereq[u].get(index);
                graph.addEdge(u, v, maxCapacity, 0);
            }
        }
//      adding edges related to s and t
        for (int v = 0; v < n; v++) {
            int tempProfit = profits[v];
            if (tempProfit < 0) {
                graph.addEdge(n, v, -tempProfit, 0);
            } else {
                graph.addEdge(v, n + 1, tempProfit, 0);
            }
        }
        GraphFlow network = new GraphFlow(graph, n, n + 1);
        network.FordFulk();
        int[] reachable = new int[n];
        System.arraycopy(network.tSideCut(), 0, reachable, 0, n);
        return reachable;
    }
}
