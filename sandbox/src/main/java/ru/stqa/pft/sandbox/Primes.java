package ru.stqa.pft.sandbox;

public class Primes {

  public static boolean IsPrime(int n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }


  public static boolean IsPrimeWhile(int n) {

    int i = 2;
    while (i < n) {
      if (n % i == 0) {
        return false;
      }
      i++;
    }
    return true;
  }

  public static boolean IsPrime(long n) {
      for (long i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean IsPrimeFast(int n) {
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m ; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}


