import java.util.LinkedList;
import java.util.Queue;

public class DGraphTopo extends DGraphWtAL {
    public DGraphTopo(int size) {
        super(size);
    }

    public int[] topoSort() {
        reset(0);
        int[] topologicalOrder = new int[super.n];
        Queue<Integer> queue = new LinkedList<>();
        int v, topologyCounter;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (GNode list = InAL[i]; list != null; list = list.next) {
                count += 1;
            }
            if (count == 0){
                queue.add(i);
            }
            mark[i] = count;
        }
        topologyCounter = 0;
        while (!queue.isEmpty()){
            v = queue.remove();
            topologicalOrder[v] = topologyCounter;
            topologyCounter += 1;
            for (GNode list = OutAL[v]; list != null; list = list.next) {
                mark[list.nbr] = mark[list.nbr] - 1;
                if (mark[list.nbr] == 0){
                    queue.add(list.nbr);
                }
            }
        }
        return topologicalOrder;
    }

}
