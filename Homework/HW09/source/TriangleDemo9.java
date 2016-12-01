/**@author Dalton Lobosky
	@version 1.0
*/

package code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.shape.Shape;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

/** The start of the class TriangleDemo9 */
public class TriangleDemo9 extends Application 
{
	// The grey box where the triangles are "drawn"
	Pane drawingBoard;
	
	// Top horizontal box for instructions
	HBox hb_T;
	
	// Bottom horizontal box for output of comparisons
	HBox hb_B;
	
	// Pane for the text boxes on the left to input triangle data
	VBox vb;
	
	// Scene that is set on myStage
	Scene myScene;
	
	// Pane for the "program" tab where the drawingBoard pane is placed 
	BorderPane mainPane;
	
	// Pane for the "author" tab	
	StackPane authorPane;
	
	// Pane for the "description" tab
	FlowPane descriptionPane;
	
	// All the text fields for the data to be input for the triangles
	TextField x1Value;
	TextField x2Value;
	TextField y1Value;
	TextField y2Value;
	TextField w1Value;
	TextField w2Value;
	TextField h1Value;
	TextField h2Value;
	
	// Buttons for creating triangles and clearing the drawingBoard
	Button submitButton;
	Button clearButton;
	
	//public String str;
	
	
	double x1;
	double x2;
	double y1;
	double y2;
	double w1;
	double w2;
	double h1;
	double h2;
	
	// X and Y coordinates for t1
	double t1x1, t1y1, t1x2, t1y2, t1x3, t1y3;
	
	// X and Y coordinates for t2
	double t2x1, t2y1, t2x2, t2y2, t2x3, t2y3;
	
	// X and Y coordinates for t3
	double t3x1, t3x2, t3x3, t3y1, t3y2, t3y3;
	
	// X and Y coordinates for t4
	double t4x1, t4x2, t4x3, t4y1, t4y2, t4y3;
	
	// Label that follows the mouse for its coordinates
	Label mouse_label = new Label("");
	
	// Variables for mouse's x and y coordinates
	double mouse_x, mouse_y;
	
	// The string used to create mouse_label
	String mouse_coords;
	
	// Variable to keep track of number of clicks which later allows you to make 2 triangles properly
	int num_clicks = 0;
	
	// t3 and t4 made "semi" global because they are referenced outside of their necessary scope for removal from pane and the mouse event handler
	Polygon t3 = new Polygon();
	Polygon t4 = new Polygon();
	
	// t1 and t2 made "semi" global because they are referenced outside of their necessary scope for removal from pane
	Polygon t1, t2;
	
    @Override
	
	/** The start method for the application*/
    public void start(Stage myStage) 
	{		
		//Setup the scene
        myStage.setTitle("Triangle Maker 5000!!!!");
		
		drawingBoard = new Pane(); // Creates the drawing board for the triangles
		drawingBoard.setStyle("-fx-background-color: LIGHTGRAY;");
		
		hb_T = new HBox(); // Top horizontal box for instructions
		hb_T.setStyle("-fx-background-color: CYAN;");
		
		hb_B = new HBox(); // Bottom horizontal box for output of comparisons
		
		vb = new VBox(); // Pane for the text boxes on the left
		vb.setStyle("-fx-background-color: CYAN;");
		vb.setSpacing(2); // setting the spacing betweek nodes in vb
		vb.setPrefWidth(125); // setting width of vb so text boxes aren't too big	
		vb.setAlignment(Pos.CENTER);
			
		mainPane = new BorderPane(); // Main Pane for the whole program
		mainPane.setCenter(drawingBoard);
		mainPane.setLeft(vb);
		mainPane.setTop(hb_T);
		mainPane.setBottom(hb_B);	
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // Makes sure the close option is not available on the tabs
		
		StackPane authorPane = new StackPane(); // Pane for the author tab
		authorPane.setStyle("-fx-background-color: CYAN;");
		
		FlowPane descriptionPane = new FlowPane(); // Pane for the description tab
		descriptionPane.setStyle("-fx-background-color: CYAN;");
		
		myScene = new Scene(tabPane, 500, 400);
		
		Tab authorTab = new Tab(); // Creates the "author" tab
		authorTab.setText("Author");
		
		Tab descriptionTab = new Tab(); // Creates the "description" tab
		descriptionTab.setText("Description");
		
		Tab programTab = new Tab(); // Creates the "program" tab
		programTab.setText("Program");
		
		authorTab.setContent(authorPane);
		descriptionTab.setContent(descriptionPane);
		programTab.setContent(mainPane);
		
		tabPane.getTabs().add(authorTab);
		tabPane.getTabs().add(descriptionTab);
		tabPane.getTabs().add(programTab);
		
		// Instructions on top of page		
		Text instr = new Text("Please enter the X and Y coordinates and the Width and Height for TWO triangles");
		instr.setStyle("-fx-font: 20 arial;");
		instr.setWrappingWidth(450);
		hb_T.getChildren().add(instr);
		
		Rectangle authorRec = new Rectangle(300,125,Color.LIGHTGRAY);
		//rectangle.relocate(70,70);
		authorPane.getChildren().add(authorRec);
		
		Text authorText = new Text("Author: Dalton Lobosky\nEmail: dgl9t5@mst.edu");
		authorText.setStyle("-fx-font: 20 comicsans;");
		authorText.setFill(Color.DARKBLUE);
		authorText.setId("fancytext");
		authorPane.getChildren().add(authorText);
		
		Text descriptionText = new Text("  This program takes in the values for the x and y coordinates and the height\n"
										+ "  and width for two triangles, then draws the two triangles and analyzes \n"
										+ "  their positions. You can also click three times for a triangle to be drawn\n"
										+ "  and then again 3 more clicks to draw a second triangle and the program will\n"
										+ "  compare those two triangle as well. IF THE TRIANGLES DO NOT DRAW PROPERLY, \n"
										+ "  CLICK ON THE \"CLEAR\" BUTTON TO RESET AND START AGAIN.\n"
										+ "  \n"
										+ "  The variables must be input in this order:\n"
										+ "  1. Triangle 1 X Coordinate\n"
										+ "  2. Triangle 1 Y Coordinate\n"
										+ "  3. Triangle 1 Width\n"
										+ "  4. Triangle 1 Height\n"
										+ "  5. Triangle 2 X Coordinate\n"
										+ "  6. Triangle 2 Y Coordinate\n"
										+ "  7. Triangle 2 Width\n"
										+ "  8. Triangle 2 Height\n"
										+ "  \n"
										+ "  The triangles' positions are compared and are tdetermined to be either:\n"
										+ "  - DC (The triangles are disconnected)\n"
										+ "  - EC (The triangles are externally connected)\n"
										+ "  - EQ (The triangles are the exact same)\n"
										+ "  - PO (The triangles have proper overlap)\n"
										+ "  - TPP (Triangle 1 is inside Triangle 2, and the boundaries are touching)\n"
										+ "  - NTTP(Triangle 1 is in Triangle 2 and the boundaries do not touch)\n"
										+ "  - TPPc (Triangle 2 is inside Triangle 1 and the boundaries are touching)\n"
										+ "  - NTPPc (Triangle 2 is inside Triangle 1 and the boundaries do not touch)\n");
		descriptionText.setStyle("-fx-font: 12 arial;");
		//descriptionText.setWrappingWidth(450);
		descriptionText.relocate(150 ,150);
		descriptionPane.getChildren().add(descriptionText);
		
		// Input Box 1
		TextField x1Value = new TextField();
		x1Value.setPromptText("X1 Coordinate");
		x1Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(x1Value);
		
		// Input Box 2
		TextField y1Value = new TextField();
		y1Value.setPromptText("Y1 Coordinate");
		y1Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(y1Value);
		
		// Input Box 3
		TextField w1Value = new TextField();
		w1Value.setPromptText("Width of Triangle 1");
		w1Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(w1Value);
		
		// Input Box 4
		TextField h1Value = new TextField();
		h1Value.setPromptText("Height of Triangle 1");
		h1Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(h1Value);
		
		// Input Box 5
		TextField x2Value = new TextField();
		x2Value.setPromptText("X2 Coordinate");
		x2Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(x2Value);
		
		// Input Box 6
		TextField y2Value = new TextField();
		y2Value.setPromptText("Y2 Coordinate");
		y2Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(y2Value);
		
		// Input Box 7
		TextField w2Value = new TextField();
		w2Value.setPromptText("Width of Triangle 2");
		w2Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(w2Value);
		
		// Input Box 8
		TextField h2Value = new TextField();
		h2Value.setPromptText("Height of Triangle 2");
		h2Value.setStyle("-fx-background-color: LIGHTCYAN;");
		vb.getChildren().add(h2Value);
			
		// Submit Button
		submitButton = new Button("SUBMIT");
		submitButton.setStyle("-fx-background-color: MEDIUMSPRINGGREEN;");
		
		clearButton = new Button("CLEAR");
		clearButton.setStyle("-fx-background-color: YELLOW;");

		vb.getChildren().addAll(submitButton, clearButton);
		
		drawingBoard.getChildren().add(mouse_label);
		drawingBoard.getChildren().add(t3);
		drawingBoard.getChildren().add(t4);
		
		myStage.setScene(myScene);

        myStage.show();
		
		drawingBoard.setOnMouseMoved(movedHandler);
		drawingBoard.setOnMouseClicked(clickedHandler);
		drawingBoard.setOnMouseEntered(enteredHandler);
		drawingBoard.setOnMouseExited(exitedHandler);

		/** Button functionality for the drawing of the triangles */
		submitButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override 
			public void handle(ActionEvent e) 
			{
				drawingBoard.getChildren().removeAll(t1, t2);
				hb_B.getChildren().clear();
				
				double x1 = Float.valueOf(x1Value.getText());
				double y1 = Float.valueOf(y1Value.getText());
				double w1 = Float.valueOf(w1Value.getText());
				double h1 = Float.valueOf(h1Value.getText());
				double x2 = Float.valueOf(x2Value.getText());
				double y2 = Float.valueOf(y2Value.getText());
				double w2 = Float.valueOf(w2Value.getText());
				double h2 = Float.valueOf(h2Value.getText());

				t1 = createTriangle(x1,y1,w1,h1);
				t2 = createTriangle(x2,y2,w2,h2);
				t1.setFill(Color.YELLOW);
				t1.setOpacity(0.7);
				t2.setFill(Color.RED);
				t2.setOpacity(0.4);
			
				drawingBoard.getChildren().addAll(t1,t2);
				
				t1x1 = x1;
				t1y1 = y1;
				t1x2 = x1 + w1;
				t1y2 = y1;
				t1x3 = (x1 + (w1/2));
				t1y3 = y1 +h1;
				
				t2x1 = x2;
				t2y1 = y2;
				t2x2 = x2 + w2;
				t2y2 = y2;
				t2x3 = x2 + (w2/2);
				t2y3 = y2 +h2;
				
				if(x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2)
				{
					hb_B.getChildren().add(new Label("The triangles are equal."));
				}
				else
				{
					if(!Shape.intersect(t1, t2).getBoundsInLocal().isEmpty())
					{
						if(InTriangle_1(t2x1, t2y1) == true && InTriangle_1(t2x2, t2y2) == true && InTriangle_1(t2x3, t2y3) == true) 
						{
							if(isTouchingEdgeTriangle1(t2x1, t2y1) || isTouchingEdgeTriangle1(t2x2, t2y2) || isTouchingEdgeTriangle1(t2x3, t2y3) || isTouchingEdgeTriangle2(t1x1, t1y1) || isTouchingEdgeTriangle2(t1x2, t1y2) || isTouchingEdgeTriangle2(t1x3, t1y3))
							{
								hb_B.getChildren().add(new Label("T2 is in T1 and touching the boundary."));
							}
							else
							{
								hb_B.getChildren().add(new Label("T2 is in T1 and they are not touching."));
							}
						}
						else
						{
							if(InTriangle_2(t1x1, t1y1) == true && InTriangle_2(t1x2, t1y2) == true && InTriangle_2(t1x3, t1y3) == true)
							{
								if(isTouchingEdgeTriangle1(t2x1, t2y1) || isTouchingEdgeTriangle1(t2x2, t2y2) || isTouchingEdgeTriangle1(t2x3, t2y3) || isTouchingEdgeTriangle2(t1x1, t1y1) || isTouchingEdgeTriangle2(t1x2, t1y2) || isTouchingEdgeTriangle2(t1x3, t1y3))
								{
									hb_B.getChildren().add(new Label("T1 is in T2 and touching the boundary."));
								}
								else
								{
									hb_B.getChildren().add(new Label("T1 is in T2 and they are not touching."));
								}
							}
							else
							{
								hb_B.getChildren().add(new Label("T1 and T2 have Proper Overlap."));
							}
						}
					}
					else
					{
						if(isTouchingEdgeTriangle1(t2x1, t2y1) || isTouchingEdgeTriangle1(t2x2, t2y2) || isTouchingEdgeTriangle1(t2x3, t2y3) || isTouchingEdgeTriangle2(t1x1, t1y1) || isTouchingEdgeTriangle2(t1x2, t1y2) || isTouchingEdgeTriangle2(t1x3, t1y3))
						{
							hb_B.getChildren().add(new Label("The two triangles are externally connected."));
						}
						else
						{
							hb_B.getChildren().add(new Label("T1 and T2 are Interior Disjoint."));					
						}
					}
				}
			}
		});
		
		/** Button to clear the drawingBoard pane */
		clearButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override 
			public void handle(ActionEvent e) 
			{
				drawingBoard.getChildren().removeAll(t1, t2, t3, t4);
				hb_B.getChildren().clear();
				num_clicks = 0;
				t3 = new Polygon();
				drawingBoard.getChildren().add(t3);
				t4 = new Polygon();
				drawingBoard.getChildren().add(t4);
			}
		});
	}
	
	/** Moved mouse event to track X and Y coordinates of mouse */
	EventHandler<MouseEvent> movedHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{
			mouse_x = mouseEvent.getX();
			mouse_y = mouseEvent.getY();
			
			mouse_coords = "X: " + (int)mouse_x + "\nY: " + (int)mouse_y;
			mouse_label.setText(mouse_coords);
			mouse_label.relocate(mouse_x + 15, mouse_y + 15);
			
		}
	};
	
	/** Mouse clicked event that creates 2 triangles after being clicked 6 times and compares them */
	EventHandler<MouseEvent> clickedHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{	
			// Switch statement to get the values of the x and y coordinates for each point in each triangle
			switch (num_clicks)
			{
				case 1:
				{
					t3x1 = mouseEvent.getX();
					t3y1 = mouseEvent.getY();
				}
				break;
				case 2:
				{
					t3x2 = mouseEvent.getX();
					t3y2 = mouseEvent.getY();
				}
				break;
				case 3:
				{
					t3x3 = mouseEvent.getX();
					t3y3 = mouseEvent.getY();
				}
				break;
				case 4:
				{
					t4x1 = mouseEvent.getX();
					t4y1 = mouseEvent.getY();
				}
				break;
				case 5:
				{
					t4x2 = mouseEvent.getX();
					t4y2 = mouseEvent.getY();
				}
				break;
				case 6:
				{
					t4x3 = mouseEvent.getX();
					t4y3 = mouseEvent.getY();
				}
				break;
			}
			
			// Allows for only 3 clicks to create the first triangle
			if(num_clicks < 3)
			{
				createTriangleFromMouseClicks();
				num_clicks++;
			}
			
			// The second set of 3 clicks makes the second triangle (totaling 6 clicks)
			else if(num_clicks < 6)
			{
				createTriangleFromMouseClicks2();
				num_clicks++;
				
				// Compares the triangles once the 6 point is made
				if(num_clicks == 6)
				{
					if(t3x1 == t4x1 && t3y1 == t4y1 && t3x2 == t4x2 && t3y2 == t4y2 && t3x3 == t4x3 && t3y3 == t4y3)
					{
						hb_B.getChildren().add(new Label("The triangles are equal."));
					}
					else
					{
						if(!Shape.intersect(t3, t4).getBoundsInLocal().isEmpty())
						{
							if(InTriangle_1_mouse(t4x1, t4y1) == true && InTriangle_1_mouse(t4x2, t4y2) == true && InTriangle_1_mouse(t4x3, t4y3) == true) 
							{
								if(isTouchingEdgeTriangle1_mouse(t4x1, t4y1) || isTouchingEdgeTriangle1_mouse(t4x2, t4y2) || isTouchingEdgeTriangle1_mouse(t4x3, t4y3) || isTouchingEdgeTriangle2_mouse(t3x1, t3y1) || isTouchingEdgeTriangle2_mouse(t3x2, t3y2) || isTouchingEdgeTriangle2_mouse(t3x3, t3y3))
								{
									hb_B.getChildren().add(new Label("T2 is in T1 and touching the boundary."));
								}
								else
								{
									hb_B.getChildren().add(new Label("T2 is in T1 and they are not touching."));
								}
							}
							else
							{
								if(InTriangle_2_mouse(t3x1, t3y1) == true && InTriangle_2_mouse(t3x2, t3y2) == true && InTriangle_2_mouse(t3x3, t3y3) == true)
								{
									if(isTouchingEdgeTriangle1_mouse(t4x1, t4y1) || isTouchingEdgeTriangle1_mouse(t4x2, t4y2) || isTouchingEdgeTriangle1_mouse(t4x3, t4y3) || isTouchingEdgeTriangle2_mouse(t3x1, t3y1) || isTouchingEdgeTriangle2_mouse(t3x2, t3y2) || isTouchingEdgeTriangle2_mouse(t3x3, t3y3))
									{
										hb_B.getChildren().add(new Label("T1 is in T2 and touching the boundary."));
									}
									else
									{
										hb_B.getChildren().add(new Label("T1 is in T2 and they are not touching."));
									}
								}
								else
								{
									hb_B.getChildren().add(new Label("T1 and T2 have Proper Overlap."));
								}
							}
						}
						else
						{
							if(isTouchingEdgeTriangle1_mouse(t4x1, t4y1) || isTouchingEdgeTriangle1_mouse(t4x2, t4y2) || isTouchingEdgeTriangle1_mouse(t4x3, t4y3) || isTouchingEdgeTriangle2_mouse(t3x1, t3y1) || isTouchingEdgeTriangle2_mouse(t3x2, t3y2) || isTouchingEdgeTriangle2_mouse(t3x3, t3y3))
							{
								hb_B.getChildren().add(new Label("The two triangles are externally connected."));
							}
							else
							{
								hb_B.getChildren().add(new Label("T1 and T2 are Interior Disjoint."));					
							}
						}
					}
				}					
			}
		}
	};
	
	/** Mouse exited event that "removes" the label for the coordinates of the mouse when the mouse leaves the designated pane */
	EventHandler<MouseEvent> exitedHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{
			mouse_coords = "";
			mouse_label.setText(mouse_coords);
		}
	};
	
	/** Mouse entered event that re-adds the label for the coordinates of the mouse when the mouse re-enters the designated pane */
	EventHandler<MouseEvent> enteredHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{			
			mouse_coords = "X: " + (int)mouse_x + "\nY: " + (int)mouse_y;
			mouse_label.setText(mouse_coords);
		}
	};

	
	/** Method to make a triangle from typed data*/
	public Polygon createTriangle(double x, double y, double w, double h)
	{
		double a,b,c,d,e,f;
		
		a = x;
		b = y;
		c = x+w;
		d = y;
		e = (x+(w/2));
		f = y+h;
		
        Polygon triangle = new Polygon(a,b,c,d,e,f);
	
        return triangle;
    }
	
	/** Method to make first triangle from mouse clicks */
	public void createTriangleFromMouseClicks()
	{
		t3.getPoints().addAll(mouse_x, mouse_y);
		t3.setFill(Color.BLUE);
		t3.setOpacity(0.5);
        return;
    }
	
	/** Method to make second triangle from mouse clicks */
	public void createTriangleFromMouseClicks2()
	{
		t4.getPoints().addAll(mouse_x, mouse_y);
		t4.setFill(Color.GREEN);
		t4.setOpacity(0.5);
        return;
    }
	
	/** Determines if T2 is in T1 from tpyed data */	
	public boolean InTriangle_1(double x, double y)
	{
		double t1x = t1x1;
		double t2x = t1x2;
		double t3x = t1x3;
		double t1y = t1y1;
		double t2y = t1y2;
		double t3y = t1y3;
		
		double num1 = ((t2y - t3y)*(x - t3x) + (t3x - t2x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num2 = ((t3y - t1y)*(x - t3x) + (t1x - t3x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num3 = -(num1) - num2 + 1;
		
		if(num1 >= 0 && num2 >= 0 && num3 >= 0)
			return true;
		else
			return false;
	}
	
	/** Determines if T1 is in T2 from typed data*/	
	public boolean InTriangle_2(double x, double y)
	{
		double t1x = t2x1;
		double t2x = t2x2;
		double t3x = t2x3;
		
		double t1y = t2y1;
		double t2y = t2y2;
		double t3y = t2y3;
		
		double num1 = ((t2y - t3y)*(x - t3x) + (t3x - t2x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num2 = ((t3y - t1y)*(x - t3x) + (t1x - t3x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num3 = -(num1) - num2 + 1;
		
		if(num1 >= 0 && num2 >= 0 && num3 >= 0)
			return true;
		else
			return false;
	}
	
	/** Determines if when T2 is in T1, they touch from typed data*/	
	public boolean isTouchingEdgeTriangle1 (double x, double y)
	{
		boolean bool1 = false;
		
		if(((x == t1x1) && (y == t1y1)) ||((x == t1x2) && (y == t1y2)) || ((x == t1x3) && (y == t1y3)))
		{
			bool1 = true;
		}
		else
		{
			if((((t1y1 - y) / (t1x1 - x)) == ((t1y2 - y) / (t1x2 - x))) && (((t1y1 - t1y2) / (t1x1 - t1x2)) == ((t1y1 - y) / (t1x1 - x))))
			{
				if(((t1x1 <= x) && (x <= t1x2)) && ((t1y1 == y) && (y == t1y2)))
				{
					bool1 = true;
				}
			}
			
			if((((t1y2 - y) / (t1x2 - x)) == ((t1y3 - y) / (t1x3 - x))) && (((t1y2 - t1y3) / (t1x2 - t1x3)) == ((t1y2 - y) / (t1x2 - x))))
			{
				if(((t1x3 <= x) && (x <= t1x2)) && ((t1y2 == y) && (y == t1y3)))
				{
					bool1 = true;
				}
			}
			
			if((((t1y3 - y) / (t1x3 - x)) == ((t1y1 - y) / (t1x1 - x))) && (((t1y3 - t1y1) / (t1x3 - t1x1)) == ((t1y3 - y) / (t1x3 - x))))
			{
				if(((t1x1 <= x) && (x <= t1x3)) && ((t1y1 == y) && (y == t1y3)))
				{
					bool1 = true;
				}
			}
		}
		
		return bool1;
	}
	
	/** Determines if when T1 is in T2, they touch from typed data */	
	public boolean isTouchingEdgeTriangle2 (double x, double y)
	{
		boolean bool1 = false;
		
		if(((x == t2x1) && (y == t2y1)) ||((x == t2x2) && (y == t2y2)) || ((x == t2x3) && (y == t2y3)))
		{
			bool1 = true;
		}
		else
		{
			if((((t2y1 - y) / (t2x1 - x)) == ((t2y2 - y) / (t2x2 - x))) && (((t2y1 - t2y2) / (t2x1 - t2x2)) == ((t2y1 - y) / (t2x1 - x))))
			{
				if(((t2x1 <= x) && (x <= t2x2)) && ((t2y1 == y) && (y == t2y2)))
				{
					bool1 = true;
				}
			}
			
			if((((t2y2 - y) / (t2x2 - x)) == ((t2y3 - y) / (t2x3 - x))) && (((t2y2 - t2y3) / (t2x2 - t2x3)) == ((t2y2 - y) / (t2x2 - x))))
			{
				if(((t2x3 <= x) && (x <= t2x2)) && ((t2y2 == y) && (y == t2y3)))
				{
					bool1 = true;
				}
			}
			
			if((((t2y3 - y) / (t2x3 - x)) == ((t2y1 - y) / (t2x1 - x))) && (((t2y3 - t2y1) / (t2x3 - t2x1)) == ((t2y3 - y) / (t2x3 - x))))
			{
				if(((t2x1 <= x) && (x <= t2x3)) && ((t2y1 == y) && (y == t2y3)))
				{
					bool1 = true;
				}
			}
		}
		
		return bool1;
	}
	
	//########################################################################################################################################################
	
	/** Determines if T4 is in T3 from clicked data*/	
	public boolean InTriangle_1_mouse(double x, double y)
	{
		double t1x = t3x1;
		double t2x = t3x2;
		double t3x = t3x3;
		double t1y = t3y1;
		double t2y = t3y2;
		double t3y = t3y3;
		
		double num1 = ((t2y - t3y)*(x - t3x) + (t3x - t2x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num2 = ((t3y - t1y)*(x - t3x) + (t1x - t3x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num3 = -(num1) - num2 + 1;
		
		if(num1 >= 0 && num2 >= 0 && num3 >= 0)
			return true;
		else
			return false;
	}
	
	/** Determines if T3 is in T4 from clicked data */	
	public boolean InTriangle_2_mouse(double x, double y)
	{
		double t1x = t4x1;
		double t2x = t4x2;
		double t3x = t4x3;
		
		double t1y = t4y1;
		double t2y = t4y2;
		double t3y = t4y3;
		
		double num1 = ((t2y - t3y)*(x - t3x) + (t3x - t2x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num2 = ((t3y - t1y)*(x - t3x) + (t1x - t3x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num3 = -(num1) - num2 + 1;
		
		if(num1 >= 0 && num2 >= 0 && num3 >= 0)
			return true;
		else
			return false;
	}
	
	/** Determines if when T2 is in T1, they touch from clicked data */	
	public boolean isTouchingEdgeTriangle1_mouse(double x, double y)
	{
		boolean bool1 = false;
		
		if(((x == t3x1) && (y == t3y1)) ||((x == t3x2) && (y == t3y2)) || ((x == t3x3) && (y == t3y3)))
		{
			bool1 = true;
		}
		else
		{
			if((((t3y1 - y) / (t3x1 - x)) == ((t3y2 - y) / (t3x2 - x))) && (((t3y1 - t3y2) / (t3x1 - t3x2)) == ((t3y1 - y) / (t3x1 - x))))
			{
				if(((t3x1 <= x) && (x <= t3x2)) && ((t3y1 == y) && (y == t3y2)))
				{
					bool1 = true;
				}
			}
			
			if((((t3y2 - y) / (t3x2 - x)) == ((t3y3 - y) / (t3x3 - x))) && (((t3y2 - t1y3) / (t3x2 - t3x3)) == ((t3y2 - y) / (t3x2 - x))))
			{
				if(((t3x3 <= x) && (x <= t3x2)) && ((t3y2 == y) && (y == t3y3)))
				{
					bool1 = true;
				}
			}
			
			if((((t3y3 - y) / (t3x3 - x)) == ((t3y1 - y) / (t3x1 - x))) && (((t3y3 - t3y1) / (t3x3 - t3x1)) == ((t3y3 - y) / (t3x3 - x))))
			{
				if(((t3x1 <= x) && (x <= t3x3)) && ((t3y1 == y) && (y == t3y3)))
				{
					bool1 = true;
				}
			}
		}
		
		return bool1;
	}
	
	/** Determines if when T1 is in T2, they touch from clicked data */	
	public boolean isTouchingEdgeTriangle2_mouse(double x, double y)
	{
		boolean bool1 = false;
		
		if(((x == t4x1) && (y == t4y1)) ||((x == t4x2) && (y == t4y2)) || ((x == t4x3) && (y == t4y3)))
		{
			bool1 = true;
		}
		else
		{
			if((((t4y1 - y) / (t4x1 - x)) == ((t4y2 - y) / (t4x2 - x))) && (((t4y1 - t4y2) / (t4x1 - t4x2)) == ((t4y1 - y) / (t4x1 - x))))
			{
				if(((t4x1 <= x) && (x <= t4x2)) && ((t4y1 == y) && (y == t4y2)))
				{
					bool1 = true;
				}
			}
			
			if((((t4y2 - y) / (t4x2 - x)) == ((t4y3 - y) / (t4x3 - x))) && (((t4y2 - t4y3) / (t4x2 - t4x3)) == ((t4y2 - y) / (t4x2 - x))))
			{
				if(((t4x3 <= x) && (x <= t4x2)) && ((t4y2 == y) && (y == t4y3)))
				{
					bool1 = true;
				}
			}
			
			if((((t4y3 - y) / (t4x3 - x)) == ((t4y1 - y) / (t4x1 - x))) && (((t4y3 - t4y1) / (t4x3 - t4x1)) == ((t4y3 - y) / (t4x3 - x))))
			{
				if(((t4x1 <= x) && (x <= t4x3)) && ((t4y1 == y) && (y == t4y3)))
				{
					bool1 = true;
				}
			}
		}
		
		return bool1;
	}
	/** Main method */
    public static void main(String[] args) 
	{
        launch(args);
    }
}