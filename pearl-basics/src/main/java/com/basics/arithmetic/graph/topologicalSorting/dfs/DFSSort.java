package com.basics.arithmetic.graph.topologicalSorting.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jrqushiwen
 * @date 2020/5/4 9:12
 */
public class DFSSort {

    public void sort(Graph graph) {
        List<Integer>[] vertices = graph.getVertices();
        List<Integer> invertVertices[] = new LinkedList[vertices.length];

        for (int i=0; i<graph.getVertexNum(); i++) {
            invertVertices[i] = new LinkedList<>();
        }

        for (int i=0; i<vertices.length; i++) {
            List<Integer> verticesList = vertices[i];
            for (int j=0; j<verticesList.size(); ++j) {
                invertVertices[verticesList.get(j)].add(i);
            }
        }

        boolean visited[] = new boolean[vertices.length];
        for (int i=0; i<vertices.length; ++i) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, invertVertices, visited);
            }
        }
    }

    private void dfs(int vertex, List<Integer> inverseAdj[], boolean[] visited) {
        for (int i=0; i<inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) {
                continue;
            }
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        System.out.print("->" + vertex);
    }
}
