import java.util.Scanner;

public class DijkstraAdjacencyMatrix {
    static class Graph {
        int vertices;
        int matrix[][];

        public Graph(int vertex) {
            this.vertices = vertex;
            matrix = new int[vertex][vertex];
        }

        public void addEdge(int source, int destination, int weight) {
            matrix[source][destination] = weight;
            matrix[destination][source] = weight;
        }

        int getMinimumVertex(boolean[] mst, int[] key) {
            int minKey = Integer.MAX_VALUE;
            int vertex = (-1);
            for (int i = 0; i < vertices; i++) {
                if (!mst[i] && minKey > key[i]) {
                    minKey = key[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        public void dijkstra_GetMinDistances(int sourceVertex) {
            boolean[] spt = new boolean[vertices];
            int[] distance = new int[vertices];
            int INFINITY = Integer.MAX_VALUE;
            for (int i = 0; i < vertices; i++) {
                distance[i] = INFINITY;
            }
            distance[sourceVertex] = 0;
            for (int i = 0; i < vertices; i++) {
                int vertex_U = getMinimumVertex(spt, distance);
                spt[vertex_U] = true;
                for (int vertex_V = 0; vertex_V < vertices; vertex_V++) {
                    if (matrix[vertex_U][vertex_V] > 0) {
                        if (!spt[vertex_V] && matrix[vertex_U][vertex_V] != INFINITY) {
                            int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
                            if (newKey < distance[vertex_V])
                                distance[vertex_V] = newKey;
                        }
                    }
                }
            }
            printDijkstra(sourceVertex, distance);
        }

        public void printDijkstra(int sourceVertex, int[] key) {
            System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i + " distance: " + key[i]);
            }
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
        System.out.println("Enter the source vertex:");
        int sv = sc.nextInt();
        graph.dijkstra_GetMinDistances(sv);
        System.out.println("IshanGupta-19BCE7467");
    }
}
