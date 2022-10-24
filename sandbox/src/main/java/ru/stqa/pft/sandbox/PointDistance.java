
package ru.stqa.pft.sandbox;

public class PointDistance {
  public static void main(String[] args) {

    Point p1 = new Point(2, 8);
    Point p2 = new Point(1, 2);


    System.out.println("Координаты точки Р1 (x,y) - " + p1.getX() + " , " + p1.getY());
    System.out.println("Координаты точки Р2 (x,y) - " + p2.getX() + " , " + p2.getY());
    System.out.println("Расстояния между точками на плоскости = " + p1.distance(p2));

  }
}