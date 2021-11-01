import java.util.LinkedList;
import java.util.Scanner;

public class PrismBinaryHeap {
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

    static class HeapNode {
        int vertex;
        int key;
    }

    static class ResultSet {
        int parent;
        int weight;
    }

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

        public void primMST() {
            boolean[] inHeap = new boolean[vertices];
            ResultSet[] resultSet = new ResultSet[vertices];
            int[] key = new int[vertices];
            HeapNode[] heapNodes = new HeapNode[vertices];
            for (int i = 0; i < vertices; i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].key = Integer.MAX_VALUE;
                resultSet[i] = new ResultSet();
                resultSet[i].parent = (-1);
                inHeap[i] = true;
                key[i] = Integer.MAX_VALUE;
            }
            heapNodes[0].key = 0;
            MinHeap minHeap = new MinHeap(vertices);
            for (int i = 0; i < vertices; i++) {
                minHeap.insert(heapNodes[i]);
            }
            while (!minHeap.isEmpty()) {
                HeapNode extractedNode = minHeap.extractMin();
                int extractedVertex = extractedNode.vertex;
                inHeap[extractedVertex] = false;
                LinkedList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    if (inHeap[edge.destination]) {
                        int destination = edge.destination;
                        int newKey = edge.weight;
                        if (key[destination] > newKey) {
                            decreaseKey(minHeap, newKey, destination);
                            resultSet[destination].parent = extractedVertex;
                            resultSet[destination].weight = newKey;
                            key[destination] = newKey;
                        }
                    }
                }
            }
            printMST(resultSet);
        }

        public void decreaseKey(MinHeap minHeap, int newKey, int vertex) {
            int index = minHeap.indexes[vertex];
            HeapNode node = minHeap.mH[index];
            node.key = newKey;
            minHeap.bubbleUp(index);
        }

        public void printMST(ResultSet[] resultSet) {
            int total_min_weight = 0;
            System.out.println("Minimum Spanning Tree: ");
            for (int i = 1; i < vertices; i++) {
                System.out.println("Edge: " + i + " – " + resultSet[i].parent + " weight: " + resultSet[i].weight);
                total_min_weight += resultSet[i].weight;
            }
            System.out.println("Total minimum key: " + total_min_weight);
        }
    }

    static class MinHeap {
        int capacity;
        int currentSize;
        HeapNode[] mH;
        int[] indexes;

        public MinHeap(int capacity) {
            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode();
            mH[0].key = Integer.MIN_VALUE;
            mH[0].vertex = (-1);
            currentSize = 0;
        }

        public void display() {
            for (int i = 0; i <= currentSize; i++) {
                System.out.println(" " + mH[i].vertex + " key " + mH[i].key);
            }
            System.out.println("________________________");
        }

        public void insert(HeapNode x) {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos) {
            int parentIdx = pos / 2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
                HeapNode currentNode = mH[currentIdx];
                HeapNode parentNode = mH[parentIdx];
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }

        public HeapNode extractMin() {
            HeapNode min = mH[1];
            HeapNode lastNode = mH[currentSize];
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return min;
        }

        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k + 1;
            if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {
                HeapNode smallestNode = mH[smallest];
                HeapNode kNode = mH[k];
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b) {
            HeapNode temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int heapSize() {
            return currentSize;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        int v = sc.nextInt();
        PrismC1.Graph graph = new PrismC1.Graph(v);
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
        graph.primMST();
        /*
         * graph.addEdge(0, 1, 4); graph.addEdge(0, 2, 3); graph.addEdge(1, 2, 1);
         * graph.addEdge(1, 3, 2); graph.addEdge(2, 3, 4); graph.addEdge(3, 4, 2);
         * graph.addEdge(4, 5, 6);
         */
    }
}
