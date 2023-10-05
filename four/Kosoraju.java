import java.util.Stack;

public class Kosoraju extends DGraphWtAL {
    public Kosoraju(int size) {
        super(size);
    }

    public int SCC() {
        reset(-1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (mark[i] == -1){
                mark[i] = 1;
                dfs(i, stack);
                stack.add(i);
            }
        }
        int[] component = new int[n];
        int component_number = 0;
        for (int i = 0; i < n; i++) {
            component[i] = -1;
        }
        while (!stack.isEmpty()){
            int u = stack.pop();
            if (component[u] == -1){
                component_number += 1;
                component[u] = component_number;
                dfsc(u, component_number, component);
            }
        }
        return component_number;
    }

    private void dfsc(int u, int component_number, int[] component){
        for (GNode list = InAL[u]; list != null; list = list.next) {
            int v = list.nbr;
            if (component[v] == -1) {
                component[v] = component_number;
                dfsc(v, component_number, component);
            }
        }
    }

    private void dfs(int u, Stack<Integer> stack){
        for (GNode list = OutAL[u]; list != null; list = list.next) {
            int v = list.nbr;
            if (mark[v] == -1) {
                mark[v] = 1;
                dfs(v, stack);
                stack.add(v);
            }
        }
    }

}
