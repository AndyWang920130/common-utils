package com.twsny.utils.map.dto;

/**
 * @program: aedms
 * @description:
 * @author: Edison
 * @create: 2021-03-31 11:39
 **/
public class Point{
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
