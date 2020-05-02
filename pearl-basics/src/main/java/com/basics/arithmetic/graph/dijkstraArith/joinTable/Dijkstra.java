package com.basics.arithmetic.graph.dijkstraArith.joinTable;

import lombok.Setter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author jrqushiwen
 * @date 2020/5/2 13:08
 */
public class Dijkstra {

    @Setter
    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }


    public void findDijkstraPath(int s, int t) {
        int v = graph.getV();
        int[] predecessor = new int[v];
        boolean[] inqueue = new boolean[v];

        Graph.Edge.Vertex[] vertices = new Graph.Edge.Vertex[v];

        for (int i=0; i<v; i++) {
            vertices[i] = new Graph.Edge.Vertex(i, Integer.MAX_VALUE);
        }
        LinkedList<Graph.Edge>[] edgeLinkedList = graph.getEdgeLinkedList();
        Queue<Graph.Edge.Vertex> queue = new PriorityQueue(v);
        queue.add(vertices[s]);

        while (!queue.isEmpty()) {
            Graph.Edge.Vertex minVertex = queue.poll();
            if (minVertex.getId() == t) {
                break;
            }

            for (int i=0; i<edgeLinkedList[minVertex.getId()].size(); i++) {
                Graph.Edge edge = edgeLinkedList[minVertex.getId()].get(i);
                Graph.Edge.Vertex nextVertex = vertices[edge.getTid()];
                if (minVertex.getPath() + edge.weight < nextVertex.getPath()) {
                    nextVertex.setPath(minVertex.getPath() + edge.getWeight());
                    predecessor[nextVertex.getId()] = minVertex.getId();
                    if (inqueue[nextVertex.getId()]) {
                        Iterator<Graph.Edge.Vertex> iterator = queue.iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().getId() == nextVertex.getId()) {
                                nextVertex.setPath(minVertex.getPath() + edge.getWeight());
                                break;
                            }
                        }
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.getId()] = true;
                    }
                }
            }
        }

        System.out.print(s);
        print(s, t , predecessor);
    }


    private void print(int s, int t, int[] processor) {
        if (s == t) {
            return;
        }

        print(s, processor[t], processor);

        System.out.print("->" + t);
    }


}
