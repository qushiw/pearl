package com.basics.arithmetic.dijkstraArith.joinMatrix;

import lombok.Data;

/**
 * @author jrqushiwen
 * @date 2020/5/1 12:10
 */
@Data
public class Vertex implements Comparable<Vertex> {

    private String name;

    private int path;

    private boolean isPeek = false;

    public Vertex(String name) {
        this.name = name;
        this.path = Integer.MAX_VALUE;
    }

    public Vertex(String name, int path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public int compareTo(Vertex o) {
        return o.path > path ? -1 : 1;
    }
}
