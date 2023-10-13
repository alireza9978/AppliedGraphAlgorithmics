public class Prefs extends Voting {
    public Prefs(int items, int voters) {
        super(items, voters);
    }

    public void prefList() {
        int count;
        int temp_diff;
        /*
            first step to define the graph based on the items relations
            counting higher ranks of each item in relation to other items
            add a link from lower rank to higher one
         */
        Kosoraju kosoraju = new Kosoraju(super.rows);
        for (int i = 0; i < super.rows; i++) {
            for (int j = i + 1; j < super.rows; j++) {
                count = 0;
                for (int k = 0; k < super.cols; k++) {
                    temp_diff = super.Grid[i][k] - super.Grid[j][k];
                    if (temp_diff > 0) {
                        count += 1;
                    } else if (temp_diff < 0) {
                        count -= 1;
                    }
                }
                if (count < 0) {
//                    add edge from i to j
                    kosoraju.addEdge(i, j, 1, 0);
                } else if (count > 0) {
//                    add edge from j to i
                    kosoraju.addEdge(j, i, 1, 0);
                } else {
//                    we have two edges
                    kosoraju.addEdge(j, i, 1, 0);
                    kosoraju.addEdge(i, j, 1, 0);
                }
            }
        }
//        System.out.println(kosoraju);
        /*
            calculating strongly connected components
         */
        Kosoraju.KosorajuOutput scc = kosoraju.SCC();

        /*
            building Graph G' from result of the Kosoraju
         */
        DGraphTopo topo = new DGraphTopo(scc.component_count);
        for (int i = 0; i < super.rows; i++) {
            for (int j = i + 1; j < super.rows; j++) {
                if (scc.components[i] == scc.components[j]) {
//                    if two nodes are in a same components then continue
                    continue;
                }
//                if there is an edge between them there must an edge between their components
                if (kosoraju.testEdge(i, j) && !topo.testEdge(scc.components[i] - 1, scc.components[j] - 1)) {
                    topo.addEdge(scc.components[i] - 1, scc.components[j] - 1, 1, 0);
                }
            }
        }
//        System.out.println(topo);
//        System.out.println("FINAL RESULT:");
        /*
            calculating the topological order of graph G'
         */
        int[] topological_order = topo.topoSort();

        /*
            printing result
            for each node in G'
                printing item in that scc
         */
        for (int i = 0; i < topological_order.length; i++) {
            for (int j = 0; j < scc.components.length; j++) {
                if (topological_order[i] == scc.components[j] - 1) {
                    System.out.print(j);
                }
            }
            System.out.println();
        }

    }
}
