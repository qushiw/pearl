package com.basics.arithmetic.graph.breadthFirstSearch;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author jrqushiwen
 * @date 2020/5/4 17:56
 */
public class BreadthFirstSearch {

    private Graph graph;

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
    }


    public void breadthFirstSearch() {
        Queue<Graph.Vertex> queue = new LinkedBlockingQueue();
        List<Graph.Vertex> resultList = new ArrayList<>(graph.getVertexMap().size());
        List findVertex = new ArrayList(graph.getVertexMap().size());
        queue.add(graph.getHeadVertex());
        while (!queue.isEmpty()) {
            Graph.Vertex vertex = queue.poll();
            resultList.add(vertex);
            List<Graph.Vertex> vertexList = graph.getVertexMap().get(vertex.getName());
            if (CollectionUtils.isEmpty(vertexList)) {
                continue;
            }
            for (int i=0; i<vertexList.size(); i++) {
                Graph.Vertex nextVertex = vertexList.get(i);
                if (findVertex.contains(nextVertex.getName())) {
                    continue;
                } else {
                    nextVertex.setPathNum(vertex.getPathNum() + 1);
                    nextVertex.getShortestPath().append(vertex.getShortestPath()).append("->").append(nextVertex.getName());
                    findVertex.add(nextVertex.getName());
                    queue.add(nextVertex);
                }
            }
        }

        for (int i=0; i<resultList.size(); i++) {
            System.out.println(resultList.get(i).getName() + ":" + resultList.get(i).getShortestPath().toString());
        }


    }


    public void printGrath() {
        Map<String, List<Graph.Vertex>> vertexMap = graph.getVertexMap();
        Iterator<Map.Entry<String,List<Graph.Vertex>>> iterator =  vertexMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Graph.Vertex>> entry = iterator.next();
            List<Graph.Vertex> vertexList = entry.getValue();
            System.out.print(entry.getKey() + "->");
            for (Graph.Vertex vertex : vertexList) {
                System.out.print(vertex.getName() + ",");
            }
            System.out.println();
        }
    }

    public void printShortest() {
        Map<String, List<Graph.Vertex>> vertexMap = graph.getVertexMap();

    }
}
