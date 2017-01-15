/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs542projectnew;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.Node;

public class MainProgram 
{
    public static CreateMatrix cm;
	static List<Integer> list = new ArrayList<Integer>();
	static List<Integer> pathlist=new ArrayList<Integer>();
	public static int[] abc;
	public static int[] path;
	public static int[]x;
	public static int[][] graph;
	public static int src;
	public static int des;
	public static int dist[];
	public static int V;
	public static int []a;
	public static Boolean flag;
	static int ref1;
	public static int delrouter;
    MainProgram t;
	// A utility function to print the constructed distance array
	void printSolution(int dist[], int V) {
		System.out.println("Vertex   Distance from Source");
		for (int i = 0; i < V; i++)
			System.out.println(i + " \t\t " + dist[i]);
	}

	/**
	 * The Below printinter function calculates and print the path from source to destination
	 * 
	 */
	
	public static void printinter(int x[], int V,int src, int des) {
		System.out.println("" + "Path From Router" + " " + " " + (src+1)+ " " + " " + "To" + " " + " " + "Router" + " " + " " + (des+1));
		//System.out.println("path array "+Arrays.toString(path));
	
	int i;
	int j;
	int cost=0;
	int a[]=new int[V+1];
	for(i=0;i<V;i++)
	{
		a[i]=-1;
	}
	a[0]=des;
	i=1;
	while(des!=src)
	{   
		a[i]=x[des];
		des=x[des];
		
		i++;
		
	}

	for(j=i;j>=0;j--)
	{	if(a[j]!=-1)
	System.out.print(""+"-->"+((a[j])+1));
	
	}
	}
	
/**
 * The below function calculates the shortest distance between source router to all the other router
 * 
 */
	public static int[] dijkstra(int graph[][], int src, int V) 
	{ 
		int x = 0;
		int p = 0;
		int q = 0;
		int l = 0;
		
		dist = new int[V + 1]; 
		
		int visitednode[] = new int[V + 1];
		
		path = new int[V + 3];
		for (int i = 0; i < V; i++)
		{	
			path[i] = -1;
			dist[i] = Integer.MAX_VALUE;
			visitednode[i] = 0;			
		}

		// Distance of source vertex from itself is always 0
		 dist[src] = 0;

		// Find shortest path for all vertices
		for (int count = 0; count < V; count++) 
                {
			int min = Integer.MAX_VALUE, min_index = -1;

			for (int v = 0; v < V; v++)
				if (visitednode[v] == 0 && dist[v] < min) 
                                {
					min = dist[v];
					min_index = v;
				}
			int u = min_index;                //It gives the visited node index
	        if(u>=0)
	        {
			visitednode[u] = 1;               //Set visited node array to 1
	        
			int v = 0;
			while (v < V) 
                        {
					if (visitednode[v] == 0 && graph[u][v] > 0 && dist[u] != Integer.MAX_VALUE
							&& dist[u] + graph[u][v] <= dist[v]) 
                                        {
                                            dist[v] = dist[u] + graph[u][v];
					    path[v]=u;	
	
					}
				
				v++;

			}

		}
		}
		return path;
		

		
	}
	
	/**
	 *The below function calculate and print the Best router among all the routers
	 *
	 */
	public static void broadcast()
	{   int min=Integer.MAX_VALUE;
	    int index=0;
		System.out.println("Best Router");
		for(int i=0;i<graph.length;i++)
		{
			dijkstra(graph,i,graph.length);
			int a=0;
			
			for(int j=0;j<graph.length;j++)
			{
				a=a+dist[j];
			}
			if(a<=min)
			{
				min=a;
				index=i;
			}
			}
		System.out.println("Best router is "+(index+1));
		
	}
	/**
	 * 
	 * The below function calculates and prints the interface table of source router
	 * 
	 */
	public static void interfaceconnection(int x[],int src)                
	{   
	int i;
	int j;
	int a[]=new int[graph.length];
//	
	for(j=0;j<graph.length;j++)
	{
	
	int ref=x[j];
	if(ref==-1)
	{System.out.println("Router"+" "+(j+1)+" "+"Interface"+" "+"None");}
	else
	{
	i=0;
	while(ref!=src)
	{
		ref1=ref;
		ref=x[ref];
		
		i++;
	}
	if(j==src)
	{
		System.out.println("Router"+" "+(j+1)+" "+"Interface"+" "+"None");
	}
	else
		if(i==0)
		{
			System.out.println("Router"+" "+(j+1)+" "+"Interface"+" "+(j+1));
		}
		else
		{ System.out.println("Router"+" "+(j+1)+" "+"Interface"+" "+(ref1+1));}
		}
	}
	}
	
	/**
	 * Below is the main function
	 * 
	 */
	public static void main(String[] args) 
        {
        int check =0;
        delrouter=0;
		MainProgram t = new MainProgram();
		cm = new CreateMatrix();
	    
		int m;
		Scanner in = new Scanner(System.in);
        do
        {
		System.out.println("1. Create a new topology");
		System.out.println("2. Build a connection table");
		System.out.println("3. Shortest Path to destination");
		System.out.println("4. Modify a topology");
		System.out.println("5. Best Router for Broadcast");
		System.out.println("Enter your desired input");

		    
			m = Integer.parseInt(in.nextLine());
			switch (m) {
			case 1: {
                src=Integer.MAX_VALUE;
				Scanner input = new Scanner(System.in);
				System.out.println("Enter file name to create network topology");
				String file = input.nextLine();
                
				flag = new File(file).exists();
				
				if (flag == true) {
					check=1;
					System.out.println("File read");
					graph = cm.read_file(file);
					V = graph.length;
				} else {
					System.out.println("File doesnt exist");
				}
                    System.out.println("-----------------------------");
				break;
			}
			
			case 2: {
			if(check==1)
			{
			System.out.println("Enter source router");
			src=Integer.parseInt(in.nextLine());
			x=dijkstra(graph, src-1, V);
		    interfaceconnection(x,src-1);
		    System.out.println("------------------------");
			}
			else
				{System.out.println("First create a network topology by using input 1");
				System.out.println("----------------------------------------------------");}
			break;
			}
				
			case 3: {
				System.out.println("Shortest path to destination");
				System.out.println("Enter the source router");
				src = Integer.parseInt(in.nextLine());
				
              //  int cost =0;
				System.out.println("Enter Destination Router");
				des = Integer.parseInt(in.nextLine());
				V=graph.length;
				int x[]=dijkstra(graph, (src-1), V);
		
				if(dist[des-1]>0&&dist[des-1]!=Integer.MAX_VALUE)
				{printinter(x, V, (src-1), (des-1));}
				else{
					System.out.println("Path doesnt exist");
				}
				System.out.println("---------------------");
				break;

			}
			case 4:{
				
				int router;
				
				System.out.println("Modify a topology");
				System.out.println("Enter router to be deleted");
                router=Integer.parseInt(in.nextLine());
               
                if(delrouter!=router)
                {
                delrouter=router;
                t.delete(graph,router-1,V);          
                if(src!=Integer.MAX_VALUE)
                {              	
                x=dijkstra(graph, src-1, V);
    		    interfaceconnection(x,src-1);
    		    }
                }
                else
                {
                System.out.println("Router"+"  "+router+"  "+"is already deleted");
                System.out.println("----------------------------------");
                }
				break;
			}
			case 5:
			{   
				System.out.println("Best Router for in network is");
				
				broadcast();
				System.out.println("---------------------------");
				break;
			}
			default:
				System.out.println("wrong command");
				continue;
			}
		} while (m < 6);

	}
	/**
	 * The below function deletes the requested router
	 * 
	 */

	private void delete(int[][] graph, int router, int nodelength) {
		// TODO Auto-generated method stub
        int i,j;
        
		for(i=0;i<nodelength;i++)
		{
		if(i==router)	
		for(j=0;j<nodelength;j++)
		{
	  graph[i][j]=-1;
		}
		}
		for(i=0;i<nodelength;i++)
		{
				for(j=0;j<nodelength;j++)
				{   if(j==router)
				{
					graph[i][j]=-1;
					}
				}
		}
		for(i=0;i<V;i++)
		{
			for(j=0;j<V;j++)
			{
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}

    

