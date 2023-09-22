import java.util.LinkedList;
import java.util.Queue;

public class GraphScope extends GraphWtAL {

    public int[] scope;
    private double[] distance;

    public GraphScope(int size) {
        super(size);
        this.scope = new int[size];
        this.distance = new double[size];
        for (int i = 0; i < size; i++) {
            this.scope[i] = 1;
        }
    }

    public void markScope(int source, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }
        distance[source] = 0;
        queue.add(source);
        Integer u = source;
        do {
            for (GNode list = AdjList[u]; list != null; list = list.next) {
                if (distance[list.nbr] == Double.POSITIVE_INFINITY) {
                    distance[list.nbr] = distance[u] + list.weight;
                    queue.add(list.nbr);
                }
            }
            queue.remove();
            u = queue.peek();
        } while (!queue.isEmpty() && distance[u] <= k - 1);
    }

    public int findScope(int x, int k) {
        markScope(x, k);
        int count = 0;
        for (int i = 0; i < this.n; i++) {
            if (distance[i] == Double.POSITIVE_INFINITY) {
                scope[i] = 0;
            }
            count += scope[i];
        }
        return count;
    }
}
