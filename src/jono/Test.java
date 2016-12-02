package jono;

import java.util.ArrayList;

public class Test {

   public static void main(String[] args) {
      for (int a = Integer.MIN_VALUE + 10; a < Integer.MAX_VALUE - 10; a--) {
         int b = -1;
         int c = a / b;
         System.out.println("a: " + a + ", c:  " + c);
      }
      
   }
}
