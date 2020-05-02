package com.basics.arithmetic.graph.dijkstraArith.joinTable;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;


/**
 * @author jrqushiwen
 * @date 2020/5/2 12:54
 */
public class Graph {

    @Setter
    @Getter
    private LinkedList<Edge> edgeLinkedList[];

    @Setter
    @Getter
    private int v;

    public Graph(int v) {
        this.v = v;
        edgeLinkedList = new LinkedList[v];
        for (int i=0; i<v; i++) {
            this.edgeLinkedList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) {
        this.edgeLinkedList[s].add(new Edge(s, t, w));
    }

    public static class Edge {
        @Getter
        @Setter
        public int sid;
        @Getter
        @Setter
        public int tid;
        @Getter
        @Setter
        public int weight;

        public Edge(int sid, int tid, int weight) {
            this.sid = sid;
            this.tid = tid;
            this.weight = weight;
        }


        public static class Vertex implements Comparable<Vertex>{
            @Setter
            @Getter
            private int id;
            @Getter
            @Setter
            private int path;

            public Vertex(int id, int path){
                this.id = id;
                this.path = path;
            }

            @Override
            public int compareTo(Vertex vertex) {
                return vertex.getPath() > this.path ? -1 : 1;
            }
        }
    }


}
