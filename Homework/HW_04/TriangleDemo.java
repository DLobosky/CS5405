// Programmer: Dalton Lobosky
// Date: 9/15/2016
// File: TriangleDemo.java

package code;

import java.util.Scanner;
import static java.lang.Math.*;

/**@author Dalton Lobosky
   @version 1.0
*/


public class TriangleDemo
{
  public static void main(String[] args)
  {
	float x,y,z; 
	double area, perim;
	String color;
	boolean filled;
 
        Triangle t1 = new Triangle();

        System.out.println("----------------------------------------");
        System.out.println("Please only use right triangles.\n");
        
	System.out.println("Please enter Side 1: ");
	Scanner input = new Scanner(System.in);
	t1.x = input.nextFloat();
	
	System.out.println("Please enter Side 2: ");
        input = new Scanner(System.in);
        t1.y = input.nextFloat();
        
        System.out.println("Please enter Side 3: ");
        input = new Scanner(System.in);
        t1.z = input.nextFloat();

	System.out.println("Please enter the color of your triangle: : ");
        input = new Scanner(System.in);
        t1.setColor(input.next());
	
	System.out.println("Is the triangle filled? (True = yes / False = no): ");
        input = new Scanner(System.in);
        t1.setFilled(input.nextBoolean());

        area = t1.getArea();
        System.out.println("\nThe area of the triangle is: " + area);
        
        perim = t1.getPerimeter();
        System.out.println("\nThe perimeter of the triangle is: " + perim);
        
        /**Returns a string representation of an object creation date, filled value, and color.*/      
        System.out.println(t1.toString());
        
        return;
  }
}
