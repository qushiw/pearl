package com.basics.arithmetic.graph.breadthFirstSearch;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jrqushiwen
 * @date 2020/5/4 17:15
 */
public class Graph {

    @Getter
    @Setter
    private Map<String, List<Vertex>> vertexMap;

    @Setter
    @Getter
    private Vertex headVertex;

    public Graph(int n) {
        vertexMap = new HashMap<>(n);
    }

    public void add(String s, String vertexName) {
        add(s, vertexName, Integer.MAX_VALUE);
    }

    public void add(String s, String vertexName, int pathNum) {
        if (vertexMap.get(s) == null) {
            List<Vertex> vertexList = new LinkedList<>();
            vertexList.add(new Vertex(vertexName, s, pathNum));
            vertexMap.put(s, vertexList);
        } else {
            vertexMap.get(s).add(new Vertex(vertexName, s, pathNum));
        }
    }


    @Data
    public static class Vertex {
        private String name;
        private String path;

        private StringBuilder shortestPath = new StringBuilder();
        private int pathNum;


        public Vertex(String name, String path) {
            this.name = name;
            this.path = path;
        }

        public Vertex(String name, String path, int pathNum) {
            this.name = name;
            this.path = path;
            this.pathNum = pathNum;
            if (pathNum == 0) {
                getShortestPath().append(name);
            }
        }
    }


}
