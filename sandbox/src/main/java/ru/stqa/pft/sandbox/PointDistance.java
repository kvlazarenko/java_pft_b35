package ru.stqa.pft.sandbox;

public class PointDistance {

  public static void main(String[] args) {

    Point p1 = new Point(77, 5);
    Point p2 = new Point(4, 6);


    System.out.println("Координаты точки Р1 (x,y) - " + p1.x + " , " + p1.y);
    System.out.println("Координаты точки Р2 (x,y) - " + p2.x + " , " + p2.y);
    System.out.println("Расстояния между точками на плоскости = " + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
      return Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));

  }
}

