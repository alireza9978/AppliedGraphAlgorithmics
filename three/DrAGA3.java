//****************************************
// DrAGA3.java -- Driver for Assignment 3
//****************************************

import java.util.Scanner;

public class DrAGA3
{
    
    public static void main(String[] args)
    {
	int n, m, x, y;
	Boolean test;
	int order[];


	Scanner scan = new Scanner(System.in);
	System.out.print("Number of vertices: ");
	n = scan.nextInt();
	System.out.print("Number of edges: ");
	m = scan.nextInt();
	DGraphTopo Graph = new DGraphTopo(n);
	System.out.println("Enter edges (pairs of indices):");
	for(int i=0;i<m;i++) {
	    x = scan.nextInt();
	    y = scan.nextInt();
	    Graph.addEdge(x,y,1,0); // weight is 1, used is 0
	}
	
	System.out.println(Graph);

	order = Graph.topoSort();
	for(int i=0;i<n;i++) {
	    System.out.print(order[i] + " ");
	}
	    System.out.print("\n");

    }   

}
