public class Prefs extends Voting {
    public Prefs(int items, int voters) {
        super(items, voters);
    }

    public void prefList() {
        int count;
        int temp_diff;
        Kosoraju kosoraju = new Kosoraju(super.rows);
        for (int i = 0; i < super.rows; i++) {
            count = 0;
            for (int j = i + 1; j < super.rows; j++) {
                for (int k = 0; k < super.cols; k++) {
                    temp_diff = super.Grid[i][k] - super.Grid[j][k];
                    if (temp_diff > 0) {
                        count += 1;
                    } else if (temp_diff < 0) {
                        count -= 1;
                    } else {
                        System.out.println("This is a bad sign!!");
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
        System.out.println(kosoraju);
        Kosoraju.KosorajuOutput scc = kosoraju.SCC();

//        I'm building Graph G' from result of the Kosoraju
        DGraphTopo topo = new DGraphTopo(scc.component_count);
        for (int i = 0; i < super.rows; i++) {
            for (int j = i + 1; j < super.rows; j++) {
                if (scc.components[i] == scc.components[j]) {
//                    if two nodes are in a same components then continue
                    continue;
                }
//                if there is an edge between them there must an edge between their components
                if (kosoraju.testEdge(i, j)) {
                    topo.addEdge(scc.components[i], scc.components[j], 1, 0);
                }
            }
        }
//        topo.topoSort()

    }
}
