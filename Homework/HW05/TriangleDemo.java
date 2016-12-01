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

public class TriangleDemo extends Application 
{
	Pane drawingBoard;
	HBox hb_T;
	HBox hb_B;
	VBox vb;
	BorderPane mainPane;
	Scene myScene;
	
	TextField x1Value;
	TextField x2Value;
	TextField y1Value;
	TextField y2Value;
	TextField w1Value;
	TextField w2Value;
	TextField h1Value;
	TextField h2Value;
	
	Button aButton;
	
	public String str;
	
	
	double x1;
	double x2;
	double y1;
	double y2;
	double w1;
	double w2;
	double h1;
	double h2;
	
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
	
    @Override
	
    public void start(Stage myStage) 
	{
		//Setup the scene
        myStage.setTitle("Triangle Maker 5000!!!!");
		
		drawingBoard = new Pane(); // Creates the drawing board for the triangles
		drawingBoard.setStyle("-fx-background-color: LIGHTGRAY;");
		
		hb_T = new HBox(); // Top horizontal box for instructions
		hb_T.setStyle("-fx-background-color: CYAN;");
		
		hb_B = new HBox(); // Bottom horizontal bos for output
		
		vb = new VBox(); // Pane for the text boxes on the left
		vb.setStyle("-fx-background-color: CYAN;");
		vb.setSpacing(2); // setting the spacing betweek nodes in vb
		vb.setPrefWidth(125); // setting width of vb so text boxes aren't too big	
		vb.setAlignment(Pos.CENTER);
			
		mainPane = new BorderPane(); // Maine Pane for the whole program
		mainPane.setCenter(drawingBoard);
		mainPane.setLeft(vb);
		mainPane.setTop(hb_T);
		mainPane.setBottom(hb_B);	

		myScene = new Scene(mainPane, 500, 400);
		
		// Instructions on top of page		
		Text instr = new Text("Please enter the X and Y coordinates and the Width and Height for TWO triangles");
		instr.setStyle("-fx-font: 20 arial;");
		instr.setWrappingWidth(450);
		hb_T.getChildren().add(instr);
		
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
		aButton = new Button("SUBMIT");
		aButton.setStyle("-fx-background-color: MEDIUMSPRINGGREEN;");

		vb.getChildren().add(aButton);
		
		myStage.setScene(myScene);

        myStage.show();

		aButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override 
			public void handle(ActionEvent e) 
			{
				drawingBoard.getChildren().clear();
				hb_B.getChildren().clear();
				
				double x1 = Float.valueOf(x1Value.getText());
				double y1 = Float.valueOf(y1Value.getText());
				double w1 = Float.valueOf(w1Value.getText());
				double h1 = Float.valueOf(h1Value.getText());
				double x2 = Float.valueOf(x2Value.getText());
				double y2 = Float.valueOf(y2Value.getText());
				double w2 = Float.valueOf(w2Value.getText());
				double h2 = Float.valueOf(h2Value.getText());

				Polygon t1 = createTriangle(x1,y1,w1,h1);
				Polygon t2 = createTriangle(x2,y2,w2,h2);
				t1.setStroke(Color.YELLOW);
				t2.setStroke(Color.RED);
			
				drawingBoard.getChildren().addAll(t1,t2);
				
				t1a = x1;
				t1b = y1;
				t1c = x1 + w1;
				t1d = y1;
				t1e = (x1 + (w1/2));
				t1f = y1 +h1;
				
				t2a = x2;
				t2b = y2;
				t2c = x2 + w2;
				t2d = y2;
				t2e = x2 + (w2/2);
				t2f = y2 +h2;
				
				if(x1 == y1 && y1 == y2 && w1 == w2 && h1 == h2)
				{
					hb_B.getChildren().add(new Label("The triangles are equal."));
				}
				else
				{
					if(!Shape.intersect(t1, t2).getBoundsInLocal().isEmpty())
					{
						if(InTriangle_1(t2a, t2b) == true && InTriangle_1(t2c, t2d) == true && InTriangle_1(t2e, t2f) == true) 
						{
							hb_B.getChildren().add(new Label("T2 is in T1."));
						}
						else
						{
							if(InTriangle_2(t1a, t1b) == true && InTriangle_2(t1c, t1d) == true && InTriangle_2(t1e, t1f) == true)
							{
								hb_B.getChildren().add(new Label("T1 is in T2."));
							}
							else
							{
								hb_B.getChildren().add(new Label("T1 and T2 have Proper Overlap."));
							}
						}
					}
					else
					{
						hb_B.getChildren().add(new Label("T1 and T2 are Interior Disjoint."));					
					}
				}
			}
		});
	}
	/** Method to make a triangle */
	public Polygon createTriangle(double x, double y, double w, double h)
	{
		double a,b,c,d,e,f;
		
		a = x;
		b = y;
		c = x+w;
		d = y;
		e = (x+(w/2));
		f = y+h;
		
        Polygon t1 = new Polygon(a,b,c,d,e,f);
		t1.setFill(Color.BLUE);
		t1.setStrokeWidth(2);
	
        return t1;
    }
	
	/** Determines if T2 is in T1 */	
	public boolean InTriangle_1(double x, double y)
	{
		double t1x = t1a;
		double t2x = t1c;
		double t3x = t1e;
		double t1y = t1b;
		double t2y = t1d;
		double t3y = t1f;
		
		double num1 = ((t2y - t3y)*(x - t3x) + (t3x - t2x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num2 = ((t3y - t1y)*(x - t3x) + (t1x - t3x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num3 = -(num1) - num2 + 1;
		
		if(num1 > 0 && num2 > 0 && num3 > 0)
			return true;
		else
			return false;
	}
	
	/** Determines if T1 is in T2 */	
	public boolean InTriangle_2(double x, double y)
	{
		double t1x = t2a;
		double t2x = t2c;
		double t3x = t2e;
		
		double t1y = t2b;
		double t2y = t2d;
		double t3y = t2f;
		
		double num1 = ((t2y - t3y)*(x - t3x) + (t3x - t2x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num2 = ((t3y - t1y)*(x - t3x) + (t1x - t3x)*(y - t3y))/((t2y - t3y)*(t1x - t3x) + (t3x - t2x)*(t1y - t3y));
		double num3 = -(num1) - num2 + 1;
		
		if(num1 > 0 && num2 > 0 && num3 > 0)
			return true;
		else
			return false;
	}
	
	public String getData()
	{
		String str = x1Value.getText();
		return str;
	}
	
	/** Main method */
    public static void main(String[] args) 
	{
        launch(args);
    }
}