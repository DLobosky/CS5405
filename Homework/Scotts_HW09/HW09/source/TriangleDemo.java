// Scott F. Payne

package code;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;



public class TriangleDemo extends Application
{
	BorderPane mainPane;
	Pane canvasPane;
	HBox hb_top, hb_bot;
	VBox vb;

	Scene mainScene;

	Button drawButton, authorButton, problemButton, mainButton, resetButton;

	double x1, y1, w1, h1;
	double x2, y2, w2, h2;
	
	Label coordsLabel = new Label("");
	double x_coor, y_coor;
	String str1;
	int count = 0;
	Polygon t3 = new Polygon();
	Polygon t4 = new Polygon();
	Polygon t1, t2;

  @Override
  public void start(Stage mainStage)
	{
		// sets title for open window
    mainStage.setTitle("Glorious Triangles");

		// creates drawing canvas with beige background
		canvasPane = new Pane();
		canvasPane.setStyle("-fx-background-color: BEIGE;");

		// creates notification pane
		hb_bot = new HBox();

		hb_top = new HBox();
		hb_top.setStyle("-fx-background-color: LIGHTGREY;");
		hb_top.setSpacing(6);
		hb_top.setPadding(new Insets(5, 10, 5, 10));

		mainButton = new Button("Main");
		mainButton.setStyle("-fx-text-fill: BLACK;");

		authorButton = new Button("Author");
		authorButton.setStyle("-fx-text-fill: BLACK;");

		problemButton = new Button("Problem");
		problemButton.setStyle("-fx-text-fill: BLACK;");

		hb_top.getChildren().addAll(mainButton, authorButton, problemButton);

		// creates vertical box for input text fields
		vb = new VBox();
		vb.setStyle("-fx-background-color: LIGHTGREY;");
		vb.setSpacing(3);
		vb.setPrefWidth(125);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(0, 10, 0, 10));

		// creates main pane and places above declared panes inside of it
		mainPane = new BorderPane();
		mainPane.setCenter(canvasPane);
		mainPane.setLeft(vb);
		mainPane.setTop(hb_top);
		mainPane.setBottom(hb_bot);

		// creates scene and defines width/height
		mainScene = new Scene(mainPane, 600, 400);

		// adds label for triangle 1 text field space to vb pane
		vb.getChildren().add(new Label("Triangle 1:"));

		// triangle 1 x-coordinate text field creation
		TextField x1text = new TextField();
		x1text.setPromptText("X");
		x1text.setStyle("-fx-background-color: WHITE;");
		x1text.setAlignment(Pos.CENTER);

		// triangle 1 y-coordinate text field creation
		TextField y1text = new TextField();
		y1text.setPromptText("Y");
		y1text.setStyle("-fx-background-color: WHITE;");
		y1text.setAlignment(Pos.CENTER);

		// triangle 1 width text field creation
		TextField w1text = new TextField();
		w1text.setPromptText("Width");
		w1text.setStyle("-fx-background-color: WHITE;");
		w1text.setAlignment(Pos.CENTER);

		// triangle 1 height text field creation
		TextField h1text = new TextField();
		h1text.setPromptText("Height");
		h1text.setStyle("-fx-background-color: WHITE;");
		h1text.setAlignment(Pos.CENTER);

		// adds triangle 1 text fields to vb pane
		vb.getChildren().addAll(x1text, y1text, w1text, h1text);

		// adds label for triangle 2 text field space to vb pane
		Label t2label = new Label("Triangle 2:");
		vb.getChildren().add(t2label);
		vb.setMargin(t2label, new Insets(10,0,0,0)); // top margin = 10

		// triangle 2 x-coordinate text field creation
		TextField x2text = new TextField();
		x2text.setPromptText("X");
		x2text.setStyle("-fx-background-color: WHITE;");
		x2text.setAlignment(Pos.CENTER);

		// triangle 2 y-coordinate text field creation
		TextField y2text = new TextField();
		y2text.setPromptText("Y");
		y2text.setStyle("-fx-background-color: WHITE;");
		y2text.setAlignment(Pos.CENTER);

		// triangle 2 width text field creation
		TextField w2text = new TextField();
		w2text.setPromptText("Width");
		w2text.setStyle("-fx-background-color: WHITE;");
		w2text.setAlignment(Pos.CENTER);

		// triangle 2 height text field creation
		TextField h2text = new TextField();
		h2text.setPromptText("Height");
		h2text.setStyle("-fx-background-color: WHITE;");
		h2text.setAlignment(Pos.CENTER);

		// adds triangle 2 text fields to vb pane
		vb.getChildren().addAll(x2text, y2text, w2text, h2text);

		// draw Button creation
		drawButton = new Button("Draw");
		drawButton.setStyle("-fx-text-fill: BLACK;");
		vb.getChildren().add(drawButton);
		vb.setMargin(drawButton,new Insets(20,0,0,0));	// top-margin = 20
		
		resetButton = new Button("Reset");
		resetButton.setStyle("-fx-text-fill: RED;");
		vb.getChildren().add(resetButton);
		vb.setMargin(resetButton,new Insets(0,0,0,0));	// top-margin = 20//-------------------------------------------------------------------------

		canvasPane.getChildren().add(coordsLabel);
		canvasPane.getChildren().add(t3);
		canvasPane.getChildren().add(t4);
		
		canvasPane.setOnMouseClicked(click);
		canvasPane.setOnMouseMoved(move);
		canvasPane.setOnMouseExited(exit);
		canvasPane.setOnMouseEntered(enter);
		
		
		// sets scene and makes it visible
		mainStage.setScene(mainScene);
    mainStage.show();

		/** On click event creation for drawButton. */
		drawButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				// clears contents of window
				canvasPane.getChildren().removeAll(t1, t2, t3, t4);
				hb_bot.getChildren().clear();

				// gathers input from text fields
				x1 = Float.valueOf(x1text.getText());
				y1 = Float.valueOf(y1text.getText());
				w1 = Float.valueOf(w1text.getText());
				h1 = Float.valueOf(h1text.getText());
				x2 = Float.valueOf(x2text.getText());
				y2 = Float.valueOf(y2text.getText());
				w2 = Float.valueOf(w2text.getText());
				h2 = Float.valueOf(h2text.getText());

				// creates triangles based on input
				t1 = createTriangle(x1,y1,w1,h1);
				t2 = createTriangle(x2,y2,w2,h2);

				// sets stroke colors of triangles
				t1.setFill(Color.BLUE);
				t2.setFill(Color.RED);

				// adds triangles to the canvas
				canvasPane.getChildren().addAll(t1,t2);

				// determines if triangles equal, overlapping, inside eachother, or disjoint
				if(x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2)
				{
					hb_bot.getChildren().add(new Label("Triangles are equal (EQ)."));
				}
				else
				{
					if(!Shape.intersect(t1, t2).getBoundsInLocal().isEmpty())
					{
						if(TestInT1(x2, y2) == true && TestInT1((x2+w2), y2) == true
							 && TestInT1((x2+(w2/2)), (y2+h2)) == true)
						{
							if(isOnEdgeT1(x2, y2) || isOnEdgeT1((x2 + w2), y2) || isOnEdgeT1((x2 + (w2/2)), (y2 + h2))
								 || isOnEdgeT2(x1, y1) || isOnEdgeT2((x1 + w1), y1) || isOnEdgeT2((x1 + (w1/2)), (y1 + h1)))
							{
								hb_bot.getChildren().add(new Label("T2 is in T1 and touches the boundary of T1 (TPPc)."));
							}
							else
							{
								hb_bot.getChildren().add(new Label("T2 is in T1 and does not touch the boundary of T1 (NTTPc)."));
							}
						}
						else
						{
							if(TestInT2(x1, y1) == true && TestInT2((x1+w1), y1) == true
								 && TestInT2((x1+(w1/2)), (y1+h1)) == true)
							{
								if(isOnEdgeT1(x2, y2) || isOnEdgeT1((x2 + w2), y2) || isOnEdgeT1((x2 + (w2/2)), (y2 + h2))
									 || isOnEdgeT2(x1, y1) || isOnEdgeT2((x1 + w1), y1) || isOnEdgeT2((x1 + (w1/2)), (y1 + h1)))
								{
									hb_bot.getChildren().add(new Label("T1 is in T2 and touches the boundary of T2 (TPP)."));
								}
								else
								{
									hb_bot.getChildren().add(new Label("T1 is in T2 and does not touch the boundary of T2 (NTTP)."));
								}
							}
							else
							{
								hb_bot.getChildren().add(new Label("Triangles have proper overlap (OP)."));
							}
						}
					}
					else
					{
						if(isOnEdgeT1(x2, y2) || isOnEdgeT1((x2 + w2), y2) || isOnEdgeT1((x2 + (w2/2)), (y2 + h2))
							 || isOnEdgeT2(x1, y1) || isOnEdgeT2((x1 + w1), y1) || isOnEdgeT2((x1 + (w1/2)), (y1 + h1)))
						{
							hb_bot.getChildren().add(new Label("The two triangles are disjoint and externally connected (EC)."));
						}
						else
						{
							hb_bot.getChildren().add(new Label("The two triangle are interior disjoint (DC)."));
						}
					}
				}
			}
		});
		
		/** On click event creation for resetButton. */
		resetButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override 
			public void handle(ActionEvent e) 
			{
				canvasPane.getChildren().removeAll(t1, t2, t3, t4);
				hb_bot.getChildren().clear();
				count = 0;
				t3 = new Polygon();
				canvasPane.getChildren().add(t3);
				t4 = new Polygon();
				canvasPane.getChildren().add(t4);
			}
		});

		/** Clears window and displays author info when authorButton pressed */
		authorButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				// pane to display information about the author
				VBox vb_author = new VBox();

				// Creating text boxes with info about author
				Text author_text_1 = new Text(10, 40,
					"  Name: 		Scott Payne");
				author_text_1.setFont(new Font(14));
				author_text_1.setFill(Color.BLACK);
				author_text_1.setWrappingWidth(600);
				author_text_1.setTextAlignment(TextAlignment.JUSTIFY);

				Text author_text_2 = new Text(10, 40,
					"  Email:		sfpr43@mst.edu");
				author_text_2.setFont(new Font(14));
				author_text_2.setFill(Color.BLACK);
				author_text_2.setWrappingWidth(600);
				author_text_2.setTextAlignment(TextAlignment.JUSTIFY);


				vb_author.getChildren().addAll(author_text_1, author_text_2);
				mainPane.setCenter(vb_author);
				mainPane.setLeft(null);
				mainPane.setBottom(null);
			}
		});

		/** Clears window and displays problem info when authorButton pressed */
		problemButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				// pane to display information about the problem
				VBox vb_problem = new VBox();

				// Cre(x1 + (w1/2))xes describing problem
				Text problem_text_1 = new Text(10, 40,
          "  Continue from HW08.");
				problem_text_1.setFont(new Font(14));
        problem_text_1.setFill(Color.BLACK);
        problem_text_1.setWrappingWidth(600);
        problem_text_1.setTextAlignment(TextAlignment.JUSTIFY);

				Text problem_text_2 = new Text(10, 40,
					"  New cases added that have to be tested for");
				problem_text_2.setFont(new Font(14));
        problem_text_2.setFill(Color.BLACK);
        problem_text_2.setWrappingWidth(600);
        problem_text_2.setTextAlignment(TextAlignment.JUSTIFY);

				Text problem_text_3 = new Text(10, 40,
					"  Test whether triangles are equal, overlapping, inside eachother and touching, inside eachother and not touching, disjoint and touching, or disjoint and not touching.");
				problem_text_3.setFont(new Font(14));
        problem_text_3.setFill(Color.BLACK);
        problem_text_3.setWrappingWidth(600);
        problem_text_3.setTextAlignment(TextAlignment.JUSTIFY);
		
		Text problem_text_4 = new Text(10, 40,
					"\n\n You can now click 3 times to add a triangle (up to 2 triangles) and clear the pane.");
				problem_text_4.setFont(new Font(14));
        problem_text_4.setFill(Color.BLACK);
        problem_text_4.setWrappingWidth(600);
        problem_text_4.setTextAlignment(TextAlignment.JUSTIFY);

				vb_problem.getChildren().addAll(problem_text_1, problem_text_2, problem_text_3, problem_text_4);
				mainPane.setCenter(vb_problem);
				mainPane.setLeft(null);
				mainPane.setBottom(null);
			}
		});

		/** Redisplays main program for triangle drawing when mainButon pressed */
		mainButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				mainPane.setCenter(canvasPane);
				mainPane.setLeft(vb);
				mainPane.setBottom(hb_bot);
			}
		});
	}
	
	
	/** Mouse entered event that re-adds the label for the coordinates of the mouse when the mouse re-enters the designated pane */
	EventHandler<MouseEvent> enter = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{			
			str1 = "X: " + x_coor + "\nY: " + y_coor;
			coordsLabel.setText(str1);
		}
	};
	
	/** Mouse clicked event that creates 2 triangles after being clicked 6 times and compares them */
	EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{				
			if(count < 3)
			{
				createT3();
				count++;
			}
			else if(count < 6)
			{
				createT4();
				count++;					
			}
		}
	};
	
	/** Moved mouse event to track X and Y coordinates of mouse */
	EventHandler<MouseEvent> move = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{
			x_coor = mouseEvent.getX();
			y_coor = mouseEvent.getY();
			
			str1 = "X: " + x_coor + "\nY: " + y_coor;
			coordsLabel.setText(str1);
			coordsLabel.relocate(x_coor + 20, y_coor);
			
		}
	};
	
	/** Mouse exited event that "removes" the label for the coordinates of the mouse when the mouse leaves the designated pane */
	EventHandler<MouseEvent> exit = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent mouseEvent) 
		{
			str1 = "";
			coordsLabel.setText(str1);
		}
	};

	/** Triangle creation method */
	public Polygon createTriangle(double x, double y, double w, double h)
	{
	  Polygon t = new Polygon(x, y, (x+w), y, (x+(w/2)), (y+h));
		//t.setFill(Color.GREEN);
		//t.setFill(null);
		//t.setStrokeWidth(3);
	  return t;
	}


	/** Tests if triangle 2 is inside of triangle 1 */
	public boolean TestInT1(double x, double y)
	{
		double num1 = ((y1-(y1+h1))*(x-(x1+(w1/2)))+((x1+(w1/2))-(x1+w1))*(y-(y1+h1)))/
									((y1-(y1+h1))*(x1-(x1+(w1/2)))+((x1+(w1/2))-(x1+w1))*(y1-(y1+h1)));
		double num2 = (((y1+h1)-y1)*(x-(x1+(w1/2)))+(x1-(x1+(w1/2)))*(y-(y1+h1)))/
									((y1-(y1+h1))*(x1-(x1+(w1/2)))+((x1+(w1/2))-(x1+w1))*(y1-(y1+h1)));
		double num3 = -(num1) - num2 + 1;

		if(num1 >= 0 && num2 >= 0 && num3 >= 0)
			return true;
		else
			return false;
	}

	/** Tests if triangle 1 is inside of triangle 2 */
	public boolean TestInT2(double x, double y)
	{
		double num1 = ((y2 - (y2+h2))*(x - (x2+(w2/2))) + ((x2+(w2/2)) - (x2+w2))*(y - (y2+h2)))/((y2 - (y2+h2))*(x2 - (x2+(w2/2))) + ((x2+(w2/2)) - (x2+w2))*(y2 - (y2+h2)));
		double num2 = (((y2+h2) - y2)*(x - (x2+(w2/2))) + (x2 - (x2+(w2/2)))*(y - (y2+h2)))/((y2 - (y2+h2))*(x2 - (x2+(w2/2))) + ((x2+(w2/2)) - (x2+w2))*(y2 - (y2+h2)));
		double num3 = -(num1) - num2 + 1;

		if(num1 >= 0 && num2 >= 0 && num3 >= 0)
			return true;
		else
			return false;
	}

	/** Determines if T2 is touching edge of T1. */
	public boolean isOnEdgeT1 (double x, double y)
	{
		boolean onEdge = false;

		if(((x == x1) && (y == y1)) ||((x == (x1 + w1)) && (y == y1)) ||
			 ((x == (x1 + (w1/2))) && (y == (y1 + h1))))
		{
			onEdge = true;
		}
		else
		{
			if((((y1 - y) / (x1 - x)) == ((y1 - y) / ((x1 + w1) - x))) &&
				 (((y1 - y1) / (x1 - (x1 + w1))) == ((y1 - y) / (x1 - x))))
			{
				if(((x1 <= x) && (x <= (x1 + w1))) && ((y1 == y) && (y == y1)))
				{
					onEdge = true;
				}
			}

			if((((y1 - y) / ((x1 + w1) - x)) == (((y1 + h1) - y) / ((x1 + (w1/2)) - x))) &&
				 (((y1 - (y1 + h1)) / ((x1 + w1) - (x1 + (w1/2)))) == ((y1 - y) / ((x1 + w1) - x))))
			{
				if((((x1 + (w1/2)) <= x) && (x <= (x1 + w1))) && ((y1 == y) && (y == (y1 + h1))))
				{
					onEdge = true;
				}
			}

			if(((((y1 + h1) - y) / ((x1 + (w1/2)) - x)) == ((y1 - y) / (x1 - x))) &&
				 ((((y1 + h1) - y1) / ((x1 + (w1/2)) - x1)) == (((y1 + h1) - y) / ((x1 + (w1/2)) - x))))
			{
				if(((x1 <= x) && (x <= (x1 + (w1/2)))) && ((y1 == y) && (y == (y1 + h1))))
				{
					onEdge = true;
				}
			}
		}

		return onEdge;
	}

	/** Determines if T1 touching edge of T2 */
	public boolean isOnEdgeT2 (double x, double y)
	{
		boolean onEdge = false;

		if(((x == x2) && (y == y2)) ||((x == (x2 + w2)) && (y == y2)) ||
			 ((x == (x2 + (w2/2))) && (y == (y2 + h2))))
		{
			onEdge = true;
		}
		else
		{
			if((((y2 - y) / (x2 - x)) == ((y2 - y) / ((x2 + w2) - x))) &&
				 (((y2 - y2) / (x2 - (x2 + w2))) == ((y2 - y) / (x2 - x))))
			{
				if(((x2 <= x) && (x <= (x2 + w2))) && ((y2 == y) && (y == y2)))
				{
					onEdge = true;
				}
			}

			if((((y2 - y) / ((x2 + w2) - x)) == (((y2 + h2) - y) / ((x2 + (w2/2)) - x))) &&
				 (((y2 - (y2 + h2)) / ((x2 + w2) - (x2 + (w2/2)))) == ((y2 - y) / ((x2 + w2) - x))))
			{
				if((((x2 + (w2/2)) <= x) && (x <= (x2 + w2))) && ((y2 == y) && (y == (y2 + h2))))
				{
					onEdge = true;
				}
			}

			if(((((y2 + h2) - y) / ((x2 + (w2/2)) - x)) == ((y2 - y) / (x2 - x))) &&
				 ((((y2 + h2) - y2) / ((x2 + (w2/2)) - x2)) == (((y2 + h2) - y) / ((x2 + (w2/2)) - x))))
			{
				if(((x2 <= x) && (x <= (x2 + (w2/2)))) && ((y2 == y) && (y == (y2 + h2))))
				{
					onEdge = true;
				}
			}
		}

		return onEdge;
	}
	
	/** Makes triangle t3 */
	public void createT3()
	{
		t3.getPoints().addAll(x_coor, y_coor);
		t3.setFill(Color.GREEN);
        return;
    }
	
	/** Makes triangle t4 */
	public void createT4()
	{
		t4.getPoints().addAll(x_coor, y_coor);
		t4.setFill(Color.RED);
        return;
    }

	/** Main method */
  public static void main(String[] args)
	{
    launch(args);
  }
}
