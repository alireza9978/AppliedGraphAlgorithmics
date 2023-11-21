import java.util.*;

public class Karger extends GraphWtAL {

    class Edge {
        int u;
        int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    private Edge[] edges;
    private int last_index = 0;
    private final int m;

    public Karger(int n, int m) {
        super(n);
        this.m = m;
        edges = new Edge[m];
    }

    public void addEdge(int u, int v) {
        super.addEdge(u, v, 1);
        edges[last_index] = new Edge(u, v);
        last_index += 1;
    }

    public int minCut(int k, int times) {
//      defining all variables that are needed in iterations
        Random random = new Random(10);
        int iteration = 0;
        int i;
        Edge tempEdge;
        int minCutEdgesCount = m;

//      main loop that run karger multiple times
        while (iteration < times) {
//          defining union-find
            UnionFind unionFind = new UnionFind(n);
//          defining remained edges and node
            int remainNode = n;
            int remainEdges = m;
//          merging graph until we have less than k node
            while (remainNode > k) {
//              selecting a random edge
                i = random.nextInt(remainNode);
                tempEdge = edges[i];
//              checking if both side of the edge are merged or not
                if (unionFind.find(tempEdge.u) != unionFind.find(tempEdge.v)) {
                    unionFind.union(tempEdge.u, tempEdge.v);
                    remainNode--;
                }
//              moving the picked edge to the end of list for easier selection in next iteration
                if (i != remainEdges - 1) {
//                  swapping last edge with picked edge
                    edges[i] = edges[remainEdges - 1];
                    edges[remainEdges - 1] = tempEdge;
                }
                remainEdges--;
            }
//          clean up the loop edges
            i = 0;
            while (i < remainEdges) {
                tempEdge = edges[i];
                if (unionFind.find(tempEdge.u) == unionFind.find(tempEdge.v)) {
                    edges[i] = edges[remainEdges - 1];
                    edges[remainEdges - 1] = tempEdge;
                    remainEdges--;
                } else {
                    i++;
                }
            }
//          finding the minimum edge count over all iterations
            if (minCutEdgesCount > remainEdges) {
                minCutEdgesCount = remainEdges;
//              updating mark array based on the union-find so array shows index of piece
//              the union-find values might differ and do not start from zero so I have to find a way to kinda map the
//              the .find() to 0 to k numbers.
                TreeSet<Integer> uniquePiece = new TreeSet<>();
                for (int j = 0; j < n; j++) {
                    mark[j] = unionFind.find(j);
                    uniquePiece.add(mark[j]);
                }
                int index = 0;
                while (!uniquePiece.isEmpty()) {
                    Integer temp = uniquePiece.pollFirst();
                    if (temp == null)
                        break;
                    for (int j = 0; j < n; j++) {
                        if (mark[j] == temp) {
                            mark[j] = index;
                        }
                    }
                    index++;
                }
            }
            iteration += 1;
        }
//      printing nodes in each piece in a single line
        int index = 0;
        while (index < k) {
            for (int j = 0; j < n; j++) {
                if (mark[j] == index) {
                    System.out.print(j);
                    System.out.print(" ");
                }
            }
            System.out.println();
            index++;
        }
        return minCutEdgesCount;
    }

}
