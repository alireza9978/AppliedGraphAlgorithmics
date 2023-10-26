public class FlowGraph {

    public DGraphWtAL network;
    public DGraphReach residual;

    public int source;
    public int target;


    public FlowGraph(DGraphWtAL network, int source, int target) {
        this.network = network;
        this.source = source;
        this.target = target;
//      creating residual graph based on initial graph and flow
        residual = new DGraphReach(network.n);
        for (int i = 0; i < network.n; i++) {
            for (DGraphWtAL.GNode list = network.OutAL[i]; list != null; list = list.next) {
                int v = list.nbr;
//              adding two edge based on the capacity and flow
                residual.addEdge(i, v, list.weight - list.mark, 0);
                residual.addEdge(v, i, list.mark, 0);
            }
        }

    }
}
