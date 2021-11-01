package com.ishan.daa;

import java.util.Arrays;
import java.util.Scanner;

class KruskalMST {
    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;
        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }
    };
    class subset
    {
        int parent, rank;
    };
    int V, E; // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges
    KruskalMST(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }
    int find(subset subsets[], int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank
                < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank
                > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    void Kruskal()
    {
        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();
        Arrays.sort(edge);
        subset subsets[] = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        i = 0; // Index used to pick next edge
        while (e < V - 1)
        {
            Edge next_edge = edge[i++];
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        System.out.println("Following are the edges in " + "the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < e; ++i)
        {
            System.out.println(result[i].src + " -- "
                    + result[i].dest
                    + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        int v = sc.nextInt();
        System.out.println("Enter the number of edge");
        int e = sc.nextInt();
        KruskalMST graph = new KruskalMST(v, e);
        for (int i=0;i<e;i++){
            System.out.println("Enter Source for "+(i+1));
            int s=sc.nextInt();
            System.out.println("Enter Destination for "+(i+1));
            int d=sc.nextInt();
            System.out.println("Enter Weight for "+(i+1));
            int w=sc.nextInt();
            graph.edge[i].src = s;
            graph.edge[i].dest = d;
            graph.edge[i].weight = w;
        }
        System.out.println("19BCE7467-Ishan Gupta");
        graph.Kruskal();
    }}

