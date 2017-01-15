/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs542projectnew;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateMatrix 
{
    static int V=0;
    static int i=0;
	public static int graph [][];
	public int [][] read_file(String file)
	{
		 
	    try
	    {
	    	Scanner sc=new Scanner(new File(file));
	    
	    	
	    	V=sc.nextLine().split(" ").length;	    	
	    	
	    	graph=new int[V][V];
	    	
	    	Scanner file1=new Scanner(new File(file));
	    	
	    	List<String>line=new ArrayList<String>();  
	    
	   
	    	while(file1.hasNextLine())
	    	{
	    	
	    		String l=file1.nextLine().trim();
	    		line.add(l);
	    		
	    	}
	    	
	    	//int x=0;
	    	
	    	String mat[]=new String[line.size()];
	    	
	    	for(i=0;i<line.size();i++)
	    	{  
	    		    		int j=0;
	    		
	    	   String abc=line.get(i);
	    	   mat=abc.split(" ");
	    	
	    	   while(j<line.size())
	    	   {
	    		   graph[i][j]=Integer.parseInt(mat[j++]);
	    		   
	    	   }
	    	}
	    	for(int j=0;j<graph.length;j++){
	    		for(int k=0;k<graph.length;k++){
	    			System.out.printf(" "+graph[j][k]);
	    		}
	    	System.out.println();
	    	}
	    	file1.close();
	    
	        return graph;
	        
	    }catch(Exception e)
	    {
	    	
	    }
	    return null;
	    }
    
}
