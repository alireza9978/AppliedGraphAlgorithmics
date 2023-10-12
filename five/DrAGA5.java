//********************************
// DrAGA5.java
// Driver for Assignment 5
//********************************

import java.util.Scanner;

public class DrAGA5
{
    
    public static void main(String[] args)
    {
	int n, m, x;

	Scanner scan = new Scanner(System.in);
	System.out.print("number of voters: ");
	m = scan.nextInt();
	System.out.print("number of candidates: ");
	n = scan.nextInt();
	
	Prefs Rankings = new Prefs(n,m);
	System.out.println("For each voter, list the candidates in order of preference.");

	for(int i=0;i<m;i++) {
	    for(int j=0;j<n;j++) {
		x = scan.nextInt();
		Rankings.addRank(x,i,j);
	    }
	}
	System.out.println(Rankings);

	Rankings.prefList();

    }   

}
