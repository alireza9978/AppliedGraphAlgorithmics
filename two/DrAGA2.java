//********************************
// DrAGA2.java
//********************************

import java.util.Scanner;

public class DrAGA2
{
    
    public static void main(String[] args)
    {
	int n, m, x, y, k, s;
	Boolean test;


	Scanner scan = new Scanner(System.in);
	System.out.print("Number of vertices: ");
	n = scan.nextInt();
	System.out.print("Number of edges: ");
	m = scan.nextInt();
	GraphScope Graph = new GraphScope(n);
	System.out.println("Enter edges (pairs of indices):");
	for(int i=0;i<m;i++) {
	    x = scan.nextInt();
	    y = scan.nextInt();
	    Graph.addEdge(x,y,1);
	}
	
	System.out.println(Graph);

	System.out.print("Set scope size: ");
	k = scan.nextInt();

	System.out.print("Enter vertices to find common scope (out-of-range to stop): ");
	x = scan.nextInt();
	while(x>=0 && x<n) {
	    s = Graph.findScope(x, k);
	    System.out.println(Graph);
	    
	    if(s>0) {
		System.out.println(s + " vertices in common scope so far.");
	    }
	    else {
		System.out.println("No remaining common scope.");
	    }
	    x = scan.nextInt();
	}

    }   

}
