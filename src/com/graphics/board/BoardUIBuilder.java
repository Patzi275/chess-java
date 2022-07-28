package com.graphics.board;

public abstract class BoardUIBuilder {
    protected int offsetLTX = 0;
    protected int offsetLTY = 0;
    protected int offsetRTX = 0;
    protected int offsetRTY = 0;
    protected int size = 0;

    public BoardUIBuilder setOffsetLT(int offsetLTX, int offsetLTY) {
        this.offsetLTX = offsetLTX;
        this.offsetLTY = offsetLTY;
        return this;
    }

    public BoardUIBuilder setOffsetRT(int offsetRTX, int offsetRTY) {
        this.offsetRTX = offsetRTX;
        this.offsetRTY = offsetRTY;
        return this;
    }

    public BoardUIBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public abstract BoardUI createInstance();
}