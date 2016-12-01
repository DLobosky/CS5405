/*
Programmer:		Jason Beard
Assignment:		homework 5
Date:			10/12/2016
*/

package code;

/**
@author Jason Beard
@version 1
*/

import javafx.scene.shape.Polygon;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Scanner;
import javafx.scene.shape.Shape;

public class TriangleDemo extends Application 
{
	double x;
	double y;
	double w;
	double h;
	
	double q;
	double r;
	double s;
	double t;
	
	double t1a;
	double t1b;
	double t1c;
	double t1d;
	double t1e;
	double t1f;

	double t2a;
	double t2b;
	double t2c;
	double t2d;
	double t2e;
	double t2f;
	
	TextField x1text;
	TextField x2text;
	TextField y1text;
	TextField y2text;
	TextField w1text;
	TextField w2text;
	TextField h1text;
	TextField h2text;
    Polygon t1;
    Polygon t2;
	HBox hbt;
	HBox hbb;
    Pane root;
	Button submint;
	Scanner scan;
	
	Label output_label_DR;
	Label output_label_PO;
	Label output_label_EQ;
	Label output_label_PP;
	Label output_label_PPc;
	
    @Override // Override the start method in the Application class
 

public void start(Stage stage) 
{
    BorderPane bp = new BorderPane();
	VBox vbr = new VBox();
	Button submint = new Button();
	
	x1text = new TextField();
	x2text = new TextField();
	y1text = new TextField();
	y2text = new TextField();
	w1text = new TextField();
	w2text = new TextField();
	h1text = new TextField();
	h2text = new TextField();
	hbt = new HBox();
	hbb = new HBox();
    root = new Pane();
	submint = new Button();
	
	Label label1 = new Label("label 1");
	Label input_label_1 = new Label("Triangle 1 (Green/Black):");
	Label input_label_2 = new Label("Triangle 2 (Blue/Red):");
	Label title_label = new Label("Triangles Spatial Relations Demo");
	output_label_DR = new Label("These two triangles are interior disjoint.");
	output_label_PO = new Label("These two triangles are proper overlap.");
	output_label_EQ = new Label("These two triangles are equal.");
	output_label_PP = new Label("Triangle 1 is inside of Triangle 2.");
	output_label_PPc = new Label("Triangle 2 is inside of Triangle 1.");
	
	x1text.setPromptText("x coordinate");
	x2text.setPromptText("x coordinate");
	y1text.setPromptText("y coordinate");
	y2text.setPromptText("y coordinate");
	w1text.setPromptText("width");
	w2text.setPromptText("width");
	h1text.setPromptText("height");
	h2text.setPromptText("height");
	
	submint.setText("   Create New Triangles   ");
	submint.setOnAction(new buttonhandler());
	
	vbr.setSpacing(5); 
	
	vbr.getChildren().addAll(input_label_1, x1text, y1text, w1text,h1text,input_label_2, x2text, y2text, w2text, h2text, submint);
	hbt.getChildren().addAll(title_label);
    
    // Create a scene and place it in the stage.
    Scene scene = new Scene(bp, 500, 500);
	bp.setCenter(root);
	bp.setRight(vbr);
	bp.setTop(hbt);
	bp.setBottom(hbb);
    stage.setScene(scene); // Place the scene in the stage.
    stage.show(); // Display the stage.
    stage.setTitle("Show Two Circles"); // Set the stage title.
}

/** Deletes the triangles and prints new ones along with adding the correct output. */
public class buttonhandler implements EventHandler<ActionEvent>
{
	public void handle(ActionEvent ae)
	{
		root.getChildren().clear();
		hbb.getChildren().clear();
		getData();
		t1=createTriangle1(x, y, w, h);
		t2=createTriangle2(q, r, s, t);
		root.getChildren().addAll(t1, t2);
		
		t1a = x;
		t1b = y;
		t1c = x + w;
		t1d = y;
		t1e = x + w/2;
		t1f = y + h;
		
		t2a = q;
		t2b = r;
		t2c = q + s;
		t2d = r;
		t2e = q + s/2;
		t2f = r + t;
		
		if(x == q && y == r && w == s && h == t)
		{
			hbb.getChildren().addAll(output_label_EQ);			
		}
		else
		{
			if(!Shape.intersect(t1, t2).getBoundsInLocal().isEmpty())
			{
				if(checkInTri1(t2a, t2b) == true && checkInTri1(t2c, t2d) == true && checkInTri1(t2e, t2f) == true) 
				{
					hbb.getChildren().addAll(output_label_PPc);
				}
				else
				{
					if(checkInTri2(t1a, t1b) == true && checkInTri2(t1c, t1d) == true && checkInTri2(t1e, t1f) == true)
					{
						hbb.getChildren().addAll(output_label_PP);
					}
					else
					{
						hbb.getChildren().addAll(output_label_PO);
					}
				}
			}
			else
			{
				hbb.getChildren().addAll(output_label_DR);					
			}
		}
	}
}

/** Retrieves data from the TextFields.*/
public void getData()
{
	x = 100;
	y = 100;
	w = 100;
	h = 100;
	
	q = 50;
	r = 50;
	s = 50;
	t = 50;
	
	if(!x1text.getText().trim().isEmpty())
	{
		scan = new Scanner(x1text.getText());
		x = scan.nextInt();
	}
	
	if(!y1text.getText().trim().isEmpty())
	{
		scan = new Scanner(y1text.getText());
		y = scan.nextInt();
	}
	
	if(!w1text.getText().trim().isEmpty())
	{
		scan = new Scanner(w1text.getText());
		w = scan.nextInt();
	}
	
	if(!h1text.getText().trim().isEmpty())
	{
		scan = new Scanner(h1text.getText());
		h = scan.nextInt();
	}
		
	if(!x2text.getText().trim().isEmpty())
	{	
		scan = new Scanner(x2text.getText());
		q = scan.nextInt();
	}
	
	if(!y2text.getText().trim().isEmpty())
	{
		scan = new Scanner(y2text.getText());
		r = scan.nextInt();
	}
	
	if(!w2text.getText().trim().isEmpty())
	{
		scan = new Scanner(w2text.getText());
		s = scan.nextInt();
	}
	
	if(!h2text.getText().trim().isEmpty())
	{
		scan = new Scanner(h2text.getText());
		t = scan.nextInt();
	}
	
}
 
/** Creates Triangle 1, the Green and Black one. */ 
public Polygon createTriangle1(double x,double y,double w, double h)
{
	double a = x;
	double b = y;
	double c = x + w;
	double d = y;
	double e = x + w/2;
	double f = y + h;
	
	// Create triangle 1 and set its properties.
	Polygon poly = new Polygon(a,b,c,d,e,f);
	poly.setStroke(Color.BLACK);
	poly.setStrokeWidth(8);
	poly.setFill(Color.GREEN);
	return poly;
}

/** Creates Triangle 2, the Blue and Red one. */	
public Polygon createTriangle2(double x,double y,double w, double h)
{
	double a = x;
	double b = y;
	double c = x + w;
	double d = y;
	double e = x + w/2;
	double f = y + h;
	
	// Create triangle 2 and set its properties
	Polygon poly = new Polygon(a,b,c,d,e,f);
	poly.setStroke(Color.RED);
	poly.setStrokeWidth(8);
	poly.setFill(Color.BLUE);
	return poly;
}

/** Checks to see if Triangle 2 is inside of Triangle 1. */	
public boolean checkInTri1(double px, double py)
{
	double p1x, p2x, p3x, p1y, p2y, p3y;
		p1x = t1a;
		p2x = t1c;
		p3x = t1e;
		
		p1y = t1b;
		p2y = t1d;
		p3y = t1f;
		
		double a = ((p2y - p3y)*(px - p3x) + (p3x - p2x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double b = ((p3y - p1y)*(px - p3x) + (p1x - p3x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double c = -a-b+1;
		
		if(a>0 && b>0 && c>0)
			return true;
		else
			return false;
}

/** Checks to see if Triangle 1 is inside of Triangle 2. */	
public boolean checkInTri2(double px, double py)
{
	double p1x, p2x, p3x, p1y, p2y, p3y;
		p1x = t2a;
		p2x = t2c;
		p3x = t2e;
		
		p1y = t2b;
		p2y = t2d;
		p3y = t2f;
		
		double a = ((p2y - p3y)*(px - p3x) + (p3x - p2x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double b = ((p3y - p1y)*(px - p3x) + (p1x - p3x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double c = -a-b+1;
		
		if(a>0 && b>0 && c>0)
			return true;
		else
			return false;
}
    
    
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) 
  {
    launch(args);
  }
}
