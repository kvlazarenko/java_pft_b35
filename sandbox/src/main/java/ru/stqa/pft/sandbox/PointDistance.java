
package ru.stqa.pft.sandbox;

public class PointDistance {

  public static void main(String[] args) {

    Point p1 = new Point(2, 8);
    Point p2 = new Point(1, 2);


    System.out.println("Расстояния между точками  = " + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.getY() - p1.getY()) * (p2.getY() - p1.getY()) + (p2.getX() - p1.getX()) * (p2.getX() - p1.getX()));
  }
}

//public class PointDistance {
//  public static void main(String[] args) {
//
//    Point p1 = new Point(4.0, 8.0);
//    Point p2 = new Point(6, 2);
//
//
//    System.out.println("Координаты точки Р1 (x,y) - " + p1.x1 + " , " + p1.y1);
//    System.out.println("Координаты точки Р2 (x,y) - " + p2.x2 + " , " + p2.y2);
//    System.out.println("Расстояния между точками на плоскости = " + p1.distance(p2));
//
//  }
//}