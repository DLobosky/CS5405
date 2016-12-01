package code;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;

/**@author Dalton Lobosky
	@version 1.0
*/

public class TriangleDemo extends Application 
{
    @Override
    public void start(Stage myStage) 
	{
		//Setup the scene
        myStage.setTitle("Triangle Maker 5000!!!!");

        GridPane rootNode = new GridPane();
		BorderPane mainPane = new BorderPane();
		//Pane drawingBoard = new Pane();
		FlowPane fp = new FlowPane();
		fp.setVgap(2);
		fp.setHgap(2);
		fp.setPrefWrapLength(200); // preferred width = 100

		VBox vb = new VBox();
        rootNode.setPadding(new Insets(0,0,0,0));
        rootNode.setHgap(2);
        rootNode.setVgap(2);
        rootNode.setAlignment(Pos.TOP_LEFT);
		
		

        Scene myScene = new Scene(rootNode, 500, 400);
		
		//rootNode.add(drawingBoard, 0, 0);
		rootNode.add(vb, 1, 9);
		rootNode.add(fp, 3, 10);
		
		// Input Box 1
		rootNode.add(new Label("X1:"), 0, 0);
		TextField x1Value = new TextField();
		rootNode.add(x1Value, 1, 0);
		
		// Input Box 2
		rootNode.add(new Label("Y1:"), 0, 1);
		TextField y1Value = new TextField();
		rootNode.add(y1Value, 1, 1);
		
		// Input Box 3
		rootNode.add(new Label("W1:"), 0, 2);
		TextField w1Value = new TextField();
		rootNode.add(w1Value, 1, 2);
		
		// Input Box 4
		rootNode.add(new Label("H1:"), 0, 3);
		TextField h1Value = new TextField();
		rootNode.add(h1Value, 1, 3);
		
		// Input Box 5
		rootNode.add(new Label("X2:"), 0, 4);
		TextField x2Value = new TextField();
		rootNode.add(x2Value, 1, 4);
		
		// Input Box 6
		rootNode.add(new Label("Y2:"), 0, 5);
		TextField y2Value = new TextField();
		rootNode.add(y2Value, 1, 5);
		
		// Input Box 7
		rootNode.add(new Label("W2:"), 0, 6);
		TextField w2Value = new TextField();
		rootNode.add(w2Value, 1, 6);
		
		// Input Box 8
		rootNode.add(new Label("H2:"), 0, 7);
		TextField h2Value = new TextField();
		rootNode.add(h2Value, 1, 7);
			
		// Submit Button
		Button aButton = new Button("Submit");
        rootNode.add(aButton, 1, 9);
        GridPane.setHalignment(aButton, HPos.CENTER);
		
		
		aButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override public void handle(ActionEvent e) 
			{
				fp.getChildren().clear();
				
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
				t1.setStroke(Color.BLUE);
				t2.setStroke(Color.RED);
				
				t2.relocate(100,100);
			
				fp.getChildren().addAll(t1,t2);
			}
		});
		
        myStage.setScene(myScene);

        myStage.show();
    }
	/** Method to make a triangle */
	public Polygon createTriangle(double x, double y, double w, double h)
	{
        // Create a circle and set its properties
		double a,b,c,d,e,f;
		
		a = x;
		b = y;
		c = x+w;
		d = y;
		e = (x+(w/2));
		f = y+h;
		
        Polygon t1 = new Polygon(a,b,c,d,e,f);
		t1.setFill(null);
		t1.setStrokeWidth(4);
	
        return t1;
    }
	
	/** Main method */
    public static void main(String[] args) 
	{
        launch(args);
    }
}