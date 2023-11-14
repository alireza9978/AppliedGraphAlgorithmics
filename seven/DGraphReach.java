import java.util.LinkedList;
import java.util.Queue;

public class DGraphReach extends DGraphWtAL {
    public DGraphReach(int size) {
        super(size);
    }

    public int[] reach(int x, int y) {
//      initializing mark and parent list for search
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            mark[i] = -1;
            parent[i] = -1;
        }
//      starting our search from x
        mark[x] = 0;
        queue.add(x);
        while (!queue.isEmpty() && mark[y] == -1) {
            Integer u = queue.remove();
            for (GNode list = OutAL[u]; list != null; list = list.next) {
//              checking if the edge remaining capacity is not 0
                if (mark[list.nbr] == -1 && list.weight > 0) {
                    mark[list.nbr] = mark[u] + 1;
                    parent[list.nbr] = u;
                    queue.add(list.nbr);
                }
            }
        }

//      checking if we found a path or not
        if (mark[y] == -1) {
            return null;
        } else {
//          retrieving path from parent list
            int[] path = new int[mark[y] + 1];
            int last_node = y;
            path[mark[y]] = y;
            for (int i = mark[y] - 1; i > -1; i--) {
                path[i] = parent[last_node];
                last_node = parent[last_node];
            }
            return path;
        }
    }

    public int getRemainedCapacity(int u, int v) {
        for (GNode list = OutAL[u]; list != null; list = list.next) {
            if (list.nbr == v) {
                return list.weight;
            }
        }
        return -1;
    }

    public void updateRemainedCapacity(int u, int v, int minCapacity) {
        for (GNode list = OutAL[u]; list != null; list = list.next) {
            if (list.nbr == v) {
                list.weight -= minCapacity;
            }
        }
        for (GNode list = OutAL[v]; list != null; list = list.next) {
            if (list.nbr == u) {
                list.weight += minCapacity;
            }
        }
//      update inverse edges for later algorithms
        for (GNode list = InAL[v]; list != null; list = list.next) {
            if (list.nbr == u) {
                list.weight -= minCapacity;
            }
        }
        for (GNode list = InAL[u]; list != null; list = list.next) {
            if (list.nbr == v) {
                list.weight += minCapacity;
            }
        }
    }

    public int[] reachableNode(int source) {
//      initializing mark list for search
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            mark[i] = -1;
        }
//      starting our search from source
        mark[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            Integer u = queue.remove();
            for (GNode list = OutAL[u]; list != null; list = list.next) {
//              checking if the edge remaining capacity is not 0
                if (mark[list.nbr] == -1 && list.weight > 0) {
                    mark[list.nbr] = mark[u] + 1;
                    queue.add(list.nbr);
                }
            }
        }
//      cleaning result based on mark array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (mark[i] >= 0) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    public int[] reverseReachableNode(int source) {
        //      initializing mark list for search
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            mark[i] = -1;
        }
//      starting our search from source
        mark[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            Integer u = queue.remove();
            for (GNode list = InAL[u]; list != null; list = list.next) {
//              checking if the edge remaining capacity is not 0
                if (mark[list.nbr] == -1 && list.weight > 0) {
                    mark[list.nbr] = mark[u] + 1;
                    queue.add(list.nbr);
                }
            }
        }
//      cleaning result based on mark array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (mark[i] >= 0) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }
        return result;
    }
}
