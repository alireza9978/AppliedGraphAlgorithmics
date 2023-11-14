public class GraphFlow {

    public DGraphWtAL network;
    public DGraphReach residual;

    public int source;
    public int target;


    public GraphFlow(DGraphWtAL network, int source, int target) {
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

    private int calculateMinCapacity(int[] tempPath) {
//      finding the b
        int b = residual.getRemainedCapacity(tempPath[0], tempPath[1]);
        for (int i = 1; i < tempPath.length - 1; i++) {
            int newB = residual.getRemainedCapacity(tempPath[i], tempPath[i + 1]);
            if (newB < b) {
                b = newB;
            }
        }
        return b;
    }

    private void augment(int[] tempPath, int minCapacity) {
        for (int i = 0; i < tempPath.length - 1; i++) {
            boolean forward = this.network.testEdge(tempPath[i], tempPath[i + 1]);
            if (forward){
                for (DGraphWtAL.GNode list = this.network.OutAL[tempPath[i]]; list != null; list = list.next) {
                    if (list.nbr == tempPath[i + 1]) {
                        list.mark += minCapacity;
                        for (DGraphWtAL.GNode otherList = this.network.InAL[tempPath[i + 1]]; otherList != null; otherList = otherList.next) {
                            if (otherList.nbr == tempPath[i]) {
                                otherList.mark += minCapacity;
                            }
                        }
                        break;
                    }
                }
            }
            else {
                for (DGraphWtAL.GNode list = this.network.OutAL[tempPath[i + 1]]; list != null; list = list.next) {
                    if (list.nbr == tempPath[i]) {
                        list.mark -= minCapacity;
                        for (DGraphWtAL.GNode otherList = this.network.InAL[tempPath[i]]; otherList != null; otherList = otherList.next) {
                            if (otherList.nbr == tempPath[i + 1]) {
                                otherList.mark -= minCapacity;
                            }
                        }
                        break;
                    }
                }
            }
        }

    }

    private void updateResidual(int[] tempPath, int minCapacity) {
        for (int i = 0; i < tempPath.length - 1; i++) {
            residual.updateRemainedCapacity(tempPath[i], tempPath[i + 1], minCapacity);
        }
    }

    public void FordFulk() {
        int[] path = residual.reach(this.source, this.target);
        while (path != null) {
            int b = calculateMinCapacity(path);
            augment(path, b);
            updateResidual(path, b);
            path = residual.reach(this.source, this.target);
        }
    }

    public int[] tSideCut(){
        return residual.reverseReachableNode(target);
    }

}
