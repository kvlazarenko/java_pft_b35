package ru.stqa.pft.sandbox;

public class MyFirstProgramm {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Konstantin");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());


    Rectangle r;
    r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello(String sombody) {

    System.out.println("Hello " + sombody + "!");
  }




}