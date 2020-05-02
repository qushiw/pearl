package com.basics.arithmetic.graph.topologicalSorting;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jrqushiwen
 * @date 2020/5/2 17:33
 */
public class TSClient {

    public static void main(String[] args) {
        Vertex avt = new Vertex("A", 0);
        Vertex bvt = new Vertex("B", 1);
        Vertex cvt = new Vertex("C", 1);
        Vertex dvt = new Vertex("D", 2);
        Vertex evt = new Vertex("E", 2);

        avt.addNextVertext(bvt);
        avt.addNextVertext(dvt);
        bvt.addNextVertext(dvt);
        bvt.addNextVertext(cvt);
        cvt.addNextVertext(evt);
        dvt.addNextVertext(evt);

        List<Vertex> list = Lists.newArrayList(avt,bvt,cvt,dvt,evt);
        GraphObject graphObject = new GraphObject(avt, list);

        TSSort tsSort = new TSSort(graphObject);
        tsSort.sort();
    }

}
