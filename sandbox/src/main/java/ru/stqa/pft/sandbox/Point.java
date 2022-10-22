//package ru.stqa.pft.sandbox;
//
//
//public class Point {
//  public double x1;
//  public double y1;
//  public int x2;
//  public int y2;
//
//  public Point(double x1, double y1) {
//
//    this.x1 = x1;
//    this.y1 = y1;
//
//  }
//
//  public Point(int x2, int y2) {
//
//    this.x2 = x2;
//    this.y2 = y2;
//  }
//
//
//  public double distance(Point p2) {
//    return Math.sqrt(((p2.y2 - this.y1) * (p2.y2 - this.y1)) + ((p2.x2 - this.x1) * (p2.x2 - this.x1)));
//  }
//
//}

package ru.stqa.pft.sandbox;

public class Point {
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

//  public void setX(double x) {
//    this.x = x;
//  }

  public double getY() {
    return y;
  }

//  public void setY(double y) {
//    this.y = y;
//  }
}


