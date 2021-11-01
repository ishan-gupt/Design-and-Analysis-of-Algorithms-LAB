import javafx.util.Pair;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraAdjacencyList {
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge);
            edge = new Edge(destination, source, weight);
            adjacencylist[destination].addFirst(edge); // for undirected graph
        }

        public void dijkstra_GetMinDistances(int sourceVertex) {
            boolean[] SPT = new boolean[vertices];
            int[] distance = new int[vertices];
            for (int i = 0; i < vertices; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices,
                    new Comparator<Pair<Integer, Integer>>() {
                        @Override
                        public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                            // sort using distance values
                            int key1 = p1.getKey();
                            int key2 = p2.getKey();
                            return key1 - key2;
                        }
                    });
            distance[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(distance[0], 0);
            pq.offer(p0);
            while (!pq.isEmpty()) {
                Pair<Integer, Integer> extractedPair = pq.poll();
                int extractedVertex = extractedPair.getValue();
                if (SPT[extractedVertex] == false) {
                    SPT[extractedVertex] = true;
                    LinkedList<Edge> list = adjacencylist[extractedVertex];
                    for (int i = 0; i < list.size(); i++) {
                        Edge edge = list.get(i);
                        int destination = edge.destination;
                        // only if edge destination is not present in mst
                        if (SPT[destination] == false) {
                            int newKey = distance[extractedVertex] + edge.weight;
                            int currentKey = distance[destination];
                            if (currentKey > newKey) {
                                Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                                pq.offer(p);
                                distance[destination] = newKey;
                            }
                        }
                    }
                }
            }
            printDijkstra(distance, sourceVertex);
        }

        public void printDijkstra(int[] distance, int sourceVertex) {
            System.out.println("Dijkstra Algorithm: (Adjacency List + Priority Queue)");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " + +i + " distance: " + distance[i]);
            }
        }
    }

    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        int v = sc.nextInt();
        Graph graph = new Graph(v);
        System.out.println("Enter the number of edge");
        int e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            System.out.println("Enter Source for " + (i + 1));
            int s = sc.nextInt();
            System.out.println("Enter Destination for " + (i + 1));
            int d = sc.nextInt();
            System.out.println("Enter Weight for " + (i + 1));
            int w = sc.nextInt();
            graph.addEdge(s, d, w);
        }
        System.out.println("Enter the source vertex");
        int sv = sc.nextInt();
        graph.dijkstra_GetMinDistances(sv);
        System.out.println("IshanGupta-19BCE7467");
    }
}