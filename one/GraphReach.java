import java.util.Queue;
import java.util.LinkedList;

public class GraphReach extends GraphWtAL {

    public GraphReach(int size) {
        super(size);
    }

    public int reach(int source, int target) {
        double[] distance = new double[this.n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }
        distance[source] = 0;
        queue.add(source);
        while (!queue.isEmpty() && distance[target] == Double.POSITIVE_INFINITY) {
            Integer u = queue.remove();
            for (GNode list = AdjList[u]; list != null; list = list.next) {
                if (distance[list.nbr] == Double.POSITIVE_INFINITY) {
                    distance[list.nbr] = distance[u] + list.weight;
                    queue.add(list.nbr);
                }
            }
        }
        if (distance[target] == Double.POSITIVE_INFINITY) {
            return -1;
        }
        return (int) distance[target];
    }

}