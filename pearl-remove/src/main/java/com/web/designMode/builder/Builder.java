package com.web.designMode.builder;

/**
*
* @author qushiwen
* @create 2018-02-21
* @version 1.0
**/
public class Builder {

    private int part1;

    private String part2;

    public Producter build(){
        Producter producter = new Producter();
        producter.setPart1(part1);
        producter.setPart2(part2);
        return producter;
    }

    public Builder setPart2(String part2) {
        this.part2 = part2;
        return this;
    }

    public Builder setPart1(int part1){
        this.part1 = part1;
        return this;
    }
}
