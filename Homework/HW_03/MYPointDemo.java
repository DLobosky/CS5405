// Programmer: Dalton Lobosky
// Date: 9/12/2016
// File: HW_03

package hw_03;

import java.util.Scanner;
import static java.lang.Math.*;

class MYPoint
{
	private float x,y;
	
	public MYPoint()
	{
		x=0;
		y=0;
	}
	
	public MYPoint(float a, float b)
	{
		x=a;
		y=b;
	}
	
	public float get_x()
	{
		return x;
	}
	
	public float get_y()
	{
		return y;
	}
	
	public void set_x(float a) // you can use "a" because it is not in the same scope as the first instance of "a"
	{
		x = a;
	}
	
	public void set_y(float y)
	{
		this.y = y; // must use "this.y" because you are using a new variable named "y" while there is another "y" in the same scope, so "this.y" refers to the one that is from this class
	}
	
	public double distance(float a, float b)
	{
		double d;
		d = sqrt(((a-x)*(a-x)) + ((b-y)*(b-y)));
		return d;
	}
	
	public double distance(MYPoint c)
	{
		double d;
		d = sqrt(((c.get_x()-x)*(c.get_x()-x)) + ((c.get_y()-y)*(c.get_y()-y)));
		return d;
	}	
}

public class MYPointDemo
{
	public static void main (String[] args)
	{
		float x, y, x2, y2;
		double d, d2;
		
		MYPoint point1 = new MYPoint();
		
		System.out.println("-------------------------------------------------------------\n");
		System.out.println("Point 1 is currently set to (" + point1.get_x() + ", " + point1.get_y() + ")\n");
		
		// ---------------As coordinates-------------------
		System.out.println("Please enter your desired X coordinate for Point 2:");
		Scanner input = new Scanner(System.in);
		x = input.nextFloat();
		
		System.out.println("Please enter your desired Y coordinate for Point 2:");
		y = input.nextFloat();
		
		MYPoint point2 = new MYPoint();
		point2.set_x(x);
		point2.set_y(y);
		
		System.out.println("\nPoint 2 is currently set to (" + x + ", " + y + ")\n");
		
		
		d = point1.distance(x, y);
		System.out.println("The distance between Point 1 and Point 2 is: " + d + "\n\n");
		System.out.println("-------------------------------------------------------------");
		
		
		//-------------- As a point---------------
		System.out.println("Please enter your desired X coordinate for Point 3:");
		x2 = input.nextFloat();
		
		System.out.println("Please enter your desired Y coordinate for Point 3:");
		y2 = input.nextFloat();
		
		MYPoint point3 = new MYPoint();
		point3.set_x(x2);
		point3.set_y(y2);
		
		System.out.println("\nPoint 3 is currently set to (" + x2 + ", " + y2 + ")\n");
		
		
		d2 = point2.distance(point3);
		System.out.println("The distance between Point 2 and Point 3 is: " + d2);
		System.out.println("\n-------------------------------------------------------------\n\n");
		
		return;		
	}
}















