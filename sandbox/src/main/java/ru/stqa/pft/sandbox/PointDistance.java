package ru.stqa.pft.sandbox;

public class PointDistance {

  public static void main(String[] args) {

    Point p1 = new Point(5, 5);
    Point p2 = new Point(4, 6);


    System.out.println("Расстояния между точками  = " + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.getY() - p1.getY()) * (p2.getY() - p1.getY()) + (p2.getX() - p1.getX()) * (p2.getX() - p1.getX()));
  }
}

