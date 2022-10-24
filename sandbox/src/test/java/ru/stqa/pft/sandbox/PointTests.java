package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.geom.Point2D;

public class PointTests {

  @Test

  public void TestArea() {

    Point p1 = new Point(2.0, 8.0);
    Point p2 = new Point(1, 2);


    Assert.assertEquals(p1.distance(p2), 6.082762530298219);
  }

  @Test

  public void TestArea2() {

    Point p1 = new Point(2.0, 8.0);
    Point p2 = new Point(1, 2);

    Assert.assertEquals(Point2D.distance(p1.getX(),p1.getY(),p2.getX(),p2.getY()), 6.082762530298219);


  }
  @Test

  public void TestArea3() {

    Point p1 = new Point(2.0, 8.0);
    Point p2 = new Point(1, 2);

    Assert.assertEquals(p1.distance(p2), Point2D.distance(p1.getX(),p1.getY(),p2.getX(),p2.getY()));


  }
  @Test
  public void TestArea4() {

    Point p1 = new Point(2.0, 8.0);
    Point p2 = new Point(1, 2);

    Assert.assertEquals(Math.sqrt((p2.getY() - p1.getY()) * ((p2.getY() - p1.getY())) + ((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()))), 6.082762530298219 );


  }

}
