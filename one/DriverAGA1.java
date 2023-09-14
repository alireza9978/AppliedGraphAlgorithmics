//********************************
// DriverAGA1.java
//********************************

import java.util.Scanner;

public class DriverAGA1
{
    
    public static void main(String[] args)
    {
	int n, m, x, y;
	int test;


	Scanner scan = new Scanner(System.in);
	System.out.print("Number of vertices: ");
	n = scan.nextInt();
	System.out.print("Number of edges: ");
	m = scan.nextInt();
	GraphReach Graph = new GraphReach(n);
	System.out.println("Enter edges (pairs of indices):");
	for(int i=0;i<m;i++) {
	    x = scan.nextInt();
	    y = scan.nextInt();
	    Graph.addEdge(x,y,1);
	}
	
	System.out.println(Graph);

	System.out.println("Pairs of vertices to test: ");
	x = scan.nextInt();
	y = scan.nextInt();
	while(x>=0 && x < n && y>=0 && y < n) { // exit if vertex does not exist
	    test = Graph.reach(x,y);
	    if(test>=0) {
		System.out.println("We can get from " + x + " to " + y + " in " + test + " edges \n");
	    }
	    else {
		System.out.println("We cannot get from " + x + " to " + y + "\n");
	    }
	    x = scan.nextInt();
	    y = scan.nextInt();
	}

	
    }   

}
