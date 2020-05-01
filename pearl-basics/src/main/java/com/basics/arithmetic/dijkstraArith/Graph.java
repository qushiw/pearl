package com.basics.arithmetic.dijkstraArith;

import java.util.*;

/**
 * @author jrqushiwen
 * @date 2020/5/1 12:12
 */
public class Graph {

    private List<Vertex> vertices;

    private int[][] edges;

    private Queue<Vertex> unVisited;


    public Graph(List<Vertex> vertices, int[][] edges) {
        this.edges = edges;
        this.vertices = vertices;
        initPriorityQueue(vertices);
    }

    public void search() {
        while (!unVisited.isEmpty()) {
            Vertex vertex = unVisited.element();
            vertex.setPeek(true);
            List<Vertex> neighborList = getNeighbor(vertex);
            updateDistance(vertex, neighborList);
            pop();
        }
        System.out.println("search over");
    }

    private void updateDistance(Vertex vertex, List<Vertex> vertexList) {
        for (Vertex tempVertex : vertexList) {
            updateDistance(vertex, tempVertex);
        }
    }


    private void updateDistance(Vertex vertex, Vertex neighborVertext) {
        int rowPosition = vertices.indexOf(vertex);
        int colPosition = vertices.indexOf(neighborVertext);
        int distance = edges[rowPosition][colPosition] + vertex.getPath();
        if (distance < neighborVertext.getPath()) {
            neighborVertext.setPath(distance);
        }
    }

    private List<Vertex> getNeighbor(Vertex vertex) {
        List<Vertex> neighBorvertices = new LinkedList<>();
        int position = vertices.indexOf(vertex);
        for (int i=0; i<vertices.size(); i++) {
            if (i == position) {
                continue;
            }
            int distance = edges[position][i];
            if (Integer.MAX_VALUE == distance) {
                continue;
            }
            Vertex tempVertex = vertices.get(i);
            if (tempVertex.isPeek()) {
                continue;
            }
            neighBorvertices.add(tempVertex);
        }
        return neighBorvertices;
    }

    private void pop() {
        Vertex vertex = unVisited.poll();
        System.out.println("name:" + vertex.getName() + ",path:" + vertex.getPath());
    }


    public void printGraph() {
        int num = vertices.size();
        for (int row=0; row<num; row++) {
            for (int col=0; col<num; col++) {
                if (Integer.MAX_VALUE == edges[row][col]) {
                    System.out.print("*");
                    System.out.print(" ");
                } else {
                    System.out.print(edges[row][col]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private void initPriorityQueue(List<Vertex> vertices) {
        unVisited = new PriorityQueue<>();
        for (Vertex vertex : vertices) {
            unVisited.add(vertex);
        }
    }


}
