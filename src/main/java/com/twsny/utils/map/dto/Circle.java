package com.twsny.utils.map.dto;

/**
 * @program: aedms
 * @description:
 * @author: Edison
 * @create: 2021-03-31 11:39
 **/
public class Circle {
    private Point center;
    private double radius;


    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(double x, double y, double radius) {
        Point center = new Point(x, y);
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
