//********************************
// DrAGA4.java
//********************************

import java.util.Scanner;

public class DrAGA4
{
    
    public static void main(String[] args)
    {
	int n, m, x, y;

	int comps;


	Scanner scan = new Scanner(System.in);
	System.out.print("Number of vertices: ");
	n = scan.nextInt();
	System.out.print("Number of edges: ");
	m = scan.nextInt();
	Kosoraju Graph = new Kosoraju(n);
	System.out.println("Enter edges (pairs of indices):");
	for(int i=0;i<m;i++) {
	    x = scan.nextInt();
	    y = scan.nextInt();
	    Graph.addEdge(x,y,1,0);
	}
	

	comps = Graph.SCC();
	System.out.println(comps + " strongly connected components");
	System.out.println(Graph);

    }   

}
