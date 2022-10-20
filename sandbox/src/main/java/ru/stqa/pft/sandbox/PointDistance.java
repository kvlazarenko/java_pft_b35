package ru.stqa.pft.sandbox;


public class PointDistance {

  public static void main(String[] args) {

    Point p1 = new Point(7.0, 5.5);
    Point p2 = new Point(8,5);


    System.out.println("Координаты точки Р1 (x,y) - " + p1.x + " , " + p1.y);
    System.out.println("Координаты точки Р2 (x,y) - " + p2.x + " , " + p2.y);
    System.out.println("Расстояния между точками на плоскости = " + p2.distance(p1));

  }
}

