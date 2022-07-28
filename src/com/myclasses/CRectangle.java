package com.myclasses;

import java.awt.*;

public class CRectangle extends Rectangle {
    public int idx = -1, idy = -1;

    public CRectangle(int x, int y, int width, int height, int idx, int idy) {
        super(x, y, width, height);
        this.idx = idx;
        this.idy = idy;
    }

    public CRectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public CRectangle setIndex(int idx, int idy) {
        this.idx = idx;
        this.idy = idy;
        return this;
    }

    public Rectangle original() {
        return (Rectangle) this;
    }
}
