package ru.nsu.chepenkov.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AdjacencyMatrix<T> implements Graph<T>{
    private final List<List<Integer>> matrix;
    private ArrayList<Vertex<T>> vertices;
    private ArrayList<Vertex<T>> visited;
    private ArrayList<Vertex<T>> topoSortArray;
    private ArrayList<Edge<T>> edges;
    public AdjacencyMatrix() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        topoSortArray = new ArrayList<>();
        visited = new ArrayList<>();
        this.matrix = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        int curSize = vertices.size();
        ArrayList<Integer> newRow= new ArrayList<>(Collections.nCopies(curSize, -1));
        for (int i = 0; i < curSize - 1; i++) {
            matrix.get(i).add(-1);
        }
        matrix.add(newRow);
    }

    @Override
    public void delVertex(Vertex<T> vertex) throws NoSuchElementException {
        int removeIndex = -1;
        int verticesSize = vertices.size();
        for (int i = 0; i < verticesSize; i++) {
            if (vertices.get(i).equals(vertex)) {
                removeIndex = i;
            }
        }

        if (removeIndex == -1) {
            throw new NoSuchElementException("No such vertex");
        }

        for (int i = 0; i < verticesSize; i++) {
            matrix.get(i).remove(removeIndex);
        }
        vertices.remove(removeIndex);
        matrix.remove(removeIndex);
    }

    @Override
    public void addEdge(Edge<T> edge) throws NoSuchElementException {
        edges.add(edge);
        int from = -1;
        int to = -1;
        int vertSize = vertices.size();
        boolean flag = false;
        for (int i = 0; i < vertSize; i++) {
            if (vertices.get(i).equals(edge.getFrom())) {
                from = i;
            }

            if (vertices.get(i).equals(edge.getTo())) {
                to = i;
            }

            if (from != -1 && to != -1) {
                flag = true;
            }
        }
        if (flag) {
            matrix.get(from).set(to, edge.getWeight());
        } else {
            throw new NoSuchElementException("No suitable vertices");
        }
    }

    @Override
    public void delEdge(Edge<T> edge) throws NoSuchElementException{
        int from = -1;
        int to = -1;
        int vertSize = vertices.size();
        boolean flag = false;
        for (int i = 0; i < vertSize; i++) {
            if (vertices.get(i).equals(edge.getFrom())) {
                from = i;
            }

            if (vertices.get(i).equals(edge.getTo())) {
                to = i;
            }

            if (from != -1 && to != -1) {
                flag = true;
            }
        }
        if (flag) {
            matrix.get(from).set(to, -1);
            edges.remove(edge);
        }
        else {
            throw new NoSuchElementException("No suitable vertices");
        }
    }

    @Override
    public List<Vertex<T>> getNeigbours(Vertex<T> vertex) throws NoSuchElementException{
        int neighbourIndex = -1;
        int verticesSize = vertices.size();
        ArrayList<Vertex<T>> result = new ArrayList<>();

        for (int i = 0; i < verticesSize; i++) {
            if (vertices.get(i).equals(vertex)) {
                neighbourIndex = i;
            }
        }

        if (neighbourIndex == -1) {
            throw new NoSuchElementException("No such vertex");
        }

        for (int i = 0; i < verticesSize; i++) {
            if (matrix.get(i).get(neighbourIndex) != -1) {
                result.add(vertices.get(i));
            }
        }
        
        return result;
    }

    @Override
    public void readFile(String path) {
        int vertAmount;
        int edgeAmount;
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            vertAmount = scanner.nextInt();
            edgeAmount = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < vertAmount; i++) {
                try {
                    String prikol = scanner.nextLine();
                    addVertex(new Vertex(prikol));
                } catch (NoSuchElementException exception) {
                    System.out.println("Error occured while adding vertex");
                }
            }
            for (int i = 0; i < edgeAmount; i++) {
                String[] verts = scanner.nextLine().split(" ");
                try {
                    addEdge(new Edge(new Vertex(verts[0]),
                            new Vertex(verts[1]), Integer.parseInt(verts[2])));
                } catch (NoSuchElementException exception) {
                    System.out.println("Error occured while adding edge");
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Failed to open file");
        }
    }

    public void dfs (Vertex<T> v) {
        visited.add(v);
        List<Vertex<T>> currentNeighbours = getNeigbours(v);
        int curSize = currentNeighbours.size();
        for (int i = 0; i < curSize; i++) {
            Vertex<T> curVert = currentNeighbours.get(i);
            if(!visited.contains(curVert)) {
                dfs(curVert);
            }
        }
        topoSortArray.add(v);
    }

    public List<Vertex<T>> topoSort() {
        int curSize = vertices.size();
        for (int i = 0; i < curSize; i++) {
            if (!visited.contains(vertices.get(i))) {
                dfs(vertices.get(i));
            }
        }
        return topoSortArray;
    }

    public ArrayList<Vertex<T>> getVertices() {
        return vertices;
    }

    public List<List<Integer>> getMatrix() {
        return matrix;
    }

    public ArrayList<Edge<T>> getEdges() {
        return edges;
    }
}
