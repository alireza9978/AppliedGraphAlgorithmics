//********************************
// DrAGA7.java
//********************************

import java.util.Scanner;

public class DrAGA7
{
    
    public static void main(String[] args)
    {
	int n, m, x, y, cap, f, s, t;


	Scanner scan = new Scanner(System.in);
	System.out.print("Number of vertices: ");
	n = scan.nextInt();
	System.out.print("Number of edges: ");
	m = scan.nextInt();
	System.out.print("Specify source vertex: ");
	s = scan.nextInt();
	System.out.print("Specify target vertex: ");
	t = scan.nextInt();	
	DGraphWtAL Graph = new DGraphWtAL(n);
	System.out.println("Enter edges (pairs of indices, capacity, and initial flow):");
	for(int i=0;i<m;i++) {
	    x = scan.nextInt();
	    y = scan.nextInt();
	    cap = scan.nextInt();
	    f = scan.nextInt();
	    Graph.addEdge(x,y,cap,f);
	}
	
	GraphFlow fpair = new GraphFlow(Graph, s, t);

	fpair.FordFulk();

	System.out.println(fpair.network);

    }   

}
