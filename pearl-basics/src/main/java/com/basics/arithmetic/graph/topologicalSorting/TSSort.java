package com.basics.arithmetic.graph.topologicalSorting;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author jrqushiwen
 * @date 2020/5/2 17:14
 */
@Data
public class TSSort {

    private GraphObject graphObject;
    private Vertex headVertext;

    public TSSort(GraphObject graphObject) {
        this.graphObject = graphObject;
        this.headVertext = graphObject.getHeadVertex();
    }

    public void sort() {
        List<Vertex> resultVertext = new LinkedList<>();
        Stack<Vertex> stack = new Stack();
        stack.push(headVertext);

        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            resultVertext.add(vertex);
            List<Vertex> vertexList = vertex.getNextVertex();
            if (CollectionUtils.isEmpty(vertexList)) {
                continue;
            }

            Iterator<Vertex> vertexIterator = vertexList.iterator();
            while (vertexIterator.hasNext()) {
                Vertex nextVertext = vertexIterator.next();
                nextVertext.setInDegree(nextVertext.getInDegree() - 1);
                if (nextVertext.getInDegree() == 0) {
                    stack.push(nextVertext);
                }
            }
        }

        resultVertext.stream().forEach((vertex -> {
            System.out.print(vertex.getValue());
        }));
    }
}
